package patrick.events;

import java.awt.Point;

import patrick.event.Event;
/**
 * <p>Ein Event, welcher ausgelöst wird, wenn eine neue Mausposition des Gegners empfangen wurde</p>
 * 
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ReceivePositionEvent extends Event{

	/**
	 * Position auf der X-Achse
	 */
	
	private int x;
	
	/**
	 * Position auf der Y-Achse
	 */
	
	private int y;
	
	/**
	 * Erzeugt ein ReceivePositionEvent
	 * 
	 * @param x Position auf der X-Achse
	 * @param y Position auf der Y-Achse
	 */
	
	public ReceivePositionEvent(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gibt die Mausposition des Gegners zurück
	 * 
	 * @return Mausposition des Gegners
	 */
	
	public Point getLocation() {
		return new Point(x,y);
	}
	
	/**
	 * Gibt die Mausposition auf der X-Achse des Gegners zurück
	 * 
	 * @return Mausposition auf der X-Achse
	 */
	
	public int getX() {
		return this.x;
	}
	
	/**
	 * Gibt die Mausposition auf der Y-Achse des Gegners zurück
	 *
	 * @return Mausposition auf der Y-Achse
	 */
	
	public int getY() {
		return this.y;
	}
	
}
