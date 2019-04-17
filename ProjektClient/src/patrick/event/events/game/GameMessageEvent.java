package patrick.event.events.game;

import patrick.event.Event;
/**
 * <p>Ein Event, welcher ausgelöst wird, wenn der Spieler eine Nachricht des Spiels
 * erhählt, welches er gerade spielt.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameMessageEvent extends Event{
	
	/**
	 * Nachricht, welche verschickt wurde
	 */
	
	private String message;
	
	/**
	 * Erzeugt einen neuen GameMessageEvent
	 * 
	 * @param message Nachricht, welche verschickt wurde
	 */
	
	public GameMessageEvent(String message) {
		this.message = message;
	}
	
	/**
	 * Liefert die Nachricht zurück, welche vom Spiel verschickt wurde
	 * 
	 * @return Nachricht welche vom Spiel verschickt wurde
	 */
	
	public String getMessage() {
		return this.message;
	}
	
}
