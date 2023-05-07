package interface_polo;

import Back.Back;
import interface_package.AnnulerListener;

import java.awt.*;
import java.sql.Statement;

import javax.swing.*;

public class RetirerCreneauFrame extends JFrame {
	
	//Attributs
	final static int WIDTH = 280;
	final static int HEIGHT = 250;

	private ChoixCreneau choix;
	
	//Constructeur
	public RetirerCreneauFrame(Statement st) {
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
		
		
		JLabel text = new JLabel("Choisissez le créneau à supprimer : ");
		
		
		//this.choix= new ChoixCreneau(st);
		
		GreenRoundButton del = new GreenRoundButton("Supprimer","Green",175,30,30);
		GreenRoundButton cancel = new GreenRoundButton("Annuler","Red",175,30,30);

		cancel.addActionListener(new AnnulerListener(this));
		//del.addActionListener(new RetirerListener(this,st));

		panel.add(text);
		//panel.add(this.choix);
		panel.add(del);
		panel.add(cancel);
		
		this.getContentPane().add(panel,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}

	public ChoixCreneau getChoix() {
		return choix;
	}
}
