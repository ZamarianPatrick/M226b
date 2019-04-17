package patrick.packets.utils;
/**
 * <p>Spieloption, welche einer GameOption hinzugefügt werden kann</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public abstract class Option {

	
	/**
	 * Text, welcher oberhalb der Option angezeigt werden soll
	 */
	
	protected String text;
	
	/**
	 * Name der Einstellung
	 */
	
	protected String name;
	
	/**
	 * Erzeugt eine neue Spieloption
	 * 
	 * @param name Name der Einstellung
	 */
	
	public Option(String name) {
		this.text = "";
		this.name = name;
	}
	
	/**
	 * Erzeugt eine neue Spieloption
	 * 
	 * @param name Name der Einstellung
	 * @param text Text, welcher oberhalb der Spieloption angezeigt wird
	 */
	
	public Option(String name, String text) {
		this.text = text;
		this.name = name;
	}
	
	/**
	 * Liefert den Text zurück, welcher oberhalb der Spieloption angezeigt wird
	 * 
	 * @return Text, welcher oberhalb der Spieloption angezeigt wird
	 */
	
	public String getText() {
		return this.text;
	}
	
	/**
	 * Legt den Text fest, welcher oberhalb der Spieloption angezeigt wird
	 * 
	 * @param text Text, welcher oberhalb der Spieloption angezeigt wird
	 */
	
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Liefert den Namen der Spieloption zurück
	 * 
	 * @return Name der Spieloption
	 */
	
	public String getName() {
		return this.name;
	}
	
	
}
