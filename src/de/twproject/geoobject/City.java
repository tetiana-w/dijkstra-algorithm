package de.twproject.geoobject;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 * Eine "City" (Stadt) ist ein geographisches Objekt, eine größere,
 * zentralisierte und abgegrenzte Siedlung im Schnittpunkt größerer Verkehrswege
 * mit einer eigenen Verwaltungs- und Versorgungsstruktur. Eine Stadt hat einen
 * identifikationsnummer, einen Namen, Koordinaten, NachbarnStädte und eine
 * Geometrie.
 * 
 * @author Tetiana Wycital
 *
 */
public class City extends Settlement implements Geoobject {
	/**
	 * identifikationsnummer vom 'City' Objekt
	 */
	private int id;
	/**
	 * der Name der Stadt
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
	 * Nachbarstädte sind die Städte, die eine Verbindung (Entfernung) zu einer
	 * anderen Stadt haben. Hier key - eine Stadt, value - die Entfernung zu dieser
	 * Stadt
	 */
	private Map<City, Integer> neighboursCities = new HashMap<City, Integer>(); // Neighbours cities with distance to
																				// this cities
	/**
	 * Geometrie des City Objekts
	 */
	private Point cityGeom = new Point();

	/**
	 * Gibt die 'String' Darstellung vom City Objekt zurück
	 */
	@Override
	public String toString() {
		return " " + name.toUpperCase();
	}

	// General: If two objects are equal, they MUST have the same hash code. If two
	// objects have the same hash code, it doesn't mean that they are equal
	/**
	 * Berechnet den Hash-Code für diese Stadt.
	 */
	/*
	 * Die gleiche Städte werden im
	 * gleichen Speicherbereich gespeichert
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * Zwei Städte sind gleich, wenn sie in der gleichen Speicheradresse gespeichert
	 * sind oder wenn sie die gleichen Namen haben
	 */
	/**
	 * Ermittelt, ob ein anderes Objekt dieser Stadt entspricht.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof City)) {
			return false;
		}
		City other = (City) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	/**
	 * Gibt die Information über die Anzahl von Bewohnern zurück
	 */
	public void getNumberOfCitizensInfo() {
		System.out.println("City has over 50 000 citizens");
	}

	/**
	 * Baut und initializiert das "City" Objekt mit einer id, einem Namen und einer
	 * Position in einem Koordinatensystem
	 * 
	 * @param id          identifikationsnummer vom 'City' Objekt
	 * @param name        der Name der Stadt
	 * @param coordinateX "x" Koordinate in einem lokalen, geografischen oder
	 *                    geodätischen Koordinatensystem
	 * @param coordinateY "y" Koordinate in einem lokalen, geografischen oder
	 *                    geodätischen Koordinatensystem
	 */
	public City(int id, String name, int coordinateX, int coordinateY) {
		this.id = id;
		this.name = name;
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}

	/**
	 * Fügt die Nachbarnstadt mit einer Entfernung zu dieser Stadt zur
	 * neighboursCities-Collection hinzu
	 * 
	 * @param city     ein Objekt von Typ "City"
	 * @param distance die Entfernung zwischen zwei Städten
	 */
	public void addNeighbourCity(City city, int distance) {
		neighboursCities.put(city, distance);
	}

	/**
	 * Erstellt Geometrie, die einen Punkt mit Koordinaten darstellt.
	 */
	@Override
	public void addGeometry() {
		cityGeom.setLocation(coordinateX, coordinateY);
	}

	/**
	 * Gibt Nachbarnstädte zurück (Städte, die eine Verbindung (Entfernung) zu einer
	 * anderen Stadt haben)
	 * 
	 * @return the neighboursCities Nachbarstädte sind die Städte, die eine
	 *         Verbindung (Entfernung) zu einer anderen Stadt haben
	 */
	public Map<City, Integer> getNeighboursCities() {
		return neighboursCities;
	}

	/**
	 * Gibt die Identifikationsnummer der Stadt zurück
	 * 
	 * @return the id identifikationsnummer vom 'City' Objekt
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gibt den Name der Stadt zurück
	 * 
	 * @return the name die Name der Stadt, z.B. Oldenburg
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

}
