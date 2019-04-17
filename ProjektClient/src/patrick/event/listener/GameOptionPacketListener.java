package patrick.event.listener;

import patrick.Client;
import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.GameOptionPacketEvent;
/**
 * <p>Ein Listener, welcher auf ein GameOptionPacketEvent Ereignis reagiert</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameOptionPacketListener implements Listener{

	/**
	 * Erzeugt einen neuen GameOptionPacketListener und registriert diesen
	 */
	
	public GameOptionPacketListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Methode, welche aufgerufen wird, wenn ein GameOptionPacketEvent ausgelöst wird
	 *  
	 * @param e GameOptionPacketEvent, welcher ausgelöst wurde
	 */
	
	public void onPacket(GameOptionPacketEvent e) {
		if(e.getPacket() != null) {
			Client.getView().openGameCreateView(e.getPacket());
		}
	}
	
}
