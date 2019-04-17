package patrick.event.listener;

import patrick.Client;
import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.RoundInfoPacketEvent;
/**
 * <p>Ein Listener, welcher auf die RoundInfoPacketEvents reagiert</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class RoundInfoPacketListener implements Listener{

	/**
	 * Erzeugt einen neuen RoundInfoPacketListener und registriert diesen
	 */
	
	public RoundInfoPacketListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Methode, welche aufgerufen wird, wenn ein RoundInfoPacketEvent ausgelöst wurde
	 * 
	 * @param e RoundInfoPacketEvent, welcher ausgelöst wurde
	 */
	
	public void onRoundInfoPacket(RoundInfoPacketEvent e) {
		Client.getView().openRoundView(e.getPacket());
	}
	
}
