package patrick.views;

import java.util.ArrayList;
import java.util.List;

import patrick.Client;
import patrick.component.action.ClickListener;
import patrick.component.components.PAutoSizeContainer;
import patrick.component.components.PButton;
import patrick.component.components.PContainer;
import patrick.component.components.PLabel;
import patrick.component.components.PRadioButton;
import patrick.component.components.PRadioButtonGroup;
import patrick.component.components.PScrollableContainer;
import patrick.component.components.PTextField;
import patrick.component.enums.AutoSize;
import patrick.event.ErrorType;
import patrick.event.EventHandler;
import patrick.event.events.ErrorEvent;
import patrick.game.Game;
import patrick.packets.GameOptionPacket;
import patrick.packets.GameSettingsPacket;
import patrick.packets.utils.CheckOption;
import patrick.packets.utils.Option;
import patrick.packets.utils.RadioOption;
import patrick.utils.Components;
import processing.core.PApplet;
import processing.event.MouseEvent;
/**
 * <p>Übersicht für eine Spielrunde zu erstellen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class GameOptionView extends PContainer{

	/**
	 * PRadioButtonGroup für die Spieleranzahl-Auswahl
	 */
	
	private PRadioButtonGroup playerGroupAmount;
	
	/**
	 * Liste der verschiedenen CheckBox-Optionen
	 */
	
	private List<CheckBoxView> checks = new ArrayList<CheckBoxView>();
	
	/**
	 * Liste der verschiedenen RadioButton-Optionen
	 */
	
	private List<RadioOptionView> radios = new ArrayList<RadioOptionView>();
	
	/**
	 * Textfeld für den Benutzernamen
	 */
	
	private PTextField userName;
	
	/**
	 * Spiel, für welches die Übersicht ist
	 */
	
	private Game game;
	
	/**
	 * Erzeugt eine GameOptionView
	 * 
	 * @param game Spiel, für welches die Übersicht ist
	 * @param packet Packet für die Optionen, welches vom Server erhalten wurde
	 * @param width Breite der Übersicht
	 * @param height Höhe der Übersicht
	 */
	
	public GameOptionView(Game game, GameOptionPacket packet, int width, int height) {
		this.width = width;
		this.height = height;
		this.game = game;
		int y = 10;
		
		int compWidth = width / 3;
		int compX = (width - width/3) / 2;
		
		PButton back = Components.getBackButton(compWidth);
		back.setLocation(compX, y);

		add(back);
		
		y += back.getHeight() + 20;
		
		PTextField userName = Components.getUsernameField("Benutzername", compWidth);
		this.userName = userName;
		userName.setLocation(compX, y);
		add(userName);
		
		y += (int) 32 * 1.5F + 20;
		
		PAutoSizeContainer optionContainer = new PAutoSizeContainer();
		optionContainer.setAutoSize(AutoSize.VERTICAL);
		optionContainer.setSize(width, 1);
		optionContainer.setLocation(0, 0);
		
		PScrollableContainer scroll = new PScrollableContainer();
		
		scroll.setLocation(0, y);
		scroll.setSize(width, height-y);
		
		y = 5;
		
		if(packet.getPlayerAvaibleAmounts().size() > 0) {
			PRadioButtonGroup group = new PRadioButtonGroup();
			this.playerGroupAmount = group;
			
			PLabel label = Components.getTitleLabel(
					"Wähle eine Spielerzahl aus",
					width, 0, y
					);
			
			optionContainer.add(label);
			
			y+= 80;
			
			for(int playerAmount : packet.getPlayerAvaibleAmounts()) {
				PRadioButton radioButton = new PRadioButton(playerAmount + " Spieler");
				radioButton.setGroup(group);
				if(playerAmount == packet.getDefaultPlayerAmount()) {
					group.activate(radioButton);
				}
				radioButton.setSize(width, 30);
				radioButton.setLocation(5, y);
				optionContainer.add(radioButton);
				y += 50;				
			}
		}
		for(Option option : packet.getOptions()) {
			if(option instanceof CheckOption) {
				CheckBoxView checkView = new CheckBoxView((CheckOption)option, width);
				checkView.setLocation(0, y);
				checks.add(checkView);
				optionContainer.add(checkView);
				y += checkView.getHeight();
			}else if(option instanceof RadioOption) {
				RadioOptionView optionView = new RadioOptionView((RadioOption) option, width);
				optionView.setLocation(0, y);
				radios.add(optionView);
				optionContainer.add(optionView);
				y += optionView.getHeight();
			}
		}
		y+= 20;
		PButton button = Components.getButton("Erstellen", compWidth);
		button.setLocation(compX, y);
		
		GameOptionView view = this;
		
		button.addActionListener(new ClickListener() {
			
			@Override
			public void onClick(MouseEvent e) {
				if(e.getButton() == PApplet.LEFT) {
					if(Client.isPermittedName(userName.getText())) {
						GameSettingsPacket packet = new GameSettingsPacket(view);
						Client.send(packet);
					}else {
						ErrorEvent event = new ErrorEvent(ErrorType.FORBIDDEN_PLAYERNAME);
						EventHandler.callEvent(event);
					}
				}
			}
		});
		
		optionContainer.add(button);
		scroll.setViewContent(optionContainer);
		add(scroll);
	}
	
	/**
	 * Liefert eine Liste aller RadioButton-Optionen
	 * 
	 * @return Liste aller RadioButton-Optionen
	 */
	
	public List<RadioOptionView> getRadios(){
		return this.radios;
	}
	
	/**
	 * Liefert eine Liste aller CheckBox-Optionen
	 * 
	 * @return Liste aller CheckBox-Optionen
	 */
	
	public List<CheckBoxView> getChecks(){
		return this.checks;
	}
	
	/**
	 * Liefert die PRadioButtonGroup für die Spieleranzahl-Auswahl
	 * 
	 * @return PRadioButtonGroup für die Spieleranzahl-Auswahl
	 */
	
	public PRadioButtonGroup getPlayerAmountGroup() {
		return this.playerGroupAmount;
	}
	
	/**
	 * Liefert den Benutzernamen zurück, welcher in der Übersicht eingegeben ist
	 *
	 * @return Benutzername, welcher in der Übersicht eingegeben ist 
	 */
	
	public String getUserName() {
		return this.userName.getText();
	}
	
	/**
	 * Liefert das Spiel zurück, von welchem die Übersicht ist
	 * 
	 * @return Spiel, von welchem die Übersicht ist
	 */
	
	public Game getGame() {
		return this.game;
	}
}
