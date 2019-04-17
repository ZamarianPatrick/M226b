package patrick;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import patrick.events.PlayerWinEvent;
import patrick.game.Game;
import patrick.game.Player;
import patrick.game.RunningRound;
import patrick.game.WaitingRound;
/**
 * <p>Einstiegsklasse des Spiels</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class TicTacToe extends RunningRound{

	/**
	 * Besetzte Felder, mit dem Spieler, welche sie besetzt hat
	 */
	
	private Map<Integer, Player> fields = new HashMap<Integer, Player>();
	
	/**
	 * Spieler, welcher am Spielzug ist
	 */
	
	public Player focusedPlayer;
	
	/**
	 * Erzeugt eine leere RunningRound
	 * Wird vom Serversystem benötigt
	 */
	
	public TicTacToe() {
		super();
	}
	
	/**
	 * Erzeugt eine laufende Runde aus einer wartenden Runde
	 * 
	 * @param round wartende Runde
	 */
	
	public TicTacToe(WaitingRound round) {
		super(round);
	}
	
	/**
	 * @see patrick.game.RunningRound#gameSetup()
	 */
	
	@Override
	public Game gameSetup() {
		Game game = new Game("Tic Tac Toe");
		game.setDefaultPlayerAmount(2);
		game.addPlayerAmount(2);
		game.setImage("tictactoe.png");
		return game;
	}
	
	/**
	 * @see patrick.game.RunningRound#onStart()
	 */

	@Override
	public void onStart() {
		registerListener();
		Random rdm = new Random();
		this.focusedPlayer = getPlayers().get(rdm.nextInt(getPlayers().size()));
		this.focusedPlayer.send("yourMove");
	}
	
	/**
	 * registriert die Listener
	 */
	
	public void registerListener() {
		new EventListener(this);
	}
	
	/**
	 * Besetzt ein Feld mit dem Spieler
	 * 
	 * @param field zu besetzendes Feld
	 * @param player Spieler, welcher das Feld besetzt
	 */
	
	public void setField(Integer field, Player player) {
		fields.put(field, player);
	}
	
	/**
	 * Liefert die Anzahl der besetzten Felder zurück
	 * 
	 * @return Anzahl besetze Felder
	 */
	
	public int getSettedFields() {
		return fields.size();
	}
	
	/**
	 * Überprüft, ob das Feld besetzt ist
	 * 
	 * @param field zu überprüfendes Feld
	 * 
	 * @return true wenn das Feld besetzt ist, ansonsten false
	 */
	
	public boolean isSetted(Integer field) {
		return fields.containsKey(field);
	}
	
	/**
	 * Überprüft, ob ein Spieler das Spiel gewonnen hat
	 * 
	 * @param player zu überprüfender Spieler
	 * 
	 * @return true wenn der Spieler gewonnen hat, ansonsten false
	 */
	
	public boolean checkWin(Player player) {
		List<Integer> playerFields = new ArrayList<Integer>();
		for(Entry<Integer, Player> entry : fields.entrySet()) {
			if(player.equals(entry.getValue())) {
				playerFields.add(entry.getKey());
			}
		}
		if(playerFields.containsAll(Arrays.asList(1,4,7)) ||
				playerFields.containsAll(Arrays.asList(2,5,8)) ||
				playerFields.containsAll(Arrays.asList(3,6,9)) ||
				playerFields.containsAll(Arrays.asList(1,2,3)) ||
				playerFields.containsAll(Arrays.asList(4,5,6)) ||
				playerFields.containsAll(Arrays.asList(7,8,9)) ||
				playerFields.containsAll(Arrays.asList(1,5,9)) ||
				playerFields.containsAll(Arrays.asList(3,5,7))) {
			PlayerWinEvent event = new PlayerWinEvent(player);
			callEvent(event);
			return true;
		} 
		return false;
	}

}
