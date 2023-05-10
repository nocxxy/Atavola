package App;
import java.sql.Date;
import java.sql.Statement;

import Back.Back;
import Front.Fonction.Creneau;
import interface_package.ConnexionFrame;
import interface_polo.ReunionBis2Frame;


public class Main {

	public static void main(String[] args) {
		
		Statement st = Back.connectionBase();
		Back.creationTable(st);
//		ConnexionFrame c = new ConnexionFrame(st);
//		c.setVisible(true);

		Creneau cr = new Creneau(new java.util.Date(),new java.util.Date());
		ReunionBis2Frame r2 = new ReunionBis2Frame(st,cr);
		r2.setVisible(true);
		
	}

}
