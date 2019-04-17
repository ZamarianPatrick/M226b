package patrick.component.components;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import patrick.component.PBorder;
import patrick.component.PColor;
import patrick.component.action.DragListener;
import patrick.component.enums.Scrollable;
import patrick.component.enums.Shape;
import patrick.component.interfaces.Borderizable;
import patrick.component.interfaces.Colorizable;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.event.MouseEvent;
/**
 * <p>Ein PContainer, welcher es ermöglicht einen Container zu Scrollen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PScrollableContainer extends PComponent
		implements
			Colorizable,
			Borderizable
				{

	/**
	 * Container, welcher gescrollt werden soll
	 */
	
	private PContainer viewContent;
	
	/**
	 * Hintergrundsfarbe des PScrollableContainer
	 */
	
	private PColor color;
	
	/**
	 * Umrandung des PScrollableContainer
	 */
	
	private PBorder border;
	
	/**
	 * Werte, um wie viel der PScrollableContainer gescrollt werden kann
	 */
	
	private int xScrollable, yScrollable;
	
	/**
	 * Werte, um wie viel der PScrollableContainer gescrollt ist
	 */
	
	private int xScrolled, yScrolled;
	
	/**
	 * Werte, um wie viel der PScrollabelContainer bei der nächsten Zeichnung
	 * gescrollt werden soll
	 */
	
	private int toScrollX, toScrollY;
	
	/**
	 * Letze Position der Maus auf der Y-Achse
	 */
	
	private int lastMouseY;
	
	/**
	 * Letzte Position der Maus auf der Y-Achse
	 */
	
	private int lastMouseX;
	
	/**
	 * Zustand, ob man den PScrollableContainer auch mit Ziehen scrollen kann
	 */
	
	private boolean allowDrag;
	
	/**
	 * Enum, in welche Richtungen der PScrollableContainer gescrollt werden kann
	 */
	
	private Scrollable scrollable;
	
	/**
	 * Geschwindigkeit des Scrollen
	 */
	
	private int scrollSpeed;
	
	/**
	 * Zustand, ob man den PScrollableContainer mit dem Mausrad scrollen kann
	 */
	
	private boolean allowScroll;
	
	/**
	 * Erzeugt einen neuen PScrollableContainer
	 */
	
	public PScrollableContainer() {
		this.viewContent = null;
		this.color = null;
		this.border = null;
		this.xScrollable = 0;
		this.xScrolled = 0;
		this.yScrollable = 0;
		this.yScrolled = 0;
		this.cursor = PApplet.MOVE;
		this.allowDrag = true;
		this.scrollable = Scrollable.BOTH;
		this.scrollSpeed = 1;
		this.allowScroll = true;
		this.addActionListener(new DragListener() {
			@Override
			public void onDrag(Point point, MouseEvent e) {
				if(lastClick != null && allowDrag && e.getButton() == PApplet.LEFT) {
					if(scrollable == Scrollable.BOTH || scrollable == Scrollable.VERTICAL) {
						int diffY = 0;
						if(lastMouseY != 0) {
							diffY = e.getY() - (int) lastClick.getY();
						}else {
							diffY = e.getY() - (int) lastClick.getY();	
						}
						diffY *= -1;
						diffY *= scrollSpeed;
						diffY /= 20;
						scrollY(diffY);
						lastMouseY = e.getX();
					}
					if(scrollable == Scrollable.BOTH || scrollable == Scrollable.HORIZONTAL) {
						int diffX = 0;
						if(lastMouseX != 0) {
							diffX = e.getX() - (int) lastClick.getX();
						}else {
							diffX = e.getX() - (int) lastClick.getX();	
						}
						diffX *= -1;
						diffX *= scrollSpeed;
						diffX /= 20;
						scrollX(diffX);
						lastMouseX = e.getX();
					}
				}
				
			}
		});
	}
	
	/**
	 * @see patrick.component.components.PComponent#draw(processing.core.PGraphics)
	 */
	
	@Override
	protected void draw(PGraphics pgParent) {
		if(color != null) {
			pgParent.fill(color.red, color.green, color.blue, color.alpha);
			pgParent.rect(x, y, width, height);
		}
		if(border != null) {
			stroke(pgParent, border);
		}
		if(viewContent != null) {
			PGraphics pg = rootContainer.getPApplet().createGraphics(width, height);
			pg.beginDraw();
			viewContent.draw(pg);
			pg.endDraw();
			pgParent.image(pg, x, y);
		}
	}
	
	protected boolean onMouse(MouseEvent e) {
		if(viewContent != null) {
			if(e.getAction() != MouseEvent.DRAG) {
				return viewContent.onMouse(e);
			}
		}
		return false;
	}
	
	/**
	 * @see patrick.component.components.PComponent#isInside(java.awt.Point)
	 */

	@Override
	public boolean isInside(Point point) {
		return isInside(point, Shape.RECT);
	}
	
	/**
	 * Legt den PContainer fest, welcher gescrollt werden soll
	 * 
	 * @param container PContainer, welcher gescrollt werden soll
	 */
	
	public void setViewContent(PContainer container) {
		container.rootContainer = this.rootContainer;
		container.parent = this;
		for(PComponent comp : getAllComponents(container)) {
			comp.rootContainer = this.rootContainer;
		}
		this.viewContent = container;
		xScrollable = viewContent.getWidth() - getWidth();
		yScrollable = viewContent.getHeight() - getHeight();
		if(xScrollable < 0) {
			xScrollable = 0;
		}
		if(yScrollable < 0) {
			yScrollable = 0;
		}
	}
	
	/**
	 * Gibt den aktuellen PContainer zurück, welcher gescrollt wird
	 * 
	 * @return aktueller PContainer, welcher gescrollt wird
	 */
	
	public PContainer getViewContent() {
		return this.viewContent;
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
	 * Gibt den Wert zurück, um wie viel der PContainer gescrollt ist auf der X-Achse
	 * 
	 * @return gescrollter Wert des PContainer auf der X-Achse
	 */
	
	public int getScrolledX() {
		return this.xScrolled * -1;
	}
	
	/**
	 * Gibt den Wert zurück, um wie viel der PContainer gescrollt ist auf der Y-Achse
	 * 
	 * @return gescrollter Wert des PContainer auf der Y-Achse
	 */
	
	public int getScrolledY() {
		return this.yScrolled * -1;
	}
	
	/**
	 * Gibt den Zustand zurück, ob man den PScrollableContainer auch mit ziehen scrollen kann
	 *
	 * @return Zustand ob Drag erlaubt ist
	 */
	
	public boolean isAllowDrag() {
		return this.allowDrag;
	}
	
	/**
	 * Setzt den Zustand, ob man den PScrollableContainer auch mit ziehen scrollen kann
	 *
	 * @param allowDrag neuer Zustand ob Drag erlaubt ist
	 */
	
	public void setAllowDrag(boolean allowDrag) {
		this.allowDrag = allowDrag;
	}
	
	/**
	 * Gibt zurück, in welche Richtungen gescrollt werden kann
	 * 
	 * @return Richtungen, in welche gescrollt werden können
	 */
	
	public Scrollable getScrollable() {
		return this.scrollable;
	}
	
	/**
	 * Legt fest, in welche Richtungen gescrollt werden kann
	 * 
	 * @param scrollable Richtungen in welche gescrollt werden soll
	 */
	
	public void setScrollable(Scrollable scrollable) {
		this.scrollable = scrollable;
	}
	
	/**
	 * Legt die Scrollgeschwindigkeit fest, Standardwert ist 1
	 * 
	 * @param speed neue Scrollgeschwindigkeit
	 */
	
	public void setScrollSpeed(int speed) {
		this.scrollSpeed = speed;
	}
	
	/**
	 * Gibt die aktuelle Scrollgeschwindigkeit zurück
	 * 
	 * @return Aktuelle Scrollgeschwindigkeit
	 */
	
	public int getScrollSpeed() {
		return this.scrollSpeed;
	}
	
	/**
	 * Gibt den Zustand zurück, ob Scrollen mit dem Mausrad erlaubt ist
	 * 
	 * @return Erlaubung von Scrollen mit dem Mausrad
	 */
	
	public boolean getAllowScroll() {
		return allowScroll;
	}
	
	/**
	 * Setzt den Zustand, ob Scrollen mit dem Mausrad erlaub ist
	 * 
	 * @param allowScroll neuer Zustand
	 */

	public void setAllowScroll(boolean allowScroll) {
		this.allowScroll = allowScroll;
	}

	/**
	 * Legt die Umrandung auf der gegebenen Grafik für den gegeneben Border fest
	 * 
	 * @param pg Grafik, auf welcher sich die Umrandung ändern soll
	 * @param border Border für die Umrandung
	 */
	
	private void stroke(PGraphics pg, PBorder border) {
		pg.strokeWeight(border.getSize());
		pg.stroke(border.getColor().red, 
				border.getColor().green, 
				border.getColor().blue, 
				border.getColor().alpha);
	}
	
	private List<PComponent> getAllComponents(PContainer cont){
		List<PComponent> comps = new ArrayList<PComponent>();
		for(PComponent comp : cont.getComponents()) {
			comps.add(comp);
			if(comp instanceof PContainer) {
				comps.addAll(getAllComponents((PContainer) comp));
			}
		}
		return comps;
	}
	
	/**
	 * Scrollt auf der X-Achse
	 * 
	 * @param x Wert, welcher gescrollt werden soll
	 */
	
	protected void scrollX(int x) {
		x *= -1;
		if(x < 0) {
			if(x >= (xScrollable + xScrolled + toScrollX)*-1) {
				toScrollX += x;
			}else {
				toScrollX += (xScrollable + xScrolled)*-1;
			}
		}else{
			if(x <= (xScrolled + toScrollX)*-1) {
				toScrollX += x;
			}else {
				toScrollX += (xScrolled+toScrollX)*-1;
			}
		}
		int newX = (int) (viewContent.getLocation().getX() + toScrollX);
		xScrolled += toScrollX;
		toScrollX = 0;
		viewContent.setLocation(newX, (int)viewContent.getLocation().getY());
	}
	
	/**
	 * Scrollt auf der Y-Achse
	 * 
	 * @param y Wert, welcher gescrollt werden soll
	 */
	
	protected void scrollY(int y) {
		y *= -1;
		if(y < 0) {
			if(y >= (yScrollable + yScrolled + toScrollY)*-1) {
				toScrollY += y;
			}else {
				toScrollY += (yScrollable + yScrolled)*-1;
			}
		}else{
			if(y <= (yScrolled + toScrollY)*-1) {
				toScrollY += y;
			}else {
				toScrollY += (yScrolled+toScrollY)*-1;
			}
		}
		int newY = (int) (viewContent.getLocation().getY() + toScrollY);
		yScrolled += toScrollY;
		toScrollY = 0;
		viewContent.setLocation((int)viewContent.getLocation().getX(), newY);
	}

	
	
}
