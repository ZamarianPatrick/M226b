package patrick.packets.utils;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>Spieleinstellungen, welche vorgenommen werden können bei Erstellung 
 * der Spielrunde</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameOption {

	/**
	 * Verfügbare Spieleranzahl Varianten
	 */
	
	private List<Integer> playerAmount = new ArrayList<Integer>();
	
	/**
	 * Standardwert für die Spieleranzahl
	 */
	
	private int defaultPlayerAmount = 2;
	
	/**
	 * Liste der verfügbaren Optionen
	 */
	
	private List<Option> options = new ArrayList<Option>();
	
	/**
	 * Liefert die verfügbaren Spieleranzahl-Varianten zurück
	 * 
	 * @return Spieleranzahl-Varianten
	 */
	
	public List<Integer> getAvaiblePlayerAmounts(){
		return this.playerAmount;
	}
	
	/**
	 * Definiert die Standard Spieleranzahl-Variante
	 * 
	 * @param defaultPlayerAmount Standard Spieleranzahl-Variante
	 */
	
	public void setDefaultPlayerAmount(int defaultPlayerAmount){
		this.defaultPlayerAmount = defaultPlayerAmount;
	}
	
	/**
	 * Liefert die Standard Spieleranzahl-Variante zurück
	 * 
	 * @return Standard Spieleranzahl-Variante
	 */
	
	public int getDefaultPlayerAmount() {
		return this.defaultPlayerAmount;
	}
	
	/**
	 * Fügt eine neue Spieleranzahl-Variante hinzu
	 * 
	 * @param amount Spieleranzahl-Variante
	 */
	
	public void addPlayerAmount(int amount) {
		this.playerAmount.add(amount);
	}
	
	/**
	 * Fügt eine neue Spieloption hinzu
	 * 
	 * @param option Spieloption
	 */
	
	public void addOption(Option option) {
		this.options.add(option);
	}
	
	/**
	 * Liefert eine Liste aller verfügbaren Spieloptionen zurück
	 * 
	 * @return Liste von allen verfügbaren Spieloptionen
	 */
	
	public List<Option> getOptions(){
		return this.options;
	}
}
