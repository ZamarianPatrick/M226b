package patrick.game.options;
/**
 * <p>Eine Spieloption, bei welcher ein Wahrheitswert den Einstellungswert darstellt</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class CheckOption extends Option{

	/**
	 * Text, welcher neben der CheckBox angezeigt werden soll
	 */
	
	private String checkBoxText;
	
	/**
	 * Standardwert, welcher die Einstellung hat
	 */
	
	private boolean defaultValue;
	
	/**
	 * Erzeugt eine neue CheckOption
	 * 
	 * @param name Name der Einstellung
	 */
	
	public CheckOption(String name) {
		super(name);
		this.checkBoxText = "";
		defaultValue = false;
	}
	
	/**
	 * Erzeugt eine neue CheckOption
	 * 
	 * @param name Name der Einstellung
	 * @param text Text, welcher oberhalb der CheckBox angezeigt werden soll
	 */
	
	public CheckOption(String name, String text) {
		super(name, text);
		this.checkBoxText = "";
	}
	
	/**
	 * Erzeugt eine neue CheckOption
	 * 
	 * @param name Name der Einstellung
	 * @param text Text, welcher oberhalb der CheckBox angezeigt werden soll
	 * @param checkBoxText CheckBox Text, welcher neben der CheckBox angezeigt werden soll
	 */
	
	public CheckOption(String name, String text, String checkBoxText) {
		super(name, text);
		this.checkBoxText = checkBoxText;
	}
	
	/**
	 * Erzeugt eine neue CheckOption
	 * 
	 * @param name Name der Einstellung
	 * @param text Text, welcher oberhalb der CheckBox angezeigt werden soll
	 * @param checkBoxText Text, welcher neben der CheckBox angezeigt werden soll
	 * @param defaultValue Standard-Wert, welcher die Einstellung haben soll
	 */
	
	public CheckOption(String name, String text, String checkBoxText, boolean defaultValue) {
		super(name, text);
		this.checkBoxText = checkBoxText;
		this.defaultValue = defaultValue;
	}
	
	/**
	 * Liefert den Text zurück, welcher neben der CheckBox angezeigt wird
	 * 
	 * @return Text, welcher neben der CheckBox angezeigt wird
	 */
	
	public String getCheckBoxText() {
		return this.checkBoxText;
	}
	
	/**
	 * Definiert den Text, welcher neben der CheckBox angezeigt werden soll
	 * 
	 * @param checkBoxText Text, welcher neben der CheckBox angezeigt wird
	 */
	
	public void setCheckBoxText(String checkBoxText) {
		this.checkBoxText = checkBoxText;
	}
	
	/**
	 * Definiert den Standard-Wert der Einstellung
	 * 
	 * @param defaultValue Standard-Wert der Einstellung
	 */
	
	public void setDefaultValue(boolean defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	/**
	 * Liefert dein Standard-Wert der Einstellung zurück
	 * 
	 * @return Standard-Wert der Einstellung
	 */
	
	public boolean getDefaultValue() {
		return this.defaultValue;
	}
}
