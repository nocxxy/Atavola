package App;
import java.sql.Statement;
import java.util.ArrayList;

import Back.Back;
import Front.Fonction.Employe;

public class Main {

	public static void main(String[] args) {
		
		Statement st = Back.connectionBase();
		//ConnexionFrame c = new ConnexionFrame(st);
		//RetirerEmployeFrame e = new RetirerEmployeFrame();
		//PoloPopUp p = new PoloPopUp();
		//c.setVisible(true);
		//e.setVisible(true);
		//p.setVisible(true);
		//Back.retireEmploye(st, 1);
		//boolean res = Back.estIndisponible(st,1, "2023-10-02", "2023-10-02");
		
		
		Employe e1 = Back.getEmployer(st, "t");
		Employe e2 = Back.getEmployer(st, "c");
		Employe e3 = Back.getEmployer(st, "d");
		Employe e4 = Back.getEmployer(st, "g");
		
		ArrayList <Employe> liste = new ArrayList();
		liste.add(e1);
		liste.add(e2);
		liste.add(e3);
		liste.add(e4);
		
		
		//Back.ajoutTravail(st, liste, "2023-10-02", "2023-10-02");
	}

}
