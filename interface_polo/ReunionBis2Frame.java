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

import EDT.EDTPanel;
import Front.Fonction.Creneau;
import interface_package.RoundJTextField;

public class ReunionBis2Frame extends JFrame {
	
	//Attributs
	final static int WIDTH = 500;
	final static int HEIGHT = 350;
	private JLabel nbEmployer = new JLabel();
	private JLabel listeNomEmploye = new JLabel();

	private ChoixCreneau creneau;

	private ReunionBis1Frame f;
	
	private JScrollPane scroll;
	
	private JPanel panel;
	
	private int nombre=0;
	private EDTPanel edtpan;

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
	public ReunionBis2Frame(Statement st,Creneau c,ReunionBis1Frame f, EDTPanel edtpan) {
		this.f = f;
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola ! | Reunion2");
		ImageIcon img = new ImageIcon("src/img/italie.png");
		this.setIconImage(img.getImage());
		this.setOpacity(1);
		this.edtpan = edtpan;
		
		
		/*
		 * Panel Général qui va tout contenir
		 */
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 15));
		panel.setPreferredSize(new Dimension(HEIGHT,WIDTH + nombre* 5 ));
		
		
		JLabel text = new JLabel("Création Réunion (2/3)");
		panel.add(text);
		
		JLabel question = new JLabel("A quelle horaire souhaitez vous la planifier ?");
		panel.add(question);
		
		//JComboBox créneaux
		this.creneau = new ChoixCreneau(c,this);
		panel.add(this.creneau);
		
		JPanel nbEmp = new JPanel();
		JLabel nb = new JLabel("Nombres d'employés dispos : ");
		nbEmp.add(nb);
		nbEmp.add(this.nbEmployer);
		panel.add(nbEmp);
		
		JPanel emp = new JPanel();
		JLabel employe = new JLabel("Employés dispo :");
		emp.add(employe);
		emp.add(this.listeNomEmploye);
		panel.add(emp);

		JPanel nav = new JPanel();
		nav.setLayout(new FlowLayout());
		
		GreenRoundButton suiv = new GreenRoundButton("➤","Green",75,30,30);
		GreenRoundButton prec = new GreenRoundButton("⮜","Red",75,30,30);

		suiv.addActionListener(new CreerReunion3Listener(this,st,edtpan));
		prec.addActionListener(new PrecedentListener(f,this));


		nav.add(prec);
		nav.add(suiv);
		
		panel.add(nav);
		
		JPanel finale = new JPanel();
		finale.setLayout(new BorderLayout());
		finale.add(panel,BorderLayout.CENTER);
		
		scroll = new JScrollPane(finale);
		scroll.revalidate();
		scroll.setPreferredSize(panel.getPreferredSize());
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		
		this.getContentPane().add(scroll);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null); 

	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public ChoixCreneau getCreneau() {
		return creneau;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
}
