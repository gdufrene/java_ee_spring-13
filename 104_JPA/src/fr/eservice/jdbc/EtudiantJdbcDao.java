package fr.eservice.jdbc;

import java.sql.Connection;

import fr.eservice.common.Etudiant;
import fr.eservice.common.EtudiantDao;

public class EtudiantJdbcDao implements EtudiantDao {
	
	Connection db;
	
	@Override
	public void init() {
		db = Database.getInstance();
	}
	
	@Override
	public Etudiant after(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Etudiant before(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Etudiant load(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	 
	 @Override
	public boolean save(Etudiant etudiant) {
		// TODO Auto-generated method stub
		return false;
	}
	 
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
