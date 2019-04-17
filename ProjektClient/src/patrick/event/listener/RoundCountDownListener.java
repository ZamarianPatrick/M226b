package patrick.event.listener;

import patrick.Client;
import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.RoundCountDownEvent;
/**
 * <p>Ein Listener, welcher auf die RoundCountDownEvents reagiert</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class RoundCountDownListener implements Listener {

	/**
	 * Erzeugt einen neuen RoundCountDownListener und registriert diesen
	 */
	
	public RoundCountDownListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Methode, welche aufgerufen wird, wenn ein RoundCountDownEvent ausgelöst wurde
	 * 
	 * @param e RoundCountDownEvent, welcher ausgelöst wurde
	 */
	
	public void onCountDown(RoundCountDownEvent e) {
		if(Client.getPlayedGame() == null) {
			return;
		}
		Client.getView().openRoundView(null, e.getCounter());
	}
	
}
