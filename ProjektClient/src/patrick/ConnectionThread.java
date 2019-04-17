package patrick;

import java.net.Socket;

import patrick.event.ErrorType;
import patrick.event.EventHandler;
import patrick.event.events.ErrorEvent;
import patrick.server.ServerListener;
/**
 * <p>Ein Thread, welcher sich zum Server verbindet. Notwendig damit
 * der Client während er sich mit dem Server zu verbinden sucht parallel dazu
 * auch weiterhin Befehle ausführen kann.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ConnectionThread extends Thread{

	/**
	 * Sockel, welcher sich verbinden soll
	 */
	private Socket socket;
	
	/**
	 * Ip-Adresse, zu welcher sich der Sockel verbinden soll
	 */
	
	private String ipAdress;
	
	/**
	 * Port, mit welchem sich der Client verbinden soll
	 */
	
	private int port;
	
	/**
	 * Erzeugt einen ConnectionThread
	 * 
	 * @param ipAdress IP-Adresse vom Server, zu welcher sich der Client verbinden soll
	 * @param port Port, zu welchem sich der Client verbinden soll
	 */
	
	public ConnectionThread(String ipAdress, int port) {
		this.ipAdress = ipAdress;
		this.port = port;
	}
	
	/**
	 * startet den Thread und versucht eine Verbindun zum Server herzustellen.
	 */
	
	@Override
	public void run() {
		try {
			socket = new Socket(ipAdress, port);
			ServerListener listener = new ServerListener(socket);
			listener.start();
			Client.socket = socket;
		} catch (Exception e) {
			ErrorEvent event = new ErrorEvent(ErrorType.NO_CONNECTION);
			EventHandler.callEvent(event);
		}finally {
			Client.connect = false;
		}
	}
	
}
