package patrick.utils;

import patrick.Client;
import patrick.component.PBorder;
import patrick.component.action.ClickListener;
import patrick.component.components.PButton;
import patrick.component.components.PLabel;
import patrick.component.components.PTextField;
import processing.core.PApplet;
import processing.event.MouseEvent;
/**
 * <p>Statische Klasse für sich ständig wiederholende Komponenten</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class Components {

	/**
	 * Liefert den Zurück Button
	 * 
	 * @param width Breite des Button
	 * 
	 * @return Zurück Button
	 */
	public static PButton getBackButton(int width) {
		PButton button = getButton("Zurück", width);
		
		button.addActionListener(new ClickListener() {
			@Override
			public void onClick(MouseEvent e) {
				if(e.getButton() == PApplet.LEFT) {
					if(Client.getView() != null) {
						Client.getView().openLastView();
					}
				}
			}
		});
		
		return button;
	}
	
	/**
	 * Erzeugt ein Button mit dem definierten Text, der definierten Breite,
	 * einer Höhe von 50 Pixel und abgerundeten Ecken
	 * 
	 * @param text Text auf dem Button
	 * @param width Breite des Button
	 * 
	 * @return vorgefertigter PButton
	 */
	
	public static PButton getButton(String text, int width){
		PButton button = new PButton();
		button.setText(text);
		button.setSize(width, 50);
		button.setCorner(15);
		return button;
	}
	
	/**
	 * Erzeugt ein PLabel welches für die Überschriften benutzt wird
	 * 
	 * @param text Titel, welcher erscheinen soll
	 * @param width Breite des PLabels in Pixel
	 * @param x Position auf der X-Achse
	 * @param y Position auf der Y-Achse
	 * 
	 * @return vorgefertiges PLabel für einen Titel
	 */
	
	public static PLabel getTitleLabel(String text, int width, int x, int y) {
		PLabel label = getLabel(text, width, 50, x, y);
		label.setColor(255,100,0);
		return label;
	}
	
	/**
	 * Erzeugt ein PLabel mit abgerundeten Ecken, Border, und den gegebenen 
	 * Eigenschaften
	 * 
	 * @param text Text auf dem PLabel
	 * @param width Breite des PLabels in Pixel
	 * @param height Höhe des PLabels in Pixel
	 * @param x Position auf der X-Achse
	 * @param y Position auf der Y-Achse
	 * 
	 * @return vorgefertigtes PLabel
	 */
	
	public static PLabel getLabel(String text, int width, int height, int x, int y) {
		PLabel label = new PLabel();
		label.setText(text);
		label.setSize(width-20, height);
		label.setLocation(x+10, y);
		label.setTextAlignX(PApplet.CENTER);
		label.setTextAlignY(PApplet.CENTER);
		label.setCorner(15);
		label.setBorder(new PBorder(0,2));
		return label;
	}
	
	/**
	 * Erzeugt ein PTextField, welches für die Eingabe eines Benutzernamen
	 * geeignet ist
	 * 
	 * @param text Text, welcher zu Beginn angezeigt werden soll
	 * @param width Breite in Pixel
	 * 
	 * @return vorgefertigtes PTextField
	 */
	
	public static PTextField getUsernameField(String text, int width) {
		PTextField userName = new PTextField();
		userName.setText(text);
		userName.setSize(width);
		userName.setAllowLetters(true);
		userName.setAllowDigit(false);
		userName.setAllowOthers(false);
		userName.setMaxLength(16);
		return userName;
	}
	
}
