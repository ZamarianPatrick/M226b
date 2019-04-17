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
	 * @param cornerValue Wert für abgerundete Ecken
	 */
	
	public void setCorner(int cornerValue);
	
	/**
	 * Gibt den aktuellen Wert für die abgerundeten Ecken zurück
	 * 
	 * @return Aktueller Wert für abgerundete Ecken
	 */
	
	public int getCorner();
	
}
