package patrick.event.events;

import patrick.event.Event;
import patrick.packets.GameOptionPacket;
/**
 * <p>Ein Event, welcher ausgelöst wird, wenn der Client ein GameOptionPacket erhält</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameOptionPacketEvent extends Event{
	
	/**
	 * Packet, welches erhalten wurde
	 */
	
	private GameOptionPacket packet;
	
	/**
	 * Erzeugt einen neuen GameOptionPacketEvent
	 * 
	 * @param packet Packet, welches erhalten wurde
	 */
	
	public GameOptionPacketEvent(GameOptionPacket packet) {
		this.packet = packet;
	}
	
	/**
	 * Liefert das Packet zurück, welches erhalten wurde
	 * 
	 * @return Packet, welches erhalten wurde
	 */
	
	public GameOptionPacket getPacket() {
		return this.packet;
	}
	
}
