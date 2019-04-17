package patrick.event.events;

import java.net.Socket;

import patrick.event.Event;
import patrick.game.Game;
import patrick.game.GameSettings;
/**
 * <p>Ein Event, welcher ausgelöst wird, wenn eine neue Runde erfolgreich erstellt wurde</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class CreateRoundEvent extends Event{

	/**
	 * Spiel, von welchem eine Spielrunde erstellt wurde
	 */
	
	private Game game;
	
	/**
	 * Einstellungen mit welchen die Spielrunde erstellt wurde
	 */
	
	private GameSettings settings;
	
	/**
	 * Sockelverbindung des Hosters, welcher die Spielrunde erstellt hat
	 */
	
	private Socket hoster;
	
	/**
	 * Name des Hosters, welcher die Spielrunde erstellt hat
	 */
	
	private String hosterName;
	
	/**
	 * Erzeugt ein CreateRoundEvent
	 * 
	 * @param hoster Sockelverbindung des Hosters
	 * @param hosterName Name des Hosters
	 * @param game Spiel, von welchem die Spielrunde erstellt wurde
	 * @param settings Einstellungen, mit welchen die Spielrunde erstellt wurde
	 */
	
	public CreateRoundEvent(Socket hoster, String hosterName, Game game, GameSettings settings) {
		this.game = game;
		this.settings = settings;
		this.hoster = hoster;
		this.hosterName = hosterName;
	}
	
	/**
	 * Liefert den vom Hoster gewählten Namen zurück
	 * 
	 * @return Name des Hosters
	 */
	
	public String getHosterName() {
		return this.hosterName;
	}
	
	/**
	 * Liefert das Spiel zurück, von welchem eine Spielrunde erstellt wurde
	 * 
	 * @return Spiel, von welchem eine Spielrunde erstellt wurde
	 */
	
	public Game getGame() {
		return this.game;
	}
	
	/**
	 * Liefert die Sockelverbindung des Hosters zurück
	 * 
	 * @return Sockelverbindung des Hosters
	 */
	
	public Socket getHoster() {
		return this.hoster;
	}
	
	/**
	 * Liefert die Einstellungen zurück, mit welchen die Spielrunde erstellt wurde
	 * 
	 * @return Einstellungen, mit welchen die Spielrunde erstellt wurde
	 */
	
	public GameSettings getSettings() {
		return this.settings;
	}
	
	
}
