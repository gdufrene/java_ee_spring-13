package fr.eservice.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private static Database instance;
	
	private Connection db;
	
	public static Connection getInstance() {
		if ( instance == null ) instance = new Database();
		return instance.getConnection();
	}

	private Connection getConnection() {
		if ( db == null ) initConnection();
		return db;
	}

	private Connection initConnection() {
		try {
			db = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "");
			createTables( db );
		} catch (SQLException e) {
			throw new Error( e );
		}
		return db;
	}

	public static void createTables( Connection db ) throws SQLException {
		Statement stm = db.createStatement();
		stm.execute(
			"Create table etudiant(" +
			"  id int GENERATED ALWAYS AS IDENTITY primary key," +
			"  nom varchar(80)," +
			"  prenom varchar(80)," +
			"  age int" + 
			")"
		);
	}
}
