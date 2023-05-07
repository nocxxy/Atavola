package EDT;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import java.sql.Statement;
import java.sql.Date;

import Front.Fonction.Creneau;
import Front.Fonction.Employe;
import Back.*;
import interface_package.IndispoFrame;
import interface_polo.*;

public class EDTPanel extends JPanel{
	private Date debut;
	private ArrayList<Color> couleursEmp = new ArrayList<Color>();
	private Employe empConn;
	private Statement st;
	private ArrayList<Employe> allEmp = new ArrayList<Employe>();
	private ArrayList<Creneau> allCreneau = new ArrayList<Creneau>();
	private ArrayList<Employe> empEDT = new ArrayList<Employe>();
	
	public EDTPanel(Statement st, Employe emp) {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.st = st;
		this.empConn = emp;
		
		//liste des employes
		if(empConn.getRang().equals("chef")) {
			allEmp = Back.getAllEmployer(st);	
		} else {
			allEmp.add(empConn);
			empEDT.add(empConn);
		}
		for(int i = 0; i<allEmp.size(); i++){
			System.out.println(allEmp.get(i).getId());
		}
		
		//Couleurs par defauts
		couleursEmp.add(new Color(84, 153, 73));
		couleursEmp.add(new Color(0, 64, 128));
		couleursEmp.add(new Color(128, 128, 192));
		couleursEmp.add(new Color(73, 175, 186));
		couleursEmp.add(new Color(232, 170, 26));
		couleursEmp.add(new Color(209, 84, 174));
		couleursEmp.add(new Color(157, 195, 227));
		couleursEmp.add(new Color(160, 232, 171));
		
		this.debut = Back.getLundi();
		
		//this.allCreneau.add(new Creneau(db,fin,1,true,10));

		//allCreneau = Back.getAllCreneauWeek(st, debut);
		
		this.setLayout(new BorderLayout(0, 0));
		
		creePanelHaut();
		creeCreneauxEDT(empEDT,empConn);
		creePanelBas(empEDT);
	}
	
