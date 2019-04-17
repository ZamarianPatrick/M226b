package patrick.game;

import java.util.List;

import patrick.Server;
/**
 * <p>Überklasse für die wartenden sowie die laufenden Spielrunden</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public abstract class GameRound {

	/**
	 * Spiel, von welchem die Runde ist
	 */
	
	protected Game game;
	
	/**
	 * Einstellungen der Runde
	 */
	
	protected GameSettings settings;
	
	/**
	 * Spieler, welcher die Runde erstellt hat
	 */
	
	protected Player host;
	
	/**
	 * Erzeugt eine GameRound
	 * 
	 * @param game Spiel, von welchem die Runde ist
	 * @param settings Einstellungen der Spielrunde
	 * @param host Spieler, welcher die Runde erstellt hat
	 */
	
	public GameRound(Game game, GameSettings settings, Player host) {
		this.game = game;
		this.settings = settings;
		this.host = host;
	}
	
	/**
	 * Erzeugt eine GameRound
	 */
	
	public GameRound() {
		
	}
	
	/**
	 * Liefert das Spiel zurück, von welchem die Runde ist
	 * 
	 * @return Spiel, von welchem die Runde ist
	 */
	
	public Game getGame() {
		return this.game;
	}
	
	/**
	 * Liefert eine Liste von Spielern zurück, welche sich in der Runde befinden
	 * 
	 * @return Liste aller Spieler in der Runde
	 */
	
	public List<Player> getPlayers(){
		return Server.getPlayersFromRound(this);
	}
	
	/**
	 * Liefer die gewählten Einstellungen der Spielrunde zurück
	 * 
	 * @return gewählte Einstellungen
	 */
	
	public GameSettings getSettings() {
		return this.settings;
	}
	
	/**
	 * Liefert die gewählte Spieleranzahl zurück
	 * 
	 * @return gewählte Spieleranzahl
	 */
	
	public int getPlayerCount() {
		return settings.getPlayerAmount();
	}
	
	/**
	 * Liefert den Spieler zurück, welcher die Runde erstellt hat
	 * 
	 * @return Spieler, welcher die Runde erstellt hat
	 */
	
	public Player getHost() {
		return this.host;
	}

}
