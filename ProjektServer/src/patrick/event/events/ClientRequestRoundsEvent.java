package patrick.event.events;

import java.net.Socket;

import patrick.Server;
import patrick.event.ClientEvent;
import patrick.game.Game;
/**
 * <p>Ein ClientEvent, welcher ausgelöst wird, wenn ein Client eine Anfrage über laufende
 * Runden an den Server schickt</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ClientRequestRoundsEvent extends ClientEvent{

	/**
	 * Spiel, von welchem der Client die Runden haben möchte
	 */
	private Game game;
	
	/**
	 * Spielname, von welchem der Client die Runden haben möchte
	 */
	
	private String gameName;

	/**
	 * Erzeugt ein ClientRequestRoundsEvent
	 * 
	 * @param client Client, welcher die Anfrage versendet hat
	 * @param gameName Spielname zu welchem Spiel der Client die Runden haben möchte
	 */
	
	public ClientRequestRoundsEvent(Socket client, String gameName) {
		super(client);
		this.gameName = gameName;
		this.game = Server.getGame(gameName);
	}
	
	/**
	 * Liefert das Spiel zurück, zu welchem der Client die Runden haben möchte
	 * 
	 * @return null wenn das Spiel mit dem gegebenen Namen nicht existiert, ansonsten das Spiel
	 */
	
	public Game getGame() {
		return this.game;
	}
	
	/**
	 * Liefert den Spielname zurück, welcher der Client gesendet hat
	 * 
	 * @return Name des Spiels, welcher der Client gesendet hat
	 */
	
	public String getGameName(){
		return this.gameName;
	}
	
	
}
