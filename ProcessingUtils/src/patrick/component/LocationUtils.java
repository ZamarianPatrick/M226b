package patrick.component;

import java.awt.Dimension;
import java.awt.Point;

import patrick.component.components.PComponent;
import patrick.component.enums.Shape;
/**
 * <p>Statische Klasse mit einigen Methoden für Kollisionserkennungen</p>
 * 
 * @author Patrick
 * @version 1.0
 *
 */
public class LocationUtils {

	/**
	 * Gibt den Wahrheitswert zurück, ob ein Punkt innerhalb des Komponenten liegt
	 * 
	 * @param component Komponent welcher geprüft werden soll
	 * @param p2 Punkt welcher überprüft werden soll
	 * @param dim Grösse des Komponenten
	 * @param shape Form des Komponenten
	 * 
	 * @return Wahrheitswert ob der Punkt innerhalb des Komponenten liegt
	 */
	
	public static boolean isInside(PComponent component, Point p2, Dimension dim, Shape shape) {
		Point loc = component.getAbsoluteLocation();
		if(shape == Shape.CIRCLE) {
			if(loc.distance(p2) < dim.getWidth() / 2 || 
					loc.distance(p2) < dim.getHeight() / 2) {
				return true;
			}
		}else if(shape == Shape.RECT) {
			int x1 = (int) loc.getX();
			int x2 = (int) p2.getX();
			int y1 = (int) loc.getY();
			int y2 = (int) p2.getY();
			
			int x3 = (int) dim.getWidth();
			int y3 = (int) dim.getHeight();
			
			if(x1<x2 && x1+x3 > x2) {
				if(y1<y2 && y1+y3 > y2) {
					return true;
				}
			}
			
		}
		return false;
	}
	
	/**
	 * Liefert ein Wahrheitswert zurück, ob ein bestimmter Punkt innerhalb eines Kreises liegt
	 * 
	 * @param circleX Position des Kreises auf der X-Achse
	 * @param circleY Position des Kreises auf der Y-Achse
	 * @param circleSize Radius des Kreises
	 * @param p Punkt welcher geprüft werden soll
	 * @return Wahrheitswert, ob der Punkt innerhalb des Kreises liegt
	 */
	
	public static boolean isInsideCircle(int circleX, int circleY, int circleSize, Point p) {
		if(new Point(circleX, circleY).distance(p) < circleSize / 2){
			return true;
		}	
		return false;
	}
	
	/**
	 * Liefert ein Wahrheitswert zurück, ob ein bestimmter Punkt innerhalb eines Rechteckes liegt
	 * 
	 * @param rectX Position des Rechteckes auf der X-Achse
	 * @param rectY Position des Rechteckes auf der Y-Achse
	 * @param width Breite des Rechteckes
	 * @param heigth Höhe des Rechteckes
	 * @param p Punkt welcher geprüft werden soll
	 * 
	 * @return Wahrheitswert, ob der Punkt innerhalb des Rechteckes liegt
	 */
	
	public static boolean isInsideRect(int rectX, int rectY, int width, int heigth, Point p) {
		int x2 = (int) p.getX();
		int y2 = (int) p.getY();
		
		if(rectX<x2 && rectX+width > x2) {
			if(rectY<y2 && rectY+heigth > y2) {
				return true;
			}
		}
		return false;
	}
	
}
