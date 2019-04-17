package patrick.event.events;

import java.net.Socket;

import patrick.event.ClientEvent;
/**
 * <p>Ein ClientEvent, welcher ausgelöst wird, wenn eine Nachricht eines Clients
 * empfangen wurde</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ClientSendMessageEvent extends ClientEvent{

	/**
	 * Nachricht, welche vom Client empfangen wurde
	 */
	
	private String message;
	
	/**
	 * Erzeugt ein ClientSendMessageEvent
	 * 
	 * @param client Client, von welchem eine Nachricht empfangen wurde
	 * @param message Nachricht, welche vom Client empfangen wurde
	 */
	
	public ClientSendMessageEvent(Socket client, String message) {
		super(client);
		this.message = message;
	}
	
	/**
	 * Liefert die Nachricht zurück, welche vom Client empfangen wurde
	 * 
	 * @return Nachricht, welche vom Client empfangen wurde
	 */
	
	public String getMessage() {
		return this.message;
	}

}
