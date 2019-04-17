package patrick.component.interfaces;
/**
 * <p>Lässt sich aktivieren oder deaktivieren</p>
 * 
 * @author Patrick
 * @version 1.0
 */
public interface Activable {

	/**
	 * Gibt den Wahrheitswert über die Aktivierung zurück
	 * 
	 * @return Wahrheitswert der Aktivierung
	 */
	
	public boolean isActivated();
	
	/**
	 * Legt den Zustand der Aktivierung fest
	 * 
	 * @param activated Zustand für die Aktivierung
	 */
	
	public void setActivated(boolean activated);
	
}
