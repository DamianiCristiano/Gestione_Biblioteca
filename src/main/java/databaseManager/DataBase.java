package databaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

import model.Libro;

public class DataBase {

	private final String url = "jdbc:mysql://localhost:3306/";
	private final String nameDB = "biblioteca";
	private final String username = "root";
	private final String password = "";
	private Connection conn = null;
	


	public Connection getConn() {
		return conn;
	}


	public void setConn(Connection conn) {
		this.conn = conn;
	}



	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url + nameDB, username, password);
			System.out.println("Connessione riuscita alla " + nameDB);
			return conn;
		} catch (Exception e) {
			System.out.println("Connection failed: " + e.getMessage());
			return null;
		}
    }
	
	
}
