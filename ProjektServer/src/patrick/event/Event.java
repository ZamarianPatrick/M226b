package patrick.event;
/**
 * <p>�berklasse f�r alle Events und Ereignisse, welche Serverbasiert geschehen k�nnen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public abstract class Event {

	/**
	 * Name des Events
	 */
	
	protected String eventName;
	
	/**
	 * Erzeugt ein Event
	 */
	
	public Event() {
		eventName = this.getClass().getSimpleName();
	}
	
	/**
	 * Liefert den Namen des Events zur�ck
	 * 
	 * @return Name des Events
	 */

	public String getEventName() {
		return this.eventName;
	}
	
}
