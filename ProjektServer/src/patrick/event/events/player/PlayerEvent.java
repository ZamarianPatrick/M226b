package patrick.event.events.player;

import patrick.event.Event;
import patrick.game.Player;
/**
 * <p>Ein Event welcher ausgelöst wird, wenn ein Spieler eine Aktion tätigt.
 * Der Spieler muss sich in einer Spielrunde befinden und der Event wird nur
 * in der jeweiligen Spielrunde, in welche er sich befindet ausgelöst.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PlayerEvent extends Event{

	/**
	 * Spieler, welcher eine Aktion getätigt hat
	 */
	
	private Player player;
	
	/**
	 * Erzeugt einen PlayerEvent
	 * 
	 * @param player Spieler, welcher eine Aktion getätigt hat
	 */
	
	public PlayerEvent(Player player) {
		this.player = player;
	}
	
	/**
	 * Liefert den Spieler zurück, welcher eine Aktion getätigt hat
	 * 
	 * @return Spieler, welcher die Aktion getätigt hat
	 */
	
	public Player getPlayer() {
		return this.player;
	}

}
