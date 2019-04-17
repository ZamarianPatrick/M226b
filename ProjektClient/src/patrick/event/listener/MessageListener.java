package patrick.event.listener;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import patrick.Client;
import patrick.event.ErrorType;
import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.ErrorEvent;
import patrick.event.events.GameOptionPacketEvent;
import patrick.event.events.GameStartEvent;
import patrick.event.events.MessageEvent;
import patrick.event.events.ReceivePacketEvent;
import patrick.event.events.ReceiveRoundsEvent;
import patrick.event.events.RoundCountDownEvent;
import patrick.event.events.game.GameMessageEvent;
import patrick.event.events.game.PlayerLeaveGameEvent;
import patrick.event.events.game.RoundStopEvent;
import patrick.game.Game;
import patrick.game.GameRound;
import patrick.game.GameSettings;
import patrick.game.Player;
import patrick.packets.GameOptionPacket;
import patrick.packets.GameSettingsPacket;
import patrick.packets.RequestRoundPacket;
/**
 * <p>Ein Listener, der auf die MessageEvents reagiert</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class MessageListener implements Listener{
	
	/**
	 * Erzeugt einen neuen MessageListener und registriert diesen
	 */
	
	public MessageListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Methode, welche aufgerufen wird, wenn ein MessageEvent ausgelöst wurde
	 * 
	 * @param e MessageEvent, welcher ausgelöst wurde
	 */
	
	public void onMessage(MessageEvent e) {
		if(e.getMessage().startsWith("error:")) {
			String[] args = e.getMessage().split(":");
			if(args.length == 2) {
				try {
					int err = Integer.parseInt(args[1]);
					ErrorType error = ErrorType.valueOf(err);
					if(error != null) {
						ErrorEvent event = new ErrorEvent(error);
						EventHandler.callEvent(event);
					}
				}catch(Exception ex) {
					
				}
			}
		}else if(e.getMessage().startsWith("games:")) {
			String args[] = e.getMessage().split(":");
			if(args.length > 1) {
				Map<String, String> map = new HashMap<String, String>();
				String gamesTxt = args[1];
				String[] games = gamesTxt.split(";");
				for(String game : games) {
					String[] split = game.split(",");
					if(split.length == 2) {
						String name = "";
						if(split[1] != null) {
							File file = Client.getImage(split[1]);
							if(file != null) {
								name = file.getAbsolutePath();
							}
						}
						map.put(split[0], name);
					}else {
						map.put(game, "");
					}
				}
				Client.getView().openGamesView(map);
			}else {
				Client.getView().openGamesView(null);
			}
		}else if(e.getMessage().startsWith("gameoption:")){
			GameOptionPacket packet = new GameOptionPacket(e.getMessage());
			GameOptionPacketEvent event = new GameOptionPacketEvent(packet);
			EventHandler.callEvent(event);
		}else if(e.getMessage().startsWith("packet:")){
			ReceivePacketEvent event = new ReceivePacketEvent(e.getMessage());
			EventHandler.callEvent(event);
		}else if(e.getMessage().startsWith("rounds:")){
			ReceiveRoundsEvent event = new ReceiveRoundsEvent(new RequestRoundPacket(e.getMessage()));
			EventHandler.callEvent(event);
		}else if(e.getMessage().startsWith("start:")){
			String[] args = e.getMessage().split(":");
			if(args.length == 2) {
				try {
					int counter = Integer.parseInt(args[1]);
					RoundCountDownEvent event = new RoundCountDownEvent(counter);
					EventHandler.callEvent(event);
				}catch(NumberFormatException ex) {}
			}else {
				String withoutStart = e.getMessage().substring(
						e.getMessage().split(":")[0].length()+1,
						e.getMessage().length()
						);
				GameSettingsPacket packet = new GameSettingsPacket(withoutStart);
				if(packet.getGame() != null) {
					Game game = packet.getGame();
					GameSettings settings = new GameSettings();
					if(packet.getSettings() != null) {
						for(Entry<String, String> entry : packet.getSettings().entrySet()) {
							String setting = entry.getKey();
							String value = entry.getValue();
							settings.addSetting(setting, value);
						}
					}
					GameStartEvent event = new GameStartEvent(game, settings);
					EventHandler.callEvent(event);
				}
			}
		}else if(e.getMessage().startsWith("gaming:")) {
			String message = e.getMessage().substring("gaming:".length(), e.getMessage().length());
			if(Client.getPlayedRound() != null) {
				GameRound round = Client.getPlayedRound();
				GameMessageEvent event = new GameMessageEvent(message);
				round.callEvent(event);
			}
		}else if(e.getMessage().startsWith("leave:")) {
			String args[] = e.getMessage().split(":");
			if(args.length == 2) {
				String playerName = args[1];
				if(Client.getPlayedRound() != null) {
					if(Client.getPlayedRound().getPlayer(playerName) != null) {
						GameRound round = Client.getPlayedRound();
						Player player = round.getPlayer(playerName);
						try {
							Class<?> clazz = GameRound.class;
							Method method = clazz.getDeclaredMethod("removePlayer", player.getClass());
							method.setAccessible(true);
							method.invoke(round, player);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						round.callEvent(new PlayerLeaveGameEvent(player));
					}
				}
			}
		}else if(e.getMessage().equals("stop")) {
			RoundStopEvent event = new RoundStopEvent(Client.getPlayedRound());
			EventHandler.callEvent(event);
		}
	}
	
}
