package patrick.component.action;
/**
 * Die Klasse wird verwendet, um PComponenten einen PressEnterListener hinzuzufügen.
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public abstract class PressEnterListener extends ActionListener{
	
	/**
	 * 	<p>Diese Methode wird jedes Mal ausgeführt, wenn der Benutzer
	 * den PComponent auf welchem sich der PressEnterListener befindet, fokusiert hat
	 * und dann eine Enter Eingabe vornimmt. Der PressEnterListener eignet sich
	 * vorallem für PTextField Komponenten, um eine bestimmte Aktion auszulösen, wenn
	 * der Benutzer während der Texteingabe eine Enter Eingabe tätigt.</p>
	 * 
	 */
	
	public abstract void onPressEnter();
	
}
