package interface_polo;

import Back.Back;

import java.awt.*;
import java.sql.Statement;

import javax.swing.*;

public class RetirerEmployeFrame extends JFrame {
	
	//Attributs
	final static int WIDTH = 650;
	final static int HEIGHT = 800;
	
	//Constructeur
	public RetirerEmployeFrame(Statement st) {
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola ! | Connexion");
	}
	
}
