package patrick.component.interfaces;

import patrick.component.PBorder;
/**
 * <p>L�sst sich Umrahmen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public interface Borderizable{

	/**
	 * Legt die Umrahmung fest
	 * 
	 * @param border Umrahmung des Objektes
	 */
	
	public void setBorder(PBorder border);
	
	/**
	 * Gibt die aktuelle Umrahmung zur�ck
	 * 
	 * @return Aktuelle Umrahmung
	 */
	
	public PBorder getBorder();
	
}
