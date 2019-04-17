package patrick.component;

import java.awt.Color;
/**
 * <p>Farben mit Rot-, Grün-, Blau- und Alphawerten</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PColor {

	/**
	 * Farbenwerte
	 */
	
	public int red, green, blue, alpha;
	
	/**
	 * Erzeugt eine neue Farbe
	 * 
	 * @param red Rotwert der Farbe
	 * @param green Grünwert der Farbe
	 * @param blue Blauwert der Farbe
	 * @param alpha Alphawert der Farbe
	 */
	
	public PColor(int red, int green, int blue, int alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}
	
	/**
	 * Erzeugt eine neue Farbe
	 * 
	 * @param grayValue Grauwert der Farbe
	 */
	
	public PColor(int grayValue) {
		this.red = grayValue;
		this.green = grayValue;
		this.blue = grayValue;
		this.alpha = 255;
	}
	
	/**
	 * Erzeugt eine neue Farbe
	 * 
	 * @param color Farbenwerte für die neue Farbe
	 */
	
	public PColor(Color color) {
		this.red = color.getRed();
		this.green = color.getGreen();
		this.blue = color.getBlue();
		this.alpha = color.getAlpha();
	}
	
	/**
	 * Erzeugt eine neue Farbe
	 * 
	 * @param red Rotwert der Farbe
	 * @param green Grünwert der Farbe
	 * @param blue Blauwert der Farbe
	 */
	
	public PColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = 255;
	}
	
	/**
	 * Erzeugt eine neue Farbe
	 * 
	 * @param grayValue Grauwert der Farbe
	 * @param alpha Alphawert der Farbe
	 */
	
	public PColor(int grayValue, int alpha) {
		this.red = grayValue;
		this.green = grayValue;
		this.blue = grayValue;
		this.alpha = alpha;
	}
	
	/**
	 * Macht die Farbe dunkler, sofern die Farbe verdunkelt werden kann
	 */
	
	public void darker() {
		Color color = translateToColor();
		Color darker = color.darker();
		this.red = darker.getRed();
		this.green = darker.getGreen();
		this.blue = darker.getBlue();
		this.alpha = darker.getAlpha();
	}
	
	/**
	 * Macht die Farbe heller, sofern die Farbe erhellt werden kann
	 */
	
	public void brighter() {
		Color color = translateToColor();
		Color brighter = color.brighter();
		this.red = brighter.getRed();
		this.green = brighter.getGreen();
		this.blue = brighter.getBlue();
		this.alpha = brighter.getAlpha();
	}
	
	/**
	 * Liefert eine dunklere Farbe zurück, sofern die Farbe verdunkelt werden kann
	 * 
	 * @return dunklere Farbe
	 */
	
	public PColor getDarker() {
		PColor color = new PColor(this.red, this.green, this.blue, this.alpha);
		color.darker();
		return color;
	}
	
	/**
	 * Liefert eine hellere Farbe zurück, sofern die Farbe erhellt werden kann
	 * 
	 * @return hellere Farbe
	 */
	
	public PColor getBrighter() {
		PColor color = new PColor(this.red, this.green, this.blue, this.alpha);
		color.brighter();
		return color;
	}
	
	/**
	 * Gibt die Farbe zurück als java.awt.Color
	 * 
	 * @return Farbe
	 */
	
	public Color translateToColor(){
		return new Color(red,green,blue,alpha);
	}
	
	/**
	 * Gibt den Rotwert der Farbe zurück
	 * 
	 * @return Rotwert
	 */

	public int getRed() {
		return red;
	}
	
	/**
	 * Legt den Rotwert fest
	 * 
	 * @param red Rotwert
	 */

	public void setRed(int red) {
		this.red = red;
	}
	
	/**
	 * Gibt den Grünwert der Farbe zurück
	 * 
	 * @return Grünwert
	 */

	public int getGreen() {
		return green;
	}
	
	/**
	 * Legt den Grünwert fest
	 * 
	 * @param green Grünwert
	 */

	public void setGreen(int green) {
		this.green = green;
	}
	
	/**
	 * Gibt den Blauwert der Farbe zurück
	 * 
	 * @return Blauwert
	 */

	public int getBlue() {
		return blue;
	}
	
	/**
	 * Legt den Blauwert fest
	 * 
	 * @param blue Blauwert
	 */

	public void setBlue(int blue) {
		this.blue = blue;
	}
	
	/**
	 * Gibt den Alphawert der Farbe zurück
	 * 
	 * @return Alphawert
	 */

	public int getAlpha() {
		return alpha;
	}
	
	/**
	 * Legt den Alphawert fest
	 * 
	 * @param alpha Alphawert
	 */

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}
	
}
