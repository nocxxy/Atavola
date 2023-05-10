package interface_polo;


import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import interface_package.RoundJTextField;

public class ReunionBis2Frame extends JFrame {
	
	//Attributs
	final static int WIDTH = 300;
	final static int HEIGHT = 274;
	
	//Constructeur
	public ReunionBis2Frame(Statement st) {
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola ! | Reunion2");
		ImageIcon img = new ImageIcon("src/img/italie.png");
		this.setIconImage(img.getImage());
		this.setOpacity(1);
		
		
		/*
		 * Panel Général qui va tout contenir
		 */
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 15));
		
		
		JLabel text = new JLabel("Création Réunion (2/3)");
		panel.add(text);
		
		JLabel question = new JLabel("A quelle horaire souhaitez vous la planifier ?");
		panel.add(question);
		
		
		this.getContentPane().add(panel,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null); 

	}}
