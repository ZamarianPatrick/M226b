package patrick;

import java.io.File;

import patrick.component.action.ClickListener;
import patrick.component.action.MoveListener;
import patrick.component.components.PGameContainer;
import patrick.component.components.PImageBox;
import processing.event.MouseEvent;
/**
 * <p>Statische Klasse, welche die grafische Benutzeroberfläche für das Spiel
 * verwalten kann.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class View {
	
	/**
	 * Bereitet die Startoberfläche für das Spiel vor
	 * 
	 * @param main Mainklasse des Spiels
	 * @param container GameContainer, auf welchem das Spiel abläuft
	 */
	
	public static void setStartView(TicTacToe main, PGameContainer container) {
		File background = Client.getImage("tBackground.png");
		PImageBox box = new PImageBox(background.getAbsolutePath());
		box.setSize(container.getWidth(), container.getHeight());
		box.setLocation(0, 0);
		container.add(box);
		
		box.addActionListener(new ClickListener() {
			
			@Override
			public void onClick(MouseEvent e) {
				main.onClick(e);
			}
		});
		
		box.addActionListener(new MoveListener() {
			
			@Override
			public void onMouseMove(MouseEvent e) {
				main.onMove(e);
			}
		});
		
		box = new PImageBox(Client.getImage("tBoard.png").getAbsolutePath());
		box.setSize(650, 650);
		box.setLocation(175, 25);
		container.add(box);
	}
	
	/**
	 * Besetzt ein Feld mit einem Symbol
	 * 
	 * @param container GameContainer, auf welchem das Spiel abläuft
	 * @param field Feld, auf welchem das Symbol sein soll
	 * @param symbol Symbol, welches platziert werden soll
	 */
	
	public static void setSymbol(PGameContainer container, int field, Symbol symbol) {
		PImageBox box = null;
		if(symbol == Symbol.CROSS) {
			box = new PImageBox(Client.getImage("tCross.png").getAbsolutePath());
		}else if(symbol == Symbol.CIRCLE) {
			box = new PImageBox(Client.getImage("tCircle.png").getAbsolutePath());
		}
		box.setSize(150, 150);
		
		int row = (int) Math.ceil(field/3D);
		int column = field - (row - 1) * 3;

		int x = 230, y = 80;
		column--;
		row--;
		
		x += column * 200;
		y += row * 200;
		
		box.setLocation(x, y);
		container.add(box);
	}
	
	/**
	 * Liefert eine PImageBox mit dem gegebenen Symbol
	 * 
	 * @param symbol Symbol, welches die ImageBox haben soll
	 * 
	 * @return vorgefertigte PImageBox
	 */
	
	public static PImageBox getCursorSymbol(Symbol symbol) {
		PImageBox box = null;
		if(symbol == Symbol.CROSS) {
			box = new PImageBox(Client.getImage("tCross.png").getAbsolutePath());
		}else if(symbol == Symbol.CIRCLE) {
			box = new PImageBox(Client.getImage("tCircle.png").getAbsolutePath());
		}
		box.setSize(150, 150);
		return box;
	}
	
}

/**
 * <p>Die Symbole, welche im Spiel benutzt werden</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */

enum Symbol{
	
	/**
	 * Kreissymbol
	 */
	
	CIRCLE, 
	
	/**
	 * Kreuzsymbol
	 */
	
	CROSS;
}
