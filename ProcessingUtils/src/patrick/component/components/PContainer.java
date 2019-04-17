package patrick.component.components;

import java.awt.Point;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

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
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.event.MouseEvent;
/**
 * <p>Ein PComponent, um meherere PComponenten darauf hinzuzufügen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PContainer extends PComponent
		implements
			Colorizable,
			Borderizable,
			RoundCorner
				{

	/**
	 * Farbe des PContainer
	 */
	
	private PColor color;
	
	/**
	 * Liste aller hinzugefügten PComponents
	 */
	
	protected List<PComponent> components = new ArrayList<PComponent>();
	
	/**
	 * Border des PContainer
	 */
	
	private PBorder border;
	
	/**
	 * Wert für abgerundete Ecken des PContainer
	 */
	
	private int cornerValue;
	
	/**
	 * Erzeugt eine PContainer ohne PComponente
	 * 
	 * <p>Standardwerte:
	 * <ul>
	 * <li>Der PContainer ist farblos</li>
	 * <li>Der PContainer besitzt kein Border</li>
	 * <li>Die Ecken sind nicht abgerundet</li>
	 * </ul>
	 */
	
	public PContainer() {
		this.color = null;
		this.border = null;
		this.cornerValue = 0;
	}
	
	/**
	 * Methode, welche vom jeweilig übergeordneten PComponent aufgerufen wird, wenn
	 * sich der PContainer zeichnen soll. Diese Methode kann überschrieben werden.
	 * Um die jeweiligen Standard Funktionen und Grafiken des PContainer beizubehalten
	 * sollte in der überschriebenen Methode die Methode drawIt(PGraphics pgParent) aufgerufen
	 * werden.
	 * 
	 * @param pgParent Grafik, auf welcher sich der PContainer zeichnen wird
	 */
	
	protected void draw(PGraphics pgParent) {
		drawIt(pgParent);
	}
	
	/**
	 * Methode um den PContainer zu zeichnen und seine Funktionen auszuführen
	 * 
	 * @param pgParent Grafik, auf welcher sich der PContainer zeichnen soll
	 */
	
	protected void drawIt(PGraphics pgParent) {
		if(rootContainer == null) {
			return;
		}
		if(border != null) {
			stroke(pgParent, border);
		}
		if(color != null) {
			pgParent.fill(color.red, color.green, color.blue, color.alpha);
			pgParent.rect(x, y, width, height, this.cornerValue);
		}
		PGraphics pg = rootContainer.getPApplet().createGraphics(width, height);
		pg.beginDraw();
		try {
			for(PComponent comp : components) {
				if(comp.isVisible() == false) {
					continue;
				}
				if(rootContainer.getHoveredComponent() != null) {
					if(rootContainer.getHoveredComponent().equals(comp)) {
						if(comp.isInside(new Point(rootContainer.getPApplet().mouseX, rootContainer.getPApplet().mouseY)) == false) {
							rootContainer.hover = null;
							rootContainer.getPApplet().cursor(PApplet.ARROW);
						}		
					}
				}
				pg.noFill();
				pg.noStroke();
				if(comp instanceof Colorizable) {
					Colorizable color = (Colorizable) comp;
					if(comp instanceof HoverColorizable) {
						if(comp.isHovered()) {
							HoverColorizable hoverColorizable = (HoverColorizable) comp;
							if(hoverColorizable.getHoverColorMode() == ColorMode.DARKER) {
								fill(pg, color.getColor().getDarker());
							}else if(hoverColorizable.getHoverColorMode() == ColorMode.BRIGHTER) {
								fill(pg, color.getColor().getBrighter());
							}else if(hoverColorizable.getHoverColorMode() == ColorMode.CUSTOM) {
								fill(pg, hoverColorizable.getHoverColor());
							}else {
								fill(pg, color.getColor());
							}
						}else {
							if(color.getColor() != null) {
								fill(pg, color.getColor());
							}
						}
					}
					if(comp instanceof Popupizable) {
						if(comp.isHovered()) {
							if(((Popupizable) comp).getPopupField() != null) {
								int mouseX = rootContainer.getPApplet().mouseX;
								int mouseY = rootContainer.getPApplet().mouseY;
								((Popupizable) comp).getPopupField().draw(rootContainer,
										mouseX,
										mouseY,
										rootContainer.getPApplet().createFont("Arial", 32));
							}
						}
					}
					if(comp instanceof PressColorizable) {
						if(comp.isPressed()) {
							PressColorizable pressColor = (PressColorizable) comp;
							if(pressColor.getPressColorMode() == ColorMode.DARKER) {
								fill(pg, color.getColor().getDarker());
							}else if(pressColor.getPressColorMode() == ColorMode.BRIGHTER) {
								fill(pg, color.getColor().getBrighter());
							}else if(pressColor.getPressColorMode() == ColorMode.CUSTOM) {
								fill(pg, pressColor.getPressColor());
							}else {
								fill(pg, color.getColor());
							}
						}
					}else if(color.getColor() != null) {
						fill(pg, color.getColor());
					}
				}
				if(comp instanceof Borderizable) {
					Borderizable border = (Borderizable) comp;
					if(border.getBorder() != null) {
						stroke(pg, border.getBorder());
					}
				}
				comp.draw(pg);
			}
		}catch(ConcurrentModificationException ex) {}
		pg.endDraw();
		pgParent.image(pg, x, y);
	}
	
	/**
	 * Methode, welche bei Mausaktionen ausgeführt wird. Die Methode gibt die Mausaktion
	 * an die jeweilig betroffenen PComponents weiter.
	 * Diese Methode sollte nicht andersweitig aufgerufen werden.
	 * 
	 * @param e MouseEvent, welcher ausgeführt wurde
	 * 
	 * @return Wahrheitswert ob sich der Fokus eines PComponent geändert hat
	 */
	
	protected boolean onMouse(MouseEvent e) {
		boolean setFocus = false;
		try {
			for(PComponent comp : this.getComponents()) {
				if(comp.isVisible() == false) {
					continue;
				}
				if(comp instanceof PContainer && comp.isInside(new Point(e.getX(), e.getY()))) {
					boolean focus = ((PContainer) comp).onMouse(e);
					setFocus = focus || setFocus;
					continue;
				}else if( comp instanceof PScrollableContainer && comp.isInside(new Point(e.getX(), e.getY()))) {
					PScrollableContainer scroll = (PScrollableContainer) comp;
					if(scroll.getViewContent() != null) {
						if(scroll.getAllowScroll() && e.getAction() == MouseEvent.WHEEL) {
							scroll.scrollY(e.getCount() * 10);
						}
						boolean focus = (scroll.onMouse(e));
						setFocus = focus || setFocus;
						if(this.rootContainer.hover == null || e.getAction() != MouseEvent.MOVE) {
							comp.mouseAction(e);
						}
						continue;
					}
				}
				if(comp.isInside(new Point(e.getX(), e.getY()))) {
					if(e.getAction() == MouseEvent.CLICK) {
						this.rootContainer.focused = comp;
						setFocus = true;
					}else if(e.getAction() == MouseEvent.MOVE) {
						this.rootContainer.hover = comp;
					}
					comp.mouseAction(e);
					if(e.getAction() == MouseEvent.PRESS && e.getButton() == PApplet.LEFT) {
						comp.isPressed = true;
					}else if(e.getAction() == MouseEvent.RELEASE){
						comp.isPressed = false;
					}
				}else {
					if(e.getAction() != MouseEvent.DRAG) {
						comp.lastClick = null;
					}
					comp.isPressed = false;
				}
			}
		}catch(ConcurrentModificationException ex) {}
		return setFocus;
	}
	
	/**
	 * Gibt zurück, ob sich ein bestimmter Punkt innerhalb des PComponent befindet
	 * 
	 * @param point Punkt welcher geprüft werden soll
	 * @return Wahrheitswert ob der Punkt innerhalb liegt
	 */

	@Override
	public boolean isInside(Point point) {
		return isInside(point, Shape.RECT);
	}
	
	/**
	 * Setzt die Farbe des PContainers
	 * 
	 * @param color neue Farbe
	 */

	@Override
	public void setColor(PColor color) {
		this.color = color;
	}

	/**
	 * Setzt die Farbe des PContainers
	 * 
	 * @param color Grauwert der neuen Farbe
	 */
	
	@Override
	public void setColor(int color) {
		this.color = new PColor(color);
	}
	
	/**
	 * Setzt die Farbe des PContainers
	 * 
	 * @param red Rotwert der neuen Farbe
	 * @param green Grünwert der neuen Farbe
	 * @param blue Blauwert der neuen Farbe
	 */

	@Override
	public void setColor(int red, int green, int blue) {
		this.color = new PColor(red, green, blue);
	}
	
	/**
	 * Setzt die Farbe des PContainers
	 * 
	 * @param red Rotwert der neuen Farbe
	 * @param green Grünwert der neuen Farbe
	 * @param blue Blauwert der neuen Farbe
	 * @param alpha Alphawert der neuen Farbe
	 */

	@Override
	public void setColor(int red, int green, int blue, int alpha) {
		this.color = new PColor(red, green, blue, alpha);
	}
	
	/**
	 * Setzt die Farbe des PContainers
	 * 
	 * @param color Grauwert der neuen Farbe
	 * @param alpha Alphawert der neuen Farbe
	 */

	@Override
	public void setColor(int color, int alpha) {
		this.color = new PColor(color, alpha);
	}
	
	/**
	 * Gibt die aktuelle Farbe des PContainers zurück
	 * 
	 * @return Aktuelle Farbe
	 */

	@Override
	public PColor getColor() {
		return this.color;
	}
	
	/**
	 * Gibt die Liste aller hinzugefügten Komponenten zurück
	 * 
	 * @return Liste aller hinzugefügten Komponenten
	 */
	
	public List<PComponent> getComponents() {
		return this.components;
	}
	
	/**
	 * Fügt dem PContainer ein PComponent hinzu
	 * 
	 * @param component PComponent, welcher hinzugefügt werden soll
	 */
	
	public void add(PComponent component) {
		addIt(component);
	}
	
	/**
	 * Methode, um auf sämtlichen untergeordneten PComponents den RootContainer sowie 
	 * den Parent PComponent zu setzen
	 * 
	 * Diese Methode wird jedes Mal wenn ein neuer PComponent hinzugefügt wird aufgerufen.
	 * Diese Methode soll nicht von ausserhalb aufgerufen werden.
	 * 
	 * @param component
	 */
	
	protected void addIt(PComponent component) {
		component.parent = this;
		component.rootContainer = this.rootContainer;
		if(component instanceof PContainer) {
			PContainer cont = (PContainer) component;
			for(PComponent comp : getAllComponents(cont)) {
				comp.rootContainer = this.rootContainer;
			}
		}
		if(component instanceof PScrollableContainer) {
			PScrollableContainer scroll = (PScrollableContainer) component;
			if(scroll.getViewContent() != null) {
				PContainer scrollView = scroll.getViewContent();
				scrollView.rootContainer = this.rootContainer;
				for(PComponent comp : getAllComponents(scrollView)) {
					comp.rootContainer = this.rootContainer;
				}
			}
		}
		this.components.add(component);
	}
	
	/**
	 * Setzt den Border des PContainers
	 * 
	 * @param border neuer Border
	 */

	@Override
	public void setBorder(PBorder border) {
		this.border = border;
		
	}
	
	/**
	 * Gibt den aktuellen Border des PContainers zurück
	 * 
	 * @return aktueller Border
	 */

	@Override
	public PBorder getBorder() {
		return this.border;
	}
	
	/**
	 * Ändert die Farbe der Grafik zu der gegebenen Farbe
	 * 
	 * @param pg Grafik auf welcher sich die Farbe ändern soll
	 * @param color Farbe, zu welcher sie sich ändern soll
	 */
	
	private void fill(PGraphics pg, PColor color) {
		pg.fill(color.red, color.green, color.blue, color.alpha);
	}
	
	/**
	 * Ändert die Umrandung der Grafik zum gegebenen Border
	 * 
	 * @param pg Grafik auf welcher die Umrandung geändert werden soll
	 * @param border Border, zu welchem die Umrandung angepasst werden soll
	 */
	
	private void stroke(PGraphics pg, PBorder border) {
		pg.strokeWeight(border.getSize());
		pg.stroke(border.getColor().red, 
				border.getColor().green, 
				border.getColor().blue, 
				border.getColor().alpha);
	}
	
	/**
	 * Listet alle untergeordneten Komponenten auf
	 * 
	 * @param cont PContainer von welchem die Komponente aufgelistet werden sollen
	 * @return Liste aller untergeordneten PComponents
	 */
	
	private List<PComponent> getAllComponents(PContainer cont){
		List<PComponent> comps = new ArrayList<PComponent>();
		for(PComponent comp : cont.getComponents()) {
			comps.add(comp);
			if(comp instanceof PContainer) {
				comps.addAll(getAllComponents((PContainer) comp));
			}else if(comp instanceof PScrollableContainer) {
				PScrollableContainer scroll = (PScrollableContainer) comp;
				if(scroll.getViewContent() != null) {
					comps.add(scroll.getViewContent());
					comps.addAll(getAllComponents(scroll.getViewContent()));
				}
			}
		}
		return comps;
	}
	
	/**
	 * Setzt den Wert für die abgerundeten Ecken des PContainers.
	 * Der Wert 0 heisst dass die Ecken nicht abgerundet werden.
	 * 
	 * @param cornerValue Wert für die abgerundeten Ecken
	 */

	@Override
	public void setCorner(int cornerValue) {
		this.cornerValue = cornerValue;
	}
	
	/**
	 * Gibt den aktuellen Wert für die abgerundeten Ecken des PContainers zurück
	 * 
	 * @return Wert für die abgerundeten Ecken
	 */

	@Override
	public int getCorner() {
		return this.cornerValue;
	}

}
