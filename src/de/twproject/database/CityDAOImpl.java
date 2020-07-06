package de.twproject.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import de.twproject.geoobject.City;


/**
 * Hier wird auf alle Dataien in einer Datenbank zugegriffen. Das ist eine Klasse,
 * die eine Implementierung des CityDAO-Interface ist.
 * 
 * @author Tetiana Wycital
 *
 */
public class CityDAOImpl implements CityDAO {
	/**
	 * Eine Collection von Städten
	 */
	private Set<City> allCities = new HashSet<City>();

	/**
	 * Die Rückgabe aller Stadte, die in einer Datenbank gespeichert sind.
	 * 
	 * @return Die Städte aus der Datenbank, wenn es keine Städte gefunden wurden,
	 *         gibt 'null' zurück
	 * 
	 */
	@Override
	public Set<City> getAllCities() {
		String dbURL = "jdbc:mysql://localhost:3306/german_cities";
		String user = "root";
		String password = "";

		try {
			Connection conn = DriverManager.getConnection(dbURL, user, password);

			// Map<City, Integer> citiesId = new HashMap<City, Integer>(); TODO delete

			// Set<City> citiesRN = new HashSet<City>();
			// RoadNetwork roadNetwork = new RoadNetwork(citiesRN);

			String sql = "SELECT * FROM city";
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);

			while (result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				int x = (int) result.getDouble(3);
				int y = (int) result.getDouble(4);

				City cityFromDB = new City(id, name, x, y);

				String sqlGetDistance = "SELECT distance.*, city.name as stadt, neighbour.name as nachbar, city.x, city.y, neighbour.x, neighbour.y "
						+ "FROM `distance` " + "LEFT JOIN city ON distance.id_city = city.id_city "
						+ "LEFT JOIN city as neighbour ON distance.id_neighbour_city = neighbour.id_city "
						+ "WHERE distance.id_city = " + cityFromDB.getId() + " " + "UNION "
						+ "SELECT distance.*, neighbour.name as stadt,city.name as nachbar, city.x, city.y, neighbour.x, neighbour.y "
						+ "FROM `distance` " + "LEFT JOIN city ON distance.id_city = city.id_city "
						+ "LEFT JOIN city as neighbour ON distance.id_neighbour_city = neighbour.id_city "
						+ "WHERE distance.id_neighbour_city = " + cityFromDB.getId();

				Statement stmtGetDistance = conn.createStatement();
				ResultSet resultDistance = stmtGetDistance.executeQuery(sqlGetDistance);

				while (resultDistance.next()) {

					City neighbourCity;
					int neighbourCityId = resultDistance.getInt(3);
					int distance = (int) resultDistance.getDouble(4);
					String neighbourCityName = resultDistance.getString(6);

					neighbourCity = new City(neighbourCityId, neighbourCityName, resultDistance.getInt(7),
							resultDistance.getInt(8));
					cityFromDB.addNeighbourCity(neighbourCity, distance);

				}
				// citiesRN.add(cityFromDB);
				allCities.add(cityFromDB);
			}

		} catch (SQLException e) {
			System.out.println("There is a problem with connection to database!");
			// e.printStackTrace();
		}
		if (allCities.size() == 0) {
			return null;
		}
		return allCities;

	}

}
