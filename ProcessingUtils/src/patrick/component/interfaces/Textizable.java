package patrick.component.interfaces;

import patrick.component.PColor;
import processing.core.PFont;
/**
 * <p>L�sst sich mit einem Text versehen</p>
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
	 * Gibt den aktuellen Text zur�ck
	 * 
	 * @return aktueller Text
	 */
	
	public String getText();
	
	/**
	 * Gibt die aktuelle Schriftart zur�ck
	 * 
	 * @return Schriftart
	 */
	
	public PFont getFont();
	
	/**
	 * Legt die Schriftart des Textes fest
	 * 
	 * @param pFont Schriftart f�r den Text
	 */
	
	public void setFont(PFont pFont);
	
	/**
	 * Legt die Farbe f�r den Text fest
	 * 
	 * @param textColor Schriftfarbe
	 */
	
	public void setFontColor(PColor textColor);
	
	/**
	 * Gibt die aktuelle Schriftfarbe des Textes zur�ck
	 * 
	 * @return Aktuelle Schriftfarbe
	 */
	
	public PColor getFontColor();
	
	
}
