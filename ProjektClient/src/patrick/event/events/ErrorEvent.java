package patrick.event.events;

import patrick.event.ErrorType;
import patrick.event.Event;
/**
 * <p>Ein Event, welcher ausgel�st wird, wenn ein Fehler auftritt</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ErrorEvent extends Event{

	/**
	 * Fehlertyp, welcher aufgetreten ist
	 */
	private ErrorType error;
	
	/**
	 * Erzeugt einen neuen ErrorEvent
	 * 
	 * @param error Fehlertyp, welcher aufgetreten ist
	 */
	
	public ErrorEvent(ErrorType error) {
		this.error = error;
	}
	
	/**
	 * Liefert den Fehlertyp zur�ck, welcher aufgetreten ist
	 * 
	 * @return Fehlertyp, welcher aufgetreten ist
	 */
	
	public ErrorType getError() {
		return this.error;
	}
	
}
