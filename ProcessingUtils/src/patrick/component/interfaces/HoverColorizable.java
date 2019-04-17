package patrick.component.interfaces;

import patrick.component.PColor;
import patrick.component.enums.ColorMode;
/**
 * <p>L�sst sich f�rben, wenn die Maus dr�ber f�hrt</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public interface HoverColorizable {

	/**
	 * Gibt den aktuellen Modus zur�ck, wie das Objekt gef�rbt werden soll
	 * 
	 * @return Aktueller F�rbungsmodus
	 */
	
	public ColorMode getHoverColorMode();
	
	/**
	 * Legt den Modus fest, wie das Objekt gef�rbt werden soll
	 * 
	 * @param mode Modus wie das Objekt gef�rbt werden soll
	 */
	
	public void setHoverColorMode(ColorMode mode);
	
	/**
	 * Gibt die aktuelle F�rbungsfarbe beim Hover zur�ck
	 * 
	 * @return Aktuelle F�rbungsfarbe beim Hover
	 */
	
	public PColor getHoverColor();
	
	/**
	 * Legt die F�rbungsfarbe beim Hover fest.
	 * Bevor dies eine Wirkung hat, musst sichergestellt werden, dass 
	 * 'ColorMode == ColorMode.CUSTOM' ist.
	 * 
	 * @param customColor F�rbungsfarbe beim Hover
	 */
	
	public void setHoverColor(PColor customColor);
	
}
