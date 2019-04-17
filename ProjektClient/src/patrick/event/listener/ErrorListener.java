package patrick.event.listener;

import javax.swing.JOptionPane;

import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.ErrorEvent;
import patrick.utils.Message;
/**
 * <p>Ein Listener, welcher die Events für Fehlermeldungen behandelt</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ErrorListener implements Listener{

	/**
	 * Erzeugt einen neuen ErrorListener und registriert diesen
	 */
	
	public ErrorListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Methode, welche aufgerufen wird, sobald ein ErrorEvent ausgelöst wird
	 * 
	 * @param e ErrorEvent, welcher ausgelöst wurde
	 */
	
	public void onError(ErrorEvent e) {
		String message = "";
		switch(e.getError()) {
		case FORBIDDEN_PLAYERNAME:
			message = Message.forbiddenName;
			break;
		case FULL_ROUND:
			message = Message.fullRound;
			break;
		case GAME_ALREADY_START:
			message = Message.gameAlreadyStart;
			break;
		case GAME_DONT_EXIST:
			message = Message.gameDontExists;
			break;
		case NO_CONNECTION:
			message = Message.noConnection;
			break;
		case PLAYER_ALREADY_EXIST:
			message = Message.playerAlreadyExists;
			break;
		default:
			message = e.getError().toString();
			break;
		}
		
		JOptionPane.showMessageDialog(
				null,
				message,
				"Fehler",
				JOptionPane.ERROR_MESSAGE
				);
	}
	
}
