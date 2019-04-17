package patrick.component.interfaces;

import patrick.component.components.PopupField;
/**
 * <p>L�sst sich mit einem PopupField versehen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public interface Popupizable {

	/**
	 * Gibt das aktuell zugewiesene PopupField zur�ck
	 * 
	 * @return Aktuelles PopupField
	 */
	
	public PopupField getPopupField();
	
	/**
	 * Weist dem Komponenten ein PopupField zu
	 * 
	 * @param popupField PopupField welches zugewiesen werden soll
	 */
	
	public void setPopupField(PopupField popupField);
	
}
