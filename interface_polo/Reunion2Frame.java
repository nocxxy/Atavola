package interface_polo;
import java.awt.*;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

public class Reunion2Frame extends JFrame {
	
	//Attributs
	final static int WIDTH = 238;
	final static int HEIGHT = 300;
	private int nb;
	
	//Constructeur
	public Reunion2Frame(Statement st,int nb) {
		
		ArrayList<ChoixEmployer> allChoice = new ArrayList<ChoixEmployer>();
		this.nb=nb;
		
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

		for(int i=0; i<nb;i++) {
			ChoixEmployer employe= new ChoixEmployer(st);
			panel.add(employe);
			allChoice.add(employe);
			
		}
		
		JPanel nav = new JPanel();
		nav.setLayout(new FlowLayout());
		
		GreenRoundButton suiv = new GreenRoundButton("➤","Green",75,30,30);
		GreenRoundButton prec = new GreenRoundButton("⮜","Red",75,30,30);
		
		nav.add(prec);
		nav.add(suiv);
		
		prec.addActionListener(new Reunion1Listener(this,st));
		suiv.addActionListener(new Reunion3Listener(this,st));
		
		panel.add(nav);
		
		this.getContentPane().add(panel,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}

}
