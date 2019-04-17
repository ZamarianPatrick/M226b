package patrick.test;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
/**
 * <p>Ein Listener, welcher auf die Nachrichten eines Servers hört.
 * Dieser wird für verschiedene Tests im JUnitTestCase benötigt.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class TestServerListener extends Thread{

	/**
	 * Sockelverbinund des Clients
	 */
	
	private Socket socket;
	
	/**
	 * Anzahl empfangene Nachrichten
	 */
	
	private int messagesReceived;
	
	/**
	 * zuletzt empfangene Nachricht
	 */
	
	private String lastMessage;
	
	/**
	 * Zustand ob der TestServerListener aktiv ist
	 */
	
	private boolean run;
	
	/**
	 * Erzeugt ein TestServerListener
	 * 
	 * @param socket Sockelverbindung, welche mit dem Server verbunden ist
	 */
	
	public TestServerListener(Socket socket) {
		this.socket = socket;
		messagesReceived = 0;
		run = true;
	}
	
	/**
	 * startet den Thread
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
				}
			}
		} catch (IOException e) {
			
		}
	}
	
	/**
	 * Liefert die zuletzt empfangene Nachricht zurück
	 * 
	 * @return zuletzt empfangene Nachricht des Servers
	 */
	
	public String getLastMessage() {
		return lastMessage;
	}
	
	/**
	 * Liefert die Anzahl der empfangenen Nachrichten zurück
	 * 
	 * @return Anzahl empfangene Nachrichten
	 */
	
	public int getMessagesReceivedCount() {
		return messagesReceived;
	}
	
	/**
	 * beendet den TestServerListener
	 */
	
	public void exit() {
		run = false;
	}
	
}
