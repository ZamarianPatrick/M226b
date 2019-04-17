package patrick;

import patrick.event.Listener;
import patrick.event.events.player.PlayerLeaveGameEvent;
import patrick.event.events.player.PlayerMessageEvent;
import patrick.events.PlayerWinEvent;
import patrick.game.Player;
/**
 * <p>Ein Listener, welcher auf sämtliche Spielbezogene Events reagiert</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class EventListener implements Listener{

	/**
	 * Mainklasse des Spiels
	 */
	
	private TicTacToe main;
	
	/**
	 * Erzeugt ein EventListener
	 * 
	 * @param main Mainklasse des Spiels
	 */
	
	public EventListener(TicTacToe main) {
		this.main = main;
		this.main.registerListener(this);
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein PlayerWinEvent ausgelöst wurde
	 * 
	 * @param e PlayerWinEvent, welcher ausgelöst wurde
	 */
	
	public void onWin(PlayerWinEvent e) {
		for(Player player : main.getPlayers()) {
			player.send("win:"+e.getPlayer().getName());
		}
		main.stop();
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein PlayerLeaveGameEvent ausgelöst wurde
	 * 
	 * @param e PlayerLeaveGameEvent, welcher ausgelöst wurde
	 */
	
	public void onLeave(PlayerLeaveGameEvent e) {
		if(this.main.getPlayers().size() == 1) {
			Player winner = this.main.getPlayers().get(0);
			PlayerWinEvent event = new PlayerWinEvent(winner);
			main.callEvent(event);
		}
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein PlayerMessageEvent ausgelöst wurde
	 * 
	 * @param e PlayerMessageEvent, welcher ausgelöst wurde
	 */
	
	public void onMessage(PlayerMessageEvent e) {
		if(e.getMessage().startsWith("position")) {
			if(main.focusedPlayer.equals(e.getPlayer()) == false) {
				return;
			}
			String[] args = e.getMessage().split(":");
			if(args.length == 2) {
				String[] positionString = args[1].split(";");
				if(positionString.length == 2) {
					try {
						int x = Integer.parseInt(positionString[0]);
						int y = Integer.parseInt(positionString[1]);
						if(x >= 0 && x <= 1000) {
							if(y >= 0 && y <= 1000) {
								for(Player player : main.getPlayers()) {
									if(player.equals(e.getPlayer()) == false) {
										player.send("position:"+x+";"+y);
									}
								}
							}
						}
					} catch (NumberFormatException ex) {
						return;
					}
				}
			}
		}else if(e.getMessage().startsWith("klick")) {
			if(main.focusedPlayer.equals(e.getPlayer()) == false) {
				return;
			}
			String[] args = e.getMessage().split(":");
			if(args.length == 2) {
				try {
					int field = Integer.parseInt(args[1]);
					if(field > 0 && field < 10) {
						if(main.isSetted(field) == false) {
							main.setField(field, e.getPlayer());
							for(Player player : main.getPlayers()) {
								if(player.equals(e.getPlayer()) == false) {
									player.send("klick:"+field);
									main.focusedPlayer = player;
								}
							}
							boolean win = main.checkWin(e.getPlayer());
							if(win == false) {
								if(main.getSettedFields() == 9) {
									for(Player player : main.getPlayers()) {
										player.send("semi");
									}
									main.stop();
								}else {
									for(Player player : main.getPlayers()) {
										if(player.equals(e.getPlayer()) == false) {
											player.send("yourMove");
										}
									}	
								}
							}
						}
					}
				} catch (NumberFormatException ex) {
					return;
				}
			}
		}
	}
	
}
