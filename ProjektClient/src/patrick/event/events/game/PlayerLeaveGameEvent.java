package patrick.event.events.game;
import patrick.event.Event;
import patrick.game.Player;
/**
 * <p>Ein Event, welcher ausgelöst wird, wenn ein anderer Spieler, welcher sich in dem
 * selben Spiel befindet, das Spiel verlassen hat.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PlayerLeaveGameEvent extends Event{

	/**
	 * Spieler, welcher das Spiel verlassen hat
	 */
	
	private Player player;
	
	/**
	 * Erzeugt einen neuen PlayerLeaveGameEvent aus
	 * 
	 * @param player Spieler, welcher das Spiel verlassen hat
	 */
	
	public PlayerLeaveGameEvent(Player player) {
		this.player = player;
	}
	
	/**
	 * Liefert den Spieler zurück, welcher das Spiel verlassen hat
	 * 
	 * @return Spieler, welcher das Spiel verlassen hat
	 */
	
	public Player getPlayer() {
		return this.player;
	}
	
}
