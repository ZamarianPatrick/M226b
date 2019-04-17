package patrick.event.listener;

import patrick.Client;
import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.ReceiveRoundsEvent;
/**
 * <p>Ein Listener, welcher auf ReceiveRoundsEvents reagiert</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ReceiveRoundsListener implements Listener{

	/**
	 * Erzeugt einen neuen ReceiveRoundsListener und registriert diesen
	 */
	
	public ReceiveRoundsListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Methode, welche aufgerufen wird, wenn ein ReceiveRoundsEvent ausgelöst wurde
	 * 
	 * @param e ReceiveRoundsEvent, welcher ausgelöst wurde
	 */
	
	public void onReceive(ReceiveRoundsEvent e) {
		Client.getView().openJoinView(e.getPacket());
	}
	
}
