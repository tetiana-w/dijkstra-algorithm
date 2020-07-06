package tests;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseTest {
	public static void main(String[] args) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/german_cities", "root", "");
		System.out.println("Verbindung: " + conn);
	}
}
