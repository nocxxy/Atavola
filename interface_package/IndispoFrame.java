package interface_package;

import java.awt.*;
import java.sql.Statement;

import javax.swing.*;
import interface_polo.GreenRoundButton;

public class IndispoFrame extends JFrame{
	
	//Attributs
		final static int WIDTH = 300;
		final static int HEIGHT = 450;
		
		//Constructeur
		public IndispoFrame(Statement st) {
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
			
			
			JLabel text = new JLabel("Veuillez compléter les informations suivantes : ");
			
			JLabel dateDebut = new JLabel("Date Début : ");
			
			JPanel pDateDebut = new JPanel();
			pDateDebut.setLayout(new FlowLayout());
			JTextField d1 = new JTextField();
			d1.setPreferredSize(new Dimension(55,30));
			JTextField m1 = new JTextField();
			m1.setPreferredSize(new Dimension(55,30));
			JTextField a1 = new JTextField();
			a1.setPreferredSize(new Dimension(55,30));
			
			pDateDebut.add(dateDebut);
			pDateDebut.add(d1);
			pDateDebut.add(m1);
			pDateDebut.add(a1);
			
			JLabel dateFin = new JLabel("Date Début : ");
			
			JPanel pDateFin = new JPanel();
			pDateFin.setLayout(new FlowLayout());
			JTextField d2 = new JTextField();
			d2.setPreferredSize(new Dimension(55,30));
			JTextField m2 = new JTextField();
			m2.setPreferredSize(new Dimension(55,30));
			JTextField a2 = new JTextField();
			a2.setPreferredSize(new Dimension(55,30));
			
			pDateFin.add(dateFin);
			pDateFin.add(d2);
			pDateFin.add(m2);
			pDateFin.add(a2);
			
			JLabel lMotif = new JLabel("Motif :");
			
			RoundJTextArea motif = new RoundJTextArea(200,100,15);
			motif.setPreferredSize(new Dimension(200,100));
			
			GreenRoundButton add = new GreenRoundButton("Valider","Green",175,30,30);
			GreenRoundButton cancel = new GreenRoundButton("Annuler","Red",175,30,30);
			
			panel.add(text);
			panel.add(pDateDebut);
			panel.add(pDateFin);
			panel.add(lMotif);
			panel.add(motif);
			panel.add(add);
			panel.add(cancel);
			
			this.getContentPane().add(panel,BorderLayout.CENTER);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
}}
