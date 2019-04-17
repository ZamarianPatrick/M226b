package patrick.game;

import java.util.HashMap;
import java.util.Map;
/**
 * <p>Spieleinstellungen, welche für eine Spielrunde vorgenommen wurden</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameSettings {

	/**
	 * Enthält alle Spieleinstellunge mit dem Namen als Key und der Einstellung
	 * als Value
	 */
	public Map<String, String> settings = new HashMap<String, String>();
	
	/**
	 * Liefert den Einstellungswert über den Einstellungsnamen
	 * 
	 * @param key Einstellungsname
	 * 
	 * @return Einstellungswert
	 */
	
	public String get(String key) {
		return settings.get(key);
	}
	
	/**
	 * Fügt eine Spieleinstellung hinzu
	 * 
	 * @param key Einstellungsname
	 * @param value Einstellungswert
	 */
	
	public void addSetting(String key, String value) {
		settings.put(key, value);
	}
	
	/**
	 * Fügt eine Spieleinstellung hinzu
	 * 
	 * @param key Einstellungsname
	 * @param value Einstellungszustand
	 */
	
	public void addSetting(String key, boolean value) {
		settings.put(key, value+"");
	}
	
	/**
	 * Liefert eine Spieleinstellung über den Einstellungsnamen
	 * 
	 * @param key Einstellungsname
	 * @param standard Standard-Zustand, welcher zurückgeliefert wenn die Einstellung nicht existiert
	 *
	 * @return Standard Wert wenn die Einstellung nicht existiert, ansonsten den Einstellungszustand
	 */
	
	public boolean getBoolean(String key, boolean standard) {
		String value = settings.get(key);
		if(value != null) {
			return value.equals("true");
		}
		return standard;
	}
	
}
