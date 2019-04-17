package patrick.event.listener;

import java.util.ArrayList;
import java.util.List;

import patrick.Client;
import patrick.event.EventHandler;
import patrick.event.Listener;
import patrick.event.events.GameStartEvent;
import patrick.game.Player;
/**
 * <p>Ein Listener, der auf die GameStartEvents reagiert</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameStartListener implements Listener {

	/**
	 * Erzeugt einen neuen GameStartListener und registriert diesen
	 */
	
	public GameStartListener() {
		EventHandler.registerListener(this);
	}
	
	/**
	 * Methode, welche aufgerufen wird, wenn ein GameStartEvent ausgelöst wurde
	 * 
	 * @param e GameStartEvent, welcher ausgelöst wurde
	 */
	
	public void onStart(GameStartEvent e) {
		if(Client.getView().getRoundView() != null) {
			List<String> playerNames = Client.getView().getRoundView().getPlayers();
			List<Player> players = new ArrayList<Player>();
			for(String playerName : playerNames) {
				Player player = new Player(playerName);
				players.add(player);
			}
			Client.startGame(e.getGame(), e.getSettings(), players);
		}
			
	}
	
}
