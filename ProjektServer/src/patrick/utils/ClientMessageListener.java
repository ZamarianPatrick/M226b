package patrick.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import patrick.event.EventHandler;
import patrick.event.events.ClientDisconnectEvent;
import patrick.event.events.ClientSendMessageEvent;
/**
 * <p>Listener, welcher auf die Nachrichten der verbundenen Clients hört</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ClientMessageListener extends Thread{

	/**
	 * Liste von allen aktiven MessageListeners
	 */
	
	private static List<ClientMessageListener> messageListeners = new ArrayList<ClientMessageListener>();
	
	/**
	 * Sockelverbindung des Clients, von welchem Nachrichten empfangen werden
	 */
	
	private Socket socket;
	
	/**
	 * Zustand, ob der ClientMessageListener aktiv ist
	 */
	
	private boolean run;
	
	/**
	 * Erzeugt ein ClientMessageListener
	 * 
	 * @param socket Sockelverbindung des Clients, von welchem Nachrichten empfangen werden sollen
	 */
	
	public ClientMessageListener(Socket socket) {
		this.socket = socket;
		run = true;
		messageListeners.add(this);
	}
	
	/**
	 * startet den ClientMessageListener
	 */

	@Override
	public void run() {
		String line;
		try {
		    BufferedReader reader =
		            new BufferedReader(
		                new InputStreamReader(socket.getInputStream()));
		    while((line = reader.readLine()) != null && run) {	
				ClientSendMessageEvent e = new ClientSendMessageEvent(socket, line);
				EventHandler.callEvent(e);
			}

			ClientDisconnectEvent event = new ClientDisconnectEvent(socket);
			EventHandler.callEvent(event);
		} catch (Exception e) {
			ClientDisconnectEvent event = new ClientDisconnectEvent(socket);
			EventHandler.callEvent(event);
			run = false;
		}	
	}
	
	/**
	 * Stoppt alle aktiven ClientMessageListener
	 */
	
	public static void stopAll() {
		for(ClientMessageListener listener : messageListeners) {
			listener.run = false;
		}
		messageListeners.clear();
	}
	
	/**
	 * Liefert die Anzahl von aktiven ClientMessageListener zurück
	 * 
	 * @return Anzahl ClientMessageListener
	 */
	
	public static int getCount() {
		return messageListeners.size();
	}
	
}
