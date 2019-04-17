package patrick.component.interfaces;
/**
 * <p>L�sst sich nach einer gewissen Zeit oder bei einem bestimmten Ereignis l�schen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public interface Destroyable {

	/**
	 * Gibt den Wahrheitswert zur�ck, ob das Objekt bei der n�chsten draw Schleife
	 * gel�scht wird
	 * 
	 * @return Wahrheitswert �ber L�schung
	 */
	
	public boolean isDestroyed();
	
	/**
	 * Legt fest, ob das Objekt bei der n�chsten draw Schleife gel�scht werden soll
	 * 
	 * @param destroyed Wahrheitswert der L�schung
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
	 * Gibt den Wert zur�ck, wie oftmal das Objekt noch durch die draw Schleife
	 * gezeichnet wird.
	 * 
	 * @return Anzahl Zeichnungen
	 */
	
	public int getLiveTicks();
	
}
