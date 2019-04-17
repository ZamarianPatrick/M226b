package patrick.event.events;

import java.net.Socket;

import patrick.event.ClientEvent;
/**
 * <p>Ein ClientEvent, welcher ausgelöst wird, wenn ein Client versucht, einer Spielrunde 
 * beizutreten. Sollten die vom Client erforderlichen Daten korrekt sein, so wird
 * ein PlayerJoinGameEvent ausgelöst.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class TryJoinRoundEvent extends ClientEvent{

	/**
	 * Name des Spiels von der Runde, welcher der Client versucht beizutreten
	 */
	
	private String gameName;
	
	/**
	 * Name der Runde, welcher der Client versucht beizutreten
	 */
	
	private String roundName;
	
	/**
	 * Spielernamen, mit welchem der Client versucht der Runde beizutreten 
	 */
	
	private String playerName;
	
	/**
	 * Erzeugt ein TryJoinRoundEvent
	 * 
	 * @param client Sockelverbinund des Clients
	 * @param gameName Name des Spiels von der Runde
	 * @param roundName Name der Runde
	 * @param playerName gewünschter Spielername des Clients
	 */
	
	public TryJoinRoundEvent(Socket client, String gameName, String roundName, String playerName) {
		super(client);
		this.gameName = gameName;
		this.roundName = roundName;
		this.playerName = playerName;
	}
	
	/**
	 * Liefert den Names des Spiels zurück, welchem der Client versucht beizutreten
	 * 
	 * @return Name des Spiels
	 */
	
	public String getGameName() {
		return this.gameName;
	}
	
	/**
	 * Liefert den Namen der Runde zurück, welcher der Client beitreten möchte
	 * 
	 * @return Name der Runde
	 */
	
	public String getRoundName() {
		return this.roundName;
	}
	
	/**
	 * Liefert den vom Client gewünschten Spielernamen zurück
	 * 
	 * @return gewünschter Spielername
	 */
	
	public String getPlayerName() {
		return this.playerName;
	}

}
