package fr.eservice.common;

public interface EtudiantDao {
	
	/**
	 * Initialise la base de donn�es ou le moteur de persistance.
	 */
	public void init();
	
	/**
	 * Recherche le premier �tudiant APRES un autre dont l'identifiant est donn�.
	 * Renvoi null si il n'y a pas d'�tudiant apr�s.
	 * 
	 * @param id l'identifiant de l'�tudiant � partir duquel lancer la recherche
	 * @return Etudiant
	 */
	public Etudiant after( int id );
	
	/**
	 * Recherche le premier �tudiant AVANT un autre dont l'identifiant est donn�.
	 * Renvoi null si il n'y a pas d'�tudiant avant. 
	 * 
	 * @param id l'identifiant de l'�tudiant � partir duquel lancer la recherche
	 * @return Etudiant
	 */
	public Etudiant before( int id );
	
	
	/**
	 * Sauvegarde un �tudiant pass� en param�tre.
	 * Au moment de la sauvegarde le param�tre Id est affect�.
	 * 
	 * @param etudiant � sauvegarder
	 * @return 
	 */
	public boolean save( Etudiant etudiant );
	
	/**
	 * Charge un �tudiant dont l'identifiant est donn�.
	 * 
	 * @param id identifiant de l'�tudiant � charger.
	 * @return Etudiant, null si non trouv�.
	 */
	public Etudiant load( int id );

	/**
	 * Clear the database
	 */
	public void clear();

}
