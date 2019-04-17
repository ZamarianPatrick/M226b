package patrick.test;

import patrick.Server;
import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.ClientSendMessageEvent;
/**
 * <p>Ein Listener, welcher für die Tests im JUnitTestCase verwendet werden</p>
 * 
 * @author Patrick
 * @version 1.0
 * 
 */
public class TestMessageListener implements Listener{

	/**
	 * Erzeugt ein TestMessageListener und registriert diesen
	 */
	
	public TestMessageListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein ClientSendMessageEvent ausgelöst wird.
	 * Die Methode reagiert auf die Nachricht 'test' und schickt dem Client eine Antwort
	 * 
	 * @param e ClientMessageEvent, welcher ausgelöst wurde
	 */
	
	public void onMessage(ClientSendMessageEvent e) {
		if(e.getMessage().equals("test")) {
			Server.sendMessage(e.getClient(), "test success");
		}
	}
	
}
