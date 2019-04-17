package patrick.event.events.player;

import patrick.game.Player;
/**
 * <p>Ein SpielerEvent, welcher immer ausgelöst wird, wenn ein Spieler eine laufende Spielrunde
 * verlässt. Dieser Event soll von den Spielen selbst abgefangen und behandelt werden.
 * Der Event wird nur bei den jeweiligen Spielrunden welche davon direkt betroffen sind
 * ausgelöst. Wenn alle Spieler aus einer RunningRoun verlassen haben, so sollte die
 * RunningRound vom Spiel aus mit der Methode stop() gestoppt werden.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PlayerLeaveGameEvent extends PlayerEvent{
	
	/**
	 * Erzeugt einen PlayerLeaveEvent
	 * 
	 * Dieser Event wird jeweils vom Serversystem instanziiert und auch weitergegeben.
	 * Um Fehler zu vermeiden soll dieser Event nicht von ausserhalb des Serversystems
	 * instanziiert und ausgelöst werden, sondern nur abgefangen und behandelt.
	 * 
	 * @param player Spieler, welche eine RunningRound verlassen hat
	 */
	
	public PlayerLeaveGameEvent(Player player) {
		super(player);
	}

}
