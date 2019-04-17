package patrick.event;

import java.net.InetAddress;
import java.net.Socket;
/**
 * <p>Ein Event, welcher ausgelöst wird, wenn ein Client eine Aktion tätigt</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public abstract class ClientEvent extends Event{

	/**
	 * Sockelverbindung des Clients
	 */
	
	private final Socket client;
	
	/**
	 * Erzeugt ein ClientEvent
	 * 
	 * @param client Sockelverbindung des Clients
	 */

	public ClientEvent(Socket client) {
		this.client = client;
	}
	
	/**
	 * Liefert die Sockelverbindung des Clients zurück
	 * 
	 * @return Sockelverbindung des Clients
	 */

	public Socket getClient() {
		return this.client;
	}
	
	/**
	 * Liefert die IP-Adresse des Clients
	 * 
	 * @return IP-Adresse des Clients
	 */
	
	public InetAddress getClientIPAdress() {
		return this.client.getInetAddress();
	}
	
}
