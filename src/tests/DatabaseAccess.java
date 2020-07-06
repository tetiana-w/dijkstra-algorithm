package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.HashMap;
import java.util.HashSet;
//import java.util.Map;
import java.util.Set;
//import java.util.Map.Entry;

import de.twproject.geoobject.City;
import de.twproject.route.RoadNetwork;

public class DatabaseAccess {
	public static void main(String[] args) throws SQLException { 
		String dbURL = "jdbc:mysql://localhost:3306/german_cities";
		String user = "root";
		String password = "";
		Connection conn = DriverManager.getConnection(dbURL, user, password);
		
		Set<City> citiesRN = new HashSet<City>();
		RoadNetwork roadNetwork = new RoadNetwork(citiesRN);

		String sql = "SELECT * FROM city";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(sql);

		while (result.next()) {
			int id = result.getInt(1);
			String name = result.getString(2);
			int x = (int) result.getDouble(3);
			int y = (int) result.getDouble(4);

			City cityFromDB = new City(id, name, x, y);
			System.out.println("\n" + cityFromDB + " (" + cityFromDB.getCoordinateX() + ", " + cityFromDB.getCoordinateY() + ")");
			
			
//			String sqlGetDistance = "SELECT distance.*, city.name, neighbour.name, city.x, city.y, neighbour.x, neighbour.y FROM `distance` "
//					+ "LEFT JOIN city ON distance.id_city = city.id_city "
//					+ "LEFT JOIN city as neighbour ON distance.id_neighbour_city = neighbour.id_city "
//					+ "WHERE (distance.id_city = "  + cityFromDB.getId() + ") " //+ " (SELECT id_city FROM city WHERE id_city = 1)) "
//					+ "OR (distance.id_neighbour_city = " + cityFromDB.getId() + ") "; //+ "(SELECT id_city FROM city WHERE id_city = 1))";
			
			String sqlGetDistance = "SELECT distance.*, city.name as stadt, neighbour.name as nachbar, city.x, city.y, neighbour.x, neighbour.y "
					+ "FROM `distance` "
					+ "LEFT JOIN city ON distance.id_city = city.id_city "
					+ "LEFT JOIN city as neighbour ON distance.id_neighbour_city = neighbour.id_city "
					+ "WHERE distance.id_city = " + cityFromDB.getId() + " "
					+ "UNION "
					+ "SELECT distance.*, neighbour.name as stadt,city.name as nachbar, city.x, city.y, neighbour.x, neighbour.y "
					+ "FROM `distance` "
					+ "LEFT JOIN city ON distance.id_city = city.id_city "
					+ "LEFT JOIN city as neighbour ON distance.id_neighbour_city = neighbour.id_city "
					+ "WHERE distance.id_neighbour_city = " + cityFromDB.getId();
			
			Statement stmtGetDistance = conn.createStatement();
			ResultSet resultDistance = stmtGetDistance.executeQuery(sqlGetDistance);

			
//			resultDistance.next();
//			int cityId = resultDistance.getInt(2);
//			String cityNameDistance = resultDistance.getString(5);
//			
//			int xCoordinate = resultDistance.getInt(7);
//			int yCoordinate = resultDistance.getInt(8);
//			City city = new City(cityId,cityNameDistance,xCoordinate,yCoordinate );
//			
//			resultDistance.beforeFirst();
			
			while (resultDistance.next()) {				
				
				City neighbourCity;				
				
				int neighbourCityId = resultDistance.getInt(3);
				int distance = (int) resultDistance.getDouble(4);
				String neighbourCityName = resultDistance.getString(6);
				
				neighbourCity = new City(neighbourCityId, neighbourCityName, resultDistance.getInt(7), resultDistance.getInt(8));				
				cityFromDB.addNeighbourCity(neighbourCity, distance);
				
				
//				String sqlGetNeighbourCityName = "SELECT name FROM city WHERE id_city = " +  neighbourCityId;
//				Statement stmtGetNeighbourCityName =  conn.createStatement();
//				ResultSet resultNeighbourName = stmtGetNeighbourCityName.executeQuery(sqlGetNeighbourCityName);					
//				String neighbourCityName = resultDistance.getString(1);
				
				//System.out.println(cityName + "	" + neighbourCityName);								
//				if(cityName.toUpperCase().equals(cityFromDB.getName().toUpperCase())) {
//					city = cityFromDB;		
//					
//					//System.out.println("**" + city + neighbourCityId + neighbourCityName);
//				}

				
				System.out.println(cityFromDB.getName()+ " " + neighbourCityName + " " + distance);		
				
			}
			System.out.println("Neighbours Cities" + cityFromDB.getNeighboursCities());
			
			citiesRN.add(cityFromDB);
			//citiesId.put(cityFromDB, id);
			
		}

		System.out.println(roadNetwork);
	}
}

/*
 * SELECT distance.*, city.name as stadt, neighbour.name as nachbar, city.x, city.y, neighbour.x, neighbour.y FROM `distance` LEFT JOIN city ON distance.id_city = city.id_city LEFT JOIN city as neighbour ON distance.id_neighbour_city = neighbour.id_city WHERE distance.id_city = 5 UNION SELECT distance.*, neighbour.name as stadt,city.name as nachbar, city.x, city.y, neighbour.x, neighbour.y FROM `distance` LEFT JOIN city ON distance.id_city = city.id_city LEFT JOIN city as neighbour ON distance.id_neighbour_city = neighbour.id_city WHERE distance.id_neighbour_city = 5 
 */
