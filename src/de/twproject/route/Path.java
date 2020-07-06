package de.twproject.route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import de.twproject.geoobject.City;

/**
 * Ist ein Pfad zwischen den Städten. Diese Klasse enthält die "findPath"
 * Methode, mit deren Hilfe es möglich ist den kürzesten Pfad zwischen zwei
 * gewählten Städten ("startCity" und "destinationCity") zu finden. Die Methode
 * basiert auf dem Dijkstra Algorithmus.
 * 
 * @author Tetiana Wycital
 *
 */
public class Path {

	/**
	 * Es wird der kürzeste Pfad zwischen zwei gewählten Städten ("startCity" und
	 * "destinationCity") nach Dijkstra Algorithmus berechnet
	 * 
	 * @param roadNetwork     ist eine Collection von Städten (Klasse City)
	 * @param startCity       ist die Stadt, die vom Benutzer als Startpunkt gewählt
	 *                        wurde
	 * @param destinationCity ist die Stadt, die vom Benutzer als Zielpunkt gewählt
	 *                        wurde
	 * @return gibt eine Sammlung von Städten zurück. Diese Sammlung beinhaltet den
	 *         kürzesten Pfad zwischen der startCity und der destinationCity
	 */
	public List<City> findPath(RoadNetwork roadNetwork, City startCity, City destinationCity) {
		/**
		 * eine Collection von Städten, zu denen die kleinste Entfernung zur vom
		 * Benutzer ausgewählten Stadt als Startpunkt schon berechnet wurde
		 */
		Set<City> visitedCities = new HashSet<City>();
		/**
		 * die kleinsten Entfernungen einer vom Benutzer ausgewählten Stadt als
		 * Startpunkt zu anderen Städten
		 */
		Map<City, Integer> minimalDistances = new HashMap<City, Integer>();
		/**
		 * eine Collection, die die Beziehung zwischen einer Stadt und ihrem nähesten
		 * Nachbarn zeigt in Bezug auf die vom Benutzer ausgewählte Stadt als Startpunkt
		 */
		Map<City, City> path = new HashMap<City, City>();
		/**
		 * die Entfernungen zwischen einer vom Benutzer ausgewählten Stadt als
		 * Startpunkt und anderen Städten. Die Entfernungen werden nach dem Dijkstra
		 * Algorithmus berechnet.
		 */
		Map<City, Integer> distances = setDistances(roadNetwork, startCity);

		while (!distances.isEmpty()) {
			/**
			 * Die Stadt, mit der kleinsten Entfernung in distances Collection
			 */
			City currentCity = findCurrentCity(distances);
			/**
			 * Die kleinste Entfernung in distances Collection
			 */
			int currentDistance = Collections.min(distances.values());
			/**
			 * Die Nachbarnstädte von currentCity
			 */
			Map<City, Integer> neighbours = getNeighbours(currentCity, visitedCities);

			updateDistances(distances, neighbours, currentDistance, minimalDistances);
			updateMinimalDistances(minimalDistances, neighbours, currentDistance);
			updatePath(path, minimalDistances, currentCity, currentDistance, neighbours);

			visitedCities.add(currentCity);
			distances.remove(currentCity);

		}
		/**
		 * Diese Sammlung beinhaltet den kürzesten Pfad zwischen der startCity und
		 * destinationCity
		 */
		List<City> shortestPath = findShortestPath(startCity, destinationCity, path, minimalDistances);
		return shortestPath;
	}

