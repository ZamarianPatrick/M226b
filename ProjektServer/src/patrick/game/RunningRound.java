package patrick.game;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import patrick.Server;
import patrick.event.Event;
import patrick.event.Listener;
/**
 * <p>Diese Klasse wiederspiegelt die laufenden Spielrunden. Alles was während
 * eines Spiels geschehen kann, wird über die RunningRound abgewickelt.
 * Um ein neues Spiel zu erstellen, wird die RunningRound als Schnittstelle zum Serversystem
 * verwendet. Damit dies einwandfrei läuft, müssen die obligatorischen Methoden einwandfrei
 * implementiert werden, und es sollen insgesamt 2 Konstruktoren vorhanden sein.
 * Der eine Konstruktor besitzt kein Argument, und der andere soll die WaitingRound als 
 * Argument haben. Spielbezogenene Events können über die RunningRound abgerufen werden
 * und es können auf der RunningRound Listener registriert werden, welche auf die Events
 * der aktuellen Spielrunde hören.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public abstract class RunningRound extends GameRound{
	
	/**
	 * Alle registrierten Listener
	 */
	
	private List<Listener> listeners = new ArrayList<Listener>();
	
	/**
	 * Alle Spieler, welche sich in der RunningRound befinden
	 */
	
	private List<Player> players = new ArrayList<Player>();
	
	/**
	 * Leerer Konstruktor, ist notwendig damit die Methode gameSetup() einwandfrei
	 * aufgerufen werden kann vom Serversystem.
	 * 
	 */
	
	public RunningRound() {
		
	}
	
	/**
	 * Erzeugt eine RunningRound aus einer wartenden Runde
	 * und startet diese.
	 * 
	 * @param round wartende Runde
	 */
	
	public RunningRound(WaitingRound round) {
		super(round.game, round.settings, round.host);
		this.players.addAll(round.getPlayers());
		onStart();
	}
	
	/**
	 * Registriert einen neuen Listener, welcher auf die Events der aktuellen 
	 * Runde hört.
	 * 
	 * @param listener zu registrierender Listener
	 */
	
	public void registerListener(Listener listener) {
		this.listeners.add(listener);
	}
	
	/**
	 * Liefert eine Liste von allen Spielern, welche sich in der Spielrunde befinde
	 * 
	 * @return Liste der Spieler, welche sich in der Spielrunde befinden
	 */
	
	@Override
	public List<Player> getPlayers(){
		return this.players;
	}
	
	/**
	 * Löst einen Event in der aktuellen Runde aus
	 * 
	 * @param event auszulösender Event
	 */
	
	public void callEvent(Event event) {
		for(Listener listener : new ArrayList<Listener>(listeners)) {
			Class<?> clazz = listener.getClass();
			for(Method method : clazz.getDeclaredMethods()) {
				if(method.getParameterCount() == 1) {
					if(method.getParameters()[0].getType().isInstance(event)) {
						try {
							method.invoke(listener, event);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	/**
	 * Methode, die aufgerufen wird, wenn eine Runde startet
	 */
	
	public abstract void onStart();
	
	/**
	 * Methode, die aufgerufen wird, wenn der Server das Spiel lädt
	 * Hier soll das Spiel erstellt, konfiguriert und dann an den Server zurückgegeben werden
	 * 
	 * @return konfiguriertes Spiel
	 */
	
	public abstract Game gameSetup();
	
	/**
	 * Stoppt die laufende Spielrunde
	 */
	
	public void stop() {
		for(Player player : players) {
			Server.sendMessage(player.getSocket(), "stop");
		}
		Server.removeRunningRound(this);
	}

}
