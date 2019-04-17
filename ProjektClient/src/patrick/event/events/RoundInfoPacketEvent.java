package patrick.event.events;

import patrick.event.Event;
import patrick.packets.WaitingRoundInfoPacket;
/**
 * <p>Ein Event, welcher ausgel�st wird, wenn eine Information �ber
 *  eine Runde empfangen wurde<p>
 *  
 * @author Patrick
 * @vesion 1.0
 *
 */
public class RoundInfoPacketEvent extends Event{

	/**
	 * Packet, welches empfangen wurde
	 */
	
	private WaitingRoundInfoPacket packet;
	
	/**
	 * Erzeugt einen neuen RoundInfoPacketEvent
	 * 
	 * @param packet Packet, welches empfangen wurde
	 */
	
	public RoundInfoPacketEvent(WaitingRoundInfoPacket packet) {
		this.packet = packet;
	}
	
	/**
	 * Liefert das Packet zur�ck, welches empfangen wurde
	 * 
	 * @return Packet, welches empfangen wurde
	 */
	
	public WaitingRoundInfoPacket getPacket() {
		return this.packet;
	}
	
}
