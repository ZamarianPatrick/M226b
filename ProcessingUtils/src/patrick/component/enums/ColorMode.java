package patrick.component.enums;
/**
 * <p>Modus, in welcher Form sich eine Farbe ver�ndern soll</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public enum ColorMode {

	/**
	 * Die Farbe wird dunkler sofern sich die Farbe verdunkeln l�sst
	 */
	
	DARKER, 
	
	/**
	 * Die Farbe wird heller sofern sich die Farbe erhellen l�sst
	 */
	
	BRIGHTER, 
	
	/**
	 * Die Farbe �ndert sicht nicht
	 */
	
	NONE,
	
	/**
	 * Es l�sst sich eine genau definierte Farbe festlegen
	 */
	
	CUSTOM;
	
}
