package patrick.views;

import java.util.ArrayList;
import java.util.List;

import patrick.Client;
import patrick.component.PBorder;
import patrick.component.PColor;
import patrick.component.action.ClickListener;
import patrick.component.components.PAutoSizeContainer;
import patrick.component.components.PButton;
import patrick.component.components.PLabel;
import patrick.component.components.PScrollableContainer;
import patrick.component.enums.AutoSize;
import patrick.packets.WaitingRoundInfoPacket;
import patrick.utils.Components;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.event.MouseEvent;
/**
 * <p>Übersicht für eine Runde, welche sich in Wartephase befindet</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class RoundView extends PAutoSizeContainer{
	
	/**
	 * Spieler, welche sich in der Runde befinden
	 */
	
	private List<String> players = new ArrayList<String>();
	
	/**
	 * Container für die Spieleransicht
	 */
	
	private PAutoSizeContainer playersContainer;
	
	/**
	 * Counter für den Countdown, wenn die Runde startet
	 */
	
	private int counter;
	
	/**
	 * Spieleranzahl die für einen Spielstart benötigt werden
	 */
	
	private int playerNeeded;
	
	/**
	 * Das Label, welches die Spieleranzahl anzeigt
	 */
	
	private PLabel playerAmountLabel;
	
	/**
	 * Koordinate auf der Y-Achse für folgende Komponenten
	 */
	
	private int compY;
	
	/**
	 * Erzeugt eine RoundView
	 * 
	 * @param packet Packet, welches vom Server erhalten wurde
	 * @param width Breite der Übersicht
	 * @param height Höhe der Übersicht
	 */
	
	public RoundView(WaitingRoundInfoPacket packet, int width, int height) {
		this.setAutoSize(AutoSize.VERTICAL);
		this.setSize(width, 1);
		
		this.counter = -1;
		this.players = packet.getPlayers();
		this.playerNeeded = packet.getRound().getMaxPlayerAmount();
		
		int firstY = 10;
		
		PButton leaveButton = Components.getButton("Verlassen", width / 5);
		leaveButton.setLocation(10, 10);
		
		leaveButton.addActionListener(new ClickListener() {
			
			@Override
			public void onClick(MouseEvent e) {
				if(e.getButton() == PApplet.LEFT) {
					Client.send("leave");
					Client.getView().openGameMenuView(Client.getPlayedGame());
					Client.setPlayedGame(null);
					Client.getView().getRoot().removeUndo();
				}
			}
		});
		
		PLabel label = new PLabel();
		label.setText("Spielrunde von " + packet.getRound().getName());
		label.setSize(width-20, 50);
		label.setTextAlignX(PApplet.CENTER);
		label.setColor(255,100,0);
		label.setBorder(new PBorder(0,2));
		label.setCorner(15);
		label.setLocation(10, firstY);
		add(label);
		
		add(leaveButton);
		
		firstY += 70;
		
		PLabel playerAmount = new PLabel();
		playerAmount.setText("Spieler: " + this.players.size() + "/" + this.playerNeeded);
		playerAmount.setSize(width / 3, 50);
		playerAmount.setLocation((width - (width/3)) / 2, firstY);
		playerAmount.setCorner(15);
		playerAmount.setColor(255, 0, 0);
		playerAmount.setBorder(new PBorder(0,2));
		
		this.playerAmountLabel = playerAmount;
		
		add(playerAmount);
		
		firstY += 70; 
		
		PLabel label2 = new PLabel();
		label2.setText("Spieler in der Runde:");
		label2.setColor(240);
		label2.setBorder(new PBorder(0,2));
		label2.setCorner(15);
		label2.setSize(width-20, 50);
		label2.setLocation(10, firstY);
		add(label2);
		
		firstY += 70;
		
		PAutoSizeContainer players = new PAutoSizeContainer();
		this.playersContainer = players;
		players.setAutoSize(AutoSize.VERTICAL);
		players.setSize(width / 2, height - firstY - 15);
		players.setLocation((width - (width/2)) / 2, 0);
		players.setBorder(new PBorder(0,2));
		players.setCorner(15);
		players.setColor(240);
		
		int y = 0;
		for(String player : this.players) {
			PLabel p = new PLabel();
			p.setText(player);
			p.setSize(width / 2, 50);
			p.setLocation(0, y);
			players.add(p);
			if(player.equals(Client.getPlayerName())) {
				p.setFontColor(new PColor(0,153,0));
			}
			y += p.getHeight();
		}
		
		PScrollableContainer scroll = new PScrollableContainer();
		scroll.setLocation(0, firstY);
		scroll.setSize(width, height - firstY - 10);
		scroll.setViewContent(players);
		add(scroll);
		
		compY = firstY;
	}
	
	/**
	 * @see patrick.component.components.PContainer#draw(processing.core.PGraphics)
	 */
	
	@Override
	protected void draw(PGraphics pg) {
		drawIt(pg);
		if(this.counter == -1) {
			this.playerAmountLabel.setText("Spieler: " + this.players.size() + "/" + this.playerNeeded);
			this.playerAmountLabel.setColor(255,0,0);
		}else {
			this.playerAmountLabel.setText("Spiel startet in " + counter + " Sekunden");
			this.playerAmountLabel.setColor(100,255,0);
		}
		playersContainer.getComponents().clear();
		playersContainer.setSize(width / 2, height - compY - 15);
		int y = 0;
		for(String player : this.players) {
			PLabel p = new PLabel();
			p.setText(player);
			p.setSize(width / 2, 50);
			p.setLocation(0, y);
			playersContainer.add(p);
			if(player.equals(Client.getPlayerName())) {
				p.setFontColor(new PColor(0,153,0));
			}
			y += p.getHeight();
		}
	}
	
	/**
	 * Liefert eine Liste aller Spieler zurück
	 * 
	 * @return Liste aller Spieler
	 */
	
	public List<String> getPlayers(){
		return this.players;
	}
	
	/**
	 * Definiert die Liste der Spieler in der Runde
	 * 
	 * @param players Liste der Spieler in der Runde
	 */
	
	public void setPlayers(List<String> players) {
		if(players != null) {
			this.players = players;
		}
	}
	
	/**
	 * Definiert den Countdown bis zum Start der Runde
	 * 
	 * @param counter Verbleibende Sekunden bis zum Start der Runde
	 */
	
	public void countDown(int counter) {
		this.counter = counter;
	}
	
	/**
	 * Löscht einen Spieler aus der Übersicht
	 * 
	 * @param name Name des zu löschenden Spielers
	 */
	
	public void leavePlayer(String name) {
		this.players.remove(name);
	}
	
	/**
	 * Fügt einen Spieler zu der Übersicht hinzu
	 * 
	 * @param name Name des hinzuzufügenden Spielers
	 */
	
	public void joinPlayer(String name) {
		if(this.players.contains(name) == false) {
			this.players.add(name);
		}
	}
}
