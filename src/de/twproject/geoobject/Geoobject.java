package de.twproject.geoobject;

/**
 * Ein benutzerdefiniertes raumbezogenes Ph�nomen, das modelliert oder
 * dargestellt werden kann (z.B. Stadt, Flu�, Stra�e usw.)
 * 
 * @author Tetiana Wycital
 *
 */

public interface Geoobject {
	/**
	 * F�gt eine Geometrie zu einem geographischen Objekt zu. Unterschiedliche
	 * geographische Objekte k�nnen unterschiedliche Geometrien haben (z.B. point,
	 * line, polyline)
	 */
	public void addGeometry();
}
