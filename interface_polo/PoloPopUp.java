package interface_polo;

import java.awt.*;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.Border;

import interface_package.ConnexionButton;
import interface_package.RoundedBorder;

import interface_package.RoundedBorder;

public class PoloPopUp extends JFrame {
	
	//Attributs
	final static int WIDTH = 650;
	final static int HEIGHT = 800;
	
	//Constructeur
	public PoloPopUp() {
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola ! | PopUp");
		ImageIcon img = new ImageIcon("src/img/italie.png");
		this.setIconImage(img.getImage());
		this.setOpacity(1);
		
		
		/*
		 * Panel Général qui va tout contenir
		 */
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 50));
		
		JLabel text = new JLabel("Que souhaitez vous rajouter ?");
		
		ConnexionButton creneau = new ConnexionButton("Créneau",true);
		RoundButton addCren = new RoundButton("Créneau");
		
		ConnexionButton reunion = new ConnexionButton("Réunion",true);
		
		ConnexionButton cancel = new ConnexionButton("Annuler l'ajout",true);
		
		panel.add(text);
		panel.add(addCren);
		panel.add(reunion);
		panel.add(cancel);
		
		this.getContentPane().add(panel,BorderLayout.CENTER);
		
		
		
	}

}
