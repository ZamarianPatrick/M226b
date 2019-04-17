package patrick.event.events;

import java.net.Socket;

import patrick.event.ClientEvent;
/**
 * <p>Ein ClientEvent, welcher ausgelöst wird, wenn sich ein Client zum Server verbindet</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ClientConnectEvent extends ClientEvent{

	/**
	 * Erzeugt ein ClientConnectEvent
	 * 
	 * @param client Client, welcher sich zum Server verbindet
	 */
	
	public ClientConnectEvent(Socket client) {
		super(client);
	}	
}
