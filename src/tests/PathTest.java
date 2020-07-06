package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import de.twproject.database.CityDAOImpl;
import de.twproject.geoobject.City;
import de.twproject.route.RoadNetwork;

public class PathTest {

	public static void main(String[] args) {

		// Get data from Database
		CityDAOImpl cityDaoImpl = new CityDAOImpl();
		Set<City> dbCitiesRoadNetwork = cityDaoImpl.getAllCities();
		RoadNetwork germanyRoadNetworkDb = new RoadNetwork(dbCitiesRoadNetwork);

		// Start City
		String gelesenStartCity = "Oldenburg";
		City startCity = dbCitiesRoadNetwork.stream().filter(c -> c.getName().equalsIgnoreCase(gelesenStartCity))
				.findFirst().orElse(null);

		// Destination City
		String destinationCityName = "Frankfurt";
		City destinationCity = dbCitiesRoadNetwork.stream()
				.filter(c -> c.getName().equalsIgnoreCase(destinationCityName)).findFirst().orElse(null);

		Map<City, Integer> distances = setDistances(germanyRoadNetworkDb, startCity);
		System.out.println("\n*****************setDistances Methode******************");
		System.out.println(distances);

		Set<City> visitedCities = new HashSet<City>(); // anfang
		Map<City, Integer> minimalDistances = new HashMap<City, Integer>();
		Map<City, City> path = new HashMap<City, City>();

		while (!distances.isEmpty()) {
			System.out.println("\n ---------------------------------------------------------------------");
			System.out.println("\n*****************findMinDistance Methode*****************");
			City currentCity = findCurrentCity(distances);
			System.out.println(currentCity);

			int currentDistance = Collections.min(distances.values());
			System.out.println("\n*****************getMinDistance*****************\n" + currentDistance);

			System.out.println("\n*****************getNeighbours Methode*****************");
			Map<City, Integer> neighbours = getNeighbours(currentCity, visitedCities);//
			System.out.println(neighbours);

			System.out.println("\n*****************Distances Nach updateDistances Methode*****************");
			updateDistances(distances, neighbours, currentDistance, minimalDistances);
			System.out.println(distances);

			updateMinimalDistances(minimalDistances, neighbours, currentDistance);
			System.out.println("\n*****************updateMinimalDistances Methode*****************");
			System.out.println(updateMinimalDistances(minimalDistances, neighbours, currentDistance));

			updatePath(path, minimalDistances, currentCity, currentDistance, neighbours);
			System.out.println("\n*****************update Path*****************");
			System.out.println(updatePath(path, minimalDistances, currentCity, currentDistance, neighbours));

			visitedCities.add(currentCity);
			System.out.println("\n*****************visitedCities Methode*****************");
			System.out.println(visitedCities);

			distances.remove(currentCity);
			System.out.println("\n*****************distances remove*****************");
			System.out.println(distances);

		}

		System.out.println("\n**********MINIMAL DISTANCES**********");
		System.out.println(minimalDistances);
		System.out.println("*************************************");
		System.out.println("\n*************PATH********************");
		System.out.println(path);
		System.out.println("*************************************");

		List<City> shortestPath = findShortestPath(startCity, destinationCity, path, minimalDistances);
		System.out.println("SHORTEST PATH " + shortestPath);

	}

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

	private static Map<City, Integer> getNeighbours(City currentCity, Set<City> visitedCities) {
		Map<City, Integer> neighbours = new HashMap<City, Integer>();
		currentCity.getNeighboursCities().forEach((neighbour, distanceToNeighbour) -> {
			if (!visitedCities.contains(neighbour)) {
				neighbours.put(neighbour, distanceToNeighbour);
			}
		});
		return neighbours;
	}

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

	private static void updateDistances(Map<City, Integer> distances, Map<City, Integer> neighbours, int currentDistance,
			Map<City, Integer> minimalDistances) {
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
