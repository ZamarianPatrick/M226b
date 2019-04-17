package patrick.event.events;

import java.net.Socket;

import patrick.event.Event;
import patrick.packets.in.GameSettingsPacket;
/**
 * <p>Ein Event, welcher ausgelöst wird, wenn ein Client versucht, eine Runde zu erstellen.
 * Sollten die Daten des Clients valid sein, welche benötigt werden um eine Runde zu erstellen,
 * so wird eine CreateRoundEvent ausgelöst.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class TryCreateRoundEvent extends Event{

	/**
	 * Sockelverbindung des Clients, welcher versucht eine Runde zu erstellen
	 */
	
	private Socket client;
	
	/**
	 * Erhaltenes Einstellungspacket des Clients, welcher versucht eine Runde zu erstellen
	 */
	
	private GameSettingsPacket packet;
	
	/**
	 * Erzeugt ein TryCreateRoundEvent
	 * 
	 * @param client Sockelverbindung des Clients, welcher versucht eine Runde zu erstellen
	 * @param packet erhaltenes Einstellungspacket vom Client
	 */
	
	public TryCreateRoundEvent(Socket client, GameSettingsPacket packet) {
		this.client = client;
		this.packet = packet;
	}
	
	/**
	 * Liefert  die Sockelverbindung des Clients zurück, welcher versucht eine Runde zu erstellen
	 * 
	 * @return Sockelverbindung des Clients
	 */
	
	public Socket getClient() {
		return this.client;
	}
	
	/**
	 * Liefert das Packet zurück, welches vom Client empfangen wurde
	 * 
	 * @return erhaltenes Einstellungspacket
	 */
	
	public GameSettingsPacket getPacket() {
		return this.packet;
	}
	
	
}
