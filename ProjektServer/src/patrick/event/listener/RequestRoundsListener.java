package patrick.event.listener;

import patrick.Server;
import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.ClientRequestRoundsEvent;
import patrick.game.WaitingRound;
import patrick.packets.out.RequestRoundPacket;
import patrick.packets.out.RoundPacket;
import patrick.utils.Message;
/**
 * <p>Ein Listener, welcher auf die RequestRoundEvents reagiert</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class RequestRoundsListener implements Listener{

	/**
	 * Erzeugt ein RequestRoundListener und registriert diesen
	 */
	
	public RequestRoundsListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein ClientRequestRoundEvent ausgelöst wurde
	 * 
	 * @param e ClientRequestRoundEvent, welcher ausgelöst wurde
	 */
	
	public void onRequest(ClientRequestRoundsEvent e) {
		if(e.getGame() != null) {
			RequestRoundPacket packet = new RequestRoundPacket(e.getGame());
			for(WaitingRound round : Server.getAllGameRounds()) {
				if(round.getGame().getName().equals(e.getGameName())) {
					RoundPacket roundPacket = new RoundPacket(round.getHost().getName(),
							round.getPlayers().size(),
							round.getPlayerCount());
					packet.addRoundPacket(roundPacket);
				}
			}
			Server.sendPacket(e.getClient(), packet);
		}else {
			Server.sendMessage(e.getClient(), Message.gameDontExists);
		}
	}

}
