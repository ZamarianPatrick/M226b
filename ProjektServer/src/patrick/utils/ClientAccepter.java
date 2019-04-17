package patrick.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import patrick.event.EventHandler;
import patrick.event.events.ClientConnectEvent;
/**
 * <p>Nimmt Clients an, welche sich zum Server verbinden wollen, und registriert diese.
 * Muss in einem seperaten Thread geschehen, damit der Server weiter arbeiten kann, und nicht
 * blockiert wird, durch die unendlich lange while Schleife.</p>
 * 
 * @author Patrick
 * @version 1.0
 * 
 */
public class ClientAccepter extends Thread{

	/**
	 * Sockelverbindung des Servers, welcher Clients annimmt
	 */
	
	private ServerSocket server;
	
	/**
	 * Erzeugt ein ClientAccepter
	 * 
	 * @param server Sockelverbindung des Servers, welcher Client annehmen soll
	 */
	
	public ClientAccepter(ServerSocket server) {
		this.server = server;
	}
	
	/**
	 * startet den Thread
	 */
	
	@Override
	public void run() {
		while(server.isClosed() == false) {
			try {
				Socket socket = server.accept();
				ClientConnectEvent e = new ClientConnectEvent(socket);
				EventHandler.callEvent(e);
				ClientMessageListener messageListener = new ClientMessageListener(socket);
				messageListener.start();
			} catch (IOException e) {
				this.interrupt();
			}
		}
	}
	
}