	private void creePanelHaut() {
		//contiendras
		JPanel ButtonsPanel = new JPanel();
		ButtonsPanel.setPreferredSize(new Dimension(10, 35));
		ButtonsPanel.setOpaque(false);
		this.add(ButtonsPanel, BorderLayout.NORTH);
		ButtonsPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		ButtonsPanel.add(panel, BorderLayout.WEST);
		
		//COMBO BOX
		if(empConn.getRang().equals("chef")) {
			String[] s1 = new String[allEmp.size()+1];
			s1[0] = "Global";
			for(int i = 0; i < allEmp.size(); i++) {
				s1[i+1] = allEmp.get(i).getNom();
			}
			
			JComboBox<String> comboBox = new JComboBox<String>(s1);
			comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			comboBox.setPreferredSize(new Dimension(110, 20));
			//Global par defaut
			empEDT = allEmp;
			
			//Actualise l'EDT quand le combobox est modifé
			comboBox.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        int selectedIndex = comboBox.getSelectedIndex();
			        if(selectedIndex == 0) {
			        	//tout les employes
			        	empEDT = allEmp; 
			        } else {
			        	//l'employe selectionné
			        	empEDT = new ArrayList<Employe>();
			        	empEDT.add(allEmp.get(selectedIndex-1));
			        }
			        creePanelBas(empEDT);
			        creeCreneauxEDT(empEDT,empConn);
			        System.out.println(empEDT.get(0).getNom());
			    }
			});

			panel.add(comboBox);
		}
		
		JPanel panelBoutons = new JPanel();
		panelBoutons.setOpaque(false);
		ButtonsPanel.add(panelBoutons, BorderLayout.EAST);
		panelBoutons.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSemaine = new JPanel();
		panelSemaine.setOpaque(false);
		panelBoutons.add(panelSemaine, BorderLayout.CENTER);
		panelSemaine.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel labelSemaine= new JLabel("Semaine");
		labelSemaine.setFont(new Font("Nirmala UI", Font.PLAIN, 16));
		panelSemaine.add(labelSemaine);
		
		JPanel panelBoutonGauche = new JPanel();
		panelBoutonGauche.setOpaque(false);
		panelBoutons.add(panelBoutonGauche, BorderLayout.WEST);
		
		JButton btnSemainePrecedente = new JButton("<");
		btnSemainePrecedente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSemainePrecedente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDebut(Back.getSemainePrecedente(getDebut()));
				allCreneau = Back.getAllCreneauWeek(st, getDebut());
				creeCreneauxEDT(empEDT,empConn);
				creePanelBas(empEDT);
			}
		});
		btnSemainePrecedente.setMargin(new Insets(0, 8, 0, 8));
		btnSemainePrecedente.setBackground(new Color(45, 106, 79));
		btnSemainePrecedente.setBorder(UIManager.getBorder("Button.border"));
		btnSemainePrecedente.setBorderPainted(false);
		btnSemainePrecedente.setFocusPainted(false);
		btnSemainePrecedente.setForeground(new Color(255, 255, 255));
		btnSemainePrecedente.setFont(new Font("Arial", Font.BOLD, 15));
		panelBoutonGauche.add(btnSemainePrecedente);
		
		JPanel panelBoutonDroit = new JPanel();
		panelBoutonDroit.setOpaque(false);
		panelBoutons.add(panelBoutonDroit, BorderLayout.EAST);
		
		JButton btnSemaineSuivante = new JButton(">");
		btnSemaineSuivante.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSemaineSuivante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDebut(Back.getSemaineSuivante(getDebut()));
				allCreneau = Back.getAllCreneauWeek(st, getDebut());
				creeCreneauxEDT(empEDT,empConn);
				creePanelBas(empEDT);
			}
		});
		btnSemaineSuivante.setFocusPainted(false);
		btnSemaineSuivante.setMargin(new Insets(0, 8, 0, 8));
		btnSemaineSuivante.setBorderPainted(false);
		btnSemaineSuivante.setBackground(new Color(45, 106, 79));
		btnSemaineSuivante.setForeground(new Color(255, 255, 255));
		btnSemaineSuivante.setFont(new Font("Arial", Font.BOLD, 15));
		panelBoutonDroit.add(btnSemaineSuivante);

	}
	
	/* Cree le panel du bas
	 * Contiendras les horaires des employés
	 * Ainsi que les boutons d'ajout de creneaux/indisponibilité
	 */
	private void creePanelBas(ArrayList<Employe> emp) {
		JPanel PanelBas = new JPanel();
		PanelBas.setLayout(new BorderLayout());
		JPanel EmployePanel = new JPanel();
		FlowLayout fl_EmployePanel = (FlowLayout) EmployePanel.getLayout();
		fl_EmployePanel.setAlignment(FlowLayout.LEFT);
		EmployePanel.setPreferredSize(new Dimension(10, 70));
		EmployePanel.setOpaque(false);
		
		JPanel empHeure;
		Color empColor;
		for(int i = 0; i < emp.size(); i++) {
			if(i > couleursEmp.size()-1) {
				empColor = Color.GREEN;
			} else {
				empColor = couleursEmp.get(i);
			}
			empHeure = heureEmp(emp.get(i).getNom(),Back.minuteToHeure(Back.getHeuresEmploye(allCreneau, 1)),empColor);
			EmployePanel.add(empHeure);
		}
		
		PanelBas.add(EmployePanel,BorderLayout.CENTER);
		JPanel BouttonContainer = new JPanel();
		//JButton BouttonPanelBas = new JButton();
		GreenRoundButton  BouttonPanelBas= new GreenRoundButton("", "Green", 150, 90, 5); 
		BouttonContainer.setLayout(new FlowLayout());
		if(empConn.getRang().equals("chef")) {
			//POUR CHEF
			BouttonPanelBas.setText("Ajouter creneau");
			BouttonPanelBas.addActionListener(new AjouterCreneauReunionListener(this.st));
		} else {
			//POUR EMPLOYE
			BouttonPanelBas.setWidth(200);
			BouttonPanelBas.setText("Signaler indisponibilité");
			BouttonPanelBas.addActionListener(new SignalerListener(this.st,empConn));
		}
		BouttonContainer.add(BouttonPanelBas);
		PanelBas.add(BouttonPanelBas, BorderLayout.EAST);
		BorderLayout layout = (BorderLayout) this.getLayout();
		if(layout.getLayoutComponent(BorderLayout.SOUTH) != null) {
			this.remove(layout.getLayoutComponent(BorderLayout.SOUTH));
		}
		this.add(PanelBas, BorderLayout.SOUTH);
	};
	

	private JPanel heureEmp(String nom, String heure, Color color) {
		JPanel emp = new JPanel();
		emp.setOpaque(false);
	
		JPanel colorPanel = new JPanel();
		colorPanel.setPreferredSize(new Dimension(20, 20));
		colorPanel.setBackground(color);
		emp.add(colorPanel);
		
		JLabel nomHeures = new JLabel(nom+" ("+heure+")");
		nomHeures.setFont(new Font("Nirmala UI", Font.BOLD, 14));
		emp.add(nomHeures);
		return emp;
	};
	
	/*Affiche l'emploi du temps
	 * En fonction de la liste d'employe en entrée
	 */
	private void creeCreneauxEDT(ArrayList<Employe> emp, Employe empConn) {
		Border grayline = BorderFactory.createLineBorder(new Color(190,190,190));
	
		JPanel EDT = new JPanel();
		EDT.setBounds(new Rectangle(0, 0, 100, 0));
		BorderLayout layout = (BorderLayout) this.getLayout();
		if(layout.getLayoutComponent(BorderLayout.CENTER) != null) {
			this.remove(layout.getLayoutComponent(BorderLayout.CENTER));
		}
		this.add(EDT, BorderLayout.CENTER);
		EDT.setLayout(new BorderLayout(0, 0));
		
		JPanel Heures = new JPanel();
		Heures.setPreferredSize(new Dimension(40, 10));
		EDT.add(Heures, BorderLayout.WEST);
		Heures.setLayout(new BorderLayout(0, 0));
		
		JPanel OffsetPanel = new JPanel();
		OffsetPanel.setPreferredSize(new Dimension(10, 30));
		Heures.add(OffsetPanel, BorderLayout.NORTH);
		OffsetPanel.setBackground(new Color(245,245,245));
		
		
		JPanel HeuresContainer = new JPanel();
		HeuresContainer.setBackground(new Color(255, 255, 255));
		Heures.add(HeuresContainer, BorderLayout.CENTER);
		HeuresContainer.setLayout(new GridLayout(3, 0, 0, 0));
		
		JLabel heureLabel;
		JPanel triplePanelHeure;
		for(int i = 0; i<3; i++) {
			triplePanelHeure = new JPanel();
			triplePanelHeure.setLayout(new GridLayout(6,0,0,0));
			triplePanelHeure.setBackground(new Color(255,255,255));
			for(int j=0; j<6;j++) {
				heureLabel = new JLabel(7+((i*6)+j)+"H",SwingConstants.RIGHT);
				heureLabel.setBorder(grayline);
				triplePanelHeure.add(heureLabel);
			}
			HeuresContainer.add(triplePanelHeure);
		}
		
		JPanel semainePanel = new JPanel();
		semainePanel.setBackground(new Color(255, 255, 255));
		EDT.add(semainePanel, BorderLayout.CENTER);
		semainePanel.setLayout(new GridLayout(0, 7, 0, 0));
		
		JPanel jourPanel;
		JPanel topJourPanel;
		JLabel jourLabel;
		JPanel colorEmpContainer;
		JPanel colorContainer;
		JPanel couleur;
		JPanel creneauPanel;
		CreneauCanvas creneauCanvas;
		for(int i = 0; i<7; i++) {
			//PANEL JOUR
			jourPanel = new JPanel();
			jourPanel.setBackground(new Color(255, 255, 255));
			semainePanel.add(jourPanel);
			jourPanel.setLayout(new BorderLayout(0, 0));
			
			//CONTENEUR CRENEAUX
			creneauPanel = new JPanel();
			creneauPanel.setLayout(new GridLayout(0, empEDT.size(), 0, 0));
			jourPanel.add(creneauPanel, BorderLayout.CENTER);
			
			//HAUT DU JOUR
			topJourPanel = new JPanel();
			topJourPanel.setPreferredSize(new Dimension(10, 30));
			jourPanel.add(topJourPanel, BorderLayout.NORTH);
			topJourPanel.setLayout(new BorderLayout(0, 0));
			
			ArrayList<String> joursAffichage = Back.jourSemaineAffichage(debut);
			
			//JOUR
			jourLabel = new JLabel(joursAffichage.get(i), SwingConstants.CENTER);
			jourLabel.setOpaque(true);
			jourLabel.setBackground(new Color(255, 255, 255));
			jourLabel.setBorder(grayline);
			topJourPanel.add(jourLabel, BorderLayout.CENTER);
			
			
			//COULEURS
			colorEmpContainer = new JPanel();
			colorEmpContainer.setBackground(new Color(255, 255, 255));
			colorEmpContainer.setPreferredSize(new Dimension(10, 13));
			topJourPanel.add(colorEmpContainer, BorderLayout.SOUTH);
			colorEmpContainer.setLayout(new GridLayout(0, empEDT.size(), 0, 0));
			
			for(int j=0; j<empEDT.size(); j++) {
				//CONTAINER
				colorContainer = new JPanel();
				colorContainer.setBorder(grayline);
				colorContainer.setBackground(new Color(255, 255, 255));
				colorEmpContainer.add(colorContainer);
				colorContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 1));
				
				//COULEUR
				couleur = new JPanel();
				couleur.setPreferredSize(new Dimension(13, 9));
				couleur.setBackground(couleursEmp.get(j));
				colorContainer.add(couleur);
				
				//CRENEAU
				creneauCanvas = new CreneauCanvas(couleursEmp.get(j),Back.getCreneauxEmp(st, debut, empEDT.get(j).getId()),empConn);
				creneauPanel.add(creneauCanvas);

			}
		}
		
		}
	
		public void setDebut(Date debut) {
			this.debut = debut;
		}
	
		public Date getDebut() {
			return this.debut;
		}
	
}