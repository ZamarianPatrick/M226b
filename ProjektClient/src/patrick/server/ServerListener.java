package patrick.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import patrick.event.EventHandler;
import patrick.event.events.MessageEvent;
/**
 * <p>Empfängt Nachrichten eines Servers, und löst den MessageEvent aus</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ServerListener extends Thread{

	/**
	 * Der Sockel, welcher mit dem Server verbunden ist
	 */
	
	private Socket socket;
	
	/**
	 * Anzahl Nachrichten, die empfangen wurden
	 */
	
	private long messagesReceived;
	
	/**
	 * Die letzte Nachricht, welche empfangen wurde
	 */
	
	private String lastMessage;
	
	/**
	 * Zustand, ob der Thread weiter arbeiten soll
	 */
	
	private boolean run;
	
	/**
	 * Erzeugt ein ServerListener
	 * 
	 * @param socket Sockel, welcher mit einem Server verbunden ist
	 */
	
	public ServerListener(Socket socket) {
		this.socket = socket;
		messagesReceived = 0;
		run = true;
	}
	
	/**
	 * @see java.lang.Thread#run()
	 */
	
	@Override
	public void run() {
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(socket.getInputStream());
			while(run) {
				String line;
				if(sc.hasNext()) {
					line = sc.nextLine();
					lastMessage = line;
					messagesReceived++;
					MessageEvent event = new MessageEvent(line);
					EventHandler.callEvent(event);
				}
			}
		} catch (IOException e) {
			
		}
	}
	
	/**
	 * Liefert die letzte empfangene Nachricht zurück
	 * 
	 * @return letzte empfangene Nachricht
	 */
	
	public String getLastMessage() {
		return lastMessage;
	}
	
	/**
	 * Liefert die Anzahl Nachrichten zurück, welche empfangen wurden
	 * 
	 * @return Anzahl Nachrichten
	 */
	
	public long getMessagesReceivedCount() {
		return messagesReceived;
	}
	
	/**
	 * Beendet den ServerListener
	 */
	
	public void exit() {
		run = false;
	}
	
}
