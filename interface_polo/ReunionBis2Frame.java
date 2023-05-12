package interface_polo;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.security.PrivateKey;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import Front.Fonction.Creneau;
import interface_package.RoundJTextField;

public class ReunionBis2Frame extends JFrame {
	
	//Attributs
	final static int WIDTH = 300;
	final static int HEIGHT = 274;
	private JLabel nbEmployer = new JLabel();
	private JLabel listeNomEmploye = new JLabel();

	private ChoixCreneau creneau;

	private ReunionBis1Frame f;

	public JLabel getNbEmployer() {
		return nbEmployer;
	}

	public JLabel getListeNomEmploye() {
		return listeNomEmploye;
	}

	public ReunionBis1Frame getF() {
		return f;
	}

	//Constructeur
	public ReunionBis2Frame(Statement st,Creneau c,ReunionBis1Frame f) {
		this.f = f;
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
		this.creneau = new ChoixCreneau(c,this);
		panel.add(this.creneau);

		JLabel nb = new JLabel("Nombres d'employés dispos : ");
		panel.add(nb);
		panel.add(this.nbEmployer);
		
		JLabel employe = new JLabel("Employés dispo :");
		panel.add(employe);
		panel.add(this.listeNomEmploye);

		JPanel nav = new JPanel();
		nav.setLayout(new FlowLayout());
		
		GreenRoundButton suiv = new GreenRoundButton("➤","Green",75,30,30);
		GreenRoundButton prec = new GreenRoundButton("⮜","Red",75,30,30);

		suiv.addActionListener(new CreerReunion3Listener(this,st));
		
		nav.add(prec);
		nav.add(suiv);
		
		panel.add(nav);
		
		this.getContentPane().add(panel,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null); 

	}

	public ChoixCreneau getCreneau() {
		return creneau;
	}
}
