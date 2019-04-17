package patrick.component.components.game;

import processing.core.PGraphics;
/**
 * Ein GameObject, welches einem PGameContainer hinzugefügt werden kann
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public abstract class GameObject {

	/**
	 * Position des GameObject mit x und y Achsenwert.
	 */
	
	private float x, y;
	
	/**
	 * 
	 * @param x die Position auf der X-Achse des GameObject
	 * @param y die Position auf der X-Achse des GameObject
	 * @param width die Breite des GameObject
	 * @param height die Höhe des GameObject
	 */
	
	public GameObject(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * <p>Ändert die Position des GameObject</p>
	 * 
	 * @param x neue Position auf der X-Achse des GameObject
	 * @param y neue Position auf der Y-Achse des GameObject
	 */
	
	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}
		
	/**
	 * 
	 * @return Die Position auf der X-Achse
	 */
	
	public float getX() {
		return this.x;
	}
	
	/**
	 * 
	 * @return Die Position auf der Y-Achse
	 */
	
	public float getY() {
		return this.y;
	}
	
	/**
	 * <p>Diese Methode soll überschrieben werden, damit der PGameContainer das
	 * GameObject auf sich selbst zeichnen kann.</p>
	 * 
	 * @param pg Die Grafik, welche auf dem PGameContainer gezeichnet wird.
	 */
	public abstract void display(PGraphics pg);
	
}
