package patrick.game;

import java.util.ArrayList;
import java.util.List;

import patrick.component.components.PGameContainer;
/**
 * <p>Spielrunde, die vorbereitet wird für eine GameRound, welche aus der Round instanziiert
 * wird, sobald die Runde startet</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class Round {

	/**
	 * PGameContainer, auf welchem das Spiel ablaufen wird
	 */
	
	protected PGameContainer gameContainer;
	
	/**
	 * Spieleinstellungen, mit welchen die Spielrunde gestartet wird
	 */
	
	private GameSettings settings;
	
	/**
	 * Spieler, welche sich in der Spielrunde befinden werden
	 */
	
	private List<Player> players = new ArrayList<Player>();
	
	/**
	 * Erzeugt eine neue Runde
	 * 
	 * @param container PGameContainer, auf welchem die Spielrunde ablaufen wird
	 * @param settings Spieleinstellungen, mit welchen die Spielrunde gestartet wird
	 * @param players Spieler, welche sich in der Spielrunde befinden werden
	 */
	
	public Round(PGameContainer container, GameSettings settings, List<Player> players) {
		this.gameContainer = container;
		this.settings = settings;
		this.players = players;
	}
	
	/**
	 * Liefert die Spieleinstellungen zurück, mit welchen die Spielrunde gestartet wird
	 * 
	 * @return Spieleinstellungen, mit welchen die Spielrunde gestartet wird
	 */
	
	public GameSettings getSettings() {
		return this.settings;
	}
	
	/**
	 * Liefert den PGameContainer zurück, auf welchem die Spielrunde ablaufen wird
	 * 
	 * @return PGameContainer, auf welchem die Spielrunde ablaufen wird
	 */
	
	public PGameContainer getGameContainer() {
		return this.gameContainer;
	}
	
	/**
	 * Liefert eine Liste der Spieler, welche sich in der Runde befinden werden
	 * 
	 * @return Liste der Spieler, welche sich in der Runde befinden werden
	 */
	
	public List<Player> getPlayers(){
		return this.players;
	}
	
}
