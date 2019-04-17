package patrick.game;

import java.net.Socket;

import patrick.Server;
import patrick.packets.Packet;
/**
 * <p>Spieler mit dem Namen und der Sockelverbindung</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class Player {

	/**
	 * Name des Spielers
	 */
	
	private String name;
	
	/**
	 * Sockelverbindung des Spielers
	 */
	
	private Socket socket;
	
	/**
	 * Erzeugt ein Spieler
	 * 
	 * @param name Name des Spielers
	 * @param socket Sockelverbindung des Spielers
	 */
	
	public Player(String name, Socket socket) {
		this.name = name;
		this.socket = socket;
	}
	
	/**
	 * Liefert den Namen des Spielers zurück
	 * 
	 * @return Name des Spielers
	 */
	
	public String getName() {
		return this.name;
	}
	
	/**
	 * Liefert die Sockelverbindung des Spielers zurück
	 * 
	 * @return Sockelverbindung des Spielers
	 */
	
	public Socket getSocket(){
		return this.socket;
	}
	
	/**
	 * Schickt eine Nachricht an das Client-Spiel des Spielers, 
	 * in welchem sich der Spieler befindet
	
	 * @param message Nachricht an den Spieler
	 */
	
	public void send(String message) {
		sendMessage("gaming:" + message);
	}
	
	/**
	 * Schickt ein Packet an das Client-Spiel des Spielers, 
	 * in welchem sich der Spieler befindet
	 * 
	 * @param packet Packet an den Spieler
	 */
	
	public void send(Packet packet) {
		send(packet.getLine());
	}
	
	/**
	 * Schickt eine Nachricht an den Client des Spielers
	 * 
	 * @param message Nachricht an den Client
	 */
	
	protected void sendMessage(String message) {
		Server.sendMessage(socket, message);
	}
	
	/**
	 * Schickt ein Packet an den Client des Spielers
	 * 
	 * @param packet Packet an den Client
	 */
	
	protected void sendPacket(Packet packet) {
		sendMessage(packet.getLine());
	}
}
