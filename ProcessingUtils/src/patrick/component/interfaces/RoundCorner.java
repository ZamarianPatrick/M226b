package patrick.component.interfaces;
/**
 * <p>Die Ecken lassen sich abrunden</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public interface RoundCorner {

	/**
	 * Legt den Wert fest, wie die Ecken abgerundet werden. Der Wert
	 * 0 bedeutet, dass die Ecken gar nicht abgerundet werden.
	 * 
	 * @param cornerValue Wert f�r abgerundete Ecken
	 */
	
	public void setCorner(int cornerValue);
	
	/**
	 * Gibt den aktuellen Wert f�r die abgerundeten Ecken zur�ck
	 * 
	 * @return Aktueller Wert f�r abgerundete Ecken
	 */
	
	public int getCorner();
	
}
