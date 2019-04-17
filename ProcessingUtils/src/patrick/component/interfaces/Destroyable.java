package patrick.component.interfaces;
/**
 * <p>Lässt sich nach einer gewissen Zeit oder bei einem bestimmten Ereignis löschen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public interface Destroyable {

	/**
	 * Gibt den Wahrheitswert zurück, ob das Objekt bei der nächsten draw Schleife
	 * gelöscht wird
	 * 
	 * @return Wahrheitswert über Löschung
	 */
	
	public boolean isDestroyed();
	
	/**
	 * Legt fest, ob das Objekt bei der nächsten draw Schleife gelöscht werden soll
	 * 
	 * @param destroyed Wahrheitswert der Löschung
	 */
	
	public void setDestroyed(boolean destroyed);
	
	/**
	 * Legt den Wert fest, wie oftmal das Objekt noch durch die draw Schleife
	 * gezeichnet wird.
	 * 
	 * @param ticks Anzahl Zeichnungen
	 */
	
	public void setLiveTicks(int ticks);
	
	/**
	 * Gibt den Wert zurück, wie oftmal das Objekt noch durch die draw Schleife
	 * gezeichnet wird.
	 * 
	 * @return Anzahl Zeichnungen
	 */
	
	public int getLiveTicks();
	
}
