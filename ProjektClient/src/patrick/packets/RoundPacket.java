package patrick.packets;

import patrick.packets.Packet;
/**
 * <p>Verwertet Rundeninformationen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class RoundPacket extends Packet{

	/**
	 * Name des Hosters dieser Runde
	 */
	
	private String name;
	
	/**
	 * Anzahl Spieler in der Runde
	 */
	
	private int playerAmount;
	
	/**
	 * Anzahl maximaler Spieler, welche die Runde beitreten können
	 */
	
	private int maxPlayerAmount;
	
	/**
	 * Erzeugt ein RoundPacket
	 * 
	 * @param line Nachricht des Servers
	 */
	
	public RoundPacket(String line) {
		String[] args = line.split(",");
		if(args.length > 0) {
			this.name = args[0];
			if(args.length > 1) {
				try {
					this.playerAmount = Integer.parseInt(args[1]);
				}catch(NumberFormatException ex) {
					this.playerAmount = 0;
				}
				if(args.length > 2) {
					try {
						this.maxPlayerAmount = Integer.parseInt(args[2]);
					} catch (NumberFormatException ex) {
						this.maxPlayerAmount = 0;
					}
				}
			}
		}
	}
	
	/**
	 * Erzeugt ein RoundPacket
	 * 
	 * @param name Name des Hosters dieser Runde
	 * @param playerAmount Anzahl Spieler in der Runde
	 * @param maxPlayerAmount maximale Anzahl Spieler, welche der Runde beitreten können
	 */
	
	public RoundPacket(String name, String playerAmount, String maxPlayerAmount) {
		this.name = name;
		try {
			this.playerAmount = Integer.parseInt(playerAmount);
		}catch(NumberFormatException ex) {
			this.playerAmount = 0;
		}
		
		try {
			this.maxPlayerAmount = Integer.parseInt(maxPlayerAmount);
		}catch(NumberFormatException ex) {
			this.maxPlayerAmount = 0;
		}
	}
	
	/**
	 * Erzeugt ein RoundPacket
	 * 
	 * @param name Name des Hosters dieser Runde
	 * @param playerAmount Anzahl Spieler in der Runde
	 * @param maxPlayerAmount maximale Anzahl Spieler, welche der Runde beitreten können
	 */

	public RoundPacket(String name, int playerAmount, int maxPlayerAmount) {
		this.name = name;
		this.playerAmount = playerAmount;
		this.maxPlayerAmount = maxPlayerAmount;
	}
	
	/**
	 * Liefert den Namen des Hosters zurück
	 * 
	 * @return Name des Hosters dieser Spielrunde
	 */
	
	public String getName() {
		return this.name;
	}
	
	/**
	 * Liefert die Spieleranzahl in der Runde zurück
	 * 
	 * @return Anzahl Spieler in der Runde
	 */
	
	public int getPlayerAmount() {
		return this.playerAmount;
	}
	
	/**
	 * Liefert die maximale Anzahl Spieler zurück welche die Runde beitreten können
	 * 
	 * @return maximale Anzahl Spieler
	 */
	
	public int getMaxPlayerAmount() {
		return this.maxPlayerAmount;
	}
	
	/**
	 * @see patrick.packets.Packet#getLine()
	 */

	@Override
	public String getLine() {
		return this.getName() + "," + 
				this.getPlayerAmount() + "," + 
				this.getMaxPlayerAmount();
	}

}
