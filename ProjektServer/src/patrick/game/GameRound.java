package patrick.game;

import java.util.List;

import patrick.Server;
/**
 * <p>�berklasse f�r die wartenden sowie die laufenden Spielrunden</p>
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
	 * Liefert das Spiel zur�ck, von welchem die Runde ist
	 * 
	 * @return Spiel, von welchem die Runde ist
	 */
	
	public Game getGame() {
		return this.game;
	}
	
	/**
	 * Liefert eine Liste von Spielern zur�ck, welche sich in der Runde befinden
	 * 
	 * @return Liste aller Spieler in der Runde
	 */
	
	public List<Player> getPlayers(){
		return Server.getPlayersFromRound(this);
	}
	
	/**
	 * Liefer die gew�hlten Einstellungen der Spielrunde zur�ck
	 * 
	 * @return gew�hlte Einstellungen
	 */
	
	public GameSettings getSettings() {
		return this.settings;
	}
	
	/**
	 * Liefert die gew�hlte Spieleranzahl zur�ck
	 * 
	 * @return gew�hlte Spieleranzahl
	 */
	
	public int getPlayerCount() {
		return settings.getPlayerAmount();
	}
	
	/**
	 * Liefert den Spieler zur�ck, welcher die Runde erstellt hat
	 * 
	 * @return Spieler, welcher die Runde erstellt hat
	 */
	
	public Player getHost() {
		return this.host;
	}

}
