package interface_polo;

import Back.Back;
import Front.Fonction.Creneau;
import interface_package.AnnulerListener;

import java.awt.*;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

public class RetirerCreneauFrame extends JFrame {
	
	//Attributs
	final static int WIDTH = 350;
	final static int HEIGHT = 280;

	private ChoixCreneau choix;
	private ArrayList<Creneau> creneau;
	
	//Constructeur
	public RetirerCreneauFrame(Statement st,ArrayList<Creneau> c) {
		this.creneau = c;
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola ! | Modifications Créneau");
		ImageIcon img = new ImageIcon("src/img/italie.png");
		this.setIconImage(img.getImage());
		this.setOpacity(1);
		
		/*
		 * Panel Général qui va tout contenir
		 */
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 15));
		
		
		JLabel text = new JLabel("Choisissez le créneau à traiter : ");
		
		
		this.choix= new ChoixCreneau(this.creneau);

		GreenRoundButton del = new GreenRoundButton("Supprimer","Green",175,30,30);
		GreenRoundButton cancel = new GreenRoundButton("Annuler","Red",175,30,30);
		GreenRoundButton modifier = new GreenRoundButton("Modifier","Green",175,30,30);

		cancel.addActionListener(new AnnulerListener(this));
		del.addActionListener(new RetirerCreneauListener(this,st));
		modifier.addActionListener(new TransiUpdateListener(st,this));

		panel.add(text);
		panel.add(this.choix);
		panel.add(del);
		panel.add(modifier);
		panel.add(cancel);
		
		this.getContentPane().add(panel,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null); 
		
		
	}

	public ChoixCreneau getChoix() {
		return choix;
	}
}
