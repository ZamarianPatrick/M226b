package patrick;

import patrick.component.components.PImageBox;
import patrick.game.Game;
import patrick.game.GameRound;
import patrick.game.Round;
import processing.core.PApplet;
import processing.event.MouseEvent;
/**
 * <p>Einstiegsklasse des Spiels</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class TicTacToe extends GameRound{

	/**
	 * Zustand, ob man selbst am Spielzug ist
	 */
	
	public boolean isMyMove;
	
	/**
	 * Repräsentiert das Bild, welches angezeigt wird, wenn man am Zug ist und die Maus bewegt
	 */
	
	public PImageBox cursor;
	
	/**
	 * Repräsentiert das Bild des Gegners, wenn der am Zug ist und die Maus bewegt
	 */
	
	public PImageBox enemyCursor;
	
	/**
	 * Array für die bereits besetzten Felder
	 */
	
	public Integer[] fields = new Integer[9];
	
	/**
	 * Erzeugt eine leere Spielrunde
	 * Wird vom Serversystem benötigt
	 */
	
	public TicTacToe() {
		super();
	}
	
	/**
	 * Erzeugt eine laufende Spielrunde aus einer wartenden Spielrunde
	 * @param round
	 */
	
	public TicTacToe(Round round) {
		super(round);
	}
	
	/**
	 * @see patrick.game.GameRound#gameSetup()
	 */
	
	@Override
	public Game gameSetup() {
		Game game = new Game("Tic Tac Toe");
		game.setDescription("Tic Tac Toe ist ein klassisches Strategie Spiel.");
		
		game.setManual("Auf einem Spielbrett mit 9 Feldern setzen die Spieler\n"
				+ "jeweils abwechslungsweise ihr Symbol. Wer als erstes 3 seiner\n"
				+ "Symbole in einer Reihe gesetzt hat gewinnt das Spiel.\n"
				+ "Diese Reihe kann auch diagonal erzielt werden.");
		
		game.addControl("Mausbewegung: Platziere dein Symbol");
		game.addControl("Mausklick: Bestätige die Position deines Symboles");
		return game;
	}
	
	/**
	 * @see patrick.game.GameRound#onStart()
	 */

	@Override
	public void onStart() {
		isMyMove = false;
		registerListener();
		showView();
	}
	
	/**
	 * registriert die Spielbezogenen Listener
	 */
	
	public void registerListener() {
		new EventListener(this);
	}
	
	/**
	 * Bereitet die Spielansicht vor
	 */
	
	public void showView() {
		View.setStartView(this, this.gameContainer);
		this.cursor = View.getCursorSymbol(Symbol.CROSS);
		this.enemyCursor = View.getCursorSymbol(Symbol.CIRCLE);
		this.cursor.setVisible(false);
		this.gameContainer.add(this.cursor);
		this.gameContainer.add(this.enemyCursor);
	}
	
	/**
	 * wird ausgeführt, wenn ein Klick auf den PGameContainer ausgeführt wurde
	 * 
	 * @param e MouseEvent, welcher ausgeführt wurde
	 */
	
	public void onClick(MouseEvent e) {
		if(e.getButton() == PApplet.LEFT) {
			if(isMyMove) {
				int x = e.getX();
				int y = e.getY();
				int row = 0;
				int column = 0;
				if(x>230 && x<750) {
					if(x<350) {
						row = 1;
					}else if(x > 450 && x < 550) {
						row = 2;
					}else if(x > 650) {
						row = 3;
					}else {
						return;
					}
				}else {
					return;
				}
				if(y > 80 && y < 600) {
					if(y < 180) {
						column = 1;
					}else if(y > 300 && y < 400) {
						column = 2;
					}else if(y > 500) {
						column = 3;
					}else {
						return;
					}
				}else {
					return;
				}
				int field = (column-1) * 3 + row;
				if(fields[field-1] != null) {
					if(fields[field-1] == 1) {
						return;
					}
				}
				View.setSymbol(this.gameContainer, field, Symbol.CROSS);
				fields[field-1] = 1;
				cursor.setVisible(false);
				this.gameContainer.getComponents().remove(enemyCursor);
				this.gameContainer.add(enemyCursor);
				enemyCursor.setVisible(true);
				send("klick:"+field);
				isMyMove = false;
			}
		}
	}
	
	/**
	 * Wird ausgeführt, wenn eine Mausbewegung auf dem GameContainer ausgeführt wurde
	 * 
	 * @param e MouseEvent, welcher ausgeführt wurde
	 */
	
	public void onMove(MouseEvent e) {
		if(isMyMove) {
			cursor.setLocation(e.getX()-75, e.getY()-75);
			send("position:"+e.getX()+";"+e.getY());
		}
	}
	
	/**
	 * wird ausgeführt, wenn man am Spielzug ist
	 */
	
	public void myMove() {
		this.isMyMove = true;
		this.cursor.setLocation(Client.getView().getRoot().getPApplet().mouseX,
				Client.getView().getRoot().getPApplet().mouseY);
		this.gameContainer.getComponents().remove(cursor);
		this.gameContainer.add(this.cursor);
		this.cursor.setVisible(true);
		this.enemyCursor.setVisible(false);
	}
	
	/**
	 * Wird ausgeführt, wenn der Gegner einen Klick ausführte
	 * 
	 * @param field Feld, auf welches der Gegner geklickt hat
	 */
	
	public void enemyKlick(int field) {
		this.enemyCursor.setVisible(false);
		View.setSymbol(this.gameContainer, field, Symbol.CIRCLE);
		fields[field-1] = 1;
	}
	
	/**
	 * Wird ausgeführt, wenn der Gegner seine Maus bewegt
	 * 
	 * @param x Mausposition auf der X-Achse
	 * @param y Mausposition auf der Y-Achse
	 */
	
	public void enemyMove(int x, int y) {
		this.enemyCursor.setLocation(x, y);
	}
	

	
}
