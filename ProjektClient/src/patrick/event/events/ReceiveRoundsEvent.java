package patrick.event.events;

import patrick.event.Event;
import patrick.packets.RequestRoundPacket;
/**
 * <p>Ein Event, welcher ausgel�st wird, wenn der Client aktuelle Runden empf�ngt</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class ReceiveRoundsEvent extends Event{

	/**
	 * Packet, welches empfangen wurde
	 */
	
	private RequestRoundPacket packet;
	
	/**
	 * Erzeugt einen neuen ReceiveRoundEvent
	 * 
	 * @param packet Packet, welches empfangen wurde
	 */
	
	public ReceiveRoundsEvent(RequestRoundPacket packet) {
		this.packet = packet;
	}
	
	/**
	 * Liefert das Packet zur�ck welches empfangen wurde
	 * 
	 * @return Packet, welches empfangen wurde
	 */
	
	public RequestRoundPacket getPacket() {
		return this.packet;
	}
	
}
