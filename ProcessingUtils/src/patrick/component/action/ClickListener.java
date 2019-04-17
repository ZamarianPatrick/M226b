package patrick.component.action;

import processing.event.MouseEvent;
/**
 * Die Klasse wird verwendet, um PComponenten einen ClickListener hinzuzuf�gen.
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public abstract class ClickListener extends ActionListener{

	/**
	 * 	<p>Diese Methode wird jedes mal ausgef�hrt, wenn der Benutzer einen Klick innerhalb
	 * des Componenten, auf welchem sich der ClickListener befindet t�tigt.</p>
	 * 
	 * @param e Der MouseEvent, welcher den ClickListener aufruft.
	 */
	
	public abstract void onClick(MouseEvent e);
	
}
