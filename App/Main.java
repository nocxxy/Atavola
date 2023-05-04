package App;
import java.awt.Color;

import Back.*;
import Front.Fonction.Employe;
import interface_package.*;
import interface_polo.*;

import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		
		Statement st = Back.connectionBase();
		ConnexionFrame c = new ConnexionFrame(st);
		PoloPopUp p = new PoloPopUp();
		c.setVisible(true);
		p.setVisible(true);
		Back.retireEmploye(st, 1);
		
	}

}
