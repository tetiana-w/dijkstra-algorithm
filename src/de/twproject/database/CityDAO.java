package de.twproject.database;

import java.util.Set;

import de.twproject.geoobject.City;

/** 
 * CityDAO ist ein Interface für den Zugriff auf die mit dem City-Objekt verbundenen Daten
 *  
 * @author Tetiana Wycital
 *
 */
public interface CityDAO {	
	/**
	 *  Gibt alle Städte einer Datenbank zurück
	 *  @return Die Menge aller Städte. Wenn es keine Städte gefunden wurden, gibt 'null' zurück
	 */
	Set<City> getAllCities();	
	
}

