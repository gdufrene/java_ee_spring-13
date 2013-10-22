package fr.eservice.common;

import javax.swing.JOptionPane;

public class Application {

	
	public static void main(String[] args) throws Exception {
		String[] options = new String[]{
			"Vue sans control",
			"Application - JDBC",
			"Application - JPA"
		};
		int i = JOptionPane.showOptionDialog(
			null, 
			"Gestion des étudiants.\n" +
			"Choisissez le DAO à utiliser, ou la vue simple sans controles.", 
			"Lancement Application",
			JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
			null, options, options[0]
		);
		
		switch( i ) {
		case 2:
			CtrlEtudiant.main(new String[]{"fr.eservice.jdbc.EtudiantJdbcDao"});
			break;
		case 1:
			CtrlEtudiant.main(new String[]{"fr.eservice.jpa.EtudiantJPADao"});
			break;
		case 0:
			VueEtudiant.main(null);
			break;
		}
		
	}
}
