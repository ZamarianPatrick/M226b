package patrick.component.components;

import java.awt.Point;

import patrick.component.PBorder;
import patrick.component.PColor;
import patrick.component.action.KeyTypedListener;
import patrick.component.action.PressEnterListener;
import patrick.component.enums.Shape;
import patrick.component.interfaces.Borderizable;
import patrick.component.interfaces.Colorizable;
import patrick.component.interfaces.Textizable;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.event.KeyEvent;
/**
 * <p>Ein PComponent, um Texteingaben des Users zu ermöglichen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PTextField extends PComponent 
		implements 
			Colorizable,
			Borderizable,
			Textizable
	{

	/**
	 * Hintergrundfarbe des PTextField
	 */
	
	private PColor color;
	
	/**
	 * Umrandung des PTextField
	 */
	
	private PBorder border;
	
	/**
	 * Text, welcher eingegeben ist
	 */
	
	private String text;
	
	/**
	 * Schriftart des Textes
	 */
	
	private PFont font;
	
	/**
	 * Schriftfarbe des Textes
	 */
	
	private PColor textColor;
	
	/**
	 * Verzögerung für das '|' Zeichen
	 */
	
	private int delay;
	
	/**
	 * Maximale Länge an Text welchen man eingeben kann
	 */
	
	private int maxLength;
	
	/**
	 * Zustand ob Zahlen erlaubt sind
	 */
	
	private boolean allowDigit;
	
	/**
	 * Zustand ob Buchstaben erlaubt sind
	 */
	
	private boolean allowLetters;
	
	/**
	 * Zustand ob Leerzeichen erlaubt sind
	 */
	
	private boolean allowSpace;
	
	/**
	 * Zustand ob sonstige Zeichen erlaubt sind
	 */
	
	private boolean allowOther;
	
	/**
	 * PressEnterListener, welcher ausgeführt wird beim drücken von Enter
	 */
	
	private PressEnterListener pressEnterListener;
	
	/**
	 * Erzeugt ein neues TextFeld ohne Text
	 */
	
	public PTextField() {
		color = new PColor(240);
		border = new PBorder(0, 1);
		cursor = PApplet.TEXT;
		delay = 15;
		text = "";
		maxLength = 10;
		width = 200;
		allowDigit = true;
		allowLetters = true;
		allowSpace = true;
		allowOther = false;
		textColor = new PColor(0);
		pressEnterListener = null;
		this.addActionListener(new KeyTypedListener() {
			@Override
			public void onKeyTyped(KeyEvent e) {
				if(e.getKey() == '\b') {
					if(text.length() > 0) {
						text = text.substring(0, text.length()-1);
					}
					return;
				}
				if(e.getKey() == '\n') {
					if(pressEnterListener != null) {
						pressEnterListener.onPressEnter();
					}
					return;
				}
				if(maxLength <= text.length()) {
					return;
				}
				if(allowOther) {
					text += e.getKey();
				}
				else if(Character.isLetter(e.getKey())) {
					if(allowLetters) {
						text += e.getKey();	
					}
				}else if(e.getKey() == ' ') {
					if(allowSpace) {
						text += " ";
					}
				}else if(Character.isDigit(e.getKey())) {
					if(allowDigit) {
						text += e.getKey();
					}
				}
			}
		});
	}
	
	/**
	 * Methode, welche vom jeweilig übergeordneten PComponent aufgerufen wird, wenn
	 * sich das PTextField zeichnen soll. Diese Methode kann überschrieben werden.
	 * Um die jeweiligen Standard Funktionen und Grafiken des PTextField beizubehalten
	 * sollte in der überschriebenen Methode die Methode drawIt(PGraphics pgParent) aufgerufen
	 * werden.
	 * 
	 * @param pg Grafik, auf welcher sich das PTextField zeichnen wird
	 */
	
	@Override
	protected void draw(PGraphics pg) {
		drawIt(pg, text);
	}
	
	/**
	 * Methode um das PTextField zu zeichnen und seine Funktionen auszuführen
	 * 
	 * @param pg Grafik, auf welcher sich das PTextField zeichnen soll
	 * @param displayText Text, welcher angezeigt werden soll
	 */
	
	protected void drawIt(PGraphics pg, String displayText) {
		if(font == null) {
			font = rootContainer.getPApplet().createFont("Arial", 32);
		}
		height = (int) (font.getSize() * 1.5F);
		pg.rect(this.x, this.y, this.width, height);
		pg.fill(textColor.red, textColor.green, textColor.green, textColor.alpha);
		pg.textAlign(PApplet.LEFT, PApplet.CENTER);
		pg.textFont(font);
		int y = this.y;
		int x = this.x;
		if(this.border != null) {
			y -= border.getSize() / 2;
			x += border.getSize() + 5;
		}
		pg.text(displayText, x, y, this.width-(this.x-x), (font.getSize() * 1.3F));
		if(hasFocus()) {
			if(delay>0) {
				pg.stroke(0);
				pg.strokeWeight(2);
				pg.line(pg.textWidth(displayText)+x+5,
						y+(font.getSize() * 1.3F) - (this.y-y),
						pg.textWidth(displayText)+x+5,
						y+5);
			}else if(delay < -30) {
				delay = 30;
			}
			delay--;
		}else {
			delay = 30;
		}
	}
	
	/**
	 * Legt die Grösse des PTextField fest.
	 * Die Höhe kann nicht festgelegt werden, diese berechnet sich automatisch anhand
	 * der Schriftgrösse. Deswegen sollte man lieber die Methode setSize(int width)
	 * benutzen.
	 * 
	 * @param width Breite des PTextField
	 * @param nothing hat keine Auswirkung
	 */

	@Deprecated
	@Override
	public void setSize(int width, int nothing) {
		this.width = width;
	}
	
	/**
	 * Legt die Breite des PTextField fest.
	 * 
	 * @param width neue Breite
	 */
	
	public void setSize(int width) {
		this.width = width;
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
		return text;
	}
	
	/**
	 * @see patrick.component.interfaces.Textizable#getFont()
	 */

	@Override
	public PFont getFont() {
		return font;
	}
	
	/**
	 * @see patrick.component.interfaces.Textizable#setFont(processing.core.PFont)
	 */

	@Override
	public void setFont(PFont font) {
		this.font = font;
	}
	
	/**
	 * @see patrick.component.components.PComponent#isInside(java.awt.Point)
	 */
	
	@Override
	public boolean isInside(Point point) {
		return isInside(point, Shape.RECT);
	}
	
	/**
	 * Legt die maximale Anzahl Zeichen fest, welche eingegeben werden können
	 * 
	 * @param maxLength maximale Anzahl Zeichen
	 */
	
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
	/**
	 * Gibt die aktuelle Anzahl an maximalen Zeichen zurück, welche eingegeben werden können
	 * 
	 * @return Aktuelle Anzahl an maximalen Zeichen
	 */
	
	public int getMaxLength() {
		return this.maxLength;
	}
	
	/**
	 * Gibt den Wahrheitswert zurück, ob man Buchstaben schreiben kann
	 * 
	 * @return Wahrheitswert ob Buchstaben erlaubt sind
	 */
	
	public boolean allowLetters() {
		return this.allowLetters;
	}
	
	/**
	 * Gibt den Wahrheitswert zurück, ob man Zahlen schreiben kann
	 * 
	 * @return Wahrheitswert ob Zahlen erlaubt sind
	 */
	
	public boolean allowDigit() {
		return this.allowDigit;
	}
	
	/**
	 * Gibt den Wahrheitswert zurück, ob man sonstige Zeichen schreiben kann
	 * 
	 * @return Wahrheitswert ob sonstige Zeichen erlaubt sind
	 */
	
	public boolean allowOthers() {
		return this.allowOther;
	}
	
	/**
	 * Gibt den Wahrheitswert zurück, ob man Leerzeichen schreiben kann
	 * 
	 * @return Wahrheitswert ob Leerzeichen erlaubt sind
	 */
	
	public boolean allowSpace() {
		return this.allowSpace;
	}
	
	/**
	 * Legt fest, ob Buchstaben zu schreiben erlaubt sein soll
	 * 
	 * @param letters Wahrheitswert ob Buchstaben erlaubt werden sollen
	 */
	
	public void setAllowLetters(boolean letters) {
		this.allowLetters = letters;
	}
	
	/**
	 * Legt fest, ob Zahlen zu schreiben erlaubt sein soll
	 * 
	 * @param digit Wahrheitswert ob Zahlen erlaubt werden sollen
	 */
	
	public void setAllowDigit(boolean digit) {
		this.allowDigit = digit;
	}
	
	/**
	 * Legt fest, ob Leerzeichen zu schreiben erlaubt sein soll
	 * 
	 * @param space Wahrheitswert ob Leerzeichen erlaubt werden sollen
	 */
	
	public void setAllowSpace(boolean space) {
		this.allowSpace = space;
	}
	
	/**
	 * Legt fest, ob sonstige Zeichen zu schreiben erlaubt sein soll
	 * 
	 * @param others Wahrheitswert ob sonstige Zeichen erlaubt werden sollen
	 */
	
	public void setAllowOthers(boolean others) {
		this.allowOther = others;
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
	 * Legt ein PressEnterListener fest, welcher ausgelöst wird, beim drücken von Enter
	 * wenn das PTextField fokusiert ist.
	 * 
	 * @param listener PressEnterListener, welcher ausgelöst werden soll
	 */
	
	public void setPressEnterListener(PressEnterListener listener) {
		this.pressEnterListener = listener;
	}
	
}
