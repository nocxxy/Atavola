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
		c.setVisible(true);


	}

}
