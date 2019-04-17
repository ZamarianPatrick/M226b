package patrick.utils;
/**
 * <p>Gehört zum Loader. Wird instanziiert und gespeichert, wenn ein Spiel
 * vom Loder geladen werden konnte.</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class LoadedGame {

	/**
	 * Name des Spiels
	 */
	
	private String name;
	
	/**
	 * Absoluter Pfad des Spiels
	 */
	
	private String path;
	
	/**
	 * Mainklasse des Spiels
	 */
	
	private String mainClass;
	
	/**
	 * Erzeugt ein LoadedGame
	 * 
	 * @param name Name des Spiels
	 * @param path Absoluter Pfad des Spiels
	 * @param mainClass Mainklasse des Spiels
	 */
	
	public LoadedGame(String name, String path, String mainClass) {
		this.name = name;
		this.path = path;
		this.mainClass = mainClass;
	}
	
	/**
	 * Liefert den Namen des Spiels zurück
	 * 
	 * @return Name des Spiels
	 */

	public String getName() {
		return name;
	}
	
	/**
	 * Definiert den Namen des Spiels
	 * 
	 * @param name Name des Spiels
	 */

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Liefert den absoluten Pfad des Spiels zurück
	 * 
	 * @return absoluter Pfad des Spiels
	 */

	public String getPath() {
		return path;
	}
	
	/**
	 * Definiert den absoluten Pfad des Spiels
	 * 
	 * @param path absoluter Pfad des Spiels
	 */

	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * Liefert die Mainklasse des Spiels zurück
	 * 
	 * @return Main Klasse des Spiels
	 */

	public String getMainClass() {
		return mainClass;
	}
	
	/**
	 * Definiert die Main Klasse des Spiels
	 * 
	 * @param mainClass Main Klasse des Spiels
	 */

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}
	
}
