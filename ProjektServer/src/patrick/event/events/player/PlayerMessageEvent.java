package patrick.event.events.player;

import patrick.game.Player;
/**
 * <p>Ein SpielerEvent, welcher ausgelöst wird, wenn eine Spieler, welcher sich in einer
 * RunningRound befindet, eine Nachricht an den Server schickt. Dieser Event soll jeweils von
 * den Spielen selbst abgefangen und behandelt werden. Der Event wird vom Serversystem
 * an die jeweilig direkt betroffene und nur an diese weitergeleitet.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PlayerMessageEvent extends PlayerEvent{

	/**
	 * Nachricht, welche der Spieler an das Spiel versendet hat
	 */
	
	private String message;
	
	/**
	 * Erzeugt einen PlayerMessageEvent
	 * 
	 * @param player Spieler, welcher eine Nachricht an das Spiel sendet
	 * @param message Nachricht des Spielers, welche an das Spiel gerichtet ist
	 */
	
	public PlayerMessageEvent(Player player, String message) {
		super(player);
		this.message = message;
	}
	
	/**
	 * Liefert die Nachricht zurück, welche an das betroffene Spiel versendet wurde
	 * 
	 * @return Nachricht an das Spiel
	 */
	
	public String getMessage() {
		return this.message;
	}
	
}
