package tests;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.twproject.dayofweek.DayOfWeek;

public class DayOfWeekTest {
	public static void main(String[] args) {
		Date now = new Date();

		SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
		System.out.println(simpleDateformat.format(now));

		simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
		System.out.println(simpleDateformat.format(now));
		// System.out.println(simpleDateformat.format(now).toString());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK)); // the day of the week in numerical format

		// PlanetImSonnensystem gewaelterPlanet =
		// PlanetImSonnensystem.valueOf(planetenname.toUpperCase());
		DayOfWeek todayDayOfWeek = DayOfWeek.valueOf(simpleDateformat.format(now).toString().toUpperCase());

		System.out.println(simpleDateformat.format(now).toString().toUpperCase());

		switch (todayDayOfWeek) {
		case FRIDAY:
			System.out.println("Heute ist FREITAG! Laut Statistik passieren die meisten Verkehrunfälle am Freitag. "
					+ "Nach einer produktiven Arbeitswoche sind die meisten von uns sehr erschöpft, "
					+ "da lässt die Konzentration und Aufmerksamkeit oft nach. Dies begünstigt Verkehrsunfälle."
					+ " Hatten Sie auch eine produktive und anstrengende Woche und sind sich sicher, "
					+ "dass sie trotzdem noch fahren wollen? "
					+ "Wollen Sie sich nicht auch lieber zunächst bei einem erfrischenden Getränk und einem Snack auf dem Sofa entspannen?");
		case FREITAG:
			System.out.println("Heute ist FREITAG! Laut Statistik passieren die meisten Verkehrunfälle am Freitag. "
					+ "Nach einer produktiven Arbeitswoche sind die meisten von uns sehr erschöpft, "
					+ "da lässt die Konzentration und Aufmerksamkeit oft nach. Dies begünstigt Verkehrsunfälle."
					+ " Hatten Sie auch eine produktive und anstrengende Woche und sind sich sicher, "
					+ "dass sie trotzdem noch fahren wollen? "
					+ "Wollen Sie sich nicht auch lieber zunächst bei einem erfrischenden Getränk und einem Snack auf dem Sofa entspannen?");
			break;
		case DONNERSTAG:
			System.out.println("test");
			System.out.println( 
							"*******************************************************************************************************\n"
							+ "Heute ist FREITAG! Laut Statistik passieren die meisten Verkehrunfälle am Freitag.\n"
							+ "Nach einer produktiven Arbeitswoche sind die meisten von uns sehr erschöpft,\n"
							+ "da lässt die Konzentration und Aufmerksamkeit oft nach. Dies begünstigt Verkehrsunfälle.\n"
							+ "Hatten Sie auch eine produktive Woche und sind sich sicher, "
							+ "dass sie trotzdem noch fahren wollen? \n"
							+ "Wollen Sie sich nicht auch lieber zunächst bei einem erfrischenden Getränk auf dem Sofa entspannen?\n"
							+ "*******************************************************************************************************\n");
			
			break;
		default:
			System.out.println("!!!");
			break;
		}
	}
}
