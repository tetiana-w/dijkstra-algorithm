package de.twproject.geoobject;

/**
 * Ein benutzerdefiniertes raumbezogenes Phänomen, das modelliert oder
 * dargestellt werden kann (z.B. Stadt, Fluß, Straße usw.)
 * 
 * @author Tetiana Wycital
 *
 */

public interface Geoobject {
	/**
	 * Fügt eine Geometrie zu einem geographischen Objekt zu. Unterschiedliche
	 * geographische Objekte können unterschiedliche Geometrien haben (z.B. point,
	 * line, polyline)
	 */
	public void addGeometry();
}
