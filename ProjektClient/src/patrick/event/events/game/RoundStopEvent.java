package patrick.event.events.game;

import patrick.event.Event;
import patrick.game.GameRound;
/**
 * <p>Ein Event, welcher ausgelöst wird, wenn die aktuelle Spielrunde gestoppt wurde.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class RoundStopEvent extends Event{

	/**
	 * Spielrunde, welche gestoppt wurde.
	 */
	private GameRound round;
	
	/**
	 * Erzeugt einen neuen RoundStopEvent
	 * 
	 * @param round Runde, welche gestoppt wurde
	 */
	
	public RoundStopEvent(GameRound round) {
		this.round = round;
	}
	
	/**
	 * Liefert die Runde zurück, welche gestoppt wurde
	 * 
	 * @return Runde, welche gestoppt wurde
	 */
	
	public GameRound getGameRound() {
		return this.round;
	}
	
}
