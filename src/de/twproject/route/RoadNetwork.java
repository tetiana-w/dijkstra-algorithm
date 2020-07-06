package de.twproject.route;

import java.util.HashSet;
import java.util.Set;

import de.twproject.geoobject.City;

/**
 * Ist eine Sammlung von verschiedenen Städten
 * 
 * @author Tetiana Wycital
 *
 */
public class RoadNetwork {
	/**
	 * Eine Collection von Städten
	 */
	private Set<City> citiesRoadNetwork = new HashSet<City>();	

	/**
	 * Baut eine Sammlung von Städten auf
	 * 
	 * @param cities ist eine Sammlung von Objekten, des Typs City
	 */
	public RoadNetwork(Set<City> cities) {
		this.citiesRoadNetwork = cities;

	}

	/**
	 * Gibt die 'String' Darstellung eines RoadNetwork Objekts zurück
	 */
	@Override
	public String toString() {
		return "RoadNetwork [citiesRoadNetwork=" + citiesRoadNetwork + "]";
	}

	/**
	 * Gibt die Städte zurück, die zur Sammlung citiesRoadNetwork gehören
	 * 
	 * @return citiesRoadNetwork eine Sammlung von Städten
	 */
	public Set<City> getCitiesRoadNetwork() {
		return citiesRoadNetwork;
	}

}
