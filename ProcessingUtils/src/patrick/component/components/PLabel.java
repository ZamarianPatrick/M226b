package patrick.component.components;

import java.awt.Point;

import patrick.component.PBorder;
import patrick.component.PColor;
import patrick.component.interfaces.Borderizable;
import patrick.component.interfaces.Colorizable;
import patrick.component.interfaces.RoundCorner;
import patrick.component.interfaces.Textizable;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
/**
 * <p>Ein PComponent, um ein Label mit einem Text zu erzeugen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PLabel extends PComponent
		implements 
			Colorizable,
			Textizable,
			Borderizable,
			RoundCorner
				{

	/**
	 * Text, welcher sich auf dem PLabel befindet
	 */
	
	private String text;
	
	/**
	 * Hintergrundsfarbe des PLabel
	 */
	
	private PColor color;
	
	/**
	 * Schriftfarbe des Textes, welcher auf dem PLabel angezeigt wird
	 */
	
	private PColor textColor;
	
	/**
	 * Umrandung um das PLabel herum
	 */
	
	private PBorder border;
	
	/**
	 * Schriftart für den Text, welcher auf dem PLabel angezeigt wird
	 */
	
	private PFont font;
	
	/**
	 * Zustand, ob sich das PLabel automatisch in der Breite vergrössern soll
	 * wenn der Text zu gross ist.
	 */
	
	private boolean autoWidth;
	
	/**
	 * Textausrichtung auf der X-Achse
	 */
	
	private int textAlignX;
	
	/**
	 * Textausrichtung auf der Y-Achse
	 */
	
	private int textAlignY;
	
	/**
	 * Wert für abgerundete Ecken des PContainer
	 */
	
	private int cornerValue;
	
	/**
	 * Erstellt einPLabel ohne Text
	 * 
	 * <p>Standardwerte:
	 * <ul>
	 * <li>Der Text ist ein leerer String</li>
	 * <li>Die Schriftfarbe ist schwarz</li>
	 * <li>Die automatische Vergrösserung in der Breite ist deaktiviert</li>
	 * <li>Die Textausrichtung auf der X-Achse ist CENTER</li>
	 * <li>Die Textausrichtung auf der Y-Achse ist CENTER</li>
	 * <li>Die Ecken sind nicht abgerundet</li>
	 * </ul>
	 */
	
	public PLabel() {
		this.text = "";
		this.textColor = new PColor(0);
		this.autoWidth = false;
		this.textAlignX = PApplet.CENTER;
		this.textAlignY = PApplet.CENTER;
		this.cornerValue = 0;
	}
	
	/**
	 * Zeichnet das PLabel auf der Parent PGraphics
	 * Diese Methode wird vom PRootContainer oder vom jeweiligen PContainer aufgerufen,
	 * auf welcher sich das PLabel befindet. Diese Methode soll nicht andersweitig
	 * aufgerufen werden.
	 * 
	 * @param pg Parent PGraphics auf welcher sich das PLabel zeichnen soll
	 */
	
	@Override
	protected void draw(PGraphics pg) {
		if(font == null) {
			font = rootContainer.getPApplet().createFont("Arial", 32);
		}
		pg.textFont(font);
		if(autoWidth) {
			this.width = (int) pg.textWidth(text);
		}
		if(color != null) {
			pg.rect(x, y, width, height, this.cornerValue);
		}
		pg.fill(textColor.red, textColor.green, textColor.blue, textColor.alpha);
		pg.textAlign(textAlignX, textAlignY);
		int y = this.y;
		if(this.border != null) {
			y -= border.getSize() / 2;
		}
		pg.text(text, this.x, y, this.width, this.height-(this.y-y));
	}
	
	/**
	 * @see patrick.component.components.PComponent#isInside(java.awt.Point)
	 */

	@Override
	public boolean isInside(Point point) {
		return false;
	}
	
	/**
	 * Setzt den Text auf dem PLabel
	 * 
	 * @param text neuer Text auf dem PLabel
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
	 * Gibt den aktuellen Zustand der automatischen Vergrösserung in der Breite zurück
	 * 
	 * @return Zustand der automatischen Vergrösserung in der Breite
	 */
	
	public boolean getAutoWidth() {
		return this.autoWidth;
	}
	
	/**
	 * Setzt den Zustand der automatischen Vergrösserung in der Breite
	 * 
	 * @param autoWidth neuer Zustand der automatischen Vergrösserung
	 */
	
	public void setAutoWidth(boolean autoWidth) {
		this.autoWidth = autoWidth;
	}
	
	/**
	 * Gibt die aktuelle Textausrichtung auf der X-Achse zurück
	 * 
	 * @return Aktuelle Textausrichtung auf der X-Achse
	 */
	
	public int getTextAlignX() {
		return this.textAlignX;
	}
	
	/**
	 * Setzt die Textausrichtung auf der X-Achse
	 * Benutze dazu die PApplet Konstanten CENTER, LEFT oder RIGHT
	 * 
	 * @param textAlignX Textausrichtung auf der X-Achse
	 */
	
	public void setTextAlignX(int textAlignX) {
		this.textAlignX = textAlignX;
	}
	
	/**
	 * Gibt die aktuelle Textausrichtung auf der Y-Achse zurück
	 * 
	 * @return Aktuelle Textausrichtung auf der Y-Achse
	 */
	
	public int getTextAlignY() {
		return this.textAlignY;
	}
	
	/**
	 * Setzt die Textausrichtung auf der Y-Achse
	 * Benutze dazu die PApplet Konstanten CENTER, LEFT oder RIGHT
	 * 
	 * @param textAlignY Textausrichtung auf der Y-Achse
	 */
	
	public void setTextAlignY(int textAlignY) {
		this.textAlignY = textAlignY;
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
