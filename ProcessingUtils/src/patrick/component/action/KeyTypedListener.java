package patrick.component.action;

import processing.event.KeyEvent;
/**
 * Die Klasse wird verwendet, um PComponenten einen KeyTypedListener hinzuzuf�gen.
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public abstract class KeyTypedListener extends ActionListener{

	/**
	 * <p>Diese Methode wird jedes Mal ausf�hrt, wenn der Benutzer Tasteineingaben vornimmt.
	 * Damit der Event auf dem PComponent ausgel�st wird, muss der PComponent auf dem
	 * PRootContainer fokusiert sein. Das heisst der Benutzer muss zuerst auf den 
	 * PComponent geklickt haben, damit dieser den KeyEvent erh�hlt.</p> 
	 * 
	 * @param e Der KeyEvent, welcher den Listener aufruft.
	 */
	
	public abstract void onKeyTyped(KeyEvent e);
	
}
