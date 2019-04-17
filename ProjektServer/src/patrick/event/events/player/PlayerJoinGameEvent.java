package patrick.event.events.player;

import patrick.game.Player;
import patrick.game.WaitingRound;
/**
 * <p>Ein PlayerEvent, welcher ausgelöst wird, wenn ein Spieler einer wartenden Runde beitritt.
 * Dieser Event kann nicht von den RunninRounds her aufgerufen oder abgefangen werden. Der
 * Event wird vom Server selbst behandelt und es sollten keine zusätzlichen Listener
 * registriert werden von ausserhalb des Serversystems.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PlayerJoinGameEvent extends PlayerEvent{

	/**
	 * Wartende Runde, welcher der Spieler beigetreten ist.
	 */
	
	private WaitingRound round;
	
	/**
	 * Erzeugt ein PlayerJoinGameEvent
	 * 
	 * @param player Spieler, welcher eine wartende Runde gejoint hat
	 * @param round Runde, welcher der Spieler beigetreten ist
	 */
	
	public PlayerJoinGameEvent(Player player, WaitingRound round) {
		super(player);
		this.round = round;
	}
	
	/**
	 * Liefert die wartende Runde zurück, welcher der Spieler beigetreten ist
	 * 
	 * @return wartende Runde, welcher der Spieler beigetreten ist
	 */
	
	public WaitingRound getRound() {
		return this.round;
	}

}
