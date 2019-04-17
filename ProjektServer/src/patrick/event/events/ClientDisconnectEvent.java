package patrick.event.events;

import java.net.Socket;

import patrick.event.ClientEvent;
/**
 * <p>Ein ClientEvent, welcher ausgelöst wird, wenn ein zum Server verbundener Client
 * seine Verbindung zum Server getrennt hat.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ClientDisconnectEvent extends ClientEvent{

	/**
	 * Erzeugt ein ClientDisconnectEvent
	 * 
	 * @param client Client, welcher seine Verbindung zum Server getrennt hat
	 */
	
	public ClientDisconnectEvent(Socket client) {
		super(client);
	}

}
