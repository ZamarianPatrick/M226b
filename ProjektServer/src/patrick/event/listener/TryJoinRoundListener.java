package patrick.event.listener;

import patrick.Server;
import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.TryJoinRoundEvent;
import patrick.event.events.player.PlayerJoinGameEvent;
import patrick.game.GameRound;
import patrick.game.Player;
import patrick.game.WaitingRound;
import patrick.utils.Message;
/**
 * <p>Ein Listener, welcher auf die TryJoinRoundEvents reagiert</p>
 * 
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class TryJoinRoundListener implements Listener{

	/**
	 * Erzeugt ein TryJoinRoundListener und registriert diesen
	 */
	
	public TryJoinRoundListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein TryJoinRoundEvent ausgelöst wurde
	 * 
	 * @param e TryJoinRoundEvent, welcher ausgelöst wurde
	 */
	
	public void onTryJoin(TryJoinRoundEvent e) {
		if(Server.getGame(e.getGameName()) != null) {
			GameRound round = Server.getGameRound(e.getRoundName());
			if(round instanceof WaitingRound || ((WaitingRound) round).isJoinable() == false) {
				WaitingRound wRound = (WaitingRound) round;
				if(wRound.getPlayerCount()> wRound.getPlayers().size()) {
					if(Server.getPlayer(e.getPlayerName()) == null) {
						Player player = new Player(e.getPlayerName(), e.getClient());
						Server.addPlayer(player, wRound);
						PlayerJoinGameEvent event = new PlayerJoinGameEvent(player, wRound);
						EventHandler.callEvent(event);
					}else {
						Server.sendMessage(e.getClient(), Message.playerAlreadyExists);
					}
				}else {
					Server.sendMessage(e.getClient(), Message.fullRound);
				}
			}else {
				Server.sendMessage(e.getClient(), Message.gameAlreadyStart);
			}
		}else {
			Server.sendMessage(e.getClient(), Message.gameDontExists);
		}
	}

}
