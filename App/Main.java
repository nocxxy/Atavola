package App;
import java.awt.Color;

import Back.*;
import Front.Fonction.Employe;
import interface_package.*;

import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		Statement st = Back.connectionBase();
		ConnexionFrame c = new ConnexionFrame(st);
		c.setVisible(true);
		
	}

}
