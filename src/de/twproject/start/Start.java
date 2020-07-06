package de.twproject.start;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import de.twproject.database.CityDAOImpl;
import de.twproject.dayofweek.DayOfWeek;
import de.twproject.exception.WrongDestinationCityException;
import de.twproject.exception.WrongStartCityException;
import de.twproject.geoobject.City;
import de.twproject.route.Path;
import de.twproject.route.RoadNetwork;

/**
 * Ablauf des Programms
 * 
 * @author Tetiana Wycital
 *
 */
public class Start {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		CityDAOImpl cityDaoImpl = new CityDAOImpl();
		Set<City> dbCitiesRoadNetwork = cityDaoImpl.getAllCities();
		RoadNetwork germanyRoadNetworkDb = new RoadNetwork(dbCitiesRoadNetwork);

		Path path = new Path();
		City startCity = null;
		City destinationCity = null;

		System.out.println("Geben Sie eine Startstadt ein: ");

		Scanner readerStartCity = new Scanner(System.in);
		String gelesenStartCity = readerStartCity.nextLine();
		// String gelesenStartCity = "Oldenburg";
		startCity = dbCitiesRoadNetwork.stream().filter(c -> c.getName().equalsIgnoreCase(gelesenStartCity)).findFirst()
				.orElse(null);

		try {
			checkStartCity(startCity);
			System.out.println("Geben Sie eine Zielstadt ein: ");

			Scanner readerDestinationCity = new Scanner(System.in);
			// String gelesenDestinationCity = "Frankfurt";
			String gelesenDestinationCity = readerDestinationCity.nextLine();

			destinationCity = dbCitiesRoadNetwork.stream()
					.filter(c -> c.getName().equalsIgnoreCase(gelesenDestinationCity)).findFirst().orElse(null);
			try {
				checkDestinationCity(destinationCity);
				List<City> shortestPath = path.findPath(germanyRoadNetworkDb, startCity, destinationCity);
				System.out.println("\nDer kürzeste Pfad zwischen" + startCity + " und" + destinationCity);
				System.out.println(shortestPath);

			} catch (WrongDestinationCityException e) {
				System.out.println("\nLeider wurde die Stadt '" + gelesenDestinationCity + "' nicht gefunden :(");

			}
		} catch (WrongStartCityException e) {
			System.out.println("\nLeider wurde die Stadt '" + gelesenStartCity + "' nicht gefunden :(");

		}

		// Verwendung von Enum (Freitags wird eine Erinnerung ausgegeben)
		Date now = new Date();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
		DayOfWeek todayDayOfWeek = DayOfWeek.valueOf(simpleDateformat.format(now).toString().toUpperCase());

		switch (todayDayOfWeek) {
		case FREITAG:
			System.out.println(
					"\n*******************************************************************************************************\n"
							+ "Heute ist FREITAG! Laut Statistik passieren die meisten Verkehrunfälle am Freitag.\n"
							+ "Nach einer produktiven Arbeitswoche sind die meisten von uns sehr erschöpft,\n"
							+ "da lässt die Konzentration und Aufmerksamkeit oft nach. Seien Sie Vorsichtig!");
			break;
		case FRIDAY:
			System.out.println(
					"\n*******************************************************************************************************\n"
							+ "Heute ist FREITAG! Laut Statistik passieren die meisten Verkehrunfälle am Freitag.\n"
							+ "Nach einer produktiven Arbeitswoche sind die meisten von uns sehr erschöpft,\n"
							+ "da lässt die Konzentration und Aufmerksamkeit oft nach. Seien Sie Vorsichtig!");
			break;
		default:
			break;
		}

	}

	/**
	 * Es wird geprüft, ob die Startstadt richtig eingegeben wurde
	 * 
	 * @param startCity ist eine vom Benutzer eingegeben Startstadt, welche in der
	 *                  Datenbank zur Verfügung steht
	 * @throws WrongStartCityException Exception wird ausgeworfen, wenn der
	 *                                 eingegebene Name der Startstadt nicht in der
	 *                                 Datenbank ist
	 */
	public static void checkStartCity(City startCity) throws WrongStartCityException {
		if (startCity == null) {
			throw new WrongStartCityException();
		}
	}

	/**
	 * Es wird geprüft, ob eine Zielstadt richtig eingegeben ist
	 * 
	 * @param destinationCity ist eine ist eine vom Benutzer eingegeben Zielstadt,
	 *                        welche in der Datenbank zur Verfügung steht
	 * @throws WrongDestinationCityException Exception wird ausgeworfen, wenn der
	 *                                       eingegebene Name der Startstadt nicht
	 *                                       in der Datenbank ist
	 */
	public static void checkDestinationCity(City destinationCity) throws WrongDestinationCityException {
		if (destinationCity == null) {
			throw new WrongDestinationCityException();
		}
	}

}
