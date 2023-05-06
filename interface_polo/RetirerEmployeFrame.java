package interface_polo;

import Back.Back;

import java.awt.*;
import java.sql.Statement;

import javax.swing.*;

public class RetirerEmployeFrame extends JFrame {
	
	//Attributs
	final static int WIDTH = 280;
	final static int HEIGHT = 250;
	
	//Constructeur
	public RetirerEmployeFrame(Statement st) {
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola ! | Retirer Employe");
		ImageIcon img = new ImageIcon("src/img/italie.png");
		this.setIconImage(img.getImage());
		this.setOpacity(1);
		
		/*
		 * Panel Général qui va tout contenir
		 */
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 15));
		
		
		JLabel text = new JLabel("Quel employé souhaitez vous retirer ?");
		
		
		ChoixEmployer employe= new ChoixEmployer(st);
		
		GreenRoundButton del = new GreenRoundButton("Retirer l'employé","Green",175,30,30);
		GreenRoundButton cancel = new GreenRoundButton("Annuler","Red",175,30,30);
		
		panel.add(text);
		panel.add(employe);
		panel.add(del);
		panel.add(cancel);
		
		this.getContentPane().add(panel,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
	
}
