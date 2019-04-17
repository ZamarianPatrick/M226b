package patrick.game;

import java.util.List;

import patrick.game.options.CheckOption;
import patrick.game.options.Option;
import patrick.game.options.RadioOption;
/**
 * <p>Ein Spiel mit dem dazugehörenden Setup, Bildname sowie Spielnamen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class Game {
	
	/**
	 * Setup, alle möglichen Einstellungen des Spiels
	 */
	
	private GameSetup setup = new GameSetup();
	
	/**
	 * Name des Anzeigebilds
	 */
	
	private String imgName;
	
	/**
	 * Name des Spiels
	 */
	
	private String name;
	
	/**
	 * Erzeugt ein Game
	 * 
	 * @param name Name des Spiels
	 */
	
	public Game(String name) {
		this.name = name;
		this.imgName = null;
		this.setup.setDefaultPlayerAmount(2);
	}
	
	/**
	 * Definiert den Namen des Anzeigebildes.
	 * Das Bild sollte sich beim Client im Ordner /images mit dem gegebenem Namen befinden.
	 * Ansonsten wird das Anzeigebild durch das Standard Anzeigebild ersetzt.
	 * 
	 * @param imgName Name des Anzeigebildes
	 */
	
	public void setImage(String imgName) {
		this.imgName = imgName;
	}
	
	/**
	 * Definiert den Spielnamen
	 * 
	 * @param name Spielname
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Fügt dem Spiel eine Spieleranzahl-Variante hinzu
	 * 
	 * @param amount Spieleranzahl-Variante
	 */
	
	public void addPlayerAmount(int amount) {
		setup.addPlayerAmount(amount);
	}
	
	/**
	 * Definiert die Standard Spieleranzahl
	 * 
	 * @param defaultPlayerAmount Standard Spieleranzahl
	 */
	
	public void setDefaultPlayerAmount(int defaultPlayerAmount) {
		setup.setDefaultPlayerAmount(defaultPlayerAmount);
	}
	
	/**
	 * Liefert die Standard Spieleranzahl zurück
	 * 
	 * @return Standard Spieleranzahl
	 */
	
	public int getDefaultPlayerAmount() {
		return setup.getDefaultPlayerAmount();
	}
	
	/**
	 * Fügt dem Spiel eine neue mögliche Option hinzu
	 * 
	 * @param option neue Option
	 */
	
	public void addOption(Option option) {
		setup.addOption(option);
	}
	
	/**
	 * Liefert eine Liste von allen möglichen Spieloptionen
	 * 
	 * @return Liste von allen Spieloptionen
	 */
	
	public List<Option> getGameOptions(){
		return setup.getOptions();
	}
	
	/**
	 * <p>Liefert eine validierte Einstellung zurück.
	 * Sollte der Name der Option nicht existierten, so wird null zurück gegeben.
	 * Wenn der Name der Option existiert, jedoch der Einstellungswert keine mögliche Option
	 * ist, so wird der Standardwert dieser Einstellung zurückgegeben.
	 * Wenn der Name der Option existiert und der Einstellungswert eine mögliche Option ist,
	 * so wird der Einstellungswert unverändert zurückgegeben.</p>
	 * 
	 * @param name Name der Einstellungsoption
	 * @param value Einstellungswert
	 * 
	 * @return validierter Einstellungswert
	 */
	
	public String getValidatedSetting(String name, String value) {
		for(Option option : getGameOptions()) {
			if(option.getName().equals(name)) {
				if(option instanceof CheckOption) {
					CheckOption checkOption = (CheckOption) option;
					if(value.equals("true")) {
						return "true";
					}else if(value.equals("false")) {
						return "false";
					}else {
						return checkOption.getDefaultValue()+"";
					}
				}else if(option instanceof RadioOption) {
					RadioOption rOption = (RadioOption) option;
					if(rOption.getChooseable().contains(value)) {
						return value;
					}else {
						return rOption.getDefaultValue();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Liefert die möglichen Spieleranzahl-Varianten zurück
	 * 
	 * @return Liste der möglichen Spieleranzahl-Varianten
	 */
	
	public List<Integer> getAvaiblePlayerAmounts(){
		return setup.getPlayerAmounts();
	}
	
	/**
	 * Liefert den Namen des Spiels zurück
	 * 
	 * @return Name des Spiels
	 */
	
	public String getName() {
		return this.name;
	}
	
	/**
	 * Liefert den Namen des Anzeigebildes zurück
	 * 
	 * @return Name des Anzeigebildes
	 */
	
	public String getImageName() {
		return this.imgName;
	}

	
}
