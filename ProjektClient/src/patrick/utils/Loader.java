package patrick.utils;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/**
 * <p>Lädt Bilder und Spiele aus einem definierten Ordner</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class Loader {

	/**
	 * Liste aller Files welche geladen werden
	 */
	
	private List<File> filesToLoad;
	
	/**
	 * Liste aller geladenen Spiele
	 */
	
	private List<LoadedGame> loadedGames;
	
	/**
	 * Erzeugt ein Loader
	 */
	
	public Loader() {
		 filesToLoad = new ArrayList<File>();
		 loadedGames = new ArrayList<LoadedGame>();
	}
	
	/**
	 * Fügt eine neue Datei zum laden hinzu
	 * 
	 * @param file Datei zum laden
	 */
	
	public void addFile(File file) {
		filesToLoad.add(file);
	}
	
	/**
	 * Fügt ein neues Verzeichnis zum laden hinzu
	 * 
	 * @param file Verzeichnis zum laden
	 */
	
	public void addDir(File file) {
		if(file.isDirectory()) {
			filesToLoad.addAll(getAllFilesFromFolder(file));
		}
	}
	
	/**
	 * Liefert eine Liste aller Dateien aus einem Verzeichnis
	 * 
	 * @param folder Verzeichnis
	 * 
	 * @return Liste aller Dateien im Verzeichnis
	 */
	
	public List<File> getAllFilesFromFolder(final File folder) {
	    List<File> files = new ArrayList<File>();
	    if(folder.exists()) {
			for (final File fileEntry : folder.listFiles()) {
		        if (fileEntry.isDirectory()) {
		            getAllFilesFromFolder(fileEntry);
		        } else {
		        	files.add(fileEntry);
		        }
		    }
	    }
		return files;
	}
	
	/**
	 * Gibt eine Datei zurück
	 * 
	 * @param fileName Name oder absolute Pfad der Datei
	 * 
	 * @return null wenn die Datei nicht dem Loader hinzugefügt ist, ansonsten die Datei
	 */
	
	public File getFile(String fileName) {
		for(File file : filesToLoad) {
			if(file.getName().equals(fileName)) {
				return file;
			}
		}
		return null;
	}
	
	/**
	 * Startet eine Spielrunde
	 * 
	 * @param gameName Spielname
	 * @param methodName MethodenName, welcher das Spiel startet
	 * 
	 * @return Object einer GameRound
	 */
	
	public Object[] startGame(String gameName, String methodName) {
		for(LoadedGame game : loadedGames) {
			if(game.getName().equals(gameName)) {
				try {
					Object[] objs = new Object[2];
					URLClassLoader child = new URLClassLoader(
					        new URL[] {new File(game.getPath()).toURI().toURL()},
					        getClass().getClassLoader()
					);
					Class<?> classToLoad = Class.forName(game.getMainClass(), true, child);
					Method method = classToLoad.getDeclaredMethod(methodName);
					Object instance = classToLoad.newInstance();
					objs[0] = method;
					objs[1] = instance;
					return objs;
				}catch(Exception ex) {
					ex.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}
	
	/**
	 * Lädt alle Spiele aus den hinzugefügten Dateien
	 * 
	 * @param configFile Name der Config File
	 * @param methodName Methodenname, welcher das Spiel zurück gibt
	 * @param objs Parameter der Methode
	 * 
	 * @return Liste von Games
	 */
	
	public List<Object> loadAllGames(String configFile, String methodName, Object...objs) {
		List<Object> games = new ArrayList<Object>();
		for(File file : filesToLoad) {
			boolean loaded = false;
			if(file.getName().endsWith(".jar")) {
				List<String> list = loadConfig(file.getAbsolutePath(), configFile);
				for(String s : list) {
					if(s.startsWith("main: ")) {
						String[] args = s.split(": ");
						if(args.length > 1) {
							String mainClass = args[1];
							Object obj = loadGame(file.getName(), mainClass, methodName, objs);
							if(obj != null) {
								try {
									Field name = obj.getClass().getDeclaredField("name");
									name.setAccessible(true);
									LoadedGame lGame = new LoadedGame((String) name.get(obj),
											file.getAbsolutePath(),
											mainClass);
									loadedGames.add(lGame);
									games.add(obj);
									loaded = true;
								} catch (Exception e) {
									e.printStackTrace();
									loaded = false;
								}
							}
						}
					}
				}
				if(loaded == false) {
					System.out.println(file.getName() + " konnte nicht geladen werden! \n");
				}
			}
		}
		return games;
	}
	
	/**
	 * Lädt ein Spiel
	 * 
	 * @param fileName Dateiname des Spiels
	 * @param className Klassenname der Main
	 * @param methodName Methodenname welche das Spiel liefert
	 * @param objs Parameter der Methode
	 * 
	 * @return Game als Object
	 */
	
	public Object loadGame(String fileName, String className, String methodName, Object...objs) {
		File file = getFile(fileName);
		if(file == null) {
			return null;
		}
		try {
			URLClassLoader child = new URLClassLoader(
			        new URL[] {file.toURI().toURL()},
			        getClass().getClassLoader()
			);
			Class<?> classToLoad = Class.forName(className, true, child);
			Method method = classToLoad.getDeclaredMethod(methodName);
			Object instance = classToLoad.newInstance();
			return method.invoke(instance, objs);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}	
	}
	
	/**
	 * Lädt ein Spiel
	 * 
	 * @param fileName Spielname
	 * @param className Klassenname der Main
	 * @param methodName Methodenname welche das Spiel liefert
	 * @param objs Parameter der Methode
	 * 
	 * @return Game als Object
	 */
	
	public Object loadGame(String gameName, String className, String methodName, Object obj) {
		File file = new File(gameName);
		if(file.exists() == false) {
			return null;
		}
		try {
			URLClassLoader child = new URLClassLoader(
			        new URL[] {file.toURI().toURL()},
			        getClass().getClassLoader()
			);
			Class<?> classToLoad = Class.forName(className, true, child);
			Method method = classToLoad.getDeclaredMethod(methodName);
			Constructor<?> constructor = classToLoad.getDeclaredConstructor(obj.getClass());
			Object instance = constructor.newInstance(obj);
			method.invoke(instance);
			return instance;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}	
	}
	
	/**
	 * Lädt eine Config Datei aus einer .jar Datei 
	 * 
	 * @param jarFile Name der .jar Datei
	 * @param configFile Name der Config Datei
	 * 
	 * @return Liste der Lines, welche sich in der Config Datei befinden
	 */
	
	public List<String> loadConfig(String jarFile, String configFile) {
		List<String> list = new ArrayList<String>();
		try {
			ZipFile zf = new ZipFile(jarFile);
			ZipEntry zipEntry = zf.getEntry(configFile);
			InputStream is = zf.getInputStream(zipEntry);
			Scanner sc = new Scanner(is);
			while(sc.hasNext()) {
				String line = sc.nextLine();
				if(line != null) {
					list.add(line);	
				}
			}
			sc.close();
			zf.close();
		} catch (Exception e) {
			return list;
		}
		
		return list;
	}
	
}
