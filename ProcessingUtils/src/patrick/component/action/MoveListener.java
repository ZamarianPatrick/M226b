package patrick.component.action;

import processing.event.MouseEvent;
/**
 * Die Klasse wird verwendet, um PComponenten einen MoveListener hinzuzufügen.
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public abstract class MoveListener extends ActionListener{

	/**
	 * 	<p>Diese Methode wird jedes Mal aufgerufen, wenn der Benutzer mit der Maus
	 * über den PComponent fährt, auf welchem sich der MoveListener befindet.</p>
	 * 
	 * @param e Der MouseEvent, welcher den MoveListener aufruft.
	 */
	
	public abstract void onMouseMove(MouseEvent e);
	
}
