package patrick.views;

import javax.swing.JOptionPane;

import patrick.Client;
import patrick.component.PBorder;
import patrick.component.action.ClickListener;
import patrick.component.components.PAutoSizeContainer;
import patrick.component.components.PButton;
import patrick.component.components.PContainer;
import patrick.component.components.PLabel;
import patrick.component.components.PScrollableContainer;
import patrick.component.enums.AutoSize;
import patrick.component.enums.Scrollable;
import patrick.packets.RequestRoundPacket;
import patrick.packets.RoundPacket;
import patrick.utils.Components;
import patrick.utils.Message;
import processing.core.PApplet;
import processing.event.MouseEvent;
/**
 * <p>Übersicht für die verfügbaren Spielrunden</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class JoinRoundView extends PContainer{

	/**
	 * Erzeugt eine JoinRoundView
	 * 
	 * @param packet Packet, welches vom Server empfangen wurde
	 * @param width Breite der Übersicht
	 * @param height Höhe der Übersicht
	 */
	
	public JoinRoundView(RequestRoundPacket packet, int width, int height) {
		this.width = width;
		this.height = height;
		int y = 10;
		
		PLabel label = Components.getTitleLabel("Verfügbare Runden:", width, 0, y);
		add(label);
		
		y += 70;
		
		PButton back = Components.getBackButton(width / 3);
		back.setLocation(10, y);
		add(back);
		
		y += back.getHeight() + 30;
		
		PAutoSizeContainer roundsContainer = new PAutoSizeContainer();
		roundsContainer.setAutoSize(AutoSize.VERTICAL);
		roundsContainer.setSize(width, 1);
		roundsContainer.setLocation(0, 0);
		
		PScrollableContainer scroll = new PScrollableContainer();
		scroll.setSize(width, height-y);
		scroll.setLocation(0, y);
		scroll.setScrollable(Scrollable.VERTICAL);
		
		y = 0;
		
		int count = 0;
		for(RoundPacket roundPacket : packet.getRounds()) {
			PAutoSizeContainer container = new PAutoSizeContainer();
			container.setAutoSize(AutoSize.VERTICAL);
			container.setSize(width-20, 1);
			container.setCorner(15);
			container.setBorder(new PBorder(0,2));
			if(count % 2 == 0) {
				container.setColor(240);
			}else {
				container.setColor(220,255,220);
			}
			PLabel roundName = new PLabel();
			roundName.setText(roundPacket.getName());
			roundName.setSize(width, 50);
			roundName.setLocation(0, 0);
			container.add(roundName);
			
			PLabel roundPlayers = new PLabel();
			roundPlayers.setText("Spieler: " + roundPacket.getPlayerAmount() + "/" + roundPacket.getMaxPlayerAmount());
			roundPlayers.setSize(width, 50);
			roundPlayers.setLocation(0, 70);
			container.add(roundPlayers);
			
			PButton button = new PButton();
			button.setText("Spiel beitreten");
			button.setSize(width/3, 50);
			button.setLocation((width - width/3) / 2, 140);
			button.addActionListener(new ClickListener() {
				
				@Override
				public void onClick(MouseEvent e) {
					if(e.getButton() == PApplet.LEFT) {
						String playerName = JOptionPane.showInputDialog(null, "Gib dein Spielername ein");
						while(Client.isPermittedName(playerName) == false) {
							playerName = JOptionPane.showInputDialog("Nur Buchstaben und maximal 16 Zeichen zulässig");
						}
						Client.send(Message.joinRound(
								packet.getGameName(),
								roundPacket.getName(),
								playerName)
								);
						Client.setPlayerName(playerName);
						Client.setPlayedGame(Client.getGame(packet.getGameName()));
					}
				}
			});
			
			container.add(button);
			
			container.setLocation(10, y);
			roundsContainer.add(container);
			y += container.getHeight() + 10;
			count++;
		}
		scroll.setViewContent(roundsContainer);
		add(scroll);
	}
	
}
