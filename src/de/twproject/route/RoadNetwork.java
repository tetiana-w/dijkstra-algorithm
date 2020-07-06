package de.twproject.route;

import java.util.HashSet;
import java.util.Set;

import de.twproject.geoobject.City;

/**
 * Ist eine Sammlung von verschiedenen St�dten
 * 
 * @author Tetiana Wycital
 *
 */
public class RoadNetwork {
	/**
	 * Eine Collection von St�dten
	 */
	private Set<City> citiesRoadNetwork = new HashSet<City>();	

	/**
	 * Baut eine Sammlung von St�dten auf
	 * 
	 * @param cities ist eine Sammlung von Objekten, des Typs City
	 */
	public RoadNetwork(Set<City> cities) {
		this.citiesRoadNetwork = cities;

	}

	/**
	 * Gibt die 'String' Darstellung eines RoadNetwork Objekts zur�ck
	 */
	@Override
	public String toString() {
		return "RoadNetwork [citiesRoadNetwork=" + citiesRoadNetwork + "]";
	}

	/**
	 * Gibt die St�dte zur�ck, die zur Sammlung citiesRoadNetwork geh�ren
	 * 
	 * @return citiesRoadNetwork eine Sammlung von St�dten
	 */
	public Set<City> getCitiesRoadNetwork() {
		return citiesRoadNetwork;
	}

}
