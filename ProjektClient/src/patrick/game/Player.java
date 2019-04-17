package patrick.game;
/**
 * <p>Spieler, welcher sich in der gleichen Spielrunde befindet</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class Player {

	/**
	 * Spielername
	 */
	private final String name;
	
	/**
	 * Erzeugt einen neuen Spieler
	 * 
	 * @param name Name des Spielers
	 */
	public Player(final String name) {
		this.name = name;
	}
	
	/**
	 * Liefert den Namen des Spielers zurück
	 * 
	 * @return Name des Spielers
	 */
	
	public String getName() {
		return this.name;
	}
	
}
