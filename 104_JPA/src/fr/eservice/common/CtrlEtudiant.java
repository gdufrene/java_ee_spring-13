package fr.eservice.common;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import fr.eservice.common.VueEtudiant.FIELD;


public class CtrlEtudiant {
	
	VueEtudiant view;
	EtudiantDao daoEtudiant;
	
	
	public CtrlEtudiant( VueEtudiant view, EtudiantDao daoEtudiant ) {
		this.view = view;
		this.daoEtudiant = daoEtudiant;
		
		initHandlers();
	}
	
	private void error( String msg ) {
		JLabel zone = view.getInfoZone();
		zone.setText(msg);
		zone.setForeground(Color.RED);
	}
	
	private void info( String msg ) {
		JLabel zone = view.getInfoZone();
		zone.setText(msg);
		zone.setForeground(Color.BLUE);
	}
	
	private void setEtudiant( Etudiant etudiant ) {
		view.setField(FIELD.ID, ""+etudiant.getId());
		view.setField(FIELD.FIRSTNAME, ""+etudiant.getFirstname());
		view.setField(FIELD.LASTNAME, ""+etudiant.getLastname());
		view.setField(FIELD.AGE, ""+etudiant.getAge());
	}
	
	private Runnable doSearchEtudiant = new Runnable() {
		@Override
		public void run() {
			String str_id = JOptionPane.showInputDialog(view, "Identifiant ?");
			try {
				int id = Integer.parseInt(str_id);
				Etudiant etudiant = daoEtudiant.load(id); //Etudiant.load(db, id);
				if ( etudiant == null ) {
					error("Aucun etudiant trouvé.");
					return;
				}
				setEtudiant(etudiant);
			} catch (NumberFormatException e) {
				error("Identifiant incorrect, ce n'est pas un nombre.");
			}
		}
	};
	
	
	private Runnable doSaveEtudiant = new Runnable() {
		@Override
		public void run() {
			Etudiant etudiant = new Etudiant();
			etudiant.setFirstname( view.getField(FIELD.FIRSTNAME) );
			etudiant.setLastname( view.getField(FIELD.LASTNAME) );
			String str_age = view.getField(FIELD.AGE);
			int age;
			try {
				age = Integer.parseInt(str_age);
			} catch (NumberFormatException e) {
				error("Vous devez indiquez un age correct (nombre)");
				return;
			}
			etudiant.setAge( age );
			//etudiant.save(db);
			daoEtudiant.save(etudiant);
			view.setField(FIELD.ID, ""+etudiant.getId());
			info("Etudiant sauvegardé !");
		}
	};
	
	private Runnable doChangeEtudiant(final boolean next) {
		return new Runnable() {
			@Override
			public void run() {
				boolean doNext = next;
				int id = 0;
				try {
					id = Integer.parseInt( view.getField(FIELD.ID) );
				} catch (NumberFormatException e) {
					doNext = true;
				}
				// Etudiant etudiant = doNext ? Etudiant.after(db, id ) : Etudiant.before(db, id );
				Etudiant etudiant = doNext ? daoEtudiant.after( id ) : daoEtudiant.before( id );
				if ( etudiant != null ) {
					setEtudiant(etudiant);
					( doNext ? view.getPreviousButton() : view.getNextButton() ).setEnabled( true );
				} else {
					error( (doNext ? "Dernier " : "Premier ") + "étudiant !");
					( doNext ? view.getNextButton() : view.getPreviousButton() ).setEnabled( false );
				}
			}
		};
	}
	
	private void initHandlers() {
		view.getSearchButton().addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(doSearchEtudiant);
			}
		});
		view.getCreateButton().addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setField(FIELD.ID, "");
				view.setField(FIELD.FIRSTNAME, "");
				view.setField(FIELD.LASTNAME, "");
				view.setField(FIELD.AGE, "");
				view.focus(FIELD.FIRSTNAME);
			}
		});
		view.getSaveButton().addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(doSaveEtudiant);
			}
		});
		view.getNextButton().addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(doChangeEtudiant(true));
			}
		});
		view.getPreviousButton().addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(doChangeEtudiant(false));
			}
		});
	}

	public static void main(String[] args) throws Exception {
		VueEtudiant vueEtudiant = new VueEtudiant();
		JFrame frame = vueEtudiant.showFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String daoImplementation = "fr.eservice.jdbc.EtudiantJdbcDao";
		if ( args != null && args.length > 1 ) {
			daoImplementation = args[0];
		}
		
		EtudiantDao dao = (EtudiantDao) Class.forName(daoImplementation).newInstance();
		new CtrlEtudiant(vueEtudiant, dao);
	}
}
