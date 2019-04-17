package patrick.event.listener;

import patrick.Server;
import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.ClientHostGameEvent;
import patrick.packets.out.GameOptionPacket;
/**
 * <p>Ein Listener, welcher auf die HostGameEvents reagiert</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class HostGameListener implements Listener{

	/**
	 * Erzeugt ein HostGameListener und registriert diesen
	 */
	
	public HostGameListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein ClientHostGameEvent ausgel�st wurde
	 * 
	 * @param e ClientHostGameEvent, welcher ausgel�st wurde
	 */
	
	public void onHostGame(ClientHostGameEvent e) {
		Server.sendPacket(e.getClient(), new GameOptionPacket(e.getGame()));
	}
	
}
