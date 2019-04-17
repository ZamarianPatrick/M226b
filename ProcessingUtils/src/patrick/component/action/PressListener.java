package patrick.component.action;

import processing.event.MouseEvent;
/**
 * Die Klasse wird verwendet, um PComponenten einen PressListener hinzuzufügen.
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public abstract class PressListener extends ActionListener{
	/**
	 * 	<p>Diese Methode wird jedes Mal ausgeführt, wenn der Benutzer auf den PComponent
	 * klickt, auf welchem sich der PressListener befindet. Anders als beim ClickListener
	 * muss hierbei nicht losgelassen werden beim klicken.</p>
	 * 
	 * @param e Der MouseEvent, welcher den PressListener aufruft.
	 */
	public abstract void onPressed(MouseEvent e);	
	
}
