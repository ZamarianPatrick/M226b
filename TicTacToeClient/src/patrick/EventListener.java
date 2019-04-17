package patrick;

import javax.swing.JOptionPane;

import patrick.event.Listener;
import patrick.event.events.game.GameMessageEvent;
import patrick.events.ReceiveKlickEvent;
import patrick.events.ReceivePositionEvent;
/**
 * <p>Ein Listener, welcher auf sämtliche Events des Spiels reagiert</p>
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
	 * Erzeugt ein EventListener und registriert diesen
	 * 
	 * @param main Mainklasse des Spiels
	 */
	
	public EventListener(TicTacToe main) {
		this.main = main;
		this.main.registerListener(this);
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein ReceiveKlickEvent ausgelöst wurde
	 * 
	 * @param e ReceiveKlickEvent, welcher ausgelöst wurde
	 */
	
	public void onEnemyKlick(ReceiveKlickEvent e) {
		main.enemyKlick(e.getField());
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein ReceivePositionEvent ausgelöst wurde
	 * 
	 * @param e ReceivePositionEvent, welcher ausgelöst wurde
	 */
	
	public void onEnemyMove(ReceivePositionEvent e) {
		main.enemyMove(e.getX()-75, e.getY()-75);
	}
	
	/**
	 * Diese Methode wird aufgerufen, wenn ein GameMessageEvent ausgelöst wurde
	 * 
	 * @param e GameMessageEvent, welcher ausgelöst wurde
	 */
	
	public void onMessage(GameMessageEvent e) {
		if(e.getMessage().startsWith("win:")) {
			String name = e.getMessage().split(":")[1];
			if(Client.getPlayerName().equals(name)) {
				JOptionPane.showMessageDialog(
						null,
						"Du hast gewonnen",
						"Sieg",
						JOptionPane.INFORMATION_MESSAGE
						);
			}else {
				JOptionPane.showMessageDialog(
						null,
						"Der Spieler " + name + " hat gewonnen!",
						"Niederlage",
						JOptionPane.INFORMATION_MESSAGE
						);	
			}
		}else if(e.getMessage().equals("semi")){
			JOptionPane.showMessageDialog(
					null,
					"Das Spiel endet unentschieden!",
					"Unentschieden",
					JOptionPane.INFORMATION_MESSAGE
					);	
		}else if(e.getMessage().equals("yourMove")) {
			this.main.myMove();
		}else if(e.getMessage().startsWith("position:")) {
			String[] args = e.getMessage().split(":");
			if(args.length == 2) {
				String[] positionString = args[1].split(";");
				if(positionString.length == 2) {
					try {
						int x = Integer.parseInt(positionString[0]);
						int y = Integer.parseInt(positionString[1]);
						ReceivePositionEvent event = new ReceivePositionEvent(x, y);
						main.callEvent(event);
					} catch (NumberFormatException ex) {}
				}
			}
		}else if(e.getMessage().startsWith("klick:")) {
			String[] args = e.getMessage().split(":");
			if(args.length == 2) {
				try {
					int field = Integer.parseInt(args[1]);
					ReceiveKlickEvent event = new ReceiveKlickEvent(field);
					main.callEvent(event);
				} catch (NumberFormatException ex) {}
			}
		}
	}
	
}
