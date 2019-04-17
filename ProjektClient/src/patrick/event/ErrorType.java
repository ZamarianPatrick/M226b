package patrick.event;

import java.util.HashMap;
import java.util.Map;
/**
 * <p>verschiedene Fehlertypen, welche auftreten können</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public enum ErrorType {

	/**
	 * Verbotener Spielername, tritt auf wenn der Client versucht,
	 * mit einem ungültigen Namen einer Runde beizutreten
	 */
	
	FORBIDDEN_PLAYERNAME(-2),
	
	/**
	 * Es konnte keine Verbindung zum Server hergestellt werden
	 */
	
	NO_CONNECTION(-1),
	
	/**
	 * Spielername existiert bereits, tritt auf wenn der Client versucht,
	 * mit einem Namen welcher bereits vergeben ist, einer Spielrunde beizutreten
	 */
	
	PLAYER_ALREADY_EXIST(1),
	
	/**
	 * Spiel existiert nicht, tritt auf wenn die Spielrunde bereits gelöscht wurde
	 */
	
	GAME_DONT_EXIST(2),
	
	/**
	 * Spielrunde hat bereits gestartet, tritt auf wenn die Spielrunde bereits gestartet
	 * hat, welcher der Client beitreten wollte
	 */
	
	GAME_ALREADY_START(3),
	
	/**
	 * Die Spielrunde, welcher der Client beitreten wollte, ist bereits voll
	 */
	
	FULL_ROUND(4)
	;
	
	/**
	 * Identifikationsnummer des Fehlers
	 */
	
	private int error;
	
	/**
	 * Alle Fehlermeldungen
	 */
	
	private static Map<Object, Object> map = new HashMap<>();
	
	/**
	 * Erzeugt einen neuen Fehlertyp
	 * 
	 * @param error Identifikationsnummer des Fehlers
	 */
	
	ErrorType(int error) {
		this.error = error;
	}
	
	/**
	 * Alle Fehlertypen der map hinzufügen
	 */
	
    static {
        for (ErrorType err : ErrorType.values()) {
            map.put(err.error, err);
        }
    }
    
    /**
     * Liefert einen Fehlertyp anhand der Identifikationsnummer zurück
     * 
     * @param error Identifikationsnummer des Fehlers
     * 
     * @return null wenn der Fehler nicht existiert, ansonsten den Fehlertypen
     */
    
    public static ErrorType valueOf(int error) {
        return (ErrorType) map.get(error);
    }
    
    /**
     * Liefert die Fehlertyp Identifikationsnummer zurück
     * 
     * @return Identifikationsnummer des Fehlers
     */
    
	public int getError() {
		return this.error;
	}
	
}
