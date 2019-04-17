package patrick.game;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>Ein Spiel, mit dem dazugehörenden Namen, der Beschreibung, der Anleitung
 * und der Tastenbelegung</p>
 * 
 * @author Patrick
 *
 */
public class Game {

	/**
	 * Name des Spiels
	 */
	
	private String name;
	
	/**
	 * Beschreibung des Spiels
	 */
	
	private String description;
	
	/**
	 * Anleitung des Spiels
	 */
	
	private String manual;
	
	/**
	 * Tastenbelegungen des Spiels
	 */
	
	private List<String> control;
	
	/**
	 * Erzeugt ein neues Spiel mit dem gegebenem Namen
	 * 
	 * @param name Name des Spiels
	 */
	
	public Game(String name) {
		this.name = name;
		this.control = new ArrayList<String>();
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
	 * Liefert die Anleitung des Spiels zurück
	 * 
	 * @return Anleitung des Spiels
	 */
	
	public String getManual() {
		return this.manual;
	}
	
	/**
	 * Liefert eine Liste aller Tastenbelegungen zurück
	 * 
	 * @return Liste der Tastenbelegungen
	 */
	
	public List<String> getControl() {
		return this.control;
	}
	
	/**
	 * Liefert die Beschreibung des Spiels zurück
	 * 
	 * @return Beschreibung des Spiels
	 */
	
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Definiert die Beschreibung des Spiels
	 * Damit diese sauber und korrekt angezeigt werden kann, sollte darauf geachtet
	 * werden genug Zeilenumbrüche einzufügen. 
	 * 
	 * @param description Beschreibung des Spiels
	 */
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Definiert die Anleitung des Spiels
	 * Damit diese sauber und korrekt angezeigt werden kann, sollte darauf geachtet
	 * werden genug Zeilenumbrüche einzufügen. 
	 * 
	 * @param manual Anleitung des Spiels
	 */
	
	public void setManual(String manual) {
		this.manual = manual;
	}
	
	/**
	 * Fügt eine neue Tastenbelegung hinzu
	 * 
	 * @param control Tastenbelegung
	 */
	
	public void addControl(String control){
		this.control.add(control);
	}
	
}
