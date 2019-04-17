package patrick.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/**
 * <p>Der EventHandler, welcher alle Events auslöst, und an die entsprechenden Listener
 * liefert und die jeweiligen Methoden des Listeners aufruft.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class EventHandler {

	/**
	 * Liste aller registrierten Listener
	 */
	
	private static List<Listener> listeners = new ArrayList<Listener>();
	
	/**
	 * Methode, welche ein Ereignis auslöst, und an alle entsprechenden Listner liefert
	 * 
	 * @param e Event, welcher ausgelöst werden soll
	 */
	
	public static void callEvent(Event e) {
		for(Listener listener : new ArrayList<Listener>(listeners)) {
			Class<?> clazz = listener.getClass();
			for(Method method : clazz.getDeclaredMethods()) {
				if(method.getParameterCount() == 1) {
					if(method.getParameters()[0].getType().isInstance(e)) {
						try {
							method.invoke(listener, e);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	/**
	 * Registriert einen Listener
	 * 
	 * @param listener Listener welcher registriert werden soll
	 */
	
	public static void registerListener(Listener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Stopt den EventHandler und löscht alle vorhandenen Listener.
	 * 
	 * Sollte auf einem Serverstop aufgerufen werden, damit Listener nicht doppelt 
	 * registriert werden.
	 */
	
	public static void stop() {
		listeners.clear();
	}
	
}
