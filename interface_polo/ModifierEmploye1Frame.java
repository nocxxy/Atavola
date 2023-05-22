package interface_polo;

import Back.Back;
import interface_package.AnnulerListener;
import interface_package.ConfirmerListener;
import interface_package.UpdateEmployerFrameListener;

import java.awt.*;
import java.sql.Statement;

import javax.swing.*;

public class ModifierEmploye1Frame extends JFrame {
	//Attributs
    final static int WIDTH = 350;
    final static int HEIGHT = 250;



    private JTextField nom = new JTextField();
    private JTextField prenom = new JTextField();
    private JTextField mdp = new JTextField();
    private Statement st;

	private ChoixEmployer choix;
		
		//Constructeur
		public ModifierEmploye1Frame(Statement st) {
			this.st = st;
			this.setBounds(100,100,WIDTH,HEIGHT);
			this.setTitle("A Tavola ! | Modifier Employe");
			ImageIcon img = new ImageIcon("src/img/italie.png");
			this.setIconImage(img.getImage());
			this.setOpacity(1);
			
			/*
			 * Panel Général qui va tout contenir
			 */
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 15));
			
			
			JLabel text = new JLabel("Quel employé souhaitez vous retirer ?");
			
			
			this.choix= new ChoixEmployer(st,this);
			
			JLabel n = new JLabel("Nom :");
	        JLabel p = new JLabel("Prenom :");
	        JLabel motdepasse = new JLabel("Mot de passe :");
	        GreenRoundButton confirmer = new GreenRoundButton("Confirmer","Green",150,30,30);
			GreenRoundButton annuler = new GreenRoundButton("Annuler","Red",150,30,30);

	        annuler.addActionListener(new AnnulerListener(this));
	        confirmer.addActionListener(new UpdateEmployerListener(this));
	        
	        

			panel.add(text);
			panel.add(this.choix);
			JPanel panelButton = new JPanel();
	        panelButton.add(annuler);
	        panelButton.add(confirmer);

	        panel.add(n);
	        nom.setPreferredSize(new Dimension(300,20));
	        panel.add((this.nom));
	        panel.add(p);
	        prenom.setPreferredSize(new Dimension(300,20));
	        panel.add(this.prenom);
	        panel.add(motdepasse);
	        mdp.setPreferredSize(new Dimension(300,20));
	        panel.add(this.mdp);
	        panel.add(panelButton);
	        
	        this.getContentPane().add(panel,BorderLayout.CENTER);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setLocationRelativeTo(null); 
			
			
		}

		public ChoixEmployer getChoix() {
			return choix;
		}
		
	    public JTextField getMdp() {
	        return mdp;
	    }

	    public JTextField getPrenom() {
	        return prenom;
	    }

	    public JTextField getNom() {
	        return nom;
	    }

	    public Statement getSt() {
	        return st;
	    }
	}

