package tests;

import de.twproject.geoobject.City;
import de.twproject.geoobject.Town;

public class VererbungUndInterfaceTest {
	public static void main(String[] args) {
		// Vererbung, die getNumberOfCitizensInfo() Methode von Eltern-Klasse Settlement
		// wurde nicht überschrieben
		Town hude = new Town("Hude", 3, 12);
		System.out.print(hude.getName() + " ");
		hude.getNumberOfCitizensInfo();

		// Vererbung, die getNumberOfCitizensInfo() Methode von Eltern-Klasse Settlement
		// wurde in City-Klasse überschrieben
		City oldenburg = new City(1, "Oldenburg", 3, 13);
		System.out.print(oldenburg.getName() + " ");
		oldenburg.getNumberOfCitizensInfo();

		// Implementierte Methode vom Geoobject Interface
		hude.addGeometry();
		System.out.println(hude.getGeometry());
	}
}
