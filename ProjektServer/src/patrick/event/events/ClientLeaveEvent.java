package patrick.event.events;

import java.net.Socket;

import patrick.event.ClientEvent;
/**
 * <p>Ein ClientEvent, welcher ausgel�st wird,
 *  wenn ein Client die Verbindung zum Server trennt. In diesem Event werden Konsequenzen davon
 *  behandelt. Sollte sich der Client in einer Spielrunde befunden haben, so werden entsprechende
 *  Event ausgel�st.</p>
 *  
 * @author Patrick
 * @version 1.0
 *
 */
public class ClientLeaveEvent extends ClientEvent{
	
	/**
	 * Erzeugt ein ClientLeaveEvent
	 * 
	 * @param client Client, welcher den Server verlassen hat
	 */
	
	public ClientLeaveEvent(Socket client) {
		super(client);
	}

}
