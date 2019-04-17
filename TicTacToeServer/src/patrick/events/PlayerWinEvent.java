package patrick.events;

import patrick.event.events.player.PlayerEvent;
import patrick.game.Player;
/**
 * <p>Ein PlayerEvent, welcher ausgelöst wird, wenn ein Spieler das Spiel gewonnen hat</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PlayerWinEvent extends PlayerEvent{
	
	/**
	 * Erzeugt ein PlayerWinEvent
	 * 
	 * @param player Spieler, welcher gewonnen hat
	 */
	
	public PlayerWinEvent(Player player) {
		super(player);
	}
	
}
