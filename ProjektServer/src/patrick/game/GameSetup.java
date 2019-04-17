package patrick.game;

import java.util.List;

import patrick.game.options.GameOption;
import patrick.game.options.Option;
/**
 * <p>Enthält die möglichen Einstellungsoptionen für ein Spiel</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameSetup {
	
	/**
	 * Die möglichen Spieloptionen
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
	 * Fügt eine neue Spieleranzahl-Variante hinzu
	 * 
	 * @param amount Spieleranzahl
	 */
	
	public void addPlayerAmount(int amount) {
		this.gameOption.addPlayerAmount(amount);
	}
	
	/**
	 * Liefert die möglichen Spieleranzahl-Varianten
	 * 
	 * @return mögliche Spieleranzahl-Varianten
	 */
	
	public List<Integer> getPlayerAmounts(){
		return this.gameOption.getAvaiblePlayerAmounts();
	}
	
	/**
	 * Liefert die möglichen Einstellungsoptionen zurück
	 * 
	 * @return mögliche Einstellungsoptionen
	 */
	
	public List<Option> getOptions(){
		return this.gameOption.getOptions();
	}
	
	/**
	 * Fügt eine neue Spieloption hinzu
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
