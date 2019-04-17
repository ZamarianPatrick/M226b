package patrick;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import patrick.component.components.PGameContainer;
import patrick.event.listener.ErrorListener;
import patrick.event.listener.GameOptionPacketListener;
import patrick.event.listener.GameStartListener;
import patrick.event.listener.MessageListener;
import patrick.event.listener.ReceivePacketListener;
import patrick.event.listener.ReceiveRoundsListener;
import patrick.event.listener.RoundCountDownListener;
import patrick.event.listener.RoundInfoPacketListener;
import patrick.event.listener.RoundStopListener;
import patrick.game.Game;
import patrick.game.GameRound;
import patrick.game.GameSettings;
import patrick.game.Player;
import patrick.game.Round;
import patrick.packets.Packet;
import patrick.utils.Loader;
import processing.core.PApplet;
/**
 * <p>Einstiegspunkt des kompletten Programmes</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class Client{

	/**
	 * Sockel für die Verbindung zum Server
	 */
	
	static Socket socket;
	
	/**
	 * Zustand ob der Client bereits versucht sicht mit dem Server zu verbinden
	 */
	
	static boolean connect = false;
	
	/**
	 * Die Einbindung der kompletten grafischen Benutzeroberfläche über ein PApplet
	 */
	
	private static ClientView view;
	
	/**
	 * Loader, welche alle Spieler und Bilder beim Start des Programms lädt
	 */
	
	private static Loader loader;
	
	/**
	 * vom User Fokusiertes Spiel
	 */
	
	private static Game focusedGame;
	
	/**
	 * Vom User gespieltes Spiel
	 */
	
	private static Game playedGame;
	
	/**
	 * Spielername in der Spielrunde
	 */
	
	private static String playerName;
	
	/**
	 * Runde, welche aktuell gespielt wird
	 */
	
	private static GameRound playedRound;
	
	/**
	 * Liste von allen geladenen Spielen
	 */
	
	private static List<Game> games = new ArrayList<Game>();
	
	/**
	 * Liste von allen geladenen Bildern
	 */
	
	private static List<File> images = new ArrayList<File>();
	
	/**
	 * Die Methode die beim Starten des Programms aufgerufen wird.
	 * Ist der Einstiegspunkt des Clients
	 * 
	 * @param args keine Argumente notwendig
	 */
	
	private static String ipAdress = "localhost";
	
	public static void main(String[] args) {
		start();
	}
	
	/**
	 * Startet den Client, registriert alle Listener, lädt alle Spiele und Bilder
	 * und öffnet die grafische Benutzeroberfläche.
	 * 
	 * Bevor die Method aufgerufen wird sollte sichergestellt werden, dass die config.txt
	 * richtig konfiguriert wurde
	 */
	
	public static void start() {
		registerListener();
		view = new ClientView();
		PApplet.runSketch(new String[]{"patrick.ClientView"}, view);
        ClassLoader classLoader = new Client().getClass().getClassLoader(); 
        File file = new File(classLoader.getResource("config.txt").getFile());
		if(file.exists()) {
			try {
				Scanner sc = new Scanner(file);
				boolean useExternPath = false;
				String path = "";
				while(sc.hasNextLine()) {
					String line = sc.nextLine();
					if(line != null) {
						if(line.startsWith("load-extern: ")) {
							String settingString = line.split(": ")[1];
							useExternPath = settingString.equals("true");
						}else if(line.startsWith("load-path: ")) {
							path = line.split(": ")[1];
						}else if(line.startsWith("ip: ")) {
							ipAdress = line.split(": ")[1];
						}
					}
				}
				sc.close();
				if(useExternPath) {
					loadGames(path);
					return;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		File normalFolder = new File(Client.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		String path = normalFolder.getAbsolutePath().substring(0,normalFolder.getAbsolutePath().length() - normalFolder.getName().length());
		loadGames(path);
	}
	
	/**
	 * registriert alle vorhandenen Listener
	 */
	
	public static void registerListener() {
		new MessageListener();
		new ErrorListener();
		new GameOptionPacketListener();
		new ReceivePacketListener();
		new RoundInfoPacketListener();
		new ReceiveRoundsListener();
		new RoundCountDownListener();
		new GameStartListener();
		new RoundStopListener();
	}
	
	/**
	 * Lädt alle Spiele aus dem Ordner mit dem gegeneben Pfad
	 * 
	 * @param path Pfad des Verzeichnisses mit den Spielen
	 */
	
	public static void loadGames(String path) {
		loader = new Loader();
		loader.addDir(new File(path + "/games"));
		List<File> files = loader.getAllFilesFromFolder(new File(path+"/images"));
		for(File file : files) {
			if(file.getName().endsWith(".jpg") ||
					file.getName().endsWith(".png") ||
					file.getName().endsWith(".gif")) {
				images.add(file);
			}
		}
		List<Object> gameContainers = loader.loadAllGames("config.txt", "gameSetup");
		for(Object gameContainer : gameContainers) {
			if(gameContainer == null) {
				return;
			}
			if(gameContainer instanceof Game) {
				games.add((Game) gameContainer);
			}
		}
	}
	
	/**
	 * Versucht den Client mit dem Server zu verbinden
	 */
	
	public static void connectToServer() {
		if(socket == null && connect == false) {
			connect = true;
			ConnectionThread con = new ConnectionThread(ipAdress, 5555);
			con.start();
		}	
	}
	
	/**
	 * Gibt ein Spiel zurück über den Spielnamen
	 * 
	 * @param name Spielname
	 * 
	 * @return null wenn das Spiel nicht existiert, ansonsten das Spiel
	 */
	
	public static Game getGame(String name) {
		for(Game game : games) {
			if(game.getName().equals(name)) {
				return game;
			}
		}
		return null;
	}
	
	/**
	 * Gibt die grafische Benutzeroberfläche des Clients zurück
	 * 
	 * @return grafische Benutzeroberfläche
	 */
	
	public static ClientView getView() {
		return view;
	}
	
	/**
	 * Liefert ein Bild zurück über seinen Namen
	 * 
	 * @param name Name des Bildes
	 * 
	 * @return null wenn das Bild nicht geladen wurde, ansonsten das Bild
	 */
	
	public static File getImage(String name) {
		for(File file : images) {
			if(file.getName().equals(name)) {
				return file;
			}
		}
		return null;
	}
	
	/**
	 * Liefert das vom User fokusierte Spiel zurück
	 * 
	 * @return null wenn der User kein Spiel fokusiert hat, ansonsten das Spiel
	 */
	
	public static Game getFocusedGame() {
		return focusedGame;
	}
	
	/**
	 * Definiert das vom User fokusierte Spiel
	 * 
	 * @param game Spiel, welches der User fokusiert
	 */
	
	public static void setFocusedGame(Game game) {
		focusedGame = game;
	}
	
	/**
	 * Sendet eine Nachricht an den Server, sofern der Client sich erfolgreich
	 * verbunden hat
	 * 
	 * @param message zu verschickende Nachricht
	 */
	
	public static void send(String message) {
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.println(message);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sendet ein Packet an den Server, sofern der Client sich erfolgreich verbunden hat
	 * 
	 * @param packet zu verschickendes Packet
	 */
	
	public static void send(Packet packet) {
		send(packet.getLine());
	}
	
	/**
	 * Liefert das vom User gespielte Spiel zurück
	 * 
	 * @return null wenn der User kein Spiel spielt, ansonsten das Spiel
	 */
	
	public static Game getPlayedGame() {
		return playedGame;
	}
	
	/**
	 * Definiert das vom User gespielte Spiel
	 * 
	 * @param game Spiel, welches der User spielt
	 */
	
	public static void setPlayedGame(Game game) {
		playedGame = game;
	}
	
	/**
	 * Definiert den Spielernamen, welcher der Client aktuell nutzt
	 * 
	 * @param name Spielername
	 */
	
	public static void setPlayerName(String name) {
		playerName = name;
	}
	
	/**
	 * Liefert den Spielernamen zurück, welcher der Client aktuell benutzt
	 * 
	 * @return Spielername
	 */
	
	public static String getPlayerName() {
		return playerName;
	}
	
	/**
	 * Startet ein Spiel und ruft die Methode onStart() des Spiels auf
	 * 
	 * @param game Spiel, welches gestartet werden soll
	 * @param settings Einstellungen, mit welchen das Spiel startet
	 * @param players Spieler, welche sich im Spiel befinden
	 */
	
	public static void startGame(Game game, GameSettings settings, List<Player> players) {
		Object[] obj = loader.startGame(game.getName(), "onStart");
		if(obj.length == 2) {
			Object methodObject = obj[0];
			Object instanceObject = obj[1];
			if(methodObject instanceof Method) {
				if(instanceObject instanceof GameRound) {
					try {
						PGameContainer container = new PGameContainer();
						container.setSize(getView().width, getView().height);
						Round round = new Round(container, settings, players);
						Constructor<?> con =
						instanceObject.getClass().getDeclaredConstructor(Round.class);
						Object gameRoundObject = con.newInstance(round);
						GameRound gameRound = (GameRound) gameRoundObject;
						((Method) methodObject).invoke(gameRound);
						playedRound = gameRound;
						getView().openGame(container);
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			}
		}
	}
	
	/**
	 * Liefert die aktuelle Spielrunde zurück, in welcher sich der Client 
	 * gerade befindet
	 * 
	 * @return aktuelle Spielrunde
	 */
	
	public static GameRound getPlayedRound() {
		return playedRound;
	}
	
	/**
	 * Stoppt ein laufendes Spiel
	 */
	
	public static void stopGame() {
		playedRound = null;
		getView().openGameMenuView(playedGame);
		playedGame = null;
	}
	
	/**
	 * Prüft ob der Name als Spielernamen erlaubt ist
	 * 
	 * @param name zu prüfender Name
	 * 
	 * @return true wenn der Name erlaubt ist, ansonsten false
	 */
	
	public static Boolean isPermittedName(String name) {
		return name.chars().allMatch(Character::isLetter) && name.length() <= 16;
	}
	
}
