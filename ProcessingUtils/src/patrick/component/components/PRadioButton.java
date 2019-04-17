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
 * <p>Ein PComponent, um dem User eine Wahl zwischen mehreren Optionen zu geben</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PRadioButton extends PComponent
			implements
			Textizable,
			Activable
				{
	
	/**
	 * Text welcher neben dem PRadioButton angezeigt werden soll
	 */
	
	private String text;
	
	/**
	 * Schriftart für den Text
	 */
	
	private PFont font;
	
	/**
	 * Zustand ob der PRadioButton aktiviert ist oder nicht
	 */
	
	protected boolean activated;
	
	/**
	 * Textfarbe
	 */
	
	private PColor textColor;
	
	/**
	 * Gruppe, welcher der PRadioButton zugewiesen ist
	 */
	
	private PRadioButtonGroup group;

	/**
	 * Erzeugt ein PRadioButton mit Text
	 * 
	 * <p>Standardwerte:
	 * <ul>
	 * <li>Die Schriftart ist Arial mit der Grösse 32</li>
	 * <li>Der PContainer besitzt keine PRadioButtonGroup</li>
	 * <li>Textfarbe ist schwarz</li>
	 * <li>Der PRadioButton ist nicht aktiviert</li>
	 * </ul>
	 * 
	 * @param text Text, welcher neben dem PRadioButton angezeigt werden soll
	 */
	
	public PRadioButton(String text) {
		this.text = text;
		this.font = null;
		this.activated = false;
		this.textColor = new PColor(0);
		this.group = null;
		this.addActionListener(new ClickListener() {
			@Override
			public void onClick(MouseEvent e) {
				if(LocationUtils.isInsideCircle(getAbsoluteX()+(height/2),
						getAbsoluteY()+(height/2),
						height,
						new Point(e.getX(), e.getY())) && e.getButton() == PApplet.LEFT) {
					setActivated(!activated);
				}
			}
		});
		this.addActionListener(new MoveListener() {
			@Override
			public void onMouseMove(MouseEvent e) {
				if(LocationUtils.isInsideCircle(getAbsoluteX()+(height/2), getAbsoluteY()+(height/2), height, new Point(e.getX(), e.getY()))) {
					cursor = PApplet.HAND;
				}else {
					cursor = PApplet.ARROW;
				}
			}
		});
	}
	
	/**
	 * @see patrick.component.components.PComponent#draw(processing.core.PGraphics)
	 */
	
	@Override
	protected void draw(PGraphics pg) {
		if(font == null) {
			font = rootContainer.getPApplet().createFont("Arial", ((int)this.height * 0.8F));
		}
		pg.stroke(0);
		pg.strokeWeight(3);
		if(activated == false) {
			pg.noFill();
		}else {
			pg.fill(0, 180, 0);
		}
		pg.ellipse(x+(height/2), y+(height/2), height, height);
		pg.textAlign(PApplet.LEFT, PApplet.CENTER);
		pg.textFont(font);
		pg.fill(this.textColor.red, textColor.green, textColor.blue, textColor.alpha);
		pg.text(this.text, x+height+(height/2), y-3, width-height, height);
	}

	/**
	 * @see patrick.component.components.PComponent#isInside(java.awt.Point)
	 */
	
	@Override
	public boolean isInside(Point point) {
		return this.isInside(point, Shape.RECT);
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
	 * @see patrick.component.interfaces.Activable#isActivated()
	 */

	@Override
	public boolean isActivated() {
		return activated;
	}

	/**
	 * @see patrick.component.interfaces.Activable#setActivated(boolean)
	 */

	@Override
	public void setActivated(boolean activated) {
		this.activated = activated;
		if(this.group != null) {
			if(activated) {
				group.activate(this);
			}else {
				if(group.isAlwaysOne()) {
					this.activated = true;
				}
			}
		}
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
	 * Setzt die Gruppe des PRadioButton
	 * Wenn eine Gruppe zugewiesen ist, werden bei Aktivierung des PRadioButton
	 * alle anderen zugewiesenen PRadioButtons diese Grupppe deaktiviert.
	 * 
	 * @param group neue PRadioButtonGroup, welche dem PRadioButton zugewiesen werden soll
	 */
	
	public void setGroup(PRadioButtonGroup group) {
		if(this.group != null) {
			this.group.removeRadioButton(this);
		}
		group.addRadioButton(this);
		this.group = group;
	}
	
	/**
	 * Gibt die aktuell zugewiesene PRadioButtonGroup zurück
	 * 
	 * @return Aktuelle PRadioButtonGroup, null wenn keine vorhanden ist
	 */
	
	public PRadioButtonGroup getGroup() {
		return this.group;
	}

}
