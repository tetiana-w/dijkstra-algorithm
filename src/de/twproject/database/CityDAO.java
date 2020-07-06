package de.twproject.database;

import java.util.Set;

import de.twproject.geoobject.City;

/** 
 * CityDAO ist ein Interface f�r den Zugriff auf die mit dem City-Objekt verbundenen Daten
 *  
 * @author Tetiana Wycital
 *
 */
public interface CityDAO {	
	/**
	 *  Gibt alle St�dte einer Datenbank zur�ck
	 *  @return Die Menge aller St�dte. Wenn es keine St�dte gefunden wurden, gibt 'null' zur�ck
	 */
	Set<City> getAllCities();	
	
}

