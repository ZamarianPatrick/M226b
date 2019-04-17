package patrick.game;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import patrick.Client;
import patrick.component.components.PGameContainer;
import patrick.event.Event;
import patrick.event.Listener;
import patrick.packets.Packet;
/**
 * <p>Eine laufende Spielrunde, welche erzeugt wird, wenn der Client sich in 
 * einem startenden Spiel befindet.
 * Diese Klasse dient als Schnittstelle für alle neuen Spiele. 
 * Um ein neues Spiel zu erstellen, soll diese Klasse erweitert werden.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public abstract class GameRound {
	
	/**
	 * PGameContainer, in welchem die Spielrunde abläuft
	 */
	
	protected PGameContainer gameContainer;
	
	/**
	 * Spieleinstellungen, mit welchen die Spielrunde gestartet wurde
	 */
	
	private GameSettings settings;
	
	/**
	 * Liste aller Spieler, welche sich in der Runde befinden
	 */
	
	private List<Player> players = new ArrayList<Player>();
	
	/**
	 * Liste aller Listener, welche auf die Events dieser Spielrunde reagieren
	 */
	
	private List<Listener> listeners = new ArrayList<Listener>();
	
	/**
	 * Dieser Konstruktor ist notwendig, damit der Client beim Start
	 * die Methode gameSetup aufrufen kann, und somit das Spiel laden.
	 */
	
	public GameRound() {
		
	}
	
	/**
	 * Dieser Konstruktor ist notwendig, damit der Client die startende Runde
	 * an das Spiel übergeben kann.
	 * 
	 * @param round
	 */
	
	public GameRound(Round round) {
		this.gameContainer = round.getGameContainer();
		this.settings = round.getSettings();
		this.players = round.getPlayers();
	}
	
	/**
	 * Liefert die Spieleinstellungen zurück, mit welchen die Spielrunde gestartet wurde
	 * 
	 * @return Spieleinstellungen, mit welchen die Spielrunde gestartet wurde
	 */
	
	public GameSettings getGameSettings() {
		return settings;
	}
	
	/**
	 * Liefert eine Liste aller Spieler, welche sich in der Runde befinden
	 * 
	 * @return Liste von allen Spieler in der Spielrunde
	 */
	
	public List<Player> getPlayers(){
		return new ArrayList<Player>(this.players);
	}
	
	/**
	 * Methode die vom Client aufgerufen wird, wenn ein Spieler das Spiel verlässt.
	 * Sollte nicht andersweitig verwendet und auch nicht überschrieben werden.
	 * 
	 * @param player Spieler, welcher das Spiel verlassen hat
	 */
	
	protected void removePlayer(Player player) {
		this.players.remove(player);
	}
	
	/**
	 * Liefert einen Spieler aus der Spielrunde über seinen Namen, sofern
	 * diese existiert
	 * 
	 * @param name Spielername
	 * 
	 * @return null wenn der Spieler nicht existiert, ansonsten den Spieler
	 */
	
	public Player getPlayer(String name) {
		for(Player player : players) {
			if(player.getName().equalsIgnoreCase(name)) {
				return player;
			}
		}
		return null;
	}
	
	/**
	 * Schickt eine Nachricht an das Spiel, welches auf dem Server läuft
	 * 
	 * @param message Nachricht, die verschickt werden soll
	 */
	
	public void send(String message) {
		Client.send("gaming:"+message);
	}
	
	/**
	 * Registriert einen neuen Listener, welcher Events der aktuellen Spielrunde
	 * empfangen kann.
	 * 
	 * @param listener Listener, welcher registriert werden soll
	 */
	
	public void registerListener(Listener listener) {
		this.listeners.add(listener);
	}
	
	/**
	 * Löst einen Spielrundenbezogenen Event aus
	 * 
	 * @param event Event, welcher ausgelöst werden soll
	 */
	
	public void callEvent(Event event) {
		for(Listener listener : new ArrayList<Listener>(listeners)) {
			Class<?> clazz = listener.getClass();
			for(Method method : clazz.getDeclaredMethods()) {
				if(method.getParameterCount() == 1) {
					if(method.getParameters()[0].getType().isInstance(event)) {
						try {
							method.invoke(listener, event);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	/**
	 * Schickt ein Packet an das Spiel, welches auf dem Server läuft
	 * 
	 * @param packet Packet, welches verschickt werden soll
	 */
	
	public void send(Packet packet) {
		send(packet.getLine());
	}
	
	/**
	 * Diese Methode wird jedes mal aufgerufen, wenn eine Spielrunde dieses Spiels startet
	 */
	
	public abstract void onStart();
	
	/**
	 * Diese Methode ist dazu da, um das Spiel zu definieren und wird jedes Mal,
	 * wenn der Client startet.
	 * 
	 * @return Spiel, welches dem Client hinzugefügt werden soll
	 */
	
	public abstract Game gameSetup();
	
}
