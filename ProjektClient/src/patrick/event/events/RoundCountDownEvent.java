package patrick.event.events;

import patrick.event.Event;
/**
 * <p>Ein Event, welcher ausgelöst wird, wenn ein Countdown einer startenden
 * Runde empfangen wird</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class RoundCountDownEvent extends Event{

	/**
	 * Anzahl verbleibende Sekunden, bis zum Start der Runde
	 */
	
	private int counter;
	
	/**
	 * Erzeugt einen neuen RoundCountDownEvent
	 * 
	 * @param counter Anzahl verbleibende Sekunden
	 */
	
	public RoundCountDownEvent(int counter) {
		this.counter = counter;
	}
	
	/**
	 * Liefert die Anzahl der verbleibenden Sekunden zurück, bis die Spielrunde startet
	 * 
	 * @return Anzahl verbleibende Sekunden
	 */
	
	public int getCounter() {
		return this.counter;
	}
	
}
