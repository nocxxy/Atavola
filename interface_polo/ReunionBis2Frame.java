package interface_polo;


import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import Front.Fonction.Creneau;
import interface_package.RoundJTextField;

public class ReunionBis2Frame extends JFrame {
	
	//Attributs
	final static int WIDTH = 300;
	final static int HEIGHT = 274;
	
	//Constructeur
	public ReunionBis2Frame(Statement st, Creneau c) {
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
		
		//JComboBox créneaux
		ChoixCreneau creneau = new ChoixCreneau(c);
		panel.add(creneau);

		JLabel nb = new JLabel("Nombres d'employés dispos : ");
		
		JLabel employe = new JLabel("Employés dispo");
		//boucle for
		
		JPanel nav = new JPanel();
		nav.setLayout(new FlowLayout());
		
		GreenRoundButton suiv = new GreenRoundButton("➤","Green",75,30,30);
		GreenRoundButton prec = new GreenRoundButton("⮜","Red",75,30,30);
		
		nav.add(prec);
		nav.add(suiv);
		
		panel.add(nav);
		
		this.getContentPane().add(panel,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null); 

	}}
