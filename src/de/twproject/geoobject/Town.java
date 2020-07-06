package de.twproject.geoobject;

import java.awt.Point;

/**
 * Kleinstadt ist eine Siedlung und ein Geographisches Objekt, die mehr Bürger
 * als einen Dorf hat, aber weniger als eine Stadt. Town hat eine Geometrie bzw.
 * Koordinaten
 * 
 * @author Tetiana Wycital
 *
 */
public class Town extends Settlement implements Geoobject {
	/**
	 * Geometrie des Town Objekts
	 */
	private Point townGeom = new Point();
	/**
	 * der Name der Kleinstadt
	 */
	private String name;
	/**
	 * "x" Koordinate in einem lokalen, geografischen oder geodätischen
	 * Koordinatensystem
	 */
	private int coordinateX;
	/**
	 * "y" Koordinate in einem lokalen, geografischen oder geodätischen
	 * Koordinatensystem
	 */
	private int coordinateY;

	/**
	 * Baut und initializiert das "Town" Objekt mit einem Namen und einer Position
	 * in einem Koordinatensystem
	 * 
	 * @param name        der Name der Stadt
	 * @param coordinateX "x" Koordinate in einem lokalen, geografischen oder
	 *                    geodätischen Koordinatensystem
	 * @param coordinateY "y" Koordinate in einem lokalen, geografischen oder
	 *                    geodätischen Koordinatensystem
	 */
	public Town(String name, int coordinateX, int coordinateY) {
		this.name = name;
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}

	/**
	 * Gibt den Name der Kleinstadt zurück
	 * 
	 * @return the name die Name der Kleinstadt, z.B. Hude
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gibt "x" Koordinate von einr Stadt in einem lokalen, geografischen oder
	 * geodätischen Koordinatensystem zurück
	 * 
	 * @return the coordinateX "x" Koordinate in einem lokalen, geografischen oder
	 *         geodätischen Koordinatensystem
	 */
	public int getCoordinateX() {
		return coordinateX;
	}

	/**
	 * Gibt "y" Koordinate von einr Stadt in einem lokalen, geografischen oder
	 * geodätischen Koordinatensystem zurück
	 * 
	 * @return the coordinateY "y" Koordinate in einem lokalen, geografischen oder
	 *         geodätischen Koordinatensystem
	 */
	public int getCoordinateY() {
		return coordinateY;
	}

//	/**
//	 * gibt die Information über die Anzahl von Bewohner zurück
//	 */
//	public void getNumberOfCitizens() {
//		System.out.println("Town has between 5 000 and 50 000 citizens");
//	}

	/**
	 * Erstellt Geometrie, die einen Punkt mit Koordinaten darstellt.
	 */
	@Override
	public void addGeometry() {
		townGeom.setLocation(coordinateX, coordinateY);
	}

	/**
	 * Gibt die Geometrie des Town Objekts zurück
	 * 
	 * @return townGeom ein Punkt mit Koordinaten
	 */
	public Point getGeometry() {
		return townGeom;
	}

}
