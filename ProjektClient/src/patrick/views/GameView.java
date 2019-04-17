package patrick.views;

import javax.swing.JOptionPane;

import patrick.Client;
import patrick.component.PBorder;
import patrick.component.action.ClickListener;
import patrick.component.components.PContainer;
import patrick.component.components.PImageBox;
import patrick.component.components.PLabel;
import patrick.utils.Message;
import processing.core.PApplet;
import processing.event.MouseEvent;
/**
 * <p>Übersicht für ein einzelnes Spiel, welche auf der GamesView angezeigt wird</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameView extends PContainer{

	/**
	 * Name des Spiels
	 */
	
	private String name;
	
	/**
	 * Bildname des Spiels
	 */
	
	private String image;
	
	/**
	 * Erzeugt eine GameView
	 * 
	 * @param name Name des Spiels
	 * @param image Name oder absoluter Pfad des Bildes
	 * @param size Grösse des Quadrates, in welchem das Spiel angezeigt wird
	 */
	
	public GameView(String name, String image, int size) {
		this.name = name;
		this.image = image;
		this.width = size;
		this.height = size + 70;
		
		PImageBox box = new PImageBox(image);
		box.setSize(size, size);
		box.setCursor(PApplet.HAND);
		
		box.addActionListener(new ClickListener() {
			
			@Override
			public void onClick(MouseEvent e) {
				if(e.getButton() == PApplet.LEFT) {
					if(Client.getGame(name) != null) {
						Client.getView().openGameMenuView(Client.getGame(name));
					}else {
						JOptionPane.showMessageDialog(
								null,
								Message.noGame,
								"Fehler",
								JOptionPane.ERROR_MESSAGE
								);
					}
				}
				
			}
		});
		
		add(box);
		
		PLabel label = new PLabel();
		label.setText(name);
		label.setSize(size-4, 50);
		label.setLocation(2, size+10);
		label.setTextAlignX(PApplet.CENTER);
		label.setCorner(15);
		label.setColor(255,100,0);
		label.setBorder(new PBorder(0,2));
		add(label);
	}
	
	/**
	 * Liefert den Spielnamen zurück
	 * 
	 * @return Spielname
	 */
	
	public String getGameName() {
		return this.name;
	}
	
	/**
	 * Liefert den Bildnamen oder absoluten Pfad des Bildes zurück
	 * 
	 * @return Bildname oder absoluter Pfad des Bildes
	 */
	
	public String getImageName() {
		return this.image;
	}
	
}
