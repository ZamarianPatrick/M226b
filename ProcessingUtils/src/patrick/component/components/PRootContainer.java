package patrick.component.components;

import java.util.ArrayList;
import java.util.List;
import patrick.component.action.ActionListener;
import patrick.component.action.ClickListener;
import patrick.component.action.MoveListener;
import patrick.component.action.PressListener;
import patrick.component.interfaces.InvokeAtDrawEnd;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
/**
 * <p>Der Container, welcher alle PComponents auf einem PApplet speichert und
 * verwaltet.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PRootContainer{
	
	/**
	 * PApplet, auf welchem sich der PRootContainer befindet
	 */
	private PApplet applet;
	
	/**
	 * Der vom User fokusierte PComponent
	 */
	
	PComponent focused;
	
	/**
	 * Der PComponent, welcher sich unter dem Mauszeiger befindet
	 */
	
	PComponent hover;
	
	/**
	 * Zustand ob der PRootContainer aktiviert ist
	 */
	
	private boolean activated;
	
	/**
	 * PContainer, auf welchem sich alle PComponents befinden
	 */
	
	private PContainer mainContainer;
	
	/**
	 * Liste von Methoden, welche sich am Ende jeder draw Schleife ausführen
	 */
	
	private List<InvokeAtDrawEnd> atDrawEnd = new ArrayList<InvokeAtDrawEnd>();
	
	/**
	 * Liste von ActionListener, welche sich auf dem PRootContainer befinden
	 */
	
	private List<ActionListener> actionListeners = new ArrayList<ActionListener>();
	
	/**
	 * Liste von PComponents, welche sich vor einem Aufruf der clear Methode auf 
	 * dem PContainer befunden haben.
	 */
	
	private List<PComponent> undoSave;
	
	/**
	 * Erzeugt einen neuen PRootContainer
	 * 
	 * @param applet PApplet, auf welchem sich der PRootContainer befinden soll
	 */
	public PRootContainer(PApplet applet) {
		this.applet = applet;
		this.applet.registerMethod("draw", this);
		this.applet.registerMethod("mouseEvent", this);
		this.applet.registerMethod("keyEvent", this);
		this.focused = null;
		this.activated = true;
		this.mainContainer = new PContainer();
		this.mainContainer.rootContainer = this;
		this.mainContainer.setSize(applet.width, applet.height);
		this.mainContainer.x = 0;
		this.mainContainer.y = 0;
	}
	
	/**
	 * @see patrick.component.components.PContainer#add(patrick.component.components.PComponent) 
	 */
	
	public void add(PComponent pComponent){
		pComponent.rootContainer = this;
		if(pComponent instanceof PContainer) {
			PContainer cont = (PContainer) pComponent;
			for(PComponent comp : getAllComponents(cont)) {
				comp.rootContainer = this;
			}
		}
		if(pComponent instanceof PScrollableContainer) {
			PScrollableContainer scroll = (PScrollableContainer) pComponent;
			if(scroll.getViewContent() != null) {
				PContainer scrollView = scroll.getViewContent();
				scrollView.rootContainer = this;
				for(PComponent comp : getAllComponents(scrollView)) {
					comp.rootContainer = this;
				}
			}
		}
		mainContainer.add(pComponent);
	}
	
	/**
	 * Gibt die Liste aller hinzugefügten Komponente zurück
	 * 
	 * @return Liste aller hinzugefügten Komponente
	 */
	
	public List<PComponent> getComponents() {
		return mainContainer.getComponents();
	}
	
	/**
	 * Entfernt alle hinzugefügten PComponents, speichert diese jedoch um die 
	 * Entfernung rückgängig zu machen mit der Methode undoClear()
	 */
	
	public void clear() {
		this.undoSave = new ArrayList<PComponent>(mainContainer.components);
		this.mainContainer.components.clear();
	}
	
	/**
	 * Entfernt die Speicherung für die Wiederherstellung von der clear() Methode
	 */
	
	public void removeUndo() {
		this.undoSave = null;
	}
	
	/**
	 * Versucht den letzten Aufruf der clear() Methode rückgängig zu machen
	 * 
	 * @return Wahrheitswert, ob die Wiederherstellung erfolgt ist oder nicht
	 */
	
	public boolean undoClear() {
		if(undoSave != null) {
			this.mainContainer.components.clear();
			this.mainContainer.components.addAll(undoSave);
			this.undoSave = null;
			return true;
		}
		return false;
	}
	
	/**
	 * Legt den Zustand fest, ob der PRootContainer aktiviert ist.
	 * Wenn der PRootContainer deaktiviert ist werden keine Aktionen mehr ausgeführt
	 * und sämtliche PComponents sind unsichtbar.
	 * 
	 * @param activated Zustand für die Aktivierung
	 */
	
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	/**
	 * Gibt den aktuellen Zustand der Aktivierung des PRootContainers zurück
	 * 
	 * @return Zustand der Aktivierung
	 */
	
	public boolean isActivated() {
		return this.activated;
	}
	
	/**
	 * Diese Methode wird jeweils am Ende jeder Draw Schleife ausgeführt.
	 * Sie sollte nicht andersweitig aufgerufen werden.
	 */
	
	public final void draw(){
		if(activated == false) {
			return;
		}
		PGraphics pg = applet.createGraphics(applet.width, applet.height);
		pg.beginDraw();
		mainContainer.draw(pg);
		for(InvokeAtDrawEnd atEnd : atDrawEnd) {
			atEnd.atDrawEnd(pg);
		}
		pg.endDraw();
		applet.image(pg.get(),0,0);
		atDrawEnd.clear();
	}
	
	/**
	 * Diese Methode wird jeweils bei einer Mausaktion ausgeführt.
	 * Sie sollte nicht andersweitig aufgerufen werden.
	 * 
	 * @param e Mausaktion, welche aufgerufen wurde
	 */
	
	public void mouseEvent(MouseEvent e) {
		if(activated == false) {
			return;
		}
		boolean setFocus = mainContainer.onMouse(e);
		for(ActionListener listener : actionListeners) {
			if(listener instanceof ClickListener && e.getAction() == MouseEvent.CLICK) {
				((ClickListener) listener).onClick(e);
			}else if(listener instanceof MoveListener && e.getAction() == MouseEvent.MOVE) {
				((MoveListener) listener).onMouseMove(e);
			}else if(listener instanceof PressListener && e.getAction() == MouseEvent.PRESS) {
				((PressListener) listener).onPressed(e);
			}
		}
		if(e.getAction() == MouseEvent.CLICK) {
			if(setFocus == false) {
				this.focused = null;
			}
		}
	}
	
	/**
	 * Diese Methode wird jeweils bei einer Tastenaktion ausgeführt.
	 * Sie sollte nicht andersweitig aufgerufen werden.
	 * 
	 * @param e KeyEvent, welcher aufgerufen wurde
	 */
	
	public void keyEvent(KeyEvent e) {
		if(activated == false) {
			return;
		}
		if(focused != null && focused.isVisible() == true) {
			focused.keyAction(e);
		}
	}
	
	/**
	 * @see patrick.component.components.PComponent#addActionListener(ActionListener)
	 */
	
	public void addActionListener(ActionListener listener) {
		this.actionListeners.add(listener);
	}
	
	/**
	 * Gibt den vom User fokusierten PComponent zurück
	 * 
	 * @return null wenn keiner fokusiert ist, ansonsten den fokusierten PComponent
	 */
	
	public PComponent getFocusedComponent() {
		return this.focused;
	}
	
	/**
	 * Gibt den PComponent zurück, welcher sich unter dem Mauszeiger befindet
	 * 
	 * @return null wenn sich keiner unter dem Mauszeiger befindet,
	 * Ansonsten der PComponent, welcher sich unter dem Mauszeiger befindet
	 */
	
	public PComponent getHoveredComponent() {
		return this.hover;
	}
	
	/**
	 * Gibt das PApplet zurück, auf welchem sich der PRootContainer befindet
	 * 
	 * @return PApplet, auf welchem sich der PRootContainer befindet
	 */
	
	public PApplet getPApplet() {
		return this.applet;
	}
	
	/**
	 * Gibt alle PComponents zurück
	 * 
	 * @param cont PContainer, von welchem man die PComponente haben will
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
	 * Fügt eine Methode hinzu, welche am Ende jeder Draw Schleife ausgeführt werden soll
	 * 
	 * @param invoke Methode, welche am Ende der Draw Schleife ausgeführt werden soll
	 */
	
	public void addInvokeAtEnd(InvokeAtDrawEnd invoke) {
		this.atDrawEnd.add(invoke);
	}
}
