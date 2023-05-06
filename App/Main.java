package App;
import java.sql.Statement;
import java.util.ArrayList;

import Back.Back;
import Front.Fonction.Employe;
import interface_package.ConnexionFrame;
import interface_polo.CreneauFrame;
import interface_polo.PoloPopUp;
import interface_polo.RetirerEmployeFrame;

public class Main {

	public static void main(String[] args) {
		
		Statement st = Back.connectionBase();
		ConnexionFrame c = new ConnexionFrame(st);
		PoloPopUp p = new PoloPopUp(st);
		c.setVisible(true);
		p.setVisible(true);
//
//
//		Employe e1 = Back.getEmployer(st, "a");
//		Employe e2 = Back.getEmployer(st, "b");
//		Employe e3 = Back.getEmployer(st, "c");
//		Employe e4 = Back.getEmployer(st, "d");
//		Employe e5 = Back.getEmployer(st, "e");
//		Employe e6 = Back.getEmployer(st, "g");
//
//		ArrayList <Employe> liste = new ArrayList();
//		liste.add(e1);
//		liste.add(e2);
//		liste.add(e3);
//		liste.add(e4);
//		liste.add(e5);
//		liste.add(e6);
//
		//Back.ajout_reunion(st, liste, "2023-10-02", "2023-11-02", false);
		
		//Back.ajoutTravail(st, liste, "2023-10-02", "2023-10-02");
		/*Back.insertEmployer(st, "a", "a", "a", "a", "a");
		Back.insertEmployer(st, "b", "b", "b", "b", "b");
		Back.insertEmployer(st, "c", "c", "c", "c", "c");
		Back.insertEmployer(st, "d", "d", "d", "d", "d");
		Back.insertEmployer(st, "e", "e", "e", "e", "e");*/
		//Back.ajoutIndisp(st, 6,"maladie","2023-10-02", "2023-11-02");
		
		//Back.retireIndisp(st, liste, "2023-10-02", "2023-11-02");
		//Back.insertEmployer(st, "g", "g", "g", "g", "g");
		
		
	}

}
