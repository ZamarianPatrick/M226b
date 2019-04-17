package patrick.event.listener;

import patrick.Server;
import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.ClientConnectEvent;
import patrick.event.events.ClientDisconnectEvent;
import patrick.event.events.ClientLeaveEvent;
import patrick.utils.Message;
/**
 * <p>Ein Listener, welcher auf die ClientConnectEvents reagiert</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ClientConnectListener implements Listener{

	/**
	 * Erzeugt ein ClientConnectListener und registriert diesen
	 */
	
	public ClientConnectListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein ClientConnectEvent ausgelöst wird
	 * 
	 * @param e ClientConnectEvent, welcher ausgelöst wurde
	 */
	
	public void onClientConnectEvent(ClientConnectEvent e) {
		Server.connectClient(e.getClient());
		Server.sendMessage(e.getClient(), Message.getGames());
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein ClientDisconnectEvent ausgelöst wird
	 * 
	 * @param e ClientDisconnectEvent welcher ausgelöst wurde
	 */
	
	public void onClientDisconnectEvent(ClientDisconnectEvent e) {
		ClientLeaveEvent event = new ClientLeaveEvent(e.getClient());
		EventHandler.callEvent(event);
		Server.disconnectClient(e.getClient());
	}
	
	
}
