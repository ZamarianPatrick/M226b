package patrick.views;

import patrick.component.components.PAutoSizeContainer;
import patrick.component.components.PCheckBox;
import patrick.component.components.PLabel;
import patrick.component.enums.AutoSize;
import patrick.packets.utils.CheckOption;
import patrick.utils.Components;
/**
 * <p>�bersicht f�r eine CheckBox Auswahl</p>
 * 
 * @author Patrick
 * @version 1.0
 * 
 */
public class CheckBoxView extends PAutoSizeContainer{
	
	/**
	 * Einstellungsname
	 */
	
	private String setting;
	
	/**
	 * Checkbox
	 */
	
	private PCheckBox box;
	
	/**
	 * Erzeugt eine CheckBoxView
	 * 
	 * @param option Option welche ausgew�hlt werden kann
	 * @param width	Breite der �bersicht in Pixel
	 */
	
	public CheckBoxView(CheckOption option, int width) {
		setAutoSize(AutoSize.VERTICAL);
		this.width = width;
		this.setting = option.getName();
		
		if(option.getText() != null) {
			PLabel label = Components.getTitleLabel(option.getText(), width, 0, 0);
			add(label);
		}
		
		PCheckBox checkBox = new PCheckBox();
		this.box = checkBox;
		checkBox.setActivated(option.getDefaultValue());
		if(option.getCheckBoxText() != null) {
			checkBox.setText(option.getCheckBoxText());
		}else {
			checkBox.setText("");
		}
		checkBox.setSize(width-20, 30);
		checkBox.setLocation(10, 70);
		add(checkBox);
	}
	
	/**
	 * Liefert die PCheckBox der �bersicht zur�ck
	 * 
	 * @return PCheckBox der �bersicht
	 */
	
	public PCheckBox getCheckBox() {
		return this.box;
	}
	
	/**
	 * Liefert die gew�hlte Option zur�ck
	 * 
	 * @return gew�hlte Option
	 */
	
	public boolean isChoosed() {
		return this.box.isActivated();
	}
	
	/**
	 * Liefert den Einstellungsnamen zur�ck
	 * 
	 * @return Einstellungsname
	 */
	
	public String getSetting() {
		return this.setting;
	}
	
}
