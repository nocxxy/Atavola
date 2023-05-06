package interface_polo;

import java.awt.*;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.Border;

import interface_package.*;

public class PoloPopUp extends JFrame {
	
	//Attributs
	final static int WIDTH = 288;
	final static int HEIGHT = 274;
	
	//Constructeur
	public PoloPopUp(Statement st) {
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola ! | PopUp");
		ImageIcon img = new ImageIcon("src/img/italie.png");
		this.setIconImage(img.getImage());
		this.setOpacity(1);
		
		
		/*
		 * Panel Général qui va tout contenir
		 */
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 25));
		
		JLabel text = new JLabel("Que souhaitez vous rajouter ?");
		
		
		GreenRoundButton creneau = new GreenRoundButton("Créneau","Green",250,40,15);
		GreenRoundButton reunion = new GreenRoundButton("Réunion","Green",250,40,15);
		GreenRoundButton cancel = new GreenRoundButton("Annuler","Red",250,40,15);
		
		cancel.addActionListener(new AnnulerListener(this));
		reunion.addActionListener(new Reunion1Listener(this,st));
		creneau.addActionListener(new CreneauListener(this,st));
		
		panel.add(text);
		panel.add(creneau);
		panel.add(reunion);
		panel.add(cancel);
		
		this.getContentPane().add(panel,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there

		this.setUndecorated(true); 
		this.setLocationRelativeTo(null); 
		
		
		
	}

}
