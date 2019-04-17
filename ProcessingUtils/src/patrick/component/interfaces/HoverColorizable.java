package patrick.component.interfaces;

import patrick.component.PColor;
import patrick.component.enums.ColorMode;
/**
 * <p>Lässt sich färben, wenn die Maus drüber fährt</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public interface HoverColorizable {

	/**
	 * Gibt den aktuellen Modus zurück, wie das Objekt gefärbt werden soll
	 * 
	 * @return Aktueller Färbungsmodus
	 */
	
	public ColorMode getHoverColorMode();
	
	/**
	 * Legt den Modus fest, wie das Objekt gefärbt werden soll
	 * 
	 * @param mode Modus wie das Objekt gefärbt werden soll
	 */
	
	public void setHoverColorMode(ColorMode mode);
	
	/**
	 * Gibt die aktuelle Färbungsfarbe beim Hover zurück
	 * 
	 * @return Aktuelle Färbungsfarbe beim Hover
	 */
	
	public PColor getHoverColor();
	
	/**
	 * Legt die Färbungsfarbe beim Hover fest.
	 * Bevor dies eine Wirkung hat, musst sichergestellt werden, dass 
	 * 'ColorMode == ColorMode.CUSTOM' ist.
	 * 
	 * @param customColor Färbungsfarbe beim Hover
	 */
	
	public void setHoverColor(PColor customColor);
	
}