	/**
	 * Es wird der kurzeste Pfad zwischen der startCity und destinationCity aus dem
	 * path ausgewählt und als eine Sammlung von Städten zurückgegeben
	 * 
	 * @param startCity        ist die Stadt, die vom Benutzer als Startpunkt
	 *                         gewählt wurde
	 * @param destinationCity  ist die Stadt, die vom Benutzer als Zielpunkt gewählt
	 *                         wurde
	 * @param path             eine Collection, die die Beziehung zwischen einer
	 *                         Stadt und ihrem nähesten Nachbarn zeigt
	 * @param minimalDistances die kleinsten Entfernungen einer vom Benutzer
	 *                         ausgewählten Stadt als Startpunkt zu anderen Städten
	 * @return gibt eine Sammlung von Städten zurück. Diese Sammlung beinhaltet den
	 *         kürzesten Pfad zwischen der startCity und destinationCity
	 */
	private static List<City> findShortestPath(City startCity, City destinationCity, Map<City, City> path,
			Map<City, Integer> minimalDistances) {
		List<City> shortestPath = new ArrayList<City>();

		while (!destinationCity.equals(startCity)) {
			for (Entry<City, City> cities : path.entrySet()) {
				City city = cities.getKey();
				City closestCity = cities.getValue();
				if (city.equals(destinationCity)) {
					shortestPath.add(destinationCity);
					destinationCity = closestCity;
				}
			}
		}
		shortestPath.add(startCity);
		return shortestPath;
	}

	/**
	 * Es wird auf die Nachbarstädte von der Stadt mit der kleinsten Entfernung in
	 * distances Collection zugegriffen
	 * 
	 * @param currentCity   die Stadt, mit der kleinsten Entfernung in distances
	 *                      Collection
	 * @param visitedCities eine Collection von Städten, zu denen die kleinste
	 *                      Entfernung zur vom Benutzer ausgewählten Stadt als
	 *                      Startpunkt schon berechnet wurde
	 * @return neighbours eine Collection von Städten, die am nähesten zur
	 *         currentCity liegen und von denen noch keine kleinste Entfernung
	 *         berechnet wurde (diese Stadt ist im visitedCities Collection nicht
	 *         vorhanden)
	 */
	private static Map<City, Integer> getNeighbours(City currentCity, Set<City> visitedCities) {
		Map<City, Integer> neighbours = new HashMap<City, Integer>();
		currentCity.getNeighboursCities().forEach((neighbour, distanceToNeighbour) -> {
			if (!visitedCities.contains(neighbour)) {
				neighbours.put(neighbour, distanceToNeighbour);
			}
		});
		return neighbours;
	}

	/**
	 * es wird die path Collection nach Dijkstra Algorithmus erneuert, die die
	 * Beziehung zwischen einer Stadt und ihrem nähesten Nachbarn zeigt in Bezug auf
	 * die vom Benutzer ausgewählte Stadt als Startpunkt
	 * 
	 * @param path             eine Collection, die die Beziehung zwischen einer
	 *                         Stadt und ihrem nähesten Nachbarn beinhaltet
	 * @param minimalDistances die kleinsten Entfernungen einer vom Benutzer
	 *                         ausgewählten Stadt als Startpunkt zu anderen Städten
	 * @param currentCity      Die Stadt mit der kleinsten Entfernung in distances
	 *                         Collection
	 * @param currentDistance  Die kleinste Entfernung in distances Collection
	 * @param neighbours       Die Nachbarnstädte von currentCity
	 * @return path eine Collection, die die Beziehung zwischen einer Stadt und
	 *         ihrem nähesten Nachbarn beinhaltet
	 */
	private static Map<City, City> updatePath(Map<City, City> path, Map<City, Integer> minimalDistances,
			City currentCity, int currentDistance, Map<City, Integer> neighbours) {

		neighbours.forEach((neighbour, distanceToNeighbour) -> {
			minimalDistances.forEach((cityMinDistance, minDistance) -> {
				path.computeIfAbsent(neighbour, city -> currentCity);
				if (neighbour.equals(cityMinDistance)) {
					path.computeIfPresent(neighbour, (neighbourCity, city) -> {
						return (distanceToNeighbour + currentDistance <= minDistance) ? currentCity : city;
					});
				}
			});
		});
		return path;
	}

