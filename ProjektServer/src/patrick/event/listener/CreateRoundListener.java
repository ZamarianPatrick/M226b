package patrick.event.listener;

import patrick.Server;
import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.CreateRoundEvent;
import patrick.game.Player;
import patrick.game.WaitingRound;
import patrick.utils.Message;
/**
 * <p>Ein Listener, welcher auf die CreateRoundEvents reagiert</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class CreateRoundListener implements Listener{

	/**
	 * Erzeugt ein CreateRoundListener und registriert diesen
	 */
	
	public CreateRoundListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein CreateRoundEvent ausgelöst wird
	 * Die Runde wird erstellt.
	 * 
	 * @param e CreateRoundEvent, welcher ausgelöst wurde
	 */
	
	public void onCreateRound(CreateRoundEvent e){
		if(Server.getPlayer(e.getHosterName()) == null
				&& Server.getGameRound(e.getHosterName()) == null) {
			Player player = new Player(e.getHosterName(), e.getHoster());
			WaitingRound round = new WaitingRound(e.getGame(), e.getSettings(), player);
			Server.addGameRound(round);
			Server.addPlayer(player, round);
			Server.sendMessage(e.getHoster(), Message.RoundCreated(round));
		}else {
			Server.sendMessage(e.getHoster(), Message.playerAlreadyExists);
		}
	}
}
