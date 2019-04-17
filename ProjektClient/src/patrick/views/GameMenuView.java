package patrick.views;

import patrick.Client;
import patrick.component.action.ClickListener;
import patrick.component.components.PButton;
import patrick.component.components.PContainer;
import patrick.component.components.PLabel;
import patrick.game.Game;
import patrick.utils.Components;
import patrick.utils.Message;
import processing.core.PApplet;
import processing.event.MouseEvent;
/**
 * <p>Übersicht für ein Spielmenü</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameMenuView extends PContainer{

	/**
	 * Spiel, für welches die Übersicht ist
	 */
	
	private final Game game;
	
	/**
	 * Erzeugt eine GameMenuView
	 * 
	 * @param game Spiel, für welches die Übersicht ist
	 * @param width Breite der Übersicht
	 * @param height Höhe der Übersicht
	 */
	
	public GameMenuView(Game game, int width, int height) {
		this.width = width;
		this.height = height;
		this.game = game;
		
		int buttonWidth = width / 3;
		int buttonX = (width - width/3) / 2;
		int distance = 50;
		int buttonY = 150;
		
		PLabel label = Components.getTitleLabel(game.getName(), width, 0, 10);	
		add(label);
		
		PButton back = Components.getBackButton(buttonWidth);
		back.setLocation(buttonX, buttonY);
		
		buttonY += back.getHeight() + distance;
		
		PButton createGame = Components.getButton("Spiel erstellen", buttonWidth);
		createGame.setLocation(buttonX, buttonY);
		
		createGame.addActionListener(new ClickListener() {
			
			@Override
			public void onClick(MouseEvent e) {
				if(e.getButton() == PApplet.LEFT) {
					Client.send(Message.hostGame(game.getName()));
					Client.setFocusedGame(getGame());
				}
			}
		});
		
		buttonY += createGame.getHeight() + distance;
		
		PButton joinGame = Components.getButton("Spiel beitreten", buttonWidth);
		joinGame.setLocation(buttonX, buttonY);
		
		joinGame.addActionListener(new ClickListener() {
			
			@Override
			public void onClick(MouseEvent e) {
				if(e.getButton() == PApplet.LEFT) {
					Client.send(Message.getRounds(game.getName()));
					Client.setFocusedGame(getGame());
				}
			}
		});
		
		buttonY += joinGame.getHeight() + distance;
		
		PButton gameInfos = Components.getButton("Spielinfos", buttonWidth);
		gameInfos.setLocation(buttonX, buttonY);
		
		gameInfos.addActionListener(new ClickListener() {
			
			@Override
			public void onClick(MouseEvent e) {
				if(e.getButton() == PApplet.LEFT) {
					Client.getView().openGameInfoView(game);
				}
			}
		});
		
		add(back);
		add(createGame);
		add(joinGame);
		add(gameInfos);
	}
	
	/**
	 * Liefert das Spiel zurück, für welches die Übersicht ist
	 * 
	 * @return Spiel, für welches die Übersicht ist
	 */
	
	public Game getGame() {
		return this.game;
	}
	
}
