package patrick.event.listener;

import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.ReceivePacketEvent;
import patrick.event.events.RoundInfoPacketEvent;
import patrick.packets.WaitingRoundInfoPacket;
/**
 * <p>Ein Listener, welcher auf die ReceivePacketEvents reagiert</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ReceivePacketListener implements Listener{

	/**
	 * Erzeugt einen neuen ReceivePacketListener und registriert diesen
	 */
	
	public ReceivePacketListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Methode, welche aufgerufen wird, wenn ein ReceivePacketEvent ausgelöst wurde
	 * 
	 * @param e ReceviePacketEvent, welcher ausgelöst wurde
	 */
	
	public void onReceive(ReceivePacketEvent e) {
		String args[] = e.getMessage().split(":");
		if(args.length > 1) {
			String packetName = args[1];
			if(packetName != null) {
				if(packetName.equals("roundinfo")) {
					WaitingRoundInfoPacket packet = new WaitingRoundInfoPacket(e.getMessage());
					RoundInfoPacketEvent event = new RoundInfoPacketEvent(packet);
					EventHandler.callEvent(event);
				}
			}
		}
	}
	
}
