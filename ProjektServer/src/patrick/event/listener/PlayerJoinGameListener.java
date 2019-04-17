package patrick.event.listener;

import java.util.ArrayList;
import java.util.List;

import patrick.Server;
import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.player.PlayerJoinGameEvent;
import patrick.event.events.player.PlayerLeaveWaitingRoundEvent;
import patrick.game.Player;
import patrick.game.WaitingRound;
import patrick.packets.out.RoundPacket;
import patrick.packets.out.WaitingRoundInfoPacket;
/**
 * <p>Ein Listener, welcher auf die PlayerJoinGameEvents reagiert</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PlayerJoinGameListener implements Listener{

	/**
	 * Erzeugt ein PlayerJoinGameListener und registriert diesen
	 */
	
	public PlayerJoinGameListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein PlayerJoinGameEvent ausgelöst wurde
	 * 
	 * @param e PlayerJoinGameEvent, welcher ausgelöst wurde
	 */
	
	public void onJoin(PlayerJoinGameEvent e) {
		if(e.getRound().getPlayerCount() == e.getRound().getPlayers().size()) {
			sendInfo(e.getRound());
			e.getRound().start();
		}else {
			sendInfo(e.getRound());
		}
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein PlayerLeaveWaitingEvent ausgelöst wurde
	 * 
	 * @param e PlayerLeaveWaitingRoundEvent, welcher ausgelöst wurde
	 */
	
	public void onLeaveWaiting(PlayerLeaveWaitingRoundEvent e) {
		sendInfo(e.getRound());
	}
	
	/**
	 * Es werden an alle Spieler in der Runde ein aktuelles WaitingRoundInfoPacket gesendet,
	 * um die Informationen bei den Clients zu aktualisieren.
	 * 
	 * @param round Runde, welche aktualisiert werden muss
	 */
	
	public void sendInfo(WaitingRound round) {
		List<String> playerList = new ArrayList<String>();
		for(Player player : round.getPlayers()) {
			playerList.add(player.getName());
		}
		RoundPacket roundPacket = new RoundPacket(round.getHost().getName(),
				playerList.size(),
				round.getPlayerCount());
		WaitingRoundInfoPacket packet = new WaitingRoundInfoPacket(roundPacket, playerList);
		for(Player player : round.getPlayers()) {
			Server.sendPacket(player.getSocket(), packet);
		}
	}

}
