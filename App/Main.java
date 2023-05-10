package App;
import java.sql.Statement;
import java.util.ArrayList;

import Back.Back;
import Front.Fonction.Employe;
import interface_package.ConnexionFrame;


public class Main {

	public static void main(String[] args) {
		
		Statement st = Back.connectionBase();
		Back.creationTable(st);
		ConnexionFrame c = new ConnexionFrame(st);
		//c.setVisible(true);
		
		ArrayList<Employe> liste = new ArrayList<Employe>();
		
		Employe e1 = Back.getEmployer(st, "MeziereEvan");

		Employe e2 = Back.getEmployer(st, "CuzeauElias");
		
		Employe e3 = Back.getEmployer(st, "polo");
		
		
		
		liste.add(e1);
		liste.add(e2);
		liste.add(e3);
		
		
		Back.ajout_reunion(st, liste, 2,"1991-10-09", "1991-10-09", false);
		
		//Back.creationTable(st);
		/*
		
		Creneau cr = new Creneau(new java.util.Date(),new java.util.Date());
		ReunionBis2Frame r2 = new ReunionBis2Frame(st,cr);
		r2.setVisible(true);
		*/
		//Back.creationTable(st);
		
	}

}
