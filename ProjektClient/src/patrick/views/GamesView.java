package patrick.views;

import java.util.Map;

import javax.swing.JOptionPane;

import patrick.component.action.ClickListener;
import patrick.component.components.PAutoSizeContainer;
import patrick.component.components.PButton;
import patrick.component.enums.AutoSize;
import patrick.utils.Components;
import processing.core.PApplet;
import processing.event.MouseEvent;
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
		int y = 10;
		int x = 5;
		
		PButton impressumButton = Components.getButton("Impressum", 200);
		impressumButton.setLocation(x, y);
		
		add(impressumButton);
		
		impressumButton.addActionListener(new ClickListener() {
			
			@Override
			public void onClick(MouseEvent e) {
				if(e.getButton() == PApplet.LEFT) {
					JOptionPane.showMessageDialog(null,
							"Impressum:\n"
							+ "company: TOPOMEDICS AG\n"
							+ "product: games for multiplayer\n"
							+ "product id: 56230\n"
							+ "creator: Patrick Zamarian"
							);
				}
			}
		});
		
		y += impressumButton.getHeight() + 20;
		
		for(Map.Entry<String, String> entry : games.entrySet()) {
			GameView gameView = new GameView(entry.getKey(), entry.getValue(), gameSize);
			gameView.setLocation(x, y);
			if(x <= width-2*(gameView.getWidth()+20)) {
				x+= gameView.getWidth()+20;
			}else {
				y+= gameView.getHeight()+20;
				x = 5;
			}
			add(gameView);
		}
	}
	
}
