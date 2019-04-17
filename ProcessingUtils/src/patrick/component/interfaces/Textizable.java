package patrick.component.interfaces;

import patrick.component.PColor;
import processing.core.PFont;
/**
 * <p>Lässt sich mit einem Text versehen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public interface Textizable {

	/**
	 * Legt den Text fest
	 * 
	 * @param text Text
	 */
	
	public void setText(String text);
	
	/**
	 * Gibt den aktuellen Text zurück
	 * 
	 * @return aktueller Text
	 */
	
	public String getText();
	
	/**
	 * Gibt die aktuelle Schriftart zurück
	 * 
	 * @return Schriftart
	 */
	
	public PFont getFont();
	
	/**
	 * Legt die Schriftart des Textes fest
	 * 
	 * @param pFont Schriftart für den Text
	 */
	
	public void setFont(PFont pFont);
	
	/**
	 * Legt die Farbe für den Text fest
	 * 
	 * @param textColor Schriftfarbe
	 */
	
	public void setFontColor(PColor textColor);
	
	/**
	 * Gibt die aktuelle Schriftfarbe des Textes zurück
	 * 
	 * @return Aktuelle Schriftfarbe
	 */
	
	public PColor getFontColor();
	
	
}
