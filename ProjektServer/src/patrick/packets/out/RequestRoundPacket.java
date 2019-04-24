package patrick.packets.out;

import java.util.ArrayList;
import java.util.List;

import patrick.game.Game;
import patrick.packets.Packet;
/**
 * <p>Verwertet Anfragen für die verfügbaren Runden</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class RequestRoundPacket extends Packet{

	/**
	 * Name des Spiels
	 */
	
	private String gameName;
	
	/**
	 * Verfügbare Runden
	 */
	
	private List<RoundPacket> rounds = new ArrayList<RoundPacket>();
	
	/**
	 * Erzeugt ein RequestRoundPacket
	 * 
	 * @param line Nachricht des Servers
	 */
	
	public RequestRoundPacket(String line) {
		String[] args = line.split(":");
		if(args.length > 1) {
			this.gameName = args[1];
			if(args.length > 2) {
				String roundsLine = args[2];
				String[] roundLines = roundsLine.split(";");
				for(String roundLine : roundLines) {
					String[] roundArgs = roundLine.split(",");
					if(roundArgs.length == 3) {
						RoundPacket roundPacket = new RoundPacket(roundArgs[0], roundArgs[1], roundArgs[2]);
						rounds.add(roundPacket);
					}
				}
			}
		}
	}
	
	/**
	 * Erzeugt ein RequestRoundPacket
	 * 
	 * @param gameName Name des Spiels
	 * @param rounds verfügbare Runden
	 */
	
	public RequestRoundPacket(String gameName, RoundPacket...rounds) {
		this.gameName = gameName;
		for(RoundPacket round : rounds) {
			this.rounds.add(round);
		}
	}
	
	/**
	 * Erzeugt ein RequestRoundPacket
	 * 
	 * @param game Spiel der Runde
	 */
	
	public RequestRoundPacket(Game game) {
		this.gameName = game.getName();
	}
	
	/**
	 * Fügt eine Runde hinzu
	 * 
	 * @param round hinzuzufügende Runde
	 */
	
	public void addRoundPacket(RoundPacket round) {
		rounds.add(round);
	}
	
	/**
	 * Liefert den Spielnamen zurück
	 * 
	 * @return Spielname
	 */
	
	public String getGameName() {
		return this.gameName;
	}
	

	/**
	 * Liefert die verfügbaren Runden zurück
	 * 
	 * @return verfügbare Runden
	 */
	
	public List<RoundPacket> getRounds(){
		return new ArrayList<RoundPacket>(rounds);
	}
	
	/**
	 * Prüft ob eine Runde mit gegebenem Namen existiert
	 * 
	 * @param name Name der Runde
	 * 
	 * @return Wahrheitswert ob die Runde existiert
	 */
	
	public boolean roundExist(String name) {
		for(RoundPacket round : rounds) {
			if(round.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Liefert eine Runde über den Namen des Hosters zurück
	 * 
	 * @param name Name des Hosters der Spielrunde
	 * 
	 * @return null wenn die Spielrunde nicht existiert, ansonsten die Spielrunde
	 */
	
	public RoundPacket getRoundPacket(String name) {
		for(RoundPacket round: rounds) {
			if(round.getName().equals(name)) {
				return round;
			}
		}
		return null;
	}
	
	/**
	 * @see patrick.packets.Packet#getLine()
	 */
	
	@Override
	public String getLine() {
		String line = "";
		line += "rounds:"+gameName+":";
		
		for(RoundPacket round : rounds) {
			line += round.getLine() + ";";
		}
		if(line.endsWith(";")) {
			line = line.substring(0, line.length()-1);
		}
		return line;
	}

}
