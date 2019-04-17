package patrick.component.interfaces;

import patrick.component.PColor;
/**
 * <p>Lässt sich färben</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public interface Colorizable {

	/**
	 * Legt die Farbe fest
	 * 
	 * @param color neue Farbe
	 */
	
	public void setColor(PColor color);
	
	/**
	 * Legt die neue Farbe über den Grauwert fest
	 * Alpha-Wert ist 255
	 * 
	 * @param color Grauwert der neuen Farbe
	 */
	
	public void setColor(int color);
	
	/**
	 * Legt die neue Farbe über den Rot-, Grün- und Blauwert fest
	 * 
	 * @param red Rotwert der neuen Farbe
	 * @param green Grünwert der neuen Farbe
	 * @param blue Blauwert der neuen Farbe
	 */
	
	public void setColor(int red, int green, int blue);
	
	/**
	 * Legt die neue Farbe über den Rot-, Grün-, Blau- und Alphawert fest
	 * 
	 * @param red Rotwert der neuen Farbe
	 * @param green Grünwert der neuen Farbe
	 * @param blue Blauwert der neuen Farbe
	 * @param alpha Alphawert der neuen Farbe
	 */
	
	public void setColor(int red, int green, int blue, int alpha);
	
	/**
	 * Legt die neue Farbe über den Grau- und Alphawert fest
	 * 
	 * @param color Grauwert der neuen Farbe
	 * @param alpha Alphawert der neuen Farbe
	 */
	
	public void setColor(int color, int alpha);
	
	/**
	 * Gibt die aktuelle Farbe zurück
	 * 
	 * @return Aktuelle Farbe
	 */
	
	public PColor getColor();
	
}
