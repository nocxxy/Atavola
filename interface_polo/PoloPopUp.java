package interface_polo;

import java.awt.*;
import java.sql.Statement;

import javax.swing.*;

public class PoloPopUp extends JFrame {
	
	//Attributs
	final static int WIDTH = 650;
	final static int HEIGHT = 800;
	
	//Constructeur
	public PoloPopUp(Statement st) {
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola ! | Connexion");
		
	}

}
