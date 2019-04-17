package patrick.component.components;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import patrick.component.LocationUtils;
import patrick.component.action.ActionListener;
import patrick.component.action.ClickListener;
import patrick.component.action.DragListener;
import patrick.component.action.KeyTypedListener;
import patrick.component.action.MoveListener;
import patrick.component.action.PressListener;
import patrick.component.enums.Shape;
import processing.core.PGraphics;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
/**
 * <p>Die Klasse welche, für die Erstellung neuer PComponenten benutzt werden kann.
 * Alle Eigenschaften, welche eine PComponent benötigt sind hier bereits enthalten
 * mit den jeweiligen Gettern und Settern.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public abstract class PComponent{

	/**
	 * Position des PComponents
	 */
	
	protected int x, y;
	
	/**
	 * Grösse des PComponents
	 */
	
	protected int width, height;
	
	/**
	 * Der PRootContainer, auf welchem sicher der PComponent befindet
	 */
	protected PRootContainer rootContainer;
	
	/**
	 * Der PComponent, auf welchem sich der PComponent befindet
	 */
	
	protected PComponent parent;
	
	/**
	 * Der Cursor welcher angezeigt wird, wenn man sich mit der Maus
	 * über dem PComponent befindet
	 */
	
	protected int cursor;
	
	/**
	 * Der Punkt, auf welchen man zuletzt geklickt hat auf dem PComponent
	 */
	
	protected Point lastClick;
	
	/**
	 * Wahrheitswert ob der PComponent gerade angeklickt ist oder nicht
	 */
	
	protected boolean isPressed;
	
	/**
	 * Sichtbarkeit des PComponent
	 */
	
	protected boolean visible;
	
	/**
	 * Liste aller hinzugefügten ActionListener des PComponents
	 */
	
	private List<ActionListener> actionListeners = new ArrayList<ActionListener>();
	
	/**
	 * Erzeugt ein PComponent
	 * 
	 * <p>Standardwerte:
	 * <ul>
	 * <li>Wert der X-Achse ist 0</li>
	 * <li>Wert der Y-Achse ist 0</li>
	 * <li>Die Breite ist 0</li>
	 * <li>Die Höhe ist 0</li>
	 * <li>Die Sichtbarkeit ist aktiviert</li>
	 * </ul>
	 */
	
	public PComponent() {
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.visible = true;
		this.addActionListener(new MoveListener() {
			@Override
			public void onMouseMove(MouseEvent e) {
				if(rootContainer != null) {
					rootContainer.getPApplet().cursor(cursor);
				}
			}
		});
	}
	
	/**
	 * Legt die Grösse des PComponent fest
	 * 
	 * @param width Breite in Pixel
	 * @param height Höhe in Pixel
	 */
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Legt die Position des PComponent fest
	 * 
	 * @param x Position auf der X-Achse
	 * @param y Position auf der Y-Achse
	 */
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gibt die Grösse des PComponent zurück
	 * 
	 * @return Grösse des PComponent
	 */
	
	public Dimension getSize() {
		return new Dimension(width, height);
	}
	
	/**
	 * Gibt die Position des PComponent zurück
	 * 
	 * @return Position des PComponent
	 */
	
	public Point getLocation() {
		return new Point(x,y);
	}
	
	/**
	 * Gibt die Breite des PComponent zurück
	 * 
	 * @return Breite in Pixel
	 */
	
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Gibt die Höhe des PComponent zurück
	 * 
	 * @return Höhe in Pixel
	 */
	
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Gibt zurück, ob der PComponent gerade vom User fokusiert ist oder nicht
	 * 
	 * @return Hat der PComponent den Fokus des Users
	 */
	
	public boolean hasFocus() {
		if(rootContainer != null) {
			if(rootContainer.getFocusedComponent() != null) {
				if(rootContainer.getFocusedComponent().equals(this)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Gibt zurück, ob der User sich mit der Maus gerade über dem PComponent befindet oder nicht
	 * 
	 * @return Ist der User mit der Maus über dem PComponent
	 */
	
	public boolean isHovered() {
		if(rootContainer != null) {
			if(rootContainer.getHoveredComponent() != null) {
				if(rootContainer.getHoveredComponent().equals(this)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Fügt dem PComponent einen ActionListener hinzu. Der ActionListener sollte nicht benutzt
	 * werden, dieser hat keine Auswirkungen. Es sollen die Erweiterten ActionListener
	 * wie der ClickListener, DragListener, KeyTypedListener, MoveListener und PressListener
	 * für die jeweiligen Aktionen verwendet werden.
	 * 
	 * @param listener Der Listener, welcher hinzugefügt werden soll
	 */
	
	public void addActionListener(ActionListener listener) {
		this.actionListeners.add(listener);
	}
	
	/**
	 * Diese Methode wird vom jeweiligen Übergeordneten PComponenten aufgerufen.
	 * Die Methode ist nicht für sonstige Aufrufe gedacht.
	 * 
	 * @param e MouseEvent, welcher ausgeführt wurde.
	 */
	
	protected void mouseAction(MouseEvent e) {
		if(e.getAction() == MouseEvent.PRESS) {
			this.lastClick = new Point(e.getX(), e.getY());
		}
		for(ActionListener listener : actionListeners) {
			if(listener instanceof ClickListener && e.getAction() == MouseEvent.CLICK) {
				((ClickListener) listener).onClick(e);
			}else if(listener instanceof MoveListener && e.getAction() == MouseEvent.MOVE) {
				((MoveListener) listener).onMouseMove(e);
			}else if(listener instanceof PressListener && e.getAction() == MouseEvent.PRESS) {
				((PressListener) listener).onPressed(e);
			}else if(listener instanceof DragListener && e.getAction() == MouseEvent.DRAG) {
				((DragListener) listener).onDrag(this.lastClick, e);
			}
		}
	}
	
	 /**
	 * Diese Methode wird vom jeweiligen Übergeordneten PComponenten aufgerufen.
	 * Die Methode ist nicht für sonstige Aufrufe gedacht.
	 * 
	 * @param e KeyEvent, welcher ausgeführt wurde
	 */
	protected void keyAction(KeyEvent e) {
		for(ActionListener listener : actionListeners) {
			if(e.getAction() == KeyEvent.TYPE) {
				if(listener instanceof KeyTypedListener) {
					((KeyTypedListener) listener).onKeyTyped(e);
				}
			}
		}
	}
	
	/**
	 * Gibt den Zustand zurück, ob der PComponent angeklickt ist
	 * 
	 * @return Zustand ob der PComponent angeklickt ist
	 */
	
	public boolean isPressed() {
		return isPressed;
	}
	
	/**
	 * Gibt den aktuellen Cursor des PComponent zurück
	 *
	 * @return Aktueller Cursor
	 */
	
	public int getCursor() {
		return this.cursor;
	}
	
	/**
	 * Legt einen neuen Cursor für den PComponent fest
	 * 
	 * @param cursor neuer Cursor für den PComponent
	 */
	
	public void setCursor(int cursor) {
		this.cursor = cursor;
	}
	
	/**
	 * Überprüft, ob der Punkt sich innerhalb des PComponent befindet
	 * Diese Methode kann auf erweiterten Komponenten verwendet werden.
	 * 
	 * @param point Punkt welcher überprüft werden soll
	 * @param shape Die Form des PComponenten
	 * @return Wahrheitswert ob sich der Punkt innerhalb des PComponent befindet
	 */
	
	protected boolean isInside(Point point, Shape shape) {
		return LocationUtils.isInside(this, point, getSize(), shape);
	}
	
	/**
	 * Setzt die Sichtbarkeit für den PComponent
	 * 
	 * @param visible neuer Sichbarkeitswert
	 */
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * Gibt den Zustand der Sichtbarkeit des PComponent zurück
	 * 
	 * @return Sichbarkeit des Komponenten
	 */
	
	public boolean isVisible() {
		return this.visible;
	}
	
	/**
	 * Gibt den Übergeordneten PComponent zurück
	 * 
	 * @return Übergeordneter PComponent
	 */
	
	public PComponent getParent() {
		return this.parent;
	}
	
	/**
	 * Gibt die Absolute Position auf der X-Achse des PComponent zurück
	 * Sofern der PComponent ein übergeordneter PComponent hat wird die Position dessen
	 * zusätzlich zu der aktuellen Position dazu berechnet
	 * 
	 * @return Absolute Position auf der X-Achse
	 */
	
	public int getAbsoluteX() {
		if(this.parent == null) {
			return x;
		}else {
			return this.parent.getAbsoluteX() + x;
		}
	}
	
	/**
	 * Gibt die Absolute Position auf der Y-Achse des PComponent zurück
	 * Sofern der PComponent ein übergeordneter PComponent hat wird die Position dessen
	 * zusätzlich zu der aktuellen Position dazu berechnet
	 * 
	 * @return Absolute Position auf der Y-Achse
	 */
	
	public int getAbsoluteY() {
		if(this.parent == null) {
			return y;
		}else {
			return this.parent.getAbsoluteY() + y;
		}
	}
	
	/**
	 * Methode, welche immer aufgerufen wird, wenn sich der PComponent zeichnen soll
	 * 
	 * @param pg Grafik auf welcher sich der PComponent zeichnet
	 */
	
	protected abstract void draw(PGraphics pg);
	
	/**
	 * Gibt zurück, ob sich ein bestimmter Punkt innerhalb des PComponent befindet
	 * 
	 * @param point Punkt welcher geprüft werden soll
	 * @return Wahrheitswert ob der Punkt innerhalb liegt
	 */
	
	public abstract boolean isInside(Point point);
	
	/**
	 * Gibt die Absolute Position des PComponent zurück
	 * Sofern der PComponent ein übergeordneter PComponent hat wird die Position dessen
	 * zusätzlich zu der aktuellen Position dazu berechnet
	 * 
	 * @return Absolute Position
	 */
	
	public Point getAbsoluteLocation() {
		return new Point(getAbsoluteX(), getAbsoluteY());
	}

}
