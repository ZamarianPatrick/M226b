package patrick.component.interfaces;

import patrick.component.PColor;
import patrick.component.enums.ColorMode;
/**
 * <p>L�sst sich f�rben, wenn das Objekt angeklickt wird</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public interface PressColorizable {

	/**
	 * Gibt den aktuellen Modus zur�ck, wie das Objekt gef�rbt werden soll
	 * 
	 * @return Aktueller F�rbungsmodus
	 */
	
	public ColorMode getPressColorMode();
	
	/**
	 * Legt den Modus fest, wie das Objekt gef�rbt werden soll
	 * 
	 * @param mode Modus wie das Objekt gef�rbt werden soll
	 */
	
	public void setPressColorMode(ColorMode mode);
	
	/**
	 * Gibt die aktuelle F�rbungsfarbe beim anklicken zur�ck
	 * 
	 * @return Aktuelle F�rbungsfarbe beim anklicken
	 */
	
	public PColor getPressColor();
	
	/**
	 * Legt die F�rbungsfarbe beim anklicken fest.
	 * Bevor dies eine Wirkung hat, musst sichergestellt werden, dass 
	 * 'ColorMode == ColorMode.CUSTOM' ist.
	 * 
	 * @param customColor F�rbungsfarbe beim anklicken
	 */
	
	public void setPressColor(PColor customColor);
	
}
