package patrick.views;

import patrick.component.components.PAutoSizeContainer;
import patrick.component.components.PLabel;
import patrick.component.components.PRadioButton;
import patrick.component.components.PRadioButtonGroup;
import patrick.component.enums.AutoSize;
import patrick.packets.utils.RadioOption;
import patrick.utils.Components;
/**
 * <p>Übersicht für eine RadioButton-Auswahl</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class RadioOptionView extends PAutoSizeContainer{
	
	/**
	 * Einstellungsname
	 */
	
	private String setting;
	
	/**
	 * PRadioButtonGroup, der Auswahl
	 */
	
	private PRadioButtonGroup group;
	
	/**
	 * Erzeugt eine RadioOptionView
	 * 
	 * @param option Auswahlmöglichkeiten der RadioOption
	 * @param width Breite der Übersicht
	 */
	
	public RadioOptionView(RadioOption option, int width) {
		setAutoSize(AutoSize.VERTICAL);
		this.width = width;
		this.setting = option.getName();
		if(option.getText() != null) {
			PLabel label = Components.getTitleLabel(option.getText(), width, 0, 5);
			add(label);
		}
		
		int y = 75;
		
		PRadioButtonGroup group = new PRadioButtonGroup();
		this.group = group;
		group.setAlwaysOne(true);
		
		for(String chooseAble : option.getChooseable()) {
			PRadioButton radioButton = new PRadioButton(chooseAble);
			radioButton.setGroup(group);
			if(chooseAble.equals(option.getDefaultValue())) {
				group.activate(radioButton);
			}
			radioButton.setSize(width-20, 30);
			radioButton.setLocation(10, y);
			add(radioButton);
			y += 50;
		}
	}
	
	/**
	 * Liefert den Einstellungsnamen zurück
	 * 
	 * @return Einstellungsname
	 */
	
	public String getSetting() {
		return this.setting;
	}
	
	/**
	 * Liefert die ausgewählte Option zurück
	 * 
	 * @return ausgewählte Option
	 */
	
	public String getChoosedValue() {
		return this.group.getActivated().getText();
	}
	
}
