package patrick.component.components;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>Eine Gruppe, welche mehrere PRadioButtons aufnehmen und diese verwalten kann</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PRadioButtonGroup {

	/**
	 * Liste der zugewiesenen PRadioButtons
	 */
	
	private List<PRadioButton> buttons = new ArrayList<PRadioButton>();
	
	/**
	 * Zustand, ob immer ein PRadioButton aktiviert sein muss
	 */
	
	private boolean alwaysOne;
	
	/**
	 * Erzeugt eine neue PRadioButtonGroup
	 * 
	 * alwaysOnce ist standardm�ssig auf true
	 */
	public PRadioButtonGroup() {
		this.alwaysOne = true;
	}
	
	/**
	 * F�gt den PRadioButton zu der Gruppe hinzu.
	 * Diese Methode wird jeweils bei der Methode setGroup vom PRadioButton aufgerufen
	 * und soll nicht andersweitig ausgef�hrt werden.
	 * 
	 * @param button PRadioButton welcher hinzugef�gt werden soll
	 */
	
	protected void addRadioButton(PRadioButton button) {
		buttons.add(button);
		if(getActivated() != null) {
			button.setActivated(false);
		}
		if(this.alwaysOne) {
			if(getActivated() == null) {
				button.setActivated(true);
			}
		}
	}
	
	/**
	 * Entfernt einen hinzuge�fgen PRadioButton wieder aus der Liste
	 * 
	 * @param button PRadioButton welcher entfernt werden soll
	 */
	
	protected void removeRadioButton(PRadioButton button) {
		buttons.remove(button);
	}
	
	/**
	 * Gibt eine Liste aller hinzugef�gten PRadioButtons zur�ck
	 * 
	 * @return Liste aller hinzugef�gten PRadioButtons
	 */
	
	public List<PRadioButton> getRadioBttons(){
		return new ArrayList<PRadioButton>(buttons);
	}
	
	/**
	 * Gibt den aktivierten PRadioButton zur�ck
	 * 
	 * @return null wenn keine aktiviert ist, ansonsten den aktivierten PRadioButton
	 */
	
	public PRadioButton getActivated() {
		for(PRadioButton button : buttons) {
			if(button.isActivated()) {
				return button;
			}
		}
		return null;
	}
	
	/**
	 * Aktiviert den gegebenen PRadioButton und deaktiviert alle anderen, welche dieser
	 * Gruppe zugewiesen sind.
	 * 
	 * @param activated PRadioButton, welcher aktiviert werden soll
	 */
	
	public void activate(PRadioButton activated) {
		if(activated.activated == false) {
			activated.activated = true;
		}
		for(PRadioButton btn : buttons) {
			if(btn.equals(activated) == false) {
				btn.activated = false;
			}
		}
	}
	
	/**
	 * Legt den Zustand fest, ob immer ein PRadioButton aktiviert sein muss
	 * 
	 * @param alwaysOne Zustand f�r die Aktvierung
	 */
	
	public void setAlwaysOne(boolean alwaysOne) {
		this.alwaysOne = alwaysOne;
	}
	
	/**
	 * Gibt den Zustand zur�ck, ob immer ein PRadioButton aktiviert sein muss
	 * 
	 * @return Zustand f�r die Aktivierung
	 */
	
	public boolean isAlwaysOne() {
		return this.alwaysOne;
	}
	
}
