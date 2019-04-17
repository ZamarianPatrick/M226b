package patrick.events;

import patrick.event.Event;
/**
 * <p>Ein Event, welcher ausgelöst wird, wenn ein Klick des Gegners empfangen wird</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ReceiveKlickEvent extends Event{

	/**
	 * Feld, auf welches der Klick gemacht wurde
	 */
	
	private int field;
	
	/**
	 * Erzeugt ein ReceiveKlickEvent
	 * 
	 * @param field Feld, auf welches der Klick gemacht wurde
	 */
	
	public ReceiveKlickEvent(int field) {
		this.field = field;
	}
	
	/**
	 * Liefert das Feld zurück, auf welches der Klick gemacht wurde
	 * 
	 * @return Feld
	 */
	
	public int getField() {
		return this.field;
	}
	
}
