package patrick.component.components;

import processing.core.PGraphics;
/**
 * <p>Ein PTextField, welches den Text mit einem definierten Passwort-Zeichen
 * anzeigen kann.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PPasswordField extends PTextField{

	/**
	 * Zeichen, welches anstelle des Textes angezeigt wird
	 */
	
	private char passwordChar;
	
	/**
	 * Erzeugt ein PasswordField ohne Text
	 * 
	 * <p>Standardwerte:
	 * <ul>
	 * <li>Das Passwort-Zeichen ist '*'</li>
	 * </ul>
	 */
	
	public PPasswordField() {
		super();
		this.passwordChar = '*';
	}
	
	/**
	 * Methode, welche vom jeweilig �bergeordneten PComponent aufgerufen wird, wenn
	 * sich das PPasswordField zeichnen soll.
	 * 
	 * Diese Methode soll nicht andersweitig aufgerufen werden.
	 */
	
	@Override
	public void draw(PGraphics pg) {
		String password = "";
		for(int i = 0; i < getText().length(); i++) {
			password += passwordChar;
		}
		drawIt(pg, password);
	}
	
	/**
	 * Setzt das Zeichen f�r das Passwort, welches statt dem Text angezeigt wird
	 * 
	 * @param passwordChar neues Passwort-Zeichen
	 */
	
	public void setPasswordChar(char passwordChar) {
		this.passwordChar = passwordChar;
	}
	
	/**
	 * Gibt das aktuelle Passwort-Zeichen zur�ck, welches statt dem Text angezeigt wird
	 * 
	 * @return Aktuelles Passwort-Zeichen
	 */
	
	public char getPasswordChar() {
		return this.passwordChar;
	}
	
}
