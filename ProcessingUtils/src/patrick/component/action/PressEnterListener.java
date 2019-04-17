package patrick.component.action;
/**
 * Die Klasse wird verwendet, um PComponenten einen PressEnterListener hinzuzuf�gen.
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public abstract class PressEnterListener extends ActionListener{
	
	/**
	 * 	<p>Diese Methode wird jedes Mal ausgef�hrt, wenn der Benutzer
	 * den PComponent auf welchem sich der PressEnterListener befindet, fokusiert hat
	 * und dann eine Enter Eingabe vornimmt. Der PressEnterListener eignet sich
	 * vorallem f�r PTextField Komponenten, um eine bestimmte Aktion auszul�sen, wenn
	 * der Benutzer w�hrend der Texteingabe eine Enter Eingabe t�tigt.</p>
	 * 
	 */
	
	public abstract void onPressEnter();
	
}
