package patrick.component.components;

import patrick.component.enums.AutoSize;
/**
 * <p>Ein PContainer, welcher seine eigene Gr�sse beim hinzuf�gen eines PComponent automatisch
 * anpassen kann.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PAutoSizeContainer extends PContainer{
	
	/**
	 * 
	 * <p>Legt fest, in welche Richtungen der PAutoSizeContainer sicher vergr�ssert.</p>
	 * 
	 */
	private AutoSize autoSize;
	
	/**
	 * 
	 * <p>AutoSize wird standardm�ssig auf <strong>ALL</strong> gesetzt.
	 * 
	 */
	public PAutoSizeContainer() {
		this.autoSize = AutoSize.ALL;
	}
	
	/**
	 * <p>Beim Aufruf dieser Methode vergr�ssert sich der PAutoSizeContainer,
	 * sofern der PComponent, welcher hinzugef�gt wird, ausserhalb des PAutoSizeContainer
	 * liegen w�rde. Der PAutoSizeContainer vergr�ssert sich allerdings nur in die Richtungen,
	 * in welche mit der <strong>AutoSize</strong> festgelegt wurden. Bei der Vergr�sserung
	 * werden jeweils 10 Pixel zus�tzlich dazu gerechnet, um einen Abstand zu den 
	 * PComponents zu gew�hren.</p>
	 * 
	 * @param component Der PComponent, welcher dem PAutoSizeContainer hinzugef�gt werden soll.
	 */
	
	public void add(PComponent component) {
		if(component.getLocation().getX() + component.getWidth() > this.width
				|| component.getLocation().getY() + component.getHeight() > this.height) {
			
			int xAdder = (int)component.getLocation().getX() + component.getWidth() - this.width;
			int yAdder = (int)component.getLocation().getY() + component.getHeight() - this.height;
			
			if(autoSize == AutoSize.ALL || autoSize == AutoSize.HORIZONTAL) {
				if(xAdder > 0) {
					xAdder += 10;
					this.width += xAdder;
				}	
			}
			if(autoSize == AutoSize.ALL || autoSize == AutoSize.VERTICAL) {
				if(yAdder > 0) {
					yAdder += 10;
					this.height += yAdder;
				}	
			}
		}
		addIt(component);
	}
	
	/**
	 * <p>Legt die Eigenschaft AutoSize fest, mit welcher die Richtungen bestimmt werden
	 * k�nnen, in welche sich der PAutoSizeContainer vergr�ssern soll.</p>
	 * 
	 * @param autoSize Die AutoSize welche dem PAutoSizeContainer zugewiesen werden soll.
	 */
	
	public void setAutoSize(AutoSize autoSize) {
		this.autoSize = autoSize;
	}
	
	/**
	 * 
	 * @return Gibt die Eigenschaft AutoSize zur�ck.
	 * 
	 */
	
	public AutoSize getAutoSize() {
		return this.autoSize;
	}
	
}
