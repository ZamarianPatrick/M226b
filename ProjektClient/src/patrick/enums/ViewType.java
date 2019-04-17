package patrick.enums;
/**
 * <p>Typen f�r die verschiedenen Ansichten</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public enum ViewType {

	/**
	 * Start�bersicht
	 */
	
	START(null),
	
	/**
	 * Games�bersicht
	 */
	
	GAMES(START),
	
	/**
	 * Gamemen� �bersicht
	 */
	
	GAMEMENU(GAMES),
	
	/**
	 * Runden�bersicht
	 */
	
	ROUNDS(GAMEMENU),
	
	/**
	 * Runde�bersicht
	 */
	
	ROUND(GAMEMENU),
	
	/**
	 * Spielinfo-�bersicht
	 */
	
	GAMEMANUAL(GAMEMENU),
	
	/**
	 * Spieloptionen-�bersicht
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
	 * Liefert die vorherige Ansicht zur�ck
	 * 
	 * @return vorherige Ansicht
	 */
	
	public ViewType getPrevious() {
		return previous;
	}
	
}
