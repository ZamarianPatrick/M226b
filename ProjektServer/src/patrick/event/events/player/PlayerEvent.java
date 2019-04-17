package patrick.event.events.player;

import patrick.event.Event;
import patrick.game.Player;
/**
 * <p>Ein Event welcher ausgel�st wird, wenn ein Spieler eine Aktion t�tigt.
 * Der Spieler muss sich in einer Spielrunde befinden und der Event wird nur
 * in der jeweiligen Spielrunde, in welche er sich befindet ausgel�st.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PlayerEvent extends Event{

	/**
	 * Spieler, welcher eine Aktion get�tigt hat
	 */
	
	private Player player;
	
	/**
	 * Erzeugt einen PlayerEvent
	 * 
	 * @param player Spieler, welcher eine Aktion get�tigt hat
	 */
	
	public PlayerEvent(Player player) {
		this.player = player;
	}
	
	/**
	 * Liefert den Spieler zur�ck, welcher eine Aktion get�tigt hat
	 * 
	 * @return Spieler, welcher die Aktion get�tigt hat
	 */
	
	public Player getPlayer() {
		return this.player;
	}

}
