package patrick.component.components;

import java.awt.Point;
import java.io.File;

import patrick.component.enums.Shape;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
/**
 * <p>Ein PComponent, um ein Bild anzeigen zu lassen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class PImageBox extends PComponent{

	/**
	 * Name des Bildes, welches angezeigt werden soll
	 * Absoluter Pfad, wenn sich das Bild extern befindet
	 */
	private String imageName;
	
	/**
	 * Das Bild, welches sich erzeugt hat
	 */
	
	private PImage image;
	
	/**
	 * Erzeugt eine neue PImageBox mit dem gegebenen Bild
	 * 
	 * @param imageName Bildname oder Absoluter Pfad wenn sich das Bild extern befindet
	 */
	
	public PImageBox(String imageName) {
		this.imageName = imageName;
	}
	
	/**
	 * Methode, welche vom jeweilig übergeordneten PComponent aufgerufen wird, wenn
	 * sich die PImageBox zeichnen soll.
	 * 
	 * Diese Methode soll nicht von ausserhalb ausgeführt werden.
	 * 
	 * @param pg Grafik, auf welcher sich die PImageBox zeichnen soll
	 */
	
	@Override
	protected void draw(PGraphics pg) {
		if(this.image != null) {
			this.image.resize(this.width, this.height);
			pg.image(image, x, y);
		}else {
			loadImage();
		}
	}
	
	/**
	 * Diese Methode überprüft, ob ein bestimmter Punkt innerhalb des PComponent liegt.
	 * 
	 * @param point Point, welcher überprüft werden soll
	 * @return Wahrheitswert ob der Punkt innerhalb des Komponenten liegt oder nicht
	 */

	@Override
	public boolean isInside(Point point) {
		return this.isInside(point, Shape.RECT);
	}
	
	/**
	 * Setzt ein neues Bild, sofern dieses existiert
	 * 
	 * @param imageName Name oder absoluter Pfad des neuen Bildes
	 */
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
		loadImage();
	}
	
	/**
	 * Gibt den Namen oder absoluten Pfad des aktuellen Bildes zurück
	 * 
	 * @return Name oder absoluter Pfad des Bildes
	 */
	
	public String getImageName() {
		return this.imageName;
	}
	
	/**
	 * Diese Methode lädt das Bild mit dem gegebenen Namen oder absoluten Pfad.
	 * Diese Methode kann nicht ausgeführt werden, sofern die PImageBox noch keinem
	 * PRootContainer hinzugefügt worden ist.
	 */
	
	public void loadImage() {
		if(this.rootContainer != null) {
			File file = rootContainer.getPApplet().dataFile(imageName);
			if(file.exists() == true) {
				PImage image = rootContainer.getPApplet().loadImage(imageName);
				this.image = image;
			}else {
				PImage image = rootContainer.getPApplet().loadImage("noImg.png");
				this.image = image;
			}
		}
	}
	
	/**
	 * Diese Methode lädt das Bild mit dem gegebenen Namen oder absoluten Pfad.
	 * Diese Methode kann auch ausgeführt werden, wenn die PImageBox noch keinem
	 * PRootContainer zugewiesen ist.
	 * 
	 * @param applet PApplet, welches das Bild laden soll
	 */
	
	public void loadImage(PApplet applet) {
		File file = applet.dataFile(imageName);
		if(file.exists() == true) {
			PImage image = applet.loadImage(imageName);
			this.image = image;
		}else {
			PImage image = applet.loadImage("noImg.png");
			this.image = image;
		}
	}
	
	

	
	
}
