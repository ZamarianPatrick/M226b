package patrick;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import patrick.event.EventHandler;
import patrick.event.listener.ClientConnectListener;
import patrick.event.listener.ClientLeaveListener;
import patrick.event.listener.CreateRoundListener;
import patrick.event.listener.HostGameListener;
import patrick.event.listener.MessageListener;
import patrick.event.listener.PlayerJoinGameListener;
import patrick.event.listener.RequestRoundsListener;
import patrick.event.listener.TryCreateRoundListener;
import patrick.event.listener.TryJoinRoundListener;
import patrick.game.Game;
import patrick.game.GameRound;
import patrick.game.WaitingRound;
import patrick.game.Player;
import patrick.game.RunningRound;
import patrick.packets.Packet;
import patrick.utils.ClientAccepter;
import patrick.utils.ClientMessageListener;
import patrick.utils.Loader;
/**
 * <p>Mainklasse des Servers. Diese verwaltet den kompletten Server, und speichert
 * notwendige Informationen.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class Server {

	/**
	 * Liste von allen mit dem Server verbundenen Clients
	 */
	
	private static List<Socket> clients = new ArrayList<Socket>();

	/**
	 * Alle Spieler, welche sich gerade in einer Spielrunde befinden
	 * mit dem Name der Spielrunde als value
	 */
	
	private static Map<Player, String> players = new HashMap<Player, String>();
	
	/**
	 * Sockelverbindung des Servers
	 */
	
	private static ServerSocket server;
	
	/**
	 * Alle Spiele des Servers, welche erfolgreich geladen wurden
	 */
	
	private static List<Game> games = new ArrayList<Game>();
	
	/**
	 * Alle Spielrunden, welche sich in der Wartephase befinden
	 */
	
	private static List<WaitingRound> gameRounds = new ArrayList<WaitingRound>();
	
	/**
	 * Alle Spielrunden, welche gerade gespielt werden
	 */
	
	private static List<RunningRound> runningRounds = new ArrayList<RunningRound>();
	
	/**
	 * Loader, welcher alle Spiele lädt
	 */
	
	private static Loader loader;
	
	/**
	 * Einstiegspunkt des Servers. Der Server wird gestartet
	 * 
	 * @param args keine Argumente notwendig
	 */
	
	public static void main(String[] args) {
		start();
	}
	
	/**
	 * Startet den Server und lädt alle Spiele
	 */
	
	public static void start() {
        ClassLoader classLoader = new Server().getClass().getClassLoader(); 
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
						}
					}
				}
				sc.close();
				if(useExternPath) {
					System.out.println("Server wird über " + path + " gestartet");
					start(path);
					return;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		File normalFolder = new File(Server.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		String path = normalFolder.getAbsolutePath().substring(0,normalFolder.getAbsolutePath().length() - normalFolder.getName().length());
		System.out.println("Server wird mit normalem Pfad gestartet!");
		start(path);
	}
	
	/**
	 * Startet den Server über den spezifischen Pfad
	 * 
	 * @param path absoluter Pfad des Server Verzeichnisses
	 */
	
	private static void start(String path) {
		loader = new Loader();
		loader.addDir(new File(path + "/games"));
		List<Object> gameObjs = loader.loadAllGames("config.txt", "gameSetup");
		for(Object gameObj : gameObjs) {
			if(gameObj == null) {
				return;
			}
			if(gameObj instanceof Game) {
				Game game = (Game) gameObj;
				System.out.println(game.getName() + " erfolgreich geladen!");
				registerGame(game);
			}
		}
		registerListener();
		try {
			if(server == null || server.isClosed() == false) {
				server = new ServerSocket(5555);
				ClientAccepter accepter = new ClientAccepter(server);
				accepter.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Server wurde erfolgreich gestartet!");
	}
	
	/**
	 * registriert alle Listener
	 */
	
	private static void registerListener() {
		new ClientConnectListener();
		new HostGameListener();
		new TryCreateRoundListener();
		new CreateRoundListener();
		new MessageListener();
		new RequestRoundsListener();
		new ClientLeaveListener();
		new TryJoinRoundListener();
		new PlayerJoinGameListener();
	}
	
	/**
	 * Stoppt den Server, entlädt alle Spiele und löscht die Listener
	 */
	
	public static void stop() {
		try {
			for(Socket socket : new ArrayList<Socket>(clients)) {
				socket.close();
			}
			clients.clear();
			server.close();
			games.clear();
			gameRounds.clear();
			ClientMessageListener.stopAll();
			server = null;
			EventHandler.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Server wurde erfolgreich gestoppt");
	}
	
	/**
	 * Startet den Server neu
	 */
	
	public void restart() {
		stop();
		start();
	}
	
	/**
	 * Fügt eine Sockelverbindung eines Clients hinzu
	 * 
	 * @param socket Sockelverbindung des Clients
	 */
	
	public static void connectClient(Socket socket) {
		if(clients.contains(socket) == false) {
			clients.add(socket);
		}
	}
	
	/**
	 * Liefert alle verbundenen Clients zurück
	 * 
	 * @return Liste der Clients
	 */
	
	public static List<Socket> getConnectedClients(){
		return clients;
	}

	/**
	 * Löscht ein Sockelverbindung eines Clients
	 * 
	 * @param client Sockelverbindung des Clients
	 */

	public static void disconnectClient(Socket client) {
		clients.remove(client);
	}
	
	/**
	 * Sendet eine Nachricht an einen Client
	 * 
	 * @param socket Sockelverbindung des Clients, welcher die Nachricht empfangen soll
	 * @param message Nachricht, welche an den Client gesendet werden soll
	 * 
	 * @return true wenn die Nachricht versendet worde ist, ansonsten false
	 */
	
	public static boolean sendMessage(Socket socket, String message) {
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.println(message);
			writer.flush();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	/**
	 * Sendet ein Packet an einen Client
	 * 
	 * @param socket Sockelverbindung des Clients, welcher das Packet empfangen soll
	 * @param packet Packet, welches an den Client gesendet werden soll
	 * 
	 * @return true wenn das Packet versendet worden ist, ansonsten false
	 */
	
	public static boolean sendPacket(Socket socket, Packet packet) {
		return sendMessage(socket, packet.getLine());
	}
	
	/**
	 * Liefert die Sockelverbindung des Servers zurück
	 * 
	 * @return Sockelverbindung des Servers
	 */
	
	public static ServerSocket getSocket() {
		return server;
	}
	
	/**
	 * Registriert ein neues Spiel
	 * 
	 * @param game zu registrierendes Spiel
	 */
	
	public static void registerGame(Game game) {
		if(game != null) {
			games.add(game);
		}
	}
	
	/**
	 * Liefert alle geladenen Spiele zurück
	 * 
	 * @return Liste aller geladenen Spiele
	 */
	
	public static List<Game> getAllGames(){
		return new ArrayList<Game>(games);
	}
	
	/**
	 * Liefert ein Spiel über den Namen zurück
	 * 
	 * @param gameName Name des Spiels
	 * 
	 * @return null wenn das Spiel nicht existiert, ansonsten das Spiel
	 */
	
	public static Game getGame(String gameName) {
		for(Game game : games) {
			if(game.getName().equals(gameName)) {
				return game;
			}
		}
		return null;
	}
	
	/**
	 * Liefert eine Liste von Spieler, welche sich in einer bestimmten Spielrunde befinden
	 * 
	 * @param round Runde, von welcher die Spieler gelistet werden sollen
	 * 
	 * @return Liste von allen Spielern in der Runde
	 */
	
	public static List<Player> getPlayersFromRound(GameRound round){
		List<Player> list = new ArrayList<Player>();
		try {
			for(Entry<Player, String> entry : players.entrySet()) {
				if(round.getHost().getName().equalsIgnoreCase(entry.getValue())) {
					list.add(entry.getKey());
				}
			}
		}catch(Exception ex) {return getPlayersFromRound(round);}
		return list;
	}
	
	/**
	 * Liefert eine Liste von allen Spielrunden, welche sich in der Wartephase befinden
	 * 
	 * @return Listen von allen WaitingRounds
	 */
	
	public static List<WaitingRound> getAllGameRounds(){
		return new ArrayList<WaitingRound>(gameRounds);
	}
	
	/**
	 * Liefert eine Listen von allen Spielrunden, welche gerade gespielt werden
	 * 
	 * @return Liste von allen RunningRounds
	 */
	
	public static List<RunningRound> getAllRunningGameRounds(){
		return new ArrayList<RunningRound>(runningRounds);
	}
	
	/**
	 * Startet eine wartende Runde und wandelt diese um in eine RunningRound
	 * 
	 * @param round wartende Runde, welche gestartet werden soll
	 */
	
	public static void startRound(WaitingRound round) {
		for(WaitingRound wRound : gameRounds) {
			if(round.getHost().getName().equals(wRound.getHost().getName())) {
				String gameName = wRound.getGame().getName();
				Object roundObj = loader.startRound(gameName, wRound);
				if(roundObj instanceof RunningRound) {
					RunningRound runningRound = (RunningRound) roundObj;
					gameRounds.remove(wRound);
					runningRounds.add(runningRound);
					return;
				}
			}
		}
	}
	
	/**
	 * Fügt eine wartende Runde hinzu
	 * 
	 * @param round hinzuzufügende Runde
	 */
	
	public static void addGameRound(WaitingRound round) {
		gameRounds.add(round);
	}
	
	/**
	 * Entfernt eine wartende Runde
	 * 
	 * @param round zu löschende Runde
	 */
	
	public static void removeWaitingRound(WaitingRound round) {
		try {
			gameRounds.remove(round);
		}catch(Exception ex) {
			
		}
		if(gameRounds.size()<0) {
			gameRounds = new ArrayList<WaitingRound>();
		}
		for(Player p : round.getPlayers()){
			players.remove(p);
		}
	}
	
	/**
	 * Fügt einen Spielereintrag mit seiner Spielrunde hinzu
	 * 
	 * @param player hinzuzufügender Spieler
	 * @param round Rundename des Spielers
	 */
	
	public static void addPlayer(Player player, GameRound round) {
		players.put(player, round.getHost().getName());
	}
	
	/**
	 * Entfernt einen Spielereintrag
	 * 
	 * @param player zu löschender Spieler
	 */
	
	public static void removePlayer(Player player) {
		players.remove(player);
	}
	
	/**
	 * Liefert ein Spieler über seinen Namen zurück
	 * 
	 * @param name Name des Spielers
	 * 
	 * @return null wenn der Spieler nicht existiert, ansonsten den Spieler
	 */
	
	public static Player getPlayer(String name) {
		for(Player player : players.keySet()) {
			if(player.getName().equalsIgnoreCase(name)) {
				return player;
			}
		}
		return null;
	}
	
	/**
	 * Liefert einen Spielereintrag mit dem Namen der Runde zurück, in welcher sich der 
	 * Spieler befindet.
	 * 
	 * @param socket Sockelverbindung des Spielers
	 * 
	 * @return Spielereintrag mit seiner Runde
	 */
	
	public static Entry<Player,String> getPlayerGameRound(Socket socket) {
		try {
			HashMap<Player, String> temp = new HashMap<Player, String>(players);
			for(Entry<Player,String> entry : temp.entrySet()) {
				if(entry.getKey().getSocket().equals(socket)) {
					return entry;
				}
			}
		}catch(Exception ex) {
			
		}
		return null;
	}
	
	/**
	 * Liefert den Rundenname eines Spielers zurück, in welcher er sich gerade befindet
	 * 
	 * @param player Spieler, von welchem die Runde erhalten werden will
	 * 
	 * @return null wenn der Spieler in keiner Runde ist, ansonsten die Runde
	 */
	
	public static String getPlayersGameRoundName(Player player) {
		return players.get(player);
	}
	
	/**
	 * Liefert den Rundenname eines Spielers zurück, in welcher er sich gerade befindet
	 * 
	 * @param playerName Name des Spielers, von welchem die Runde erhalten werden will
	 * 
	 * @return null wenn der Spieler in keiner Runde ist, ansonsten die Runde
	 */
	
	public static String getPlayersGameRoundName(String playerName) {
		for(Entry<Player, String> entry : players.entrySet()) {
			if(entry.getKey().getName().equalsIgnoreCase(playerName)) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	/**
	 * Liefert alle Spielereinträge zurück
	 * 
	 * @return Spielereinträge
	 */
	
	public static Map<Player, String> getPlayers(){
		try {
			return new HashMap<Player, String>(players);
		}catch(Exception ex) {
			return getPlayers();
		}
	}
	
	/**
	 * Liefert eine Spielrunde über den Namen zurück.
	 * Dies kann eine wartende oder eine laufende Runde sein.
	 * 
	 * @param gameRoundName Name der Runde
	 * 
	 * @return null wenn die Runde nicht existiert, ansonsten die Runde
	 */
	
	public static GameRound getGameRound(String gameRoundName) {
		for(GameRound round : gameRounds) {
			if(round.getHost().getName().equals(gameRoundName)) {
				return round;
			}
		}
		for(GameRound round : runningRounds) {
			if(round.getHost().getName().equals(gameRoundName)) {
				return round;
			}
		}
		return null;
	}
	
	/**
	 * Entfernt eine laufende Runde
	 * 
	 * @param round zu entfernende Runde
	 */

	public static void removeRunningRound(RunningRound round){
		try {
			runningRounds.remove(round);
		}catch(Exception ex) {
			
		}
		if(runningRounds.size() < 0) {
			runningRounds = new ArrayList<RunningRound>();
		}
		for(Player p : round.getPlayers()) {
			players.remove(p);
		}
	}
	
}
