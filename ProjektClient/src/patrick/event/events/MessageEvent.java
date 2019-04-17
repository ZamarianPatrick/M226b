package patrick.event.events;

import patrick.event.Event;
/**
 * <p>Ein Event, welcher ausgelöst wird, wenn der Client eine Nachricht des 
 * Servers erhält</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class MessageEvent extends Event{

	/**
	 * Nachricht, welche vom Server empfangen wurde
	 */
	
	private String message;
	
	/**
	 * Erzeugt einen neuen MessageEvent
	 * 
	 * @param message Nachricht, welche vom Server empfangen wurde
	 */
	
	public MessageEvent(String message) {
		this.message = message;
	}
	
	/**
	 * Liefert die Nachricht zurück, welche vom Server empfangen wurde
	 * 
	 * @return Nachricht, welche vom Server empfangen wurde
	 */
	
	public String getMessage() {
		return this.message;
	}
	
}
