package patrick.component.interfaces;

import patrick.component.PColor;
/**
 * <p>L�sst sich f�rben</p>
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
	 * Legt die neue Farbe �ber den Grauwert fest
	 * Alpha-Wert ist 255
	 * 
	 * @param color Grauwert der neuen Farbe
	 */
	
	public void setColor(int color);
	
	/**
	 * Legt die neue Farbe �ber den Rot-, Gr�n- und Blauwert fest
	 * 
	 * @param red Rotwert der neuen Farbe
	 * @param green Gr�nwert der neuen Farbe
	 * @param blue Blauwert der neuen Farbe
	 */
	
	public void setColor(int red, int green, int blue);
	
	/**
	 * Legt die neue Farbe �ber den Rot-, Gr�n-, Blau- und Alphawert fest
	 * 
	 * @param red Rotwert der neuen Farbe
	 * @param green Gr�nwert der neuen Farbe
	 * @param blue Blauwert der neuen Farbe
	 * @param alpha Alphawert der neuen Farbe
	 */
	
	public void setColor(int red, int green, int blue, int alpha);
	
	/**
	 * Legt die neue Farbe �ber den Grau- und Alphawert fest
	 * 
	 * @param color Grauwert der neuen Farbe
	 * @param alpha Alphawert der neuen Farbe
	 */
	
	public void setColor(int color, int alpha);
	
	/**
	 * Gibt die aktuelle Farbe zur�ck
	 * 
	 * @return Aktuelle Farbe
	 */
	
	public PColor getColor();
	
}
