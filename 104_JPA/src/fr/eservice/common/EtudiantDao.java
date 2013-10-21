package fr.eservice.common;

public interface EtudiantDao {
	
	/**
	 * Initialise la base de données ou le moteur de persistance.
	 */
	public void init();
	
	/**
	 * Recherche le premier étudiant APRES un autre dont l'identifiant est donné.
	 * Renvoi null si il n'y a pas d'étudiant après.
	 * 
	 * @param id l'identifiant de l'étudiant à partir duquel lancer la recherche
	 * @return Etudiant
	 */
	public Etudiant after( int id );
	
	/**
	 * Recherche le premier étudiant AVANT un autre dont l'identifiant est donné.
	 * Renvoi null si il n'y a pas d'étudiant avant. 
	 * 
	 * @param id l'identifiant de l'étudiant à partir duquel lancer la recherche
	 * @return Etudiant
	 */
	public Etudiant before( int id );
	
	
	/**
	 * Sauvegarde un étudiant passé en paramètre.
	 * Au moment de la sauvegarde le paramètre Id est affecté.
	 * 
	 * @param etudiant à sauvegarder
	 */
	public void save( Etudiant etudiant );
	
	/**
	 * Charge un étudiant dont l'identifiant est donné.
	 * 
	 * @param id identifiant de l'étudiant à charger.
	 * @return Etudiant, null si non trouvé.
	 */
	public Etudiant load( int id );

}
