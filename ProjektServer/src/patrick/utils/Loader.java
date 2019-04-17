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
 * <p>Loader, welcher alle serverseitigen Spiele lädt, speichert und derren Methoden aufrufen
 * kann</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class Loader {

	/**
	 * Alle Dateien, welche geladen werden sollen
	 */
	
	private List<File> filesToLoad;

	/**
	 * Alle geladenen Spiele
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
	 * Fügt eine Datei hinzu, welche geladen werden soll
	 * 
	 * @param file Datei, welche geladen werden soll
	 */
	
	public void addFile(File file) {
		filesToLoad.add(file);
	}
	
	/**
	 * Fügt ein Verzeichnis hinzu, welches geladen werden soll
	 * 
	 * @param file Verzeichnis, welches geladen werden soll
	 */
	
	public void addDir(File file) {
		if(file.isDirectory()) {
			filesToLoad.addAll(getAllFilesFromFolder(file));
		}
	}
	
	/**
	 * Liefert eine Liste von allen Dateien zurück, welche sich im definierten 
	 * Verzeichnis befinden
	 * 
	 * @param folder Verzeichnis, von welchem die Dateien gelistet werden sollen
	 * 
	 * @return Alle Dateien aus dem Verzeichnis
	 */
	
	public List<File> getAllFilesFromFolder(final File folder) {
	    List<File> files = new ArrayList<File>();
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            getAllFilesFromFolder(fileEntry);
	        } else {
	        	files.add(fileEntry);
	        }
	    }
		return files;
	}
	
	/**
	 * Gibt eine aus dem Loader zurück über den Dateinamen
	 * 
	 * @param fileName Dateiname
	 * 
	 * @return null wenn die Datei nicht existiert, ansonsten die Datei
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
	 * Startet eine Runde und gibt eine RunningRound als Object zurück
	 * 
	 * @param gameName Name des Spiels
	 * @param round wartende Runde, welche benötigt wird für die Instanziierung der RunningRound
	 * 
	 * @return Liefert die RunningRound als Object
	 */
	
	public Object startRound(String gameName, Object round) {
		for(LoadedGame game : loadedGames) {
			if(game.getName().equals(gameName)) {
				return loadRound(game.getPath(), game.getMainClass(), round);
			}
		}
		return null;
	}
	
	/**
	 * Lädt alle Spiele aus den angegebenen Verzeichnissen und liefert eine Liste von 
	 * Game Objects zurück
	 * 
	 * @param configFile Name der Config File
	 * @param methodName Name der Methode, um die Spiele zu erhalten
	 * @param objs Argumente der Methode, um die Spiele zu erhalten
	 * 
	 * @return Liste von Games als Objects
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
	 * Lädt ein Spiel und gibt dieses als Object zurück
	 * 
	 * @param fileName Name der .jar Datei des Spiels
	 * @param className Name der Mainklasse
	 * @param methodName Name der Methode
	 * @param objs Argumente der Methode
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
	 * Lädt eine Runde, um diese dann zu starten
	 * 
	 * @param gameName Name des Spiels
	 * @param className Name der Mainklasse
	 * @param obj wartende Runde als Object
	 * 
	 * @return Gibt eine RunningRound des Spiels zurück
	 */
	
	public Object loadRound(String gameName, String className, Object obj) {
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
			Constructor<?> constructor = classToLoad.getDeclaredConstructor(obj.getClass());
			Object instance = constructor.newInstance(obj);
			return instance;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}	
	}
	
	/**
	 * lädt die Config File eines Spiels
	 * 
	 * @param jarFile absoluter Pfad der .jar File
	 * @param configFile Name der Config Datei
	 * 
	 * @return Gibt den Inhalt der Config-Datei zeilenweise als Liste zurück
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
