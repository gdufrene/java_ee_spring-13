package fr.eservice.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eservice.common.Etudiant;
import fr.eservice.common.EtudiantDao;

public class EtudiantJdbcDao implements EtudiantDao {
	
	Connection db;
	
	private static Etudiant sqlLoad(Connection db, String comp, int id) {
		Etudiant etudiant = null;
		try {
			PreparedStatement stm = db.prepareStatement(
				"select TOP 1 id, nom, prenom, age from etudiant where id "+comp+" ?" +
				("=".equals(comp) ? "" : " ORDER BY ID" + ("<".equals(comp) ? " DESC" : "") )
			);
			stm.setInt(1, id);
			ResultSet res = stm.executeQuery();
			if ( !res.next() ) return null;
			etudiant = new Etudiant();
			etudiant.setId( res.getInt(1) );
			etudiant.setLastname( res.getString(2) );
			etudiant.setFirstname( res.getString(3) );
			etudiant.setAge( res.getInt(4) );
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Unable to select etudiant");
		}
		return etudiant;
	}
	
	@Override
	public void init() {
		db = Database.getInstance();
	}
	
	@Override
	public Etudiant after(int id) {
		return sqlLoad(db, ">", id);
	}
	
	@Override
	public Etudiant before(int id) {
		return sqlLoad(db, "<", id);
	}
	
	@Override
	public Etudiant load(int id) {
		return sqlLoad(db, "=", id);
	}
	 
	 @Override
	public boolean save(Etudiant etudiant) {
			try {
				PreparedStatement insert = db.prepareStatement(
					"insert into etudiant(nom, prenom, age) values(?, ?, ?)"
				);
				insert.setString(1, etudiant.getLastname());
				insert.setString(2, etudiant.getFirstname());
				insert.setInt(3, etudiant.getAge());
				insert.execute();
				insert.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("ERROR - impossible d'exŽcuter la requte insert etudiant.");
			}
			
			// HINT: this code set the generated id.
			try { 
				Statement stm = db.createStatement();
				ResultSet res = stm.executeQuery("call identity()");
				if ( res.next() ) etudiant.setId( res.getInt(1) );
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Unable to get ID.");
				return false;
			}
			
			return true;
	}
	 
	@Override
	public void clear() {
		try {
			db.createStatement().execute("delete from etudiant");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
