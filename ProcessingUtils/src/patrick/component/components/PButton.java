package patrick.component.components;

import java.awt.Point;

import patrick.component.PBorder;
import patrick.component.PColor;
import patrick.component.enums.ColorMode;
import patrick.component.enums.Shape;
import patrick.component.interfaces.Borderizable;
import patrick.component.interfaces.Colorizable;
import patrick.component.interfaces.HoverColorizable;
import patrick.component.interfaces.Popupizable;
import patrick.component.interfaces.PressColorizable;
import patrick.component.interfaces.RoundCorner;
import patrick.component.interfaces.Textizable;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.event.MouseEvent;
/**
 * <p>Ein PComponent, um Klicks des Users sinnvoll zu verwerten können</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PButton extends PComponent
	implements
		Colorizable,
		Borderizable,
		Textizable,
		HoverColorizable,
		PressColorizable,
		Popupizable,
		RoundCorner
		{

	/**
	 * Der Text, welcher auf dem PButton angezeigt wird.
	 */
	private String text;
	/**
	 * Die Schriftart, mit welcher der Text auf dem PButton angezeigt wird.
	 */
	private PFont font;
	/**
	 * Die Farbe, mit welcher der PButton gezeichnet wird.
	 */
	private PColor color;
	/**
	 * Der Border, welcher um den PButton herum gezeichnet wird.
	 */
	private PBorder border;
	/**
	 * Der ColorMode, welcher die Farbänderung des PButtons beim drüber fahren mit der Maus
	 * festlegt.
	 */
	private ColorMode hoverColorMode;
	/**
	 * Die Farbe, zu welcher sich der PButton beim drüber fahren mit der Maus ändert.
	 * Diese Farbe findet nur Anwendung wenn der ColorMode = ColorMode.CUSTOM ist.
	 */
	private PColor hoverColor;
	/**
	 * Der ColorMode, welcher die Farbänderung des PButtons beim anklicken mit der Maus
	 * festlegt.
	 */
	private ColorMode pressColorMode;
	/**
	 * Die Farbe, zu welcher sich der PButton beim anklicken mit der Maus ändert.
	 * Diese Farbe findet nur Anwendung wenn der ColorMode = ColorMode.CUSTOM ist.
	 */
	private PColor pressColor;
	/**
	 * Die Farbe, mit welcher der Text über dem Button gezeichnet wird
	 */
	private PColor textColor;
	/**
	 * Das PopupField, welches beim drüber fahren mit der Maus auf dem PButton angezeigt wird
	 */
	private PopupField popupField;
	
	/**
	 * Der Wert für die abgerundeten Ecken
	 */
	private int cornerValue;
	
	/**
	 * Erstellt ein PButton ohne Text
	 * 
	 * <p>Standardwerte:
	 * <ul>
	 * <li>Der Text ist ein leerer String</li>
	 * <li>Die Schriftart ist Arial mit der Grösse 32</li>
	 * <li>Die Farbe des PButton ist rgb(180,180,180)</li>
	 * <li>Der Border ist schwarz und 3 pixel breit</li>
	 * <li>Der Cursor ist eine Hand</li>
	 * <li>hoverColorMode = ColorMode.DARKER</li>
	 * <li>pressColorMode = ColorMode.NONE</li>
	 * <li>Die Farbe des Textes ist schwart</li>
	 * <li>Die Ecken sind nicht rund</li>
	 * </ul>
	 */
	public PButton() {
		this.text = "";
		this.font = null;
		this.color = new PColor(180);
		this.border = new PBorder(new PColor(0), 3);
		this.cursor = PApplet.HAND;
		this.hoverColorMode = ColorMode.DARKER;
		this.hoverColor = this.color.getDarker();
		this.pressColorMode = ColorMode.NONE;
		this.pressColor = this.color;
		this.textColor = new PColor(0);
		this.cornerValue = 0;
	}
	
	/**
	 * Der Button zeichnet sich selbst auf der PGraphics
	 */
	
	@Override
	protected void draw(PGraphics pg) {
		if(font == null) {
			font = rootContainer.getPApplet().createFont("Arial", 32);
		}
		pg.rect(x, y, width, height, this.cornerValue);
		pg.fill(textColor.red, textColor.green, textColor.blue, textColor.alpha);
		pg.textAlign(PApplet.CENTER, PApplet.CENTER);
		pg.textFont(font);
		int y = this.y;
		if(this.border != null) {
			y -= border.getSize() / 2;
		}
		pg.text(text, this.x, y, this.width, this.height-(this.y-y));
	}

	/**
	 * @param point Point um abzufragen, ob er sich innerhalb des Buttons befindet
	 * @return Ist der Point innerhalb des PButton
	 */
	
	@Override
	public boolean isInside(Point point) {
		return isInside(point, Shape.RECT);
	}

	/**
	 * Setzt den Text des PButton
	 * 
	 * @param text Der neue Text
	 */
	
	@Override
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * @return Aktueller Text des PButton
	 */

	@Override
	public String getText() {
		return this.text;
	}
	
	/**
	 * @return Die Schriftart des Textes
	 */

	@Override
	public PFont getFont() {
		return font;
	}

	/**
	 * Setzt die Schriftart des Textes
	 * 
	 * @param pFont Die neue Schriftart
	 */
	
	@Override
	public void setFont(PFont pFont) {
		this.font = pFont;
	}

	/**
	 * Setzt die Farbe des PButton
	 * 
	 * @param color Die neue Farbe des PButton
	 */
	
	@Override
	public void setColor(PColor color) {
		this.color = color;
	}

	/**
	 * Setzt die Farbe des PButton
	 * 
	 * @param color Der Grau-Wert der neuen Farbe
	 */
	
	@Override
	public void setColor(int color) {
		this.color = new PColor(color);
	}
	
	/**
	 * Setzt die Farbe des PButton
	 * 
	 * @param red Der Rot-Wert der neuen Farbe
	 * @param green Der Grün-Wert der neuen Farbe
	 * @param blue Der Blau-Wert der neuen Farbe
	 */

	@Override
	public void setColor(int red, int green, int blue) {
		this.color = new PColor(red, green, blue);	
	}
	
	/**
	 * Setzt die Farbe des PButton
	 * 
	 * @param red Der Rot-Wert der neuen Farbe
	 * @param green Der Grün-Wert der neuen Farbe
	 * @param blue Der Blau-Wert der neuen Farbe
	 * @param alpha Der Alpha-Wert der neuen Farbe 
	 */

	@Override
	public void setColor(int red, int green, int blue, int alpha) {
		this.color = new PColor(red, green, blue, alpha);
	}

	/**
	 * Setzt die Farbe des PButton
	 * 
	 * @param color Der Grau-Wert der neuen Farbe
	 * @param alpha Der Alpha-Wert der neuen Farbe
	 */
	
	@Override
	public void setColor(int color, int alpha) {
		this.color = new PColor(color, alpha);
	}
	
	/**
	 * @return Die Farbe des PButton
	 */

	@Override
	public PColor getColor() {
		return this.color;
	}
	
	/**
	 * Setzt den Border des PButton
	 * 
	 * @param border Der neue Border des PButton
	 */

	@Override
	public void setBorder(PBorder border) {
		this.border = border;
	}

	/**
	 * @return Der Border des PButton
	 */
	
	@Override
	public PBorder getBorder() {
		return border;
	}
	
	/**
	 * @return Der ColorMode beim drüber fahren mit der Maus
	 */

	@Override
	public ColorMode getHoverColorMode() {
		return hoverColorMode;
	}

	/**
	 * Setzt den ColorMode beim drüber fahren mit der Maus
	 * 
	 * @param mode Der neue ColorMode
	 */
	
	@Override
	public void setHoverColorMode(ColorMode mode) {
		this.hoverColorMode = mode;
	}
	
	/**
	 * @return Die Farbe beim drüber fahren mit der Maus
	 */

	@Override
	public PColor getHoverColor() {
		return this.hoverColor;
	}
	
	/**
	 * Setzt die Farbe des PButton, wenn der Benutzer mit der Maus drüber fährt
	 * 
	 * <p>Bevor diese Farbe gesetzt wird, sollte der ColorMode beim Hover auf
	 * ColorMode.CUSTOM gesetzt werden. Ansonsten hat dies keine Auswirkungen.
	 * 
	 * @param customColor Die Farbe des PButton, wenn der Benutzer mit der Maus drüber fährt
	 */

	@Override
	public void setHoverColor(PColor customColor) {
		this.hoverColor = customColor;
	}
	
	/**
	 * @return Der ColorMode beim anklicken des PButton
	 */

	@Override
	public ColorMode getPressColorMode() {
		return this.pressColorMode;
	}
	
	/**
	 * Setzt den ColorMode beim anklicken des PButton
	 * 
	 * @param mode Der neue ColorMode
	 */

	@Override
	public void setPressColorMode(ColorMode mode) {
		this.pressColorMode = mode;
	}
	
	/**
	 * @return Die Farbe des PButton beim anklicken
	 */

	@Override
	public PColor getPressColor() {
		return pressColor;
	}
	
	/**
	 * Setzt die Farbe des PButton wenn er geklickt wird
	 * 
	 * <p>Bevor diese Farbe gesetzt wird, sollte der ColorMode beim Hover auf
	 * ColorMode.CUSTOM gesetzt werden. Ansonsten hat dies keine Auswirkungen.
	 * 
	 * @param customColor Die neue Farbe des PButton beim anklicken
	 */

	@Override
	public void setPressColor(PColor customColor) {
		this.pressColor = customColor;
	}
	
	/**
	 * Setzt die Textfarbe des PButton
	 * 
	 * @param textColor neue Textfarbe des PButton
	 */

	@Override
	public void setFontColor(PColor textColor) {
		this.textColor = textColor;
	}
	
	/**
	 * @return Textfarbe des PButton
	 */

	@Override
	public PColor getFontColor() {
		return textColor;
	}
	
	/**
	 * Führt einen gewöhnlichen Klick auf den PButton aus.
	 * 
	 * <p>Alle ClickListener werden beim Aufruf dieser Method ausgelöst.
	 * Der Button ändert seine Farbe kurzzeitig auf die definierte Press-Farbe.
	 */
	
	public void doClick() {
		this.mouseAction(new MouseEvent(
				null,
				50, 
				MouseEvent.CLICK, 
				0, 
				getAbsoluteX()+1, 
				getAbsoluteY()+1, 
				PApplet.LEFT, 
				1
				));
	}

	/**
	 * @return Das PopupField welches dem PButton zugewiesen ist
	 * <strong>null</strong> wenn keines zugewiesen ist
	 */
	
	@Override
	public PopupField getPopupField() {
		return this.popupField;
	}
	
	/**
	 * Setzt das PopupField des PButton
	 * 
	 * <p>Dies wird immer beim drüber fahren mit der Maus über dem PButton angezeigt
	 *
	 *@param popupField neues PopupField für den PButton
	 */

	@Override
	public void setPopupField(PopupField popupField) {
		this.popupField = popupField;
	}
	
	/**
	 * Setzt den Ecken-Wert für abgerundete Ecken
	 * 
	 * <p>Ist der gesetzte Wert 0, so hat der PButton keine abgerundeten Ecken.
	 * 
	 * @param cornerValue Wert für die abgerundeten Ecken
	 */

	@Override
	public void setCorner(int cornerValue) {
		this.cornerValue = cornerValue;
	}
	
	/**
	 * @return Gibt den Wert für die abgerundeten Ecken
	 */

	@Override
	public int getCorner() {
		return this.cornerValue;
	}
}
