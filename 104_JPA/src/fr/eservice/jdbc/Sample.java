package fr.eservice.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Sample {
	
	Connection db;
	
	
	public Sample() throws Exception {
		db = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "");
		createTables();
	}
	
	private void createTables() throws SQLException {
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
	
	public static void main(String[] args) throws Exception {
		new Sample();
	}

}
