package patrick.event.listener;

import java.net.Socket;
import patrick.Server;
import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.ClientHostGameEvent;
import patrick.event.events.ClientLeaveEvent;
import patrick.event.events.ClientRequestRoundsEvent;
import patrick.event.events.ClientSendMessageEvent;
import patrick.event.events.TryCreateRoundEvent;
import patrick.event.events.TryJoinRoundEvent;
import patrick.event.events.player.PlayerMessageEvent;
import patrick.game.Game;
import patrick.game.GameRound;
import patrick.game.Player;
import patrick.game.RunningRound;
import patrick.packets.in.GameSettingsPacket;
import patrick.utils.Message;
/**
 * <p>Ein Listener, welcher auf die MessageEvents reagiert, und die Nachrichten
 * in entsprechende Events umwandelt und diese auslöst.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class MessageListener implements Listener{

	/**
	 * Erzeugt ein MessageListener und registriert diesen
	 */
	
	public MessageListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein ClientSendMessageEvent ausgelöst wurde
	 * Die Methode prüft sämtliche Nachrichten die von Clients empfangen werden, und wandelt
	 * diese in die entsprechenden Events um und löst diese aus. Diese werden dann von den
	 * entsprechenden Listener weiterverarbeitet.
	 * 
	 * @param e ClientSendMessageEvent, welcher ausgelöst wurde
	 */
	
	public void onMessage(ClientSendMessageEvent e) {
		if(e.getMessage().startsWith("game:")) {
			String[] split = e.getMessage().split(":");
			if(split.length >= 2) {
				if(split.length == 3) {
					if(split[1].startsWith("host")) {
						Game game = Server.getGame(split[2]);
						if(game != null) {
							leave(e.getClient());
							ClientHostGameEvent event = new ClientHostGameEvent(e.getClient(), game);
							EventHandler.callEvent(event);
						}else {
							Server.sendMessage(e.getClient(), Message.gameDontExists);
						}
					}
				}
			}
		}else if(e.getMessage().startsWith("create:")) {
			leave(e.getClient());
			String packetText = e.getMessage().replace("create:", "");
			GameSettingsPacket packet = new GameSettingsPacket(packetText);
			TryCreateRoundEvent event = new TryCreateRoundEvent(e.getClient(), packet);
			EventHandler.callEvent(event);
		}else if(e.getMessage().startsWith("join:")) {
			String[] args = e.getMessage().split(":");
			if(args.length == 2) {
				leave(e.getClient());
				ClientRequestRoundsEvent event = new ClientRequestRoundsEvent(e.getClient(), args[1]);
				EventHandler.callEvent(event);
			}else if(args.length == 4) {
				leave(e.getClient());
				TryJoinRoundEvent event = new TryJoinRoundEvent(e.getClient(), args[1], args[2], args[3]);
				EventHandler.callEvent(event);
			}
		}else if(e.getMessage().equals("leave")){
			leave(e.getClient());
		}else if(e.getMessage().startsWith("gaming:")) {
			try {
				Player player = Server.getPlayerGameRound(e.getClient()).getKey();
				String roundName = Server.getPlayersGameRoundName(player);
				GameRound round = Server.getGameRound(roundName);
				String message = e.getMessage().substring("gaming:".length(), e.getMessage().length()); 
				if(round instanceof RunningRound) {
					((RunningRound) round).callEvent(new PlayerMessageEvent(player, message));
				}
			}catch(Exception ex) {
				
			}
		}
	}
	
	/**
	 * Löst ein ClientLeaveEvent aus
	 * 
	 * @param socket Sockelverbindung des Clients, mit welcher der Event ausgelöst wird
	 */
	
	public void leave(Socket socket) {
		ClientLeaveEvent event = new ClientLeaveEvent(socket);
		EventHandler.callEvent(event);
	}
	
}
