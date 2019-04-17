package patrick.event;
/**
 * <p>Eine Überklasse für alle Ereignisse</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public abstract class Event {

	/**
	 * Der Name des Events
	 */
	
	protected String eventName;
	
	/**
	 * Erzeugt einen neuen Event und setzt den Namen auf den Klassennamen des Events
	 */
	
	public Event() {
		eventName = this.getClass().getSimpleName();
	}
	
	/**
	 * Liefert den Eventnamen zurück
	 * 
	 * @return Eventname
	 */

	public String getEventName() {
		return this.eventName;
	}
	
}
