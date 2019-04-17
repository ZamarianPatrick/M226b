package patrick.utils;

import patrick.Server;
import patrick.game.Game;
import patrick.game.WaitingRound;
import patrick.packets.out.WaitingRoundInfoPacket;
/**
 * <p>Statische Klasse, für diverse Nachrichten</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class Message {
	
	public static final String playerAlreadyExists = "error:1";
	public static final String gameDontExists = "error:2";
	public static final String gameAlreadyStart = "error:3";
	public static final String fullRound = "error:4";
	
	public static final String RoundCreated(WaitingRound round) {
		return new WaitingRoundInfoPacket(round).getLine();
	}
	
	public static final String getGames() {
		String toSend = "games:";
		for(Game game : Server.getAllGames()) {
			toSend += game.getName() + "," + game.getImageName()+";";
		}
		if(toSend.length()> 6) {
			toSend = toSend.substring(0, toSend.length()-1);
		}
		return toSend;
	}
	
}
