package patrick.event.events;

import patrick.event.Event;
import patrick.packets.RequestRoundPacket;
/**
 * <p>Ein Event, welcher ausgelöst wird, wenn der Client aktuelle Runden empfängt</p>
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
	 * Liefert das Packet zurück welches empfangen wurde
	 * 
	 * @return Packet, welches empfangen wurde
	 */
	
	public RequestRoundPacket getPacket() {
		return this.packet;
	}
	
}
