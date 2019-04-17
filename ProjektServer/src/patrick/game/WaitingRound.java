package patrick.game;

import java.util.Timer;
import java.util.TimerTask;

import patrick.Server;
import patrick.packets.Packet;
import patrick.packets.in.GameSettingsPacket;
/**
 * <p>Platzhalter für die Spielrunden, welche sich in der Wartephase befinden</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class WaitingRound extends GameRound{
	
	/**
	 * Zustand ob die Runde bereits gestoppt ist
	 */
	
	private boolean stopped;
	
	/**
	 * Zustand ob die Runde betretbar ist
	 */
	
	private boolean joinable;
	
	/**
	 * Erzeugt eine WaitingRound
	 * 
	 * @param game Spiel der Runde
	 * @param settings Einstellungen der Runde
	 * @param host Spieler, welcher die Runde erstellt hat
	 */
	
	public WaitingRound(Game game, GameSettings settings, Player host) {
		super(game, settings, host);
		stopped = false;
		joinable = true;
	}
	
	/**
	 * Stoppt die Runde
	 */
	
	public void stop() {
		if(stopped) {
			return;
		}
		stopped = true;
		Server.removeWaitingRound(this);
	}
	
	/**
	 * Startet den Countdown, und wandelt sich nach Ablauf des Countdowns zu einer
	 * RunningRound um. Sollten Spieler die Runde verlassen vor Ablauf des Countdowns, so
	 * wird der Countdown abgebrochen.
	 */
	
	public void start() {
		this.joinable = false;
		Timer timer = new Timer();
		WaitingRound round = this;
		int needed = this.settings.getPlayerAmount();
		timer.schedule(new TimerTask() {
			private int count = 10;
			@Override
			public void run() {
				if(getPlayers().size() != needed) {
					sendAll("start:-1");
					cancel();
					joinable = true;
					return;
				}
				
				sendAll("start:"+count);
				
				if(count == 1) {
					GameSettingsPacket packet = new GameSettingsPacket(round);
					sendAll(packet);
					Server.startRound(round);
					this.cancel();
				}
				count--;
			}
		}, 0, 1000);
	}
	
	/**
	 * Liefert den Zustand, ob die Runde betretbar ist
	 * 
	 * @return true wenn die Runde betretbar ist, ansonsten false
	 */
	
	public boolean isJoinable() {
		return this.joinable;
	}
	
	/**
	 * Schickt eine Nachricht an alle Spieler, welche sich in der Runde befinden
	 * 
	 * @param message zu schickende Nachricht
	 */
	
	private void sendAll(String message) {
		for(Player player : getPlayers()) {
			player.sendMessage(message);
		}
	}
	
	/**
	 * Schickt ein Packet an alle Spieler, welche sich in der Runde befinden
	 * 
	 * @param packet zu schickendes Packet
	 */
	
	private void sendAll(Packet packet) {
		sendAll(packet.getLine());
	}
}
