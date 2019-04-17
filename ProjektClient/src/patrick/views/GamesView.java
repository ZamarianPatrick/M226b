package patrick.views;

import java.util.Map;

import patrick.component.components.PAutoSizeContainer;
import patrick.component.enums.AutoSize;
/**
 * <p>Übersicht, welche alle Spiele anzeigt</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GamesView extends PAutoSizeContainer{

	/**
	 * Erzeugt eine GamesView
	 * 
	 * @param games Spiele, welche alle existieren
	 * @param width Breite der Übersicht
	 * @param gameSize Grösse der einzelnen Spiele
	 */
	
	public GamesView(Map<String, String> games, int width, int gameSize) {
		this.setAutoSize(AutoSize.VERTICAL);
		this.width = width;
		this.x = 0;
		this.y = 0;
		int y = 0;
		int x = 0;
		for(Map.Entry<String, String> entry : games.entrySet()) {
			GameView gameView = new GameView(entry.getKey(), entry.getValue(), gameSize);
			gameView.setLocation(x, y);
			if(x <= width-2*(gameView.getWidth()+20)) {
				x+= gameView.getWidth()+20;
			}else {
				y+= gameView.getHeight()+20;
				x = 0;
			}
			add(gameView);
		}
	}
	
}
