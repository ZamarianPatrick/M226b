package patrick.component.components;

import patrick.component.PBorder;
import patrick.component.PColor;
import patrick.component.interfaces.Borderizable;
import patrick.component.interfaces.Colorizable;
import patrick.component.interfaces.InvokeAtDrawEnd;
import patrick.component.interfaces.RoundCorner;
import patrick.component.interfaces.Textizable;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
/**
 * <p>Ein PopupField, welches den PComponents zugewiesen werden kann, welche
 * das Popupizable Interface implementieren</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PopupField 
		implements 
			Colorizable,
			Borderizable,
			Textizable,
			RoundCorner
				{

	/**
	 * Grösse des PopupField
	 */
	
	private int width, height;
	
	/**
	 * Umrandung des PopupField
	 */
	
	private PBorder border;
	
	/**
	 * Hintergrundsfarbe des PopupField
	 */
	
	private PColor color;
	
	/**
	 * Text auf dem PopupField
	 */
	
	private String text;
	
	/**
	 * Schriftfarbe des Textes auf dem PopupField
	 */
	
	private PColor textColor;
	
	/**
	 * Schrifart des Textes auf dem PopupField
	 */
	
	private PFont font;
	
	/**
	 * Wert für die abgerundeten Ecken des PopupField
	 */
	
	private int cornerValue;
	
	/**
	 * Erzeugt ein PopupField mit Text
	 * 
	 * <p>Standardwerte:
	 * <ul>
	 * <li>Keine Hintergrundsfarbe</li>
	 * <li>Keine TextFarbe</li>
	 * <li>Schriftart ist keine zugewiesen</li>
	 * <li>Die Ecken sind nicht abgerundet</li>
	 * </ul>
	 * 
	 * @param text Text, welcher im PopupField angezeigt werden soll
	 * @param width Breite des PopupField
	 * @param height Höhe des PopupField 
	 */
	
	public PopupField(String text, int width, int height){
		this.width = width;
		this.height = height;
		this.color = null;
		this.text = text;
		this.textColor = null;
		this.font = null;
		this.cornerValue = 0;
	}
	
	/**
	 * Diese Methode wird jeweils aufgerufen, wenn sich der Mauszeiger über dem PComponent 
	 * befindet, welchem das PopupField zugewiesen wurde.
	 * 
	 * Diese Methode soll nicht andersweitig aufgerufen werden
	 * 
	 * @param rootContainer Der PRootContainer, auf welchem sich das PopupField zeichnen soll
	 * @param x Position auf der X-Achse
	 * @param y Position auf der Y-Achse
	 * @param pFont Schriftart, welche verwendet werden soll wenn keine zugewiesen ist
	 */
	
	protected void draw(PRootContainer rootContainer, int x, int y, PFont pFont) {
		InvokeAtDrawEnd atEnd = new InvokeAtDrawEnd() {
			@Override
			public void atDrawEnd(PGraphics pg) {
				pg.noStroke();
				pg.noFill();
				if(color != null) {
					pg.fill(color.red, color.green, color.blue, color.alpha);
				}
				if(border != null) {
					pg.stroke(border.getColor().red, 
							border.getColor().green, 
							border.getColor().blue, 
							border.getColor().alpha);
					pg.strokeWeight(border.getSize());
				}
				
				pg.rect(x, y, width, height, cornerValue);
				
				if(font != null) {
					pg.textFont(font);
				}else {
					pg.textFont(pFont);
				}
				if(textColor != null) {
					pg.fill(textColor.red, textColor.green, textColor.blue, textColor.alpha);
				}
				pg.textAlign(PApplet.CENTER, PApplet.CENTER);
				pg.text(text, x, y, width, height);
				pg.noStroke();
				pg.noFill();
			}
		};
		rootContainer.addInvokeAtEnd(atEnd);
	}
	
	/**
	 * @see patrick.component.interfaces.Borderizable#setBorder(patrick.component.PBorder)
	 */

	@Override
	public void setBorder(PBorder border) {
		this.border = border;
	}
	
	/**
	 * @see patrick.component.interfaces.Borderizable#getBorder()
	 */

	@Override
	public PBorder getBorder() {
		return this.border;
	}
	
	/**
	 * @see patrick.component.interfaces.Colorizable#setColor(patrick.component.PColor)
	 */

	@Override
	public void setColor(PColor color) {
		this.color = color;
	}
	
	/**
	 * @see patrick.component.interfaces.Colorizable#setColor(int)
	 */

	@Override
	public void setColor(int color) {
		this.color = new PColor(color);
	}
	
	/**
	 * @see patrick.component.interfaces.Colorizable#setColor(int, int, int)
	 */

	@Override
	public void setColor(int red, int green, int blue) {
		this.color = new PColor(red, green, blue);
	}
	
	/**
	 * @see patrick.component.interfaces.Colorizable#setColor(int, int, int, int)
	 */

	@Override
	public void setColor(int red, int green, int blue, int alpha) {
		this.color = new PColor(red, green, blue, alpha);
	}
	
	/**
	 * @see patrick.component.interfaces.Colorizable#setColor(int, int)
	 */

	@Override
	public void setColor(int color, int alpha) {
		this.color = new PColor(color, alpha);
	}
	
	/**
	 * @see patrick.component.interfaces.Colorizable#getColor()
	 */

	@Override
	public PColor getColor() {
		return this.color;
	}
	
	/**
	 * @see patrick.component.interfaces.Textizable#setText(java.lang.String)
	 */

	@Override
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * @see patrick.component.interfaces.Textizable#getText()
	 */

	@Override
	public String getText() {
		return this.text;
	}
	
	/**
	 * @see patrick.component.interfaces.Textizable#getFont()
	 */

	@Override
	public PFont getFont() {
		return this.font;
	}
	
	/**
	 * @see patrick.component.interfaces.Textizable#setFont(processing.core.PFont)
	 */

	@Override
	public void setFont(PFont pFont) {
		this.font = pFont;
	}
	
	/**
	 * @see patrick.component.interfaces.Textizable#setFontColor(patrick.component.PColor)
	 */

	@Override
	public void setFontColor(PColor textColor) {
		this.textColor = textColor;
	}
	
	/**
	 * @see patrick.component.interfaces.Textizable#getFontColor()
	 */

	@Override
	public PColor getFontColor() {
		return this.textColor;
	}
	
	/**
	 * @see patrick.component.interfaces.RoundCorner#setCorner(int)
	 */

	@Override
	public void setCorner(int cornerValue) {
		this.cornerValue = cornerValue;
	}
	
	/**
	 * @see patrick.component.interfaces.RoundCorner#getCorner()
	 */

	@Override
	public int getCorner() {
		return this.cornerValue;
	}	
}
