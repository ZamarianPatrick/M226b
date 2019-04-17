package patrick.event.events;

import patrick.event.Event;
import patrick.packets.GameOptionPacket;
/**
 * <p>Ein Event, welcher ausgel�st wird, wenn der Client ein GameOptionPacket erh�lt</p>
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
	 * Liefert das Packet zur�ck, welches erhalten wurde
	 * 
	 * @return Packet, welches erhalten wurde
	 */
	
	public GameOptionPacket getPacket() {
		return this.packet;
	}
	
}
