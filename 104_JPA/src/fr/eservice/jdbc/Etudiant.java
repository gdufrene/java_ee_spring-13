package fr.eservice.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Etudiant {
	
	private int id;
	private String firstname;
	private String lastname;
	private int age;
	
	public static Etudiant load( Connection db, int id ) {
		Etudiant etudiant = null;
		try {
			PreparedStatement stm = db.prepareStatement(
				"select id, nom, prenom, age from etudiant where id = ?"
			);
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();
			if ( !res.next() ) return null;
			etudiant = new Etudiant();
			etudiant.id = res.getInt(1);
			etudiant.lastname = res.getString(2);
			etudiant.firstname = res.getString(3);
			etudiant.age = res.getInt(4);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Unable to select etudiant");
		}
		return etudiant;
	}
	
	public boolean save( Connection db ) {
		
		try {
			PreparedStatement insert = db.prepareStatement(
				"insert into etudiant(nom, prenom, age) values(?, ?, ?)"
			);
			insert.setString(1, lastname);
			insert.setString(2, firstname);
			insert.setInt(3, age);
			insert.execute();
			insert.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR - impossible d'exécuter la requête insert etudiant.");
		}
		
		// HINT: this code set the generated id.
		try { 
			Statement stm = db.createStatement();
			ResultSet res = stm.executeQuery("call identity()");
			if ( res.next() ) id = res.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Unable to get ID.");
			return false;
		}
		
		return true;
	}
	
	public static Etudiant before(int id) {
		// TODO complete this code !
		// SELECT TOP 1 ... FROM ... Where ... Order by ... 
		
		throw new Error("Complete this code");
	}
	
	public static Etudiant after(int id) {
		// TODO Complete this code !
		// SELECT TOP 1 ... FROM ... Where ... Order by ...
		
		throw new Error("Complete this code");
	}
	
	/** Getters and setters ... */

	public int getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}



}