	/**
	 * Es werden die kleinsten Entfernungen eines Startpunkts zu anderen Städten
	 * nach Dijkstra Algorithmus erneuert
	 * 
	 * @param minimalDistances die kleinsten Entfernungen einer vom Benutzer
	 *                         ausgewählten Stadt als Startpunkt zu anderen Städten
	 * @param neighbours       Die Nachbarstädte von currentCity
	 * @param currentDistance  Die kleinste Entfernung in distances Collection
	 * @return minimalDistances die kleinsten Entfernungen einer vom Benutzer
	 *         ausgewählten Stadt als Startpunkt zu anderen Städten
	 */
	private static Map<City, Integer> updateMinimalDistances(Map<City, Integer> minimalDistances,
			Map<City, Integer> neighbours, int currentDistance) {

		neighbours.forEach((neighbour, distanceToNeighbour) -> {
			minimalDistances.computeIfAbsent(neighbour, minDistance -> distanceToNeighbour + currentDistance);
			minimalDistances.computeIfPresent(neighbour,
					(city, minDistance) -> (distanceToNeighbour + currentDistance < minDistance)
							? distanceToNeighbour + currentDistance
							: minDistance);
		});
		return minimalDistances;
	}

	/**
	 * Zuerst wird die Entfernung zur "startCity" auf 0 gesetzt und die Entfernung
	 * zu allen anderen Städten auf infinity
	 * 
	 * @param roadNetwork Ist eine Sammlung von verschiedenen Städten
	 * @param cityStart   ist die Stadt, die vom Benutzer als Startpunkt gewählt
	 *                    wurde
	 * @return distances die Entfernungen zwischen einer vom Benutzer ausgewählten
	 *         Stadt als Startpunkt zu anderen Städten. Die Entfernungen werden nach
	 *         dem Dijkstra Algorithmus berechnet.
	 */
	private static Map<City, Integer> setDistances(RoadNetwork roadNetwork, City cityStart) {
		Map<City, Integer> distances = new HashMap<City, Integer>();
		Set<City> citiesRN = roadNetwork.getCitiesRoadNetwork();

		for (City city : citiesRN) {
			if (city != cityStart) {
				distances.put(city, Integer.MAX_VALUE);
			} else {
				distances.put(city, 0);
			}
		}
		return distances;
	}

	/**
	 * Es wird die Stadt ausgewählt, die die kleinste Entfernung hat
	 * 
	 * @param distances die Entfernungen zwischen einer vom Benutzer ausgewählten
	 *                  Stadt als Startpunkt zu anderen Städten. Die Entfernungen
	 *                  werden nach dem Dijkstra Algorithmus berechnet.
	 * @return gibt die Stadt zurück,die die kleinste Entfernung hat
	 */
	private static City findCurrentCity(Map<City, Integer> distances) {
		int minValue = Collections.min(distances.values());
		City currentCity = new City(0, null, 0, 0);

		for (Entry<City, Integer> city : distances.entrySet()) {
			if (minValue == city.getValue()) {
				currentCity = city.getKey();
			}
		}
		return currentCity;
	}

	/**
	 * Die Methode erneuert die Collection "distances" mit neuen Entfernungen, die
	 * nach dem Dijkstra Algorithmus berechnet werden
	 * 
	 * @param distances        die Entfernungen zwischen einer vom Benutzer
	 *                         ausgewählten Stadt als Startpunkt zu anderen Städten.
	 *                         Die Entfernungen werden nach dem Dijkstra Algorithmus
	 *                         berechnet.
	 * @param neighbours       Die Nachbarstädte von currentCity
	 * @param currentDistance  Die kleinste Entfernung in distances Collection
	 * @param minimalDistances die kleinsten Entfernungen einer vom Benutzer
	 *                         ausgewählten Stadt als Startpunkt zu anderen Städten
	 */
	private static void updateDistances(Map<City, Integer> distances, Map<City, Integer> neighbours,
			int currentDistance, Map<City, Integer> minimalDistances) {
		distances.forEach((city, distance) -> {
			neighbours.forEach((neighbour, distanceToNeighbour) -> {
				if (neighbour.equals(city) && distanceToNeighbour + currentDistance < distance) {
					distances.computeIfPresent(neighbour,
							(neighbourCity, distanceToNeighbourCity) -> distanceToNeighbourCity = distanceToNeighbour
									+ currentDistance);
				}
			});
		});

	}

}
