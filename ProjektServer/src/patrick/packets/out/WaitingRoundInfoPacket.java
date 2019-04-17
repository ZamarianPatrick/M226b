package patrick.packets.out;

import java.util.ArrayList;
import java.util.List;

import patrick.game.Player;
import patrick.game.WaitingRound;
import patrick.packets.Packet;
/**
 * <p>Verwertet Informationen über die Runde in welcher sich der Client befindet</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class WaitingRoundInfoPacket extends Packet{

	/**
	 * Runde, über welche die Informationen gelten
	 */
	
	private RoundPacket round;
	
	/**
	 * Liste der Spieler, welche sich in der Runde befinden
	 */
	
	private List<String> players = new ArrayList<String>();
	
	/**
	 * Erzeugt ein WaitingRoundPacket
	 * 
	 * @param line Nachricht des Servers
	 */
	
	public WaitingRoundInfoPacket(String line) {
		String[] separation = line.split(";");
		if(separation.length == 2) {
			String[] args = separation[0].split(":");
			String[] players = separation[1].split(",");
			if(args.length > 2) {
				this.round = new RoundPacket(args[2]);
			}
			for(String player : players) {
				this.players.add(player);
			}
		}
	}
	
	/**
	 * Erzeugt ein WaitingRoundInfoPacket
	 * 
	 * @param round wartende Runde, von welcher das WaitingRoundInfoPacket erzeugt werden soll
	 */
	
	public WaitingRoundInfoPacket(WaitingRound round) {
		this.round = new RoundPacket(round.getHost().getName(), round.getPlayers().size(), round.getPlayerCount());
		this.players = new ArrayList<String>();
		for(Player player : round.getPlayers()) {
			this.players.add(player.getName());
		}
	}
	
	/**
	 * Erzeugt ein WaitingRoundInfoPacket
	 * 
	 * @param packet Runde, über welche die Informationen sind
	 * @param players Spieler, welche sich in der Runde befinden
	 */
	
	public WaitingRoundInfoPacket(RoundPacket packet, List<String> players) {
		this.round = packet;
		this.players = players;
	}
	
	/**
	 * Liefert die Runde, über welche die Informationen sind
	 * 
	 * @return Runde, über welche die Informationen sind
	 */
	
	public RoundPacket getRound() {
		return this.round;
	}
	
	/**
	 * Liefert die Spieler, welche sich in der Runde befinden
	 * 
	 * @return Spieler, welche sich in der Runde befinden
	 */
	
	public List<String> getPlayers(){
		return this.players;
	}
	
	/**
	 * @see patrick.packets.Packet#getLine()
	 */

	@Override
	public String getLine() {
		String build = "packet:roundinfo:"+round.getLine()+";";
		for(String player : players) {
			build += player + ",";
		}
		if(build.endsWith(",")) {
			build = build.substring(0, build.length()-1);
		}
		return build;
	}

}
