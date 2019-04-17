package patrick.packets.utils;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>Spieleinstellungen, welche vorgenommen werden k�nnen bei Erstellung 
 * der Spielrunde</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameOption {

	/**
	 * Verf�gbare Spieleranzahl Varianten
	 */
	
	private List<Integer> playerAmount = new ArrayList<Integer>();
	
	/**
	 * Standardwert f�r die Spieleranzahl
	 */
	
	private int defaultPlayerAmount = 2;
	
	/**
	 * Liste der verf�gbaren Optionen
	 */
	
	private List<Option> options = new ArrayList<Option>();
	
	/**
	 * Liefert die verf�gbaren Spieleranzahl-Varianten zur�ck
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
	 * Liefert die Standard Spieleranzahl-Variante zur�ck
	 * 
	 * @return Standard Spieleranzahl-Variante
	 */
	
	public int getDefaultPlayerAmount() {
		return this.defaultPlayerAmount;
	}
	
	/**
	 * F�gt eine neue Spieleranzahl-Variante hinzu
	 * 
	 * @param amount Spieleranzahl-Variante
	 */
	
	public void addPlayerAmount(int amount) {
		this.playerAmount.add(amount);
	}
	
	/**
	 * F�gt eine neue Spieloption hinzu
	 * 
	 * @param option Spieloption
	 */
	
	public void addOption(Option option) {
		this.options.add(option);
	}
	
	/**
	 * Liefert eine Liste aller verf�gbaren Spieloptionen zur�ck
	 * 
	 * @return Liste von allen verf�gbaren Spieloptionen
	 */
	
	public List<Option> getOptions(){
		return this.options;
	}
}
