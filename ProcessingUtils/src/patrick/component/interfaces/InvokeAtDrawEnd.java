package patrick.component.interfaces;

import processing.core.PGraphics;
/**
 * <p>Ermöglicht die Ausführung von Befehlen am Ende jeder draw Schleife</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public interface InvokeAtDrawEnd {

	/**
	 * Die Methode wird bei jedem draw Ende aufgerufen
	 * 
	 * @param pg Grafik, auf welcher gezeichnet wird
	 */
	
	public void atDrawEnd(PGraphics pg);
	
}
