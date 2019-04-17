package patrick.packets;
/**
 * <p>Verwerten Nachrichten des Serves mit viel Informationen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public abstract class Packet {

	/**
	 * Liefert die Informationen des Packets als String
	 * 
	 * @return Informationen des Packets
	 */
	
	public abstract String getLine();
	
}
