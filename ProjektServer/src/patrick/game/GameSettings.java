package patrick.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import patrick.game.options.CheckOption;
import patrick.game.options.RadioOption;
/**
 * <p>Wird für die gewählten Spieleinstellungen einer Spielrunde verwendet</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameSettings {

	/**
	 * gewählte Spieleranzahl der Runde
	 */
	
	private int playerAmount;
	
	/**
	 * gewählte CheckBox Einstellungen
	 */
	
	private Map<String, Boolean> checkBoxes = new HashMap<String, Boolean>();
	
	/**
	 * gewählte RadioButton Einstellungen
	 */
	
	private Map<String, String> chooseable = new HashMap<String, String>();
	
	/**
	 * Erzeugt eine GameSettings
	 * 
	 * @param playerAmount gewählte Spieleranzahl
	 */
	
	public GameSettings(int playerAmount) {
		this.playerAmount = playerAmount;
	}
	
	/**
	 * Liefert die gewählten RadioButton Einstellungen zurück
	 * 
	 * @return gewählte RadioButton Einstellungen
	 */
	
	public Map<String, String> getChooseables(){
		return chooseable;
	}
	
	/**
	 * Liefert die gewählten CheckBox Einstellungen zurück
	 * 
	 * @return gewählte CheckBox Einstellungen
	 */
	
	public Map<String, Boolean> getCheckSettings(){
		return this.checkBoxes;
	}
	
	/**
	 * Liefert die CheckBox Einstellungen als Strings zurück
	 * 
	 * @return gewählte CheckBox Einstellungen
	 */
	
	public Map<String, String> getCheckSettingsAsString(){
		Map<String, String> map = new HashMap<String, String>();
		for(Entry<String, Boolean> entry : checkBoxes.entrySet()) {
			map.put(entry.getKey(), entry.getValue()+"");
		}
		return map;
	}
	
	/**
	 * Fügt eine gewählte CheckBox Einstellung hinzu
	 * 
	 * @param option Einstellungsoption
	 * @param value Einstellungswert
	 */
	
	public void addCheckSetting(CheckOption option, boolean value) {
		checkBoxes.put(option.getName(), value);
	}
	
	/**
	 * Fügt eine gewählte RadioButton Einstellung hinzu
	 * 
	 * @param option Einstellungsoption
	 * @param value Einstellungswert
	 */
	
	public void addChooseable(RadioOption option, String value) {
		chooseable.put(option.getName(), value);
	}
	
	/**
	 * Fügt eine gewählte CheckBox Einstellung hinzu
	 * 
	 * @param setting Einstellungsname
	 * @param value Einstellungswert
	 */
	
	public void addCheckSetting(String setting, boolean value) {
		checkBoxes.put(setting, value);
	}
	
	/**
	 * Fügt eine gewählte RadioButton Einstellung hinzu
	 * 
	 * @param setting Einstellungsname
	 * @param value Einstellungswert
	 */
	
	public void addChooseable(String setting, String value) {
		chooseable.put(setting, value);
	}
	
	/**
	 * Liefert eine gewählte CheckBox Einstellung über den Namen zurück.
	 * Sollte zuerst mit der Methode hasCheckSetting überprüft werden, um sicherzustellen
	 * dass die Einstellung überhaupt existiert, denn ein Boolean kann nicht als null zurück
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
	 * Prüft, ob eine CheckBox Einstellung mit dem gegebenen Namen existiert
	 * 
	 * @param setting Einstellungsname
	 * 
	 * @return true wenn die Einstellung existiert, false wenn die Einstellung nicht existiert
	 */
	
	public boolean hasCheckSetting(String setting) {
		return checkBoxes.containsKey(setting);
	}
	
	/**
	 * Liefert eine RadioButton Einstellung über den Namen zurück
	 * 
	 * @param setting Einstellungsname
	 * 
	 * @return Einstellungswert
	 */
	
	public String getChooseable(String setting) {
		return chooseable.get(setting);
	}
	
	/**
	 * Liefert die gewählte Spieleranzahl zurück
	 * 
	 * @return gewählte Spieleranzahl
	 */
	
	public int getPlayerAmount() {
		return this.playerAmount;
	}
	
	
}
