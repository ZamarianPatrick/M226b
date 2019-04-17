package patrick.component.interfaces;

import patrick.component.PColor;
import patrick.component.enums.ColorMode;
/**
 * <p>Lässt sich färben, wenn das Objekt angeklickt wird</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public interface PressColorizable {

	/**
	 * Gibt den aktuellen Modus zurück, wie das Objekt gefärbt werden soll
	 * 
	 * @return Aktueller Färbungsmodus
	 */
	
	public ColorMode getPressColorMode();
	
	/**
	 * Legt den Modus fest, wie das Objekt gefärbt werden soll
	 * 
	 * @param mode Modus wie das Objekt gefärbt werden soll
	 */
	
	public void setPressColorMode(ColorMode mode);
	
	/**
	 * Gibt die aktuelle Färbungsfarbe beim anklicken zurück
	 * 
	 * @return Aktuelle Färbungsfarbe beim anklicken
	 */
	
	public PColor getPressColor();
	
	/**
	 * Legt die Färbungsfarbe beim anklicken fest.
	 * Bevor dies eine Wirkung hat, musst sichergestellt werden, dass 
	 * 'ColorMode == ColorMode.CUSTOM' ist.
	 * 
	 * @param customColor Färbungsfarbe beim anklicken
	 */
	
	public void setPressColor(PColor customColor);
	
}
