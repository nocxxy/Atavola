package interface_polo;


import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import Front.Fonction.Employe;
import interface_package.RoundJTextField;

public class ReunionBis3Frame extends JFrame {
	
	//Attributs
	final static int WIDTH = 320;
	final static int HEIGHT = 274;
	
	private ArrayList<JCheckBox> select; 
	private ArrayList<Employe> listEmploye;
	private ReunionBis2Frame f;
	
	//Constructeur
	public ReunionBis3Frame(Statement st,ArrayList<Employe> listEmploye,ReunionBis2Frame f) {
		this.f = f;
		this.listEmploye = listEmploye;
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola ! | Reunion3");
		ImageIcon img = new ImageIcon("src/img/italie.png");
		this.setIconImage(img.getImage());
		this.setOpacity(1);
		
		
		/*
		 * Panel Général qui va tout contenir
		 */
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 2000, 15));
		panel.setPreferredSize(new Dimension(HEIGHT,WIDTH + this.listEmploye.size()* 15 ));
		
		
		JLabel text = new JLabel("Création Réunion (3/3)");
		panel.add(text);
		
		JLabel question = new JLabel("Quels employés souhaitez vous convier ?");
		panel.add(question);
		
		
		for(int i = 0; i<this.listEmploye.size();i++) {
			String pn = this.listEmploye.get(i).getPrenom() + " " + this.listEmploye.get(i).getNom();
			JCheckBox cb = new JCheckBox(pn);
			cb.setSelected(true);
			panel.add(cb);
		}
		
		JPanel nav = new JPanel();
		nav.setLayout(new FlowLayout());
		
		GreenRoundButton suiv = new GreenRoundButton("➤","Green",75,30,30);
		GreenRoundButton prec = new GreenRoundButton("⮜","Red",75,30,30);

		suiv.addActionListener(new CreerReunionListener(st,this));
		prec.addActionListener(new PrecedentListener(f,this));

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
		
		
		
		this.getContentPane().add(scroll);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null); 

	}

	public ReunionBis2Frame getF() {
		return f;
	}

	public ArrayList<Employe> getListEmploye() {
		return listEmploye;
	}

	public ArrayList<JCheckBox> getSelect() {
		return select;
	}
}
