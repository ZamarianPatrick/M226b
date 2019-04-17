package patrick.views;

import patrick.Client;
import patrick.component.action.ClickListener;
import patrick.component.components.PButton;
import patrick.component.components.PContainer;
import processing.core.PApplet;
import processing.event.MouseEvent;
/**
 * <p>Übersicht welche angezeigt wird, wenn der Client gestartet wurde</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class StartView extends PContainer{

	/**
	 * Erzeugt eine StartView
	 * 
	 * @param width Breite der Übersicht
	 * @param height Höhe der Übersicht
	 */
	
	public StartView(int width, int height) {
		this.width = width;
		this.height = height;
		
		PButton button = new PButton();
		button.setText("Verbinden");
		button.setSize(width / 5, height / 10);
		button.setLocation(width/2 - (button.getWidth()/2), height/2 - (button.getHeight() / 2));
		button.setCorner(15);
		
		button.addActionListener(new ClickListener() {
			
			@Override
			public void onClick(MouseEvent e) {
				if(e.getButton() == PApplet.LEFT) {
					Client.connectToServer();
				}
			}
		});
		
		add(button);
	}
	
}
