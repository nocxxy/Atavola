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

public class ModifierReunion2Frame extends JFrame implements Scrollable {
	
	//Attributs
	final static int WIDTH = 350;
	final static int HEIGHT = 310;
	private JLabel nbEmployer = new JLabel();
	private JLabel listeNomEmploye = new JLabel();
	private ChoixCreneau creneau;

	public JLabel getNbEmployer() {
		return nbEmployer;
	}

	public JLabel getListeNomEmploye() {
		return listeNomEmploye;
	}

	//Constructeur
	public ModifierReunion2Frame(Statement st,Creneau c,ModifierReunion1Frame f) {
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola ! | Reunion2");
		ImageIcon img = new ImageIcon("src/img/italie.png");
		this.setIconImage(img.getImage());
		this.setOpacity(1);
		
		
		/*
		 * Panel Général qui va tout contenir
		 */
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 2000, 15));
		
		
		JLabel text = new JLabel("Modification Réunion (2/3)");
		panel.add(text);
		
		JLabel question = new JLabel("A quelle horaire souhaitez vous la planifier ?");
		panel.add(question);
		
		//JComboBox créneaux
		 this.creneau = new ChoixCreneau(c,this);
		panel.add(creneau);

		JPanel pNbEmp = new JPanel();
		JLabel nb = new JLabel("Nombres d'employés dispos : ");
		pNbEmp.add(nb);
		pNbEmp.add(this.nbEmployer);
		panel.add(pNbEmp);
		
		JPanel pEmp = new JPanel();
		JLabel employe = new JLabel("Employés dispo :");
		pEmp.add(employe);
		pEmp.add(this.listeNomEmploye);
		panel.add(pEmp);

		JPanel nav = new JPanel();
		nav.setLayout(new FlowLayout());
		
		GreenRoundButton suiv = new GreenRoundButton("➤","Green",75,30,30);
		GreenRoundButton prec = new GreenRoundButton("⮜","Red",75,30,30);

		suiv.addActionListener(new CreerReunionUpdate3Listener(st,this));
		
		nav.add(prec);
		nav.add(suiv);
		
		panel.add(nav);
		
		JPanel finale = new JPanel();
		finale.setLayout(new BorderLayout());
		finale.add(panel,BorderLayout.CENTER);
		
		JScrollPane scroll = new JScrollPane(finale);
		scroll.setPreferredSize(new Dimension(0,0));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		scroll.getViewport().addChangeListener(null);
		
		this.getContentPane().add(scroll);
		
		this.getContentPane().add(panel,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null); 

	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		// TODO Auto-generated method stub
		return false;
	}

	public ChoixCreneau getCreneau() {
		return creneau;
	}
}
