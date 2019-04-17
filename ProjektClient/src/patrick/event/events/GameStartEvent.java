package patrick.event.events;

import patrick.event.Event;
import patrick.game.Game;
import patrick.game.GameSettings;
/**
 * <p>Ein Event, welcher ausgelöst wird, wenn das Spiel startet,
 * in welchem sich der Client aktuell befindet.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameStartEvent extends Event{

	/**
	 * Einstellungen, mit welchen das Spiel gestartet wurde
	 */
	
	private GameSettings settings;
	
	/**
	 * Spiel, welches gestartet wurde
	 */
	
	private Game game;
	
	/**
	 * Erzeugt einen neuen GameStartEvent
	 * 
	 * @param game Spiel, welches gestartet wurde
	 * @param settings Einstellungen, mit welchen das Spiel gestartet wurde
	 */
	
	public GameStartEvent(Game game, GameSettings settings) {
		this.game = game;
		this.settings = settings;
	}
	
	/**
	 * Liefert das Spiel zurück, welches gestartet wurde
	 * 
	 * @return Spiel, welches gestartet wurde
	 */
	
	public Game getGame() {
		return this.game;
	}
	
	/**
	 * Liefert die Einstellungen zurück, mit welchen das Spiel gestartet wurde
	 * 
	 * @return Einstellungen, mit welchen das Spiel gestartet wurde
	 */
	
	public GameSettings getSettings() {
		return this.settings;
	}
	
}
