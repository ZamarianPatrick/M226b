package patrick;

import java.util.Map;

import patrick.component.components.PContainer;
import patrick.component.components.PGameContainer;
import patrick.component.components.PLabel;
import patrick.component.components.PRootContainer;
import patrick.component.components.PScrollableContainer;
import patrick.component.enums.Scrollable;
import patrick.enums.ViewType;
import patrick.game.Game;
import patrick.packets.GameOptionPacket;
import patrick.packets.RequestRoundPacket;
import patrick.packets.WaitingRoundInfoPacket;
import patrick.views.GameInfoView;
import patrick.views.GameMenuView;
import patrick.views.GameOptionView;
import patrick.views.GamesView;
import patrick.views.JoinRoundView;
import patrick.views.RoundView;
import patrick.views.StartView;
import processing.core.PApplet;
import processing.core.PImage;
/**
 * <p>grafische Benutzeroberfl�che f�r den Client</p>
 * 
 * @author Patrick
 * @version 1.0
 * 
 */
public class ClientView extends PApplet{

	/**
	 * PRootContainer, auf welchem sich s�mtliche Komponente befinden
	 */
	
	private PRootContainer root;
	
	/**
	 * geladene Spiel�bersicht aller geladenen Spiele
	 */
	
	private PScrollableContainer gamesView;
	
	/**
	 * Runden�bersicht
	 */
	
	private RoundView roundView;
	
	/**
	 * Definiert, welche �bersicht gerade ge�ffnet ist
	 */
	
	private ViewType viewType;
	
	/**
	 * Erzeugt eine ClientView
	 */
	
	public ClientView() {
		viewType = ViewType.START;
		roundView = null;
	}
	
	/**
	 * Definiert die Fenstergr�sse auf 1000x700
	 */
	
	@Override
	public void settings() {
		size(1000,700);
	}
	
	/**
	 * Legt den PRootContainer und �ffnet die Start-�bersicht
	 */
	
	@Override
	public void setup() {
		PImage logo = loadImage("logo.png");
		surface.setIcon(logo);
		surface.setTitle("TOPOMEDICS - Client.jar v1.0");
		root = new PRootContainer(this);
		StartView view = new StartView(width, height);
		root.add(view);
	}
	
	/**
	 * Zeichnet bei jedem draw Durchgang einen weissen Hintergrund
	 */
	
	@Override
	public void draw() {
		background(255);
	}
	
	/**
	 * �ffnet die Spiel�bersicht �ber alle Spiele
	 * 
	 * @param games Spiele, welche verf�gbar sind
	 */
	
	public void openGamesView(Map<String, String> games) {
		root.clear();
		viewType = ViewType.GAMES;
		if(gamesView != null) {
			root.add(gamesView);
			return;
		}
		if(games == null) {
			PContainer container = new PContainer();
			container.setSize(width, height);
			container.setLocation(0, 0);
			
			PLabel label = new PLabel();
			label.setText("Aktuell sind leider keine Spiele verf�gbar!");
			label.setSize(width, height);
			label.setTextAlignX(CENTER);
			label.setTextAlignY(CENTER);
			label.setLocation(0, 0);
			
			container.add(label);
			root.add(container);
		}else {
			PScrollableContainer scroll = new PScrollableContainer();
			scroll.setSize(width, height);
			scroll.setLocation(0, 0);
			scroll.setScrollable(Scrollable.VERTICAL);
			GamesView view = new GamesView(games, width, height / 3);
			scroll.setViewContent(view);
			root.add(scroll);
			this.gamesView = scroll;
		}
	}
	
	/**
	 * �ffnet die das Spielmen� eines bestimmten Spieles
	 * 
	 * @param game Spiel, von welchem das Men� ge�ffnet werden soll
	 */
	
	public void openGameMenuView(Game game) {
		root.clear();
		viewType = ViewType.GAMEMENU;
		GameMenuView view = new GameMenuView(game, width, height);
		root.add(view);
	}
	
	/**
	 * �ffnet die zuletzt angesehene �bersicht,
	 * wird f�r den zur�ck Button verwendet
	 */
	
	public void openLastView() {
		boolean isUndo = root.undoClear();
		this.viewType = viewType.getPrevious();
		if(isUndo == false) {
			switch(this.viewType){
			case GAMES:
				openGamesView(null);
				break;
			case GAMEMENU:
				openGameMenuView(Client.getFocusedGame());
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * �ffnet die Spielinfo-�bersicht eines bestimmten Spieles
	 * 
	 * @param game Spiel, von welchem die Spiel�bersicht ge�ffnet werden soll
	 */
	
	public void openGameInfoView(Game game) {
		root.clear();
		this.viewType = ViewType.GAMEMANUAL;
		GameInfoView view = new GameInfoView(game, width, height);
		root.add(view);	
	}
	
	/**
	 * �ffnet die �bersicht, mit welcher man eine Spielrunde erstellen kann
	 * 
	 * @param packet Packet, welches vom Server erhalten wurde
	 */
	
	public void openGameCreateView(GameOptionPacket packet) {
		root.clear();
		this.viewType = ViewType.GAMEOPTION;
		GameOptionView view = new GameOptionView(Client.getFocusedGame(), packet, width, height);
		root.add(view);
	}
	
	/**
	 * �ffnet die Runden�bersicht, sollte diese bereits ge�ffnet sein,
	 * so wird diese nur aktualisiert und nicht neu erstellt
	 * 
	 * @param packet Packet, welches vom Server erhalten wurde
	 * @param count verbleibende Sekunden bis zum Start der Runde
	 */
	
	public void openRoundView(WaitingRoundInfoPacket packet, Integer...count) {
		if(this.viewType.equals(ViewType.ROUND) && this.roundView != null) {
			if(packet == null && count.length == 1) {
				this.roundView.countDown(count[0]);
			}else {
				this.roundView.setPlayers(packet.getPlayers());
			}
		}else {
			root.clear();
			this.viewType = ViewType.ROUND;
			RoundView view = new RoundView(packet, width, height);
			this.roundView = view;
			root.add(view);
		}
	}
	
	/**
	 * �ffnet die �bersicht mit allen verf�gbaren Spielrunden eines Spiels
	 * 
	 * @param packet Packet, welches vom Server erhalten wurde
	 */
	
	public void openJoinView(RequestRoundPacket packet) {
		root.clear();
		this.viewType = ViewType.ROUNDS;
		JoinRoundView view = new JoinRoundView(packet, width, height);
		root.add(view);
	}
	
	/**
	 * �ffnet ein Spiel
	 * 
	 * @param container PGameContainer, auf welchem das Spiel abl�uft
	 */
	
	public void openGame(PGameContainer container) {
		root.clear();
		this.viewType = ViewType.GAMING;
		root.add(container);
	}
	
	/**
	 * Liefert den PRootContainer zur�ck, auf welchem s�mtliche Komponente enthalten sind
	 * 
	 * @return PRootContainer der grafischen Benutzeroberfl�che
	 */
	
	public PRootContainer getRoot() {
		return root;
	}
	
	/**
	 * Liefert die Runden�bersicht zur�ck
	 * 
	 * @return Runden�bersicht
	 */
	
	public RoundView getRoundView() {
		return roundView;
	}
	
	
}
