package patrick.views;

import patrick.component.components.PAutoSizeContainer;
import patrick.component.components.PButton;
import patrick.component.components.PContainer;
import patrick.component.components.PLabel;
import patrick.component.components.PScrollableContainer;
import patrick.component.enums.AutoSize;
import patrick.component.enums.Scrollable;
import patrick.game.Game;
import patrick.utils.Components;
/**
 * <p>Übersicht für die Informationen eines Spiels</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameInfoView extends PContainer{

	/**
	 * Spiel, von welchem die Informationen stammen
	 */
	
	private Game game;
	
	/**
	 * Erzeugt eine GameInfoView
	 * 
	 * @param game Spiel, von welchem die Informationen stammen
	 * @param width Breite der Übersicht
	 * @param height Höhe der Übersicht
	 */
	
	public GameInfoView(Game game, int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;
		
		PButton back = Components.getBackButton(width / 5);
		back.setLocation(10, 5);
		
		PLabel label = Components.getTitleLabel(
				game.getName() + " Spielinfos",
				width,
				10,
				5
				);
		
		add(label);
		add(back);
		
		int descriptionRows = getRows(game.getDescription()) + 1;
		int manualRows = getRows(game.getManual()) + 1;
		int controlRows = game.getControl().size() + 1;
		
		PAutoSizeContainer container = new PAutoSizeContainer();
		container.setAutoSize(AutoSize.VERTICAL);
		container.setSize(width, 1);
		container.setLocation(0, 0);
		
		PScrollableContainer scroll = new PScrollableContainer();
		scroll.setLocation(0, 70);
		scroll.setSize(width, height-70);
		
		int y = 0;
		
		PLabel desc = Components.getLabel(
				"Spielbeschreibung:\n" + game.getDescription(),
				width,
				descriptionRows * 50,
				0,
				y
				);
		
		desc.setColor(240);
		
		y += desc.getHeight() + 20;
		
		PLabel manual = Components.getLabel(
				"Spielanleitung:\n" + game.getManual(),
				width,
				manualRows * 50,
				0,
				y
				);
		
		manual.setColor(240);
		
		y += manual.getHeight() + 20;
		
		String controlText = "Spielsteuerung:";
		for(String control : game.getControl()) {
			controlText += "\n"+control;
		}
		
		PLabel control = Components.getLabel(
				controlText,
				width,
				controlRows * 50,
				0,
				y
				);

		control.setColor(240);
		
		container.add(desc);
		container.add(manual);
		container.add(control);
		
		scroll.setScrollable(Scrollable.VERTICAL);
		scroll.setViewContent(container);
		add(scroll);
	}
	
	/**
	 * Liefert das Spiel zurück, von welchem die Informationen stammen
	 * 
	 * @return Spiel, von welchem die Informationen stammen
	 */
	
	public Game getGame() {
		return this.game;
	}
	
	/**
	 * Liefert die Anzahl Zeilen eines Textes
	 * 
	 * @param text zu überprüfender Text
	 * 
	 * @return Anzahl Zeilen des Textes
	 */
	
	private int getRows(String text) {
		int count = 1;
		for(char c : text.toCharArray()) {
			if(c == '\n') {
				count++;
			}
		}
		return count;
	}
	
}
