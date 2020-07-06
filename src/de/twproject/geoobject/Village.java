package de.twproject.geoobject;

import java.awt.Point;

/**
 * Als Dorf wird ein geographisches Objekt bezeichnet, das eine kleine
 * Gruppensiedlung darstellt und eine Geometrie und Koordinaten hat
 * 
 * @author Tetiana Wycital
 *
 */
public class Village extends Settlement implements Geoobject {
	/**
	 * Geometrie des Village Objekts
	 */
	private Point villageGeom = new Point();
	/**
	 * der Name des Dorfs
	 */
	private String name;
	/**
	 * "x" Koordinate in einem lokalen, geografischen oder geod�tischen
	 * Koordinatensystem
	 */
	private int coordinateX;
	/**
	 * "y" Koordinate in einem lokalen, geografischen oder geod�tischen
	 * Koordinatensystem
	 */
	private int coordinateY;

	/**
	 * gibt die Information �ber die Anzahl von Bewohner zur�ck
	 */
	public void getNumberOfCitizensInfo() {
		System.out.println("Village has less then 5 000 citizens");
	}

	/**
	 * 
	 * Erstellt Geometrie, die einen Punkt mit Koordinaten darstellt.
	 */
	@Override
	public void addGeometry() {
		villageGeom.setLocation(coordinateX, coordinateY);
	}

	/**
	 * Baut und initializiert das "Village" Objekt mit einem Namen und einer
	 * Position in einem Koordinatensystem
	 * 
	 * @param name        der Name der Stadt
	 * @param coordinateX "x" Koordinate in einem lokalen, geografischen oder
	 *                    geod�tischen Koordinatensystem
	 * @param coordinateY "y" Koordinate in einem lokalen, geografischen oder
	 *                    geod�tischen Koordinatensystem
	 */
	public Village(String name, int coordinateX, int coordinateY) {
		this.name = name;
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}

	/**
	 * Gibt den Name des Dorfs zur�ck
	 * 
	 * @return the name die Name eines Dorfs
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gibt "x" Koordinate von einr Stadt in einem lokalen, geografischen oder
	 * geod�tischen Koordinatensystem zur�ck
	 * 
	 * @return the coordinateX "x" Koordinate in einem lokalen, geografischen oder
	 *         geod�tischen Koordinatensystem
	 */
	public int getCoordinateX() {
		return coordinateX;
	}

	/**
	 * Gibt "y" Koordinate von einr Stadt in einem lokalen, geografischen oder
	 * geod�tischen Koordinatensystem zur�ck
	 * 
	 * @return the coordinateY "y" Koordinate in einem lokalen, geografischen oder
	 *         geod�tischen Koordinatensystem
	 */
	public int getCoordinateY() {
		return coordinateY;
	}

}
