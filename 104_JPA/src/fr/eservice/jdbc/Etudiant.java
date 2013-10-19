package fr.eservice.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Etudiant {
	
	private int id;
	private String firstname;
	private String lastname;
	private int age;
	
	public static Etudiant load( Connection db, int id ) {
		// TODO : Completer ce code pour sélection le bon étudiant dans la base.
		
		throw new Error("Complete this code");
	}
	
	public boolean save( Connection db ) {
		
		// TODO : Completer ce code pour sauvegarder le bon étudiant.
		
		// HINT: this code set the generated id.
		try { 
			Statement stm = db.createStatement();
			ResultSet res = stm.executeQuery("call identity()");
			if ( res.next() ) id = res.getInt(1);
			stm.close();
		} catch (SQLException e) {
			return false;
		}
		
		throw new Error("Complete this code");
	}
	
	public static Etudiant before(Connection db, int id) {
		// TODO complete this code !
		// SELECT TOP 1 ... FROM ... Where ... Order by ... 
		
		throw new Error("Complete this code");
	}
	
	public static Etudiant after(Connection db, int id) {
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
