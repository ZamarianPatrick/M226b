package patrick.utils;
/**
 * <p>Statische Klasse für diverse Nachrichten</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class Message {
	
	public static final String noConnection = "Verbindung zum Server konnte nicht hergestellt werden!";
	public static final String noGame = "Dieses Spiel ist leider nicht installiert";
	public static final String forbiddenName = "Als Spielername sind nur Buchstaben zulässig";
	public static final String fullRound = "Diese Runde ist bereits voll!";
	public static final String gameAlreadyStart = "Dieses Spiel hat bereits gestartet";
	public static final String gameDontExists = "Dieses Spiel existiert leider nicht mehr";
	public static final String playerAlreadyExists = "Dieser Spielername ist bereits vergeben";
	
	/**
	 * Liefert die Nachricht, welche an den Server geschickt werden muss,
	 * wenn man eine Spielrunde erstellen möchte
	 * 
	 * @param gameName Name des Spiels
	 * 
	 * @return Nachricht die versendet werden soll
	 */
	
	public static final String hostGame(String gameName) {
		return "game:host:"+gameName;
	}
	
	/**
	 * Liefert die Nachricht, welche an den Server geschickt werden muss,
	 * Wenn man die verfügbaren Runden eines Spiels erhalten will.
	 * 
	 * @param gameName Name des Spiels
	 * 
	 * @return Nachricht, die versendet werden soll
	 */
	
	public static final String getRounds(String gameName) {
		return "join:"+gameName;
	}
	
	/**
	 * Liefert die Nachricht, welche an den Server geschickt werden muss,
	 * wenn man einer Spielrunde beitreten möchte.
	 * 
	 * @param gameName Name des Spiels
	 * @param roundName Name des Hosters der Spielrunde
	 * @param playerName Spielername, welcher man verwenden möchte
	 * 
	 * @return Nachricht, die versendet werden soll
	 */
	
	public static final String joinRound(String gameName, String roundName, String playerName) {
		return "join:"+gameName+":"+roundName+":"+playerName;
	}
	
}
