package patrick.component.enums;
/**
 * <p>Modus, in welcher Form sich eine Farbe verändern soll</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public enum ColorMode {

	/**
	 * Die Farbe wird dunkler sofern sich die Farbe verdunkeln lässt
	 */
	
	DARKER, 
	
	/**
	 * Die Farbe wird heller sofern sich die Farbe erhellen lässt
	 */
	
	BRIGHTER, 
	
	/**
	 * Die Farbe ändert sicht nicht
	 */
	
	NONE,
	
	/**
	 * Es lässt sich eine genau definierte Farbe festlegen
	 */
	
	CUSTOM;
	
}
