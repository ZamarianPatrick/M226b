package patrick.event.events;

import patrick.event.Event;
/**
 * <p>Ein Event, welcher ausgelöst wird, wenn ein Packet erhalten wird</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ReceivePacketEvent extends Event{

	/**
	 * Nachricht. welche empfangen wurde
	 */
	
	private String message;
	
	/**
	 * Erzeugt einen neuen ReceivePacketEvent
	 * 
	 * @param message Nachricht, welche empfangen wurde
	 */
	
	public ReceivePacketEvent(String message) {
		this.message = message;
	}
	
	/**
	 * Liefert die Nachricht zurück, welche empfangen wurde
	 * 
	 * @return Nachricht, welche empfangen wurde
	 */
	
	public String getMessage() {
		return this.message;
	}
	
}
