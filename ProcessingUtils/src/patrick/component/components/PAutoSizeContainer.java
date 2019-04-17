package patrick.component.components;

import patrick.component.enums.AutoSize;
/**
 * <p>Ein PContainer, welcher seine eigene Grösse beim hinzufügen eines PComponent automatisch
 * anpassen kann.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PAutoSizeContainer extends PContainer{
	
	/**
	 * 
	 * <p>Legt fest, in welche Richtungen der PAutoSizeContainer sicher vergrössert.</p>
	 * 
	 */
	private AutoSize autoSize;
	
	/**
	 * 
	 * <p>AutoSize wird standardmässig auf <strong>ALL</strong> gesetzt.
	 * 
	 */
	public PAutoSizeContainer() {
		this.autoSize = AutoSize.ALL;
	}
	
	/**
	 * <p>Beim Aufruf dieser Methode vergrössert sich der PAutoSizeContainer,
	 * sofern der PComponent, welcher hinzugefügt wird, ausserhalb des PAutoSizeContainer
	 * liegen würde. Der PAutoSizeContainer vergrössert sich allerdings nur in die Richtungen,
	 * in welche mit der <strong>AutoSize</strong> festgelegt wurden. Bei der Vergrösserung
	 * werden jeweils 10 Pixel zusätzlich dazu gerechnet, um einen Abstand zu den 
	 * PComponents zu gewähren.</p>
	 * 
	 * @param component Der PComponent, welcher dem PAutoSizeContainer hinzugefügt werden soll.
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
	 * können, in welche sich der PAutoSizeContainer vergrössern soll.</p>
	 * 
	 * @param autoSize Die AutoSize welche dem PAutoSizeContainer zugewiesen werden soll.
	 */
	
	public void setAutoSize(AutoSize autoSize) {
		this.autoSize = autoSize;
	}
	
	/**
	 * 
	 * @return Gibt die Eigenschaft AutoSize zurück.
	 * 
	 */
	
	public AutoSize getAutoSize() {
		return this.autoSize;
	}
	
}
