package patrick.component.components;

import java.util.ArrayList;
import java.util.List;

import patrick.component.components.game.GameObject;
import patrick.component.interfaces.Destroyable;
import processing.core.PGraphics;
/**
 * <p>Ein PContainer, mit der Erweiterung von GameObjects</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PGameContainer extends PContainer{

	/**
	 * Liste der hinzugef�gten GameObjects
	 */
	
	private List<GameObject> objects = new ArrayList<GameObject>();
	
	/**
	 * Methode, welche vom jeweilig �bergeordneten PComponent aufgerufen wird, wenn
	 * sich der PContainer zeichnen soll.
	 * 
	 * @param pg Grafik, auf welcher sich der Container zeichnen soll
	 */
	
	@Override
	public void draw(PGraphics pg) {
		List<GameObject> toRemove = new ArrayList<GameObject>();
		for(GameObject obj : objects) {
			obj.display(pg);
			if(obj instanceof Destroyable) {
				Destroyable destroy = (Destroyable) obj;
				if(destroy.getLiveTicks() > 0) {
					destroy.setLiveTicks(destroy.getLiveTicks()-1);
				}
				if(destroy.isDestroyed() || destroy.getLiveTicks() == 0) {
					toRemove.add(obj);
				}
			}
		}
		objects.removeAll(toRemove);
		drawIt(pg);
	}
	
	/**
	 * F�gt dem PGameContainer ein GameObject hinzu
	 * 
	 * @param obj GameObject, welches hinzugef�gt werden soll
	 */
	
	public void add(GameObject obj) {
		this.objects.add(obj);
	}
	
	/**
	 * Gibt eine Liste aller hinzugef�gten GameObjects zur�ck
	 * 
	 * @return Liste aller hinzugef�gten GameObjects
	 */
	
	public List<GameObject> getGameObjects(){
		return objects;
	}
	
}
