package patrick.event.listener;

import java.lang.reflect.Field;

import patrick.Client;
import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.game.RoundStopEvent;
/**
 * <p>Ein Listener, welcher auf RoundStopEvents reagiert</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class RoundStopListener implements Listener {

	/**
	 * Erzeugt einen neuen RoundStopListener und registriert diesen
	 */
	
	public RoundStopListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Methode, welche aufgerufen wird, wenn ein RoundStopEvent ausgelöst wurde
	 * 
	 * @param e RoundStopEvent, welcher ausgelöst wurde
	 */
	
	public void onStop(RoundStopEvent e) {
		if(e.getGameRound() != null) {
			try {
				Client.getView().openGameMenuView(Client.getPlayedGame());
				Client.getView().getRoot().removeUndo();
				Field field = Client.class.getDeclaredField("playedRound");
				field.setAccessible(true);
				field.set(Client.class, null);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
