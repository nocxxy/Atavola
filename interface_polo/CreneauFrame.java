package interface_polo;
import java.awt.*;
import javax.swing.*;

import interface_package.AnnulerListener;

import java.sql.Statement;

public class CreneauFrame extends JFrame {
	
	//Attributs
	final static int WIDTH = 288;
	final static int HEIGHT = 305;
	
	//Constructeur
	public CreneauFrame(Statement st) {
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola ! | Créneau");
		ImageIcon img = new ImageIcon("src/img/italie.png");
		this.setIconImage(img.getImage());
		this.setOpacity(1);
		
		/*
		 * Panel Général qui va tout contenir
		 */
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 500, 85));
		
		JLabel text = new JLabel("Sélectionner employé : ");
		ChoixEmployer employe = new ChoixEmployer(st);
		
		
		JLabel date = new JLabel("Date : ");
		
		JPanel pDate = new JPanel();
		panel.setLayout(new FlowLayout());
		JTextField dJour = new JTextField();
		dJour.setPreferredSize(new Dimension(55,30));
		JTextField dMois = new JTextField();
		dMois.setPreferredSize(new Dimension(55,30));
		JTextField dAn = new JTextField();
		dAn.setPreferredSize(new Dimension(55,30));
		
		pDate.add(dJour);
		pDate.add(dMois);
		pDate.add(dAn);
		
		JLabel horaire = new JLabel("Horaires : ");
		
		JPanel pHour = new JPanel();
		panel.setLayout(new FlowLayout());
		JTextField dBegin = new JTextField();
		dBegin.setPreferredSize(new Dimension(50,30));
		JTextField dEnd = new JTextField();
		dEnd.setPreferredSize(new Dimension(50,30));
		
		pHour.add(dBegin);
		pHour.add(dEnd);
		
		GreenRoundButton add = new GreenRoundButton("Ajouter le créneau","Green",175,30,30);
		GreenRoundButton cancel = new GreenRoundButton("Annuler","Red",175,30,30);
		
		
		
		panel.add(text);
		panel.add(employe);
		panel.add(date);
		panel.add(pDate);
		panel.add(horaire);
		panel.add(pHour);
		
		panel.add(add);
		panel.add(cancel);
		
		cancel.addActionListener(new AnnulerListener(this));
		
		
		this.getContentPane().add(panel,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}

}
