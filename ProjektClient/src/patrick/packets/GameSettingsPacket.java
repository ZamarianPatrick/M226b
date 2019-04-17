package patrick.packets;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import patrick.Client;
import patrick.game.Game;
import patrick.views.CheckBoxView;
import patrick.views.GameOptionView;
import patrick.views.RadioOptionView;
/**
 * <p>GameSettingsPacket, verwertet eine GameSettings-Nachricht des Servers</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameSettingsPacket extends Packet{

	/**
	 * Spiel von welchem die Settings sind
	 */
	
	private Game game;
	
	/**
	 * Name des Hosters dieser Spielrunde
	 */
	
	private String hosterName;
	
	/**
	 * gewählte Spieleranzahl-Variante
	 */
	
	private int playerAmount;
	
	/**
	 * gewählte Einstellungen für die Spielrunde
	 */
	
	private Map<String, String> settings = new HashMap<String, String>();
	
	/**
	 * Erzeugt ein GameSettingsPacket
	 * 
	 * @param line Nachricht des Servers
	 */
	
	public GameSettingsPacket(String line) {
		String[] args = line.split(":");
		if(args.length >= 3) {
			String gameName = args[0];
			this.hosterName = args[1];
			try {
				this.playerAmount = Integer.parseInt(args[2]);
			}catch(NumberFormatException ex) {}
			this.game = Client.getGame(gameName);
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
	 * Erzeugt eine GameSettingsPacket
	 * 
	 * @param game Spiel, von welchem die Einstellungen sind
	 * @param hosterName Name des Hosters der Runde
	 * @param playerAmount gewählte Spieleranzahl-Variante
	 * @param settings gewählte Spieleinstellungen
	 */
	
	public GameSettingsPacket(Game game, String hosterName, int playerAmount, Map<String, String> settings) {
		this.game = game;
		this.hosterName = hosterName;
		this.playerAmount = playerAmount;
		this.settings = settings;
	}
	
	/**
	 * Erzeugt ein GameSettingsPacket
	 * 
	 * @param view Ansicht, auf welcher die Einstellungen vorgenommen wurden
	 */
	
	public GameSettingsPacket(GameOptionView view) {
		this.hosterName = view.getUserName();
		Client.setPlayedGame(view.getGame());
		Client.setPlayerName(view.getUserName());
		this.game = view.getGame();
		if(view.getPlayerAmountGroup() != null) {
			if(view.getPlayerAmountGroup().getActivated() != null) {
				String playerAmountString = view.getPlayerAmountGroup().getActivated().getText();
				try {
					this.playerAmount = Integer.parseInt(playerAmountString.split(" ")[0]);
				}catch(NumberFormatException ex){
					this.playerAmount = -1;
				}
			}
		}
		for(RadioOptionView radioView : view.getRadios()) {
			String name = radioView.getSetting();
			String value = radioView.getChoosedValue();
			settings.put(name, value);
		}
		
		for(CheckBoxView checkView : view.getChecks()) {
			String name = checkView.getSetting();
			String value = checkView.isChoosed()+"";
			settings.put(name, value);
		}
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
	 * Liefert den Namen des Hosters der Spielrunde zurück
	 * 
	 * @return Name des Hosters von dieser Spielrunde
	 */
	
	public String getHosterName() {
		return this.hosterName;
	}
	
	/**
	 * Liefert die gewählte Spieleranzahl-Variante zurück
	 * 
	 * @return gewählte Spieleranzahl-Variante
	 */
	
	public int getPlayerAmount() {
		return this.playerAmount;
	}
	
	/**
	 * Liefert die gewählten Einstellungen zurück
	 * 
	 * @return gewählte Einstellungen
	 */
	
	public Map<String, String> getSettings(){
		return this.settings;
	}
	
	/**
	 * @see patrick.packets.Packet#getLine()
	 */

	@Override
	public String getLine() {
		String line = "create:"+this.game.getName()+":"+this.hosterName+":"+playerAmount+":";
		for(Entry<String, String> entry : settings.entrySet()) {
			line += entry.getKey() + "=" + entry.getValue() + ";";
		}
		if(line.endsWith(";")) {
			line.substring(0, line.length() - 1);
		}
		return line;
	}
	
}
