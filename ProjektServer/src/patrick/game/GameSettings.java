package patrick.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import patrick.game.options.CheckOption;
import patrick.game.options.RadioOption;
/**
 * <p>Wird f�r die gew�hlten Spieleinstellungen einer Spielrunde verwendet</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameSettings {

	/**
	 * gew�hlte Spieleranzahl der Runde
	 */
	
	private int playerAmount;
	
	/**
	 * gew�hlte CheckBox Einstellungen
	 */
	
	private Map<String, Boolean> checkBoxes = new HashMap<String, Boolean>();
	
	/**
	 * gew�hlte RadioButton Einstellungen
	 */
	
	private Map<String, String> chooseable = new HashMap<String, String>();
	
	/**
	 * Erzeugt eine GameSettings
	 * 
	 * @param playerAmount gew�hlte Spieleranzahl
	 */
	
	public GameSettings(int playerAmount) {
		this.playerAmount = playerAmount;
	}
	
	/**
	 * Liefert die gew�hlten RadioButton Einstellungen zur�ck
	 * 
	 * @return gew�hlte RadioButton Einstellungen
	 */
	
	public Map<String, String> getChooseables(){
		return chooseable;
	}
	
	/**
	 * Liefert die gew�hlten CheckBox Einstellungen zur�ck
	 * 
	 * @return gew�hlte CheckBox Einstellungen
	 */
	
	public Map<String, Boolean> getCheckSettings(){
		return this.checkBoxes;
	}
	
	/**
	 * Liefert die CheckBox Einstellungen als Strings zur�ck
	 * 
	 * @return gew�hlte CheckBox Einstellungen
	 */
	
	public Map<String, String> getCheckSettingsAsString(){
		Map<String, String> map = new HashMap<String, String>();
		for(Entry<String, Boolean> entry : checkBoxes.entrySet()) {
			map.put(entry.getKey(), entry.getValue()+"");
		}
		return map;
	}
	
	/**
	 * F�gt eine gew�hlte CheckBox Einstellung hinzu
	 * 
	 * @param option Einstellungsoption
	 * @param value Einstellungswert
	 */
	
	public void addCheckSetting(CheckOption option, boolean value) {
		checkBoxes.put(option.getName(), value);
	}
	
	/**
	 * F�gt eine gew�hlte RadioButton Einstellung hinzu
	 * 
	 * @param option Einstellungsoption
	 * @param value Einstellungswert
	 */
	
	public void addChooseable(RadioOption option, String value) {
		chooseable.put(option.getName(), value);
	}
	
	/**
	 * F�gt eine gew�hlte CheckBox Einstellung hinzu
	 * 
	 * @param setting Einstellungsname
	 * @param value Einstellungswert
	 */
	
	public void addCheckSetting(String setting, boolean value) {
		checkBoxes.put(setting, value);
	}
	
	/**
	 * F�gt eine gew�hlte RadioButton Einstellung hinzu
	 * 
	 * @param setting Einstellungsname
	 * @param value Einstellungswert
	 */
	
	public void addChooseable(String setting, String value) {
		chooseable.put(setting, value);
	}
	
	/**
	 * Liefert eine gew�hlte CheckBox Einstellung �ber den Namen zur�ck.
	 * Sollte zuerst mit der Methode hasCheckSetting �berpr�ft werden, um sicherzustellen
	 * dass die Einstellung �berhaupt existiert, denn ein Boolean kann nicht als null zur�ck
	 * gegeben werden.
	 * 
	 * @param setting Einstellungsname
	 * 
	 * @return Einstellungswert
	 */
	
	public boolean getCheckSetting(String setting) {
		return checkBoxes.get(setting);
	}
	
	/**
	 * Pr�ft, ob eine CheckBox Einstellung mit dem gegebenen Namen existiert
	 * 
	 * @param setting Einstellungsname
	 * 
	 * @return true wenn die Einstellung existiert, false wenn die Einstellung nicht existiert
	 */
	
	public boolean hasCheckSetting(String setting) {
		return checkBoxes.containsKey(setting);
	}
	
	/**
	 * Liefert eine RadioButton Einstellung �ber den Namen zur�ck
	 * 
	 * @param setting Einstellungsname
	 * 
	 * @return Einstellungswert
	 */
	
	public String getChooseable(String setting) {
		return chooseable.get(setting);
	}
	
	/**
	 * Liefert die gew�hlte Spieleranzahl zur�ck
	 * 
	 * @return gew�hlte Spieleranzahl
	 */
	
	public int getPlayerAmount() {
		return this.playerAmount;
	}
	
	
}
