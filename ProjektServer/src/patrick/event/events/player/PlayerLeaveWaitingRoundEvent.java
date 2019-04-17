package patrick.event.events.player;

import patrick.game.Player;
import patrick.game.WaitingRound;
/**
 * <p>Ein SpielerEvent, welcher ausgelöst wird, wenn ein Spieler eine wartende Runde verlässt.
 * Dieser Event wird nur vom Serversystem abgefangen und behandelt und wird nie in an eine
 * RunningRound weitergegeben.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PlayerLeaveWaitingRoundEvent extends PlayerEvent{

	/**
	 * wartende Runde, welche der Spieler verlassen hat
	 */
	
	private WaitingRound round;
	
	/**
	 * Erzeugt einen neuen PlayerLeaveWaitingRoundEvent
	 * 
	 * Sollte nicht von ausserhalb des Serversystems instanziiert und erst recht nicht
	 * ausgelöst werden. Dies könnte zu Fehlern des Serversystems führen.
	 * 
	 * @param player Spieler, welcher die wartende Runde verlassen hat
	 * @param round Runde, welche der Spieler verlassen hat
	 */
	
	public PlayerLeaveWaitingRoundEvent(Player player, WaitingRound round) {
		super(player);
		this.round = round;
	}
	
	/**
	 * Liefert die Runde zurück, welche der Spieler verlassen hat
	 * 
	 * @return Runde, welche der Spieler verlassen hat
	 */
	
	public WaitingRound getRound() {
		return this.round;
	}

}
