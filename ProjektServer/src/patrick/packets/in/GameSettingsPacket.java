package patrick.packets.in;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import patrick.Server;
import patrick.game.Game;
import patrick.game.GameSettings;
import patrick.game.WaitingRound;
import patrick.packets.Packet;
/**
 * <p>GameSettingsPacket, welches von Clients empfangen wird, wenn diese eine Runde mit
 * bestimmten Einstellungen erstellen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameSettingsPacket extends Packet{

	/**
	 * Spiel, von welchem die Einstellungen sind
	 */
	
	private Game game;
	
	/**
	 * Name des Spielers, welche die Einstellungen schickt
	 */
	
	private String hosterName;
	
	/**
	 * Anzahl der Spieler, welche die Runde betreten können
	 */
	private int playerAmount;
	
	/**
	 * Eingestellte Auswahlmöglichkeiten des Spiels
	 */
	
	private Map<String, String> settings = new HashMap<String, String>();
	
	/**
	 * Erzeugt ein GameSettingsPacket
	 * 
	 * @param line Nachricht des Clients
	 */
	
	public GameSettingsPacket(String line) {
		String[] args = line.split(":");
		if(args.length >= 3) {
			String gameName = args[0];
			this.hosterName = args[1];
			try {
				this.playerAmount = Integer.parseInt(args[2]);
			}catch(NumberFormatException ex) {}
			this.game = Server.getGame(gameName);
			if(args.length > 3) {
				args = args[3].split(";");
				for(String settingText : args) {
					String[] setting = settingText.split("=");
					if(setting.length == 2) {
						settings.put(setting[0], setting[1]);
					}
				}
			}
		}
	}
	
	/**
	 * Erzeugt ein GameSettingsPacket
	 * 
	 * @param round wartende Runde
	 */
	
	public GameSettingsPacket(WaitingRound round) {
		this.game = round.getGame();
		this.hosterName = round.getHost().getName();
		GameSettings settings = round.getSettings();
		this.settings = new HashMap<String, String>(settings.getChooseables());
		this.settings.putAll(settings.getCheckSettingsAsString());
		this.playerAmount = round.getPlayerCount();
	}
	
	/**
	 * Liefert das Spiel zurück, von welchem die Einstellungen sind
	 * 
	 * @return Spiel, von welchem die Einstellungen sind
	 */
	
	public Game getGame() {
		return this.game;
	}
	
	/**
	 * Liefert den Spielernamen von dem Spieler, welcher die Runde erstellt
	 * 
	 * @return Spielernamen vom Hoster
	 */
	
	public String getHosterName() {
		return this.hosterName;
	}
	
	/**
	 * Liefert die Spieleranzahl zurück, welche die Runde betreten können
	 * 
	 * @return gewählte Spieleranzahl
	 */
	
	public int getPlayerAmount() {
		return this.playerAmount;
	}
	
	/**
	 * Liefert die gewählten Auswahlmöglichkeiten zurück
	 * 
	 * @return gewählte Auswahlmöglichkeiten
	 */
	
	public Map<String, String> getSettings(){
		return this.settings;
	}
	
	/**
	 * @see patrick.packets.Packet#getLine()
	 */

	@Override
	public String getLine() {
		String line = "start:"+this.game.getName()+":"+this.hosterName+":"+playerAmount+":";
		for(Entry<String, String> entry : settings.entrySet()) {
			line += entry.getKey() + "=" + entry.getValue() + ";";
		}
		if(line.endsWith(";")) {
			line.substring(0, line.length() - 1);
		}
		return line;
	}
	
}
