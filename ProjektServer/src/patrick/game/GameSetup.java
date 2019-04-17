package patrick.game;

import java.util.List;

import patrick.game.options.GameOption;
import patrick.game.options.Option;
/**
 * <p>Enth�lt die m�glichen Einstellungsoptionen f�r ein Spiel</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameSetup {
	
	/**
	 * Die m�glichen Spieloptionen
	 */
	
	private GameOption gameOption;
	
	/**
	 * Erzeugt ein GameSetup
	 * 
	 */
	
	public GameSetup() {
		this.gameOption = new GameOption();
	}
	
	/**
	 * F�gt eine neue Spieleranzahl-Variante hinzu
	 * 
	 * @param amount Spieleranzahl
	 */
	
	public void addPlayerAmount(int amount) {
		this.gameOption.addPlayerAmount(amount);
	}
	
	/**
	 * Liefert die m�glichen Spieleranzahl-Varianten
	 * 
	 * @return m�gliche Spieleranzahl-Varianten
	 */
	
	public List<Integer> getPlayerAmounts(){
		return this.gameOption.getAvaiblePlayerAmounts();
	}
	
	/**
	 * Liefert die m�glichen Einstellungsoptionen zur�ck
	 * 
	 * @return m�gliche Einstellungsoptionen
	 */
	
	public List<Option> getOptions(){
		return this.gameOption.getOptions();
	}
	
	/**
	 * F�gt eine neue Spieloption hinzu
	 * 
	 * @param option Spieloption
	 */
	
	public void addOption(Option option) {
		this.gameOption.addOption(option);
	}
	
	/**
	 * Definiert die Standart Spieleranzahl
	 * 
	 * @param defaultPlayerAmount Standard Spieleranzahl
	 */
	
	public void setDefaultPlayerAmount(int defaultPlayerAmount) {
		gameOption.setDefaultPlayerAmount(defaultPlayerAmount);
	}
	
	/**
	 * Liefert die Standard Spieleranzahl
	 * 
	 * @return Standard Spieleranzahl
	 */
	
	public int getDefaultPlayerAmount() {
		return gameOption.getDefaultPlayerAmount();
	}
	
}
