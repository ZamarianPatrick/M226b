package patrick.event.listener;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import patrick.Server;
import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.ClientLeaveEvent;
import patrick.event.events.player.PlayerLeaveGameEvent;
import patrick.event.events.player.PlayerLeaveWaitingRoundEvent;
import patrick.game.GameRound;
import patrick.game.Player;
import patrick.game.RunningRound;
import patrick.game.WaitingRound;
/**
 * <p>Ein Listener, welcher auf die ClientLeaveEvents reagiert</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ClientLeaveListener implements Listener{

	/**
	 * Erzeugt ein ClientLeaveListener und registriert diesen
	 */
	
	public ClientLeaveListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein ClientLeaveEvent ausgelöst wird
	 * Die Methode überprüft,  ob ein der Client, welcher den Server verlassen hat,
	 * in einer WaitingRound oder einer RunningRound war, und löst entsprechende Reaktionen
	 * aus.
	 * 
	 * @param e ClientLeaveEvent, welcher ausgelöst wurde
	 */
	
	public void onLeave(ClientLeaveEvent e) {
		Map<Player, String> map = Server.getPlayers();
		for(Entry<Player, String> entry : map.entrySet()) {
			if(entry.getKey().getSocket().equals(e.getClient())) {
				GameRound round = Server.getGameRound(entry.getValue());
				if(round != null) {
					Server.removePlayer(entry.getKey());
					if(round.getPlayers().size() == 0) {
						if(round instanceof WaitingRound) {
							((WaitingRound) round).stop();
						}else if(round instanceof RunningRound) {
							((RunningRound) round).stop();
						}
					}
					if(round instanceof RunningRound) {
						PlayerLeaveGameEvent event = new PlayerLeaveGameEvent(entry.getKey());
						for(Player p : ((RunningRound)round).getPlayers()) {
							Server.sendMessage(p.getSocket(), "leave:"+p.getName());
						}
						try {
							Field field = RunningRound.class.getDeclaredField("players");
							field.setAccessible(true);
							List<?> players = (List<?>) field.get(round);
							players.remove(entry.getKey());
						}catch(Exception ex) {}
						((RunningRound) round).callEvent(event);
					}else if(round instanceof WaitingRound){
						PlayerLeaveWaitingRoundEvent event = new PlayerLeaveWaitingRoundEvent(entry.getKey(), (WaitingRound) round);
						EventHandler.callEvent(event);
					}
				}
			}
		}
	}

}
