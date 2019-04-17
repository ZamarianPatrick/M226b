package patrick.event.events;

import java.net.Socket;

import patrick.event.ClientEvent;
import patrick.game.Game;
/**
 * <p>Ein ClientEvent, welcher ausgelöst wird, wenn ein Client eine neue 
 * Spielrunde hosten will. Der Client hat erstmal eine Anfrage zu den Optionen geschickt.
 * Diese Optionen muss er einstellen und sofern er die gewählten Spieleinstellungen schickt
 * ist dies ein TryCreateRoundEvent. In diesem Event wird also noch keine Runde erstellt
 * sondern nur die Optionen eines Spiels abgefragt.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ClientHostGameEvent extends ClientEvent{

	/**
	 * Spiel, von welchem eine neue Spielrunde erstellt wird
	 */
	
	private Game game;
	
	/**
	 * Erzeugt ein ClientHostGameEvent
	 * 
	 * @param client Client, welcher eine neue Spielrunde erstellt
	 * @param game Spiel, von welchem eine neue Spielrunde erstellt wird
	 */
	
	public ClientHostGameEvent(Socket client, Game game) {
		super(client);
		this.game = game;
	}
	
	/**
	 * Liefert das Spiel zurück, von welchem eine neue Spielrunde erstellt wird
	 * 
	 * @return Spiel, von welchem eine neue Spielrunde erstellt wird
	 */
	
	public Game getGame() {
		return this.game;
	}

}
