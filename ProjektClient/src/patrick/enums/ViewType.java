package patrick.enums;
/**
 * <p>Typen für die verschiedenen Ansichten</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public enum ViewType {

	/**
	 * Startübersicht
	 */
	
	START(null),
	
	/**
	 * Gamesübersicht
	 */
	
	GAMES(START),
	
	/**
	 * Gamemenü Übersicht
	 */
	
	GAMEMENU(GAMES),
	
	/**
	 * Rundenübersicht
	 */
	
	ROUNDS(GAMEMENU),
	
	/**
	 * Rundeübersicht
	 */
	
	ROUND(GAMEMENU),
	
	/**
	 * Spielinfo-Übersicht
	 */
	
	GAMEMANUAL(GAMEMENU),
	
	/**
	 * Spieloptionen-Übersicht
	 */
	
	GAMEOPTION(GAMEMENU),
	
	/**
	 * Spiel
	 */
	
	GAMING(null)
	;
	
	/**
	 * Vorherige Ansicht
	 */
	
	private ViewType previous;
	
	/**
	 * Erzeugt ein neuer ViewType
	 * 
	 * @param previous Vorherige Ansicht
	 */
	
	ViewType(ViewType previous){
		this.previous = previous;
	}
	
	/**
	 * Liefert die vorherige Ansicht zurück
	 * 
	 * @return vorherige Ansicht
	 */
	
	public ViewType getPrevious() {
		return previous;
	}
	
}
