package patrick.component.interfaces;
/**
 * <p>L�sst sich aktivieren oder deaktivieren</p>
 * 
 * @author Patrick
 * @version 1.0
 */
public interface Activable {

	/**
	 * Gibt den Wahrheitswert �ber die Aktivierung zur�ck
	 * 
	 * @return Wahrheitswert der Aktivierung
	 */
	
	public boolean isActivated();
	
	/**
	 * Legt den Zustand der Aktivierung fest
	 * 
	 * @param activated Zustand f�r die Aktivierung
	 */
	
	public void setActivated(boolean activated);
	
}
