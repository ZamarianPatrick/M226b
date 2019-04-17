package patrick.component.components;

import java.awt.Point;

import patrick.component.LocationUtils;
import patrick.component.PColor;
import patrick.component.action.ClickListener;
import patrick.component.action.MoveListener;
import patrick.component.enums.Shape;
import patrick.component.interfaces.Activable;
import patrick.component.interfaces.Textizable;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.event.MouseEvent;
/**
 * <p>Ein PComponent, um eine PCheckBox zu erzeugen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PCheckBox extends PComponent
		implements 
			Textizable,
			Activable
				{
	
	/**
	 * Zustand ob die Checkbox aktiviert ist oder nicht
	 */
	
	private boolean activated;
	
	/**
	 * Text, welcher neben der CheckBox angezeigt wird
	 */
	
	private String text;
	
	/**
	 * Textfarbe f�r den Text, welcher neben der Checkbox angezeigt wird
	 */
	
	private PColor textColor;
	
	/**
	 * Schriftart f�r den Text, welcher neben der Checkbox angezeigt wird
	 */
	
	private PFont font;
	
	/**
	 * Erstellt eine PCheckBox ohne Text
	 * 
	 * <p>Standardwerte:
	 * <ul>
	 * <li>Der Text ist ein leerer String</li>
	 * <li>Die Checkbox ist deaktiviert</li>
	 * <li>Die Textfarbe ist schwarz</li>
	 * <li>Die Schriftart ist Arial mit der Gr�sse 32</li>
	 * </ul>
	 */
	
	public PCheckBox() {
		this.activated = false;
		this.text = "";
		this.textColor = new PColor(0);
		this.font = null;
		
		this.addActionListener(new MoveListener() {
			
			@Override
			public void onMouseMove(MouseEvent e) {
				if(LocationUtils.isInsideRect(
						getAbsoluteX(), 
						getAbsoluteY(), 
						height,
						height,
						new Point(e.getX(),
						e.getY()))) {
					cursor = PApplet.HAND;
				}else {
					cursor = PApplet.ARROW;
				}
				
			}
		});
		
		this.addActionListener(new ClickListener() {
			@Override
			public void onClick(MouseEvent e) {
				if(LocationUtils.isInsideRect(
						getAbsoluteX(), 
						getAbsoluteY(), 
						height,
						height,
						new Point(e.getX(),
						e.getY())) && e.getButton() == PApplet.LEFT) {
					
					setActivated(!activated);
				}
			}
		});
	}
	
	/**
	 * Zeichnet die PCheckBox auf der Parent PGraphics
	 * Diese Methode wird vom PRootContainer oder vom jeweiligen PContainer aufgerufen,
	 * auf welcher sich die PCheckBox befindet. Diese Methode soll nicht andersweitig
	 * aufgerufen werden.
	 * 
	 * @param pg Parent PGraphics
	 */

	@Override
	protected void draw(PGraphics pg) {
		if(font == null) {
			font = rootContainer.getPApplet().createFont("Arial", ((int)this.height * 0.8F));
		}
		pg.stroke(0);
		pg.strokeWeight(3);
		pg.noFill();
		if(activated == false) {
		}else {
			pg.line(x, y, x+height, y+height);
			pg.line(x+height, y, x, y+height);
		}
		pg.rect(x, y, height, height);
		pg.textAlign(PApplet.LEFT, PApplet.CENTER);
		pg.textFont(font);
		pg.fill(this.textColor.red, textColor.green, textColor.blue, textColor.alpha);
		pg.text(this.text, x+height+(height/2), y-3, width-height, height);
	}
	
	/**
	 * Diese Methode �berpr�ft, ob ein bestimmter Punkt innerhalb des PComponent liegt.
	 * 
	 * @param point Point, welcher �berpr�ft werden soll
	 * @return Wahrheitswert ob der Punkt innerhalb des Komponenten liegt oder nicht
	 */

	@Override
	public boolean isInside(Point point) {
		return this.isInside(point, Shape.RECT);
	}
	
	/**
	 * @return Zustand der Checkbox
	 */

	@Override
	public boolean isActivated() {
		return activated;
	}

	/**
	 * Setzt einen neuen Zustand f�r die PCheckBox
	 * 
	 * @param activated neuer Zustand f�r die PCheckBox
	 */
	
	@Override
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	/**
	 * Setzt einen neuen Text f�r die PCheckBox
	 * 
	 * @param text neuer Text f�r die PCheckBox
	 */

	@Override
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Gibt den aktuellen Text der Checkbox zur�ck
	 * 
	 * @return aktueller Text der PCheckBox
	 */

	@Override
	public String getText() {
		return this.text;
	}
	
	/**
	 * Gibt die aktuelle Schriftart der Checkbox zur�ck
	 * 
	 * @return Aktuelle Schriftart der PCheckBox
	 */

	@Override
	public PFont getFont() {
		return this.font;
	}
	
	/**
	 * Legt eine neue Schriftart f�r die Checkbox fest
	 * 
	 * @param pFont neue Schriftart f�r die PCheckBox
	 */

	@Override
	public void setFont(PFont pFont) {
		this.font = pFont;
	}

	/**
	 * Setzt eine neue Schriftfarbe f�r die Checkbox
	 * 
	 * @param textColor neue Schriftart f�r die PCheckBox
	 */
	
	@Override
	public void setFontColor(PColor textColor) {
		this.textColor = textColor;
	}
	
	/**
	 * Gibt die aktuelle Schriftfarbe f�r den Text der Checkbox zur�ck
	 * 
	 *  @return aktuelle Schriftfarbe des Textes der PCheckBox
	 */

	@Override
	public PColor getFontColor() {
		return this.textColor;
	}
}
