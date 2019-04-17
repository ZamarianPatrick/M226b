package patrick.component.action;

import java.awt.Point;

import processing.event.MouseEvent;
/**
 * Die Klasse wird verwendet, um PComponenten einen ClickListener hinzuzuf�gen.
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public abstract class DragListener extends ActionListener{

	/**
	 * 	<p>Diese Methode wird jedes Mal ausgef�hrt, wenn der Benutzer mit der Maus 
	 * �ber den PComoponent auf welchem der Listener angebracht ist zieht.</p>
	 * 
	 * @param lastClick Der Punkt, an welchem der letzte Klick auf den PComponent get�tigt wurde
	 * @param e Der MouseEvent, welcher den DragListener aufruft.
	 */
	
	public abstract void onDrag(Point lastClick, MouseEvent e);
	
}
