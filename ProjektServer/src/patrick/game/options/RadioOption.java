package patrick.game.options;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>Spieloption mit mehreren Auswahlm�glichkeiten</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class RadioOption extends Option{

	/**
	 * Liste der Auswahlm�glichkeiten
	 */
	
	private List<String> chooseable = new ArrayList<String>();
	
	/**
	 * Standardwert der Einstellung
	 */
	
	private String defaultValue;
	
	/**
	 * Erzeugt eine neue RadioOption
	 * 
	 * @param name Name der Einstellung
	 */
	
	public RadioOption(String name) {
		super(name);
		defaultValue = "";
	}
	
	/**
	 * Erzeuge eine neue RadioOption
	 * 
	 * @param name Name der Einstellung
	 * @param text Text, welcher oberhalb der RadioOption angezeigt werden soll
	 */
	
	public RadioOption(String name, String text) {
		super(name, text);
	}
	
	/**
	 * F�gt eine neue Auswahlm�glichkeit hinzu
	 * 
	 * @param chooseable Auswahlm�glichkeit
	 */
	
	public void addChooseable(String chooseable) {
		this.chooseable.add(chooseable);
	}
	
	/**
	 * Liefert eine Liste aller Auswahlm�glichkeiten zur�ck
	 * 
	 * @return Liste von allen Auswahlm�glichkeiten
	 */
	
	public List<String> getChooseable(){
		return this.chooseable;
	}
	
	/**
	 * Liefert den Standard-Wert der Einstellung zur�ck
	 * 
	 * @return Standard-Wert der Einstellung
	 */
	
	public String getDefaultValue() {
		return this.defaultValue;
	}
	
	/**
	 * Definiert den Standard-Wert der Einstellung
	 * 
	 * @param defaultValue Standard-Wert der Einstellung
	 */
	
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
