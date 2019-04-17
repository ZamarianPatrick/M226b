package patrick.component;
/**
 * <p>Eine Umrahmung für ein Objekt oder Komponent</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PBorder {

	/**
	 * Farbe der Umrahmung
	 */
	
	private PColor color;
	
	/**
	 * Breite der Umrahmung in Pixel
	 */
	
	private int size;
	
	/**
	 * Erzeugt eine Umrahmung mit der gegebenen Farbe und Breite
	 * 
	 * @param color Farbe der Umrahmung
	 * @param size Breite der Umrahmung in Pixel
	 */
	
	public PBorder(PColor color, int size) {
		this.color = color;
		this.size = size;
	}
	
	/**
	 * Erzeugt eine Umrahmung mit der gegebenen Farbe und Breite
	 * 
	 * @param color Grauwert der Farbe
	 * @param size Breite der Umrahmung in Pixel
	 */
	
	public PBorder(int color, int size) {
		this.color = new PColor(color);
		this.size = size;
	}
	
	/**
	 * Erzeugt eine Umrahmung mit der gegebenen Farbe und Breite
	 * 
	 * @param red Rotwert der Farbe
	 * @param green Grünwert der Farbe
	 * @param blue Blauwert der Farbe
	 * @param size Breite der Umrahmung in Pixel
	 */
	
	public PBorder(int red, int green, int blue, int size) {
		this.color = new PColor(red, green, blue);
		this.size = size;
	}
	
	/**
	 * Erzeugt eine Umrahmung mit der gegebenen Farbe und Breite
	 * 
	 * @param red Rotwert der Farbe
	 * @param green Grünwert der Farbe
	 * @param blue Blauwert der Farbe
	 * @param alpha Alphawert der Farbe
	 * @param size Breite der Umrahmung in Pixel
	 */
	
	public PBorder(int red, int green, int blue, int alpha, int size) {
		this.color = new PColor(red, green, blue, alpha);
		this.size = size;
	}
	
	/**
	 * Gibt die Farbe der Umrahmung zurück
	 * 
	 * @return Farbe der Umrahmung
	 */
	
	public PColor getColor() {
		return color;
	}
	
	/**
	 * Definiert die Farbe der Umrahmung
	 * 
	 * @param color neue Farbe
	 */

	public void setColor(PColor color) {
		this.color = color;
	}
	
	/**
	 * Liefert die Breite der Umrahmung in Pixel zurück
	 * 
	 * @return Breite in Pixel
	 */

	public int getSize() {
		return size;
	}
	
	/**
	 * Definiert die Breite der Umrahmung in Pixel
	 * 
	 * @param size Breite in Pixel
	 */

	public void setSize(int size) {
		this.size = size;
	}
	
}
