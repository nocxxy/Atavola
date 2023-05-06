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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import java.sql.Statement;
import java.sql.Date;

import Front.Fonction.Creneau;
import Front.Fonction.Employe;
import Back.*;

public class EDTPanel extends JPanel{
	private Date debut;
	private ArrayList<Color> couleursEmp = new ArrayList<Color>();
	private Employe empConn;
	private Statement st;
	private ArrayList<Employe> allEmp = new ArrayList<Employe>();
	private ArrayList<Creneau> allCreneau = new ArrayList<Creneau>();
	
	public EDTPanel(Statement st, Employe emp) {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.st = st;
		this.empConn = emp;
		allEmp = Back.getAllEmployer(st);
		for(int i = 0; i<allEmp.size(); i++){
			System.out.println(allEmp.get(i).getId());
		}
		
		couleursEmp.add(new Color(84, 153, 73));
		couleursEmp.add(new Color(0, 64, 128));
		couleursEmp.add(new Color(128, 128, 192));
		couleursEmp.add(new Color(73, 175, 186));
		couleursEmp.add(new Color(232, 170, 26));
		couleursEmp.add(new Color(209, 84, 174));
		couleursEmp.add(new Color(157, 195, 227));
		couleursEmp.add(new Color(160, 232, 171));
		
		this.debut = Back.getLundi();
		ArrayList<String> test = Back.jourSemaineAffichage(debut);
		for(int i = 0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
		
		//this.allCreneau.add(new Creneau(db,fin,1,true,10));

		//allCreneau = Back.getAllCreneauWeek(st, debut);
		
		this.setLayout(new BorderLayout(0, 0));
		
		creePanelHaut();
		
		JPanel EmployePanel = new JPanel();
		FlowLayout fl_EmployePanel = (FlowLayout) EmployePanel.getLayout();
		fl_EmployePanel.setAlignment(FlowLayout.LEFT);
		EmployePanel.setPreferredSize(new Dimension(10, 70));
		EmployePanel.setOpaque(false);
		this.add(EmployePanel, BorderLayout.SOUTH);
		
		JPanel empHeure;
		Color empColor;
		for(int i = 0; i < allEmp.size(); i++) {
			if(i > couleursEmp.size()-1) {
				empColor = Color.GREEN;
			} else {
				empColor = couleursEmp.get(i);
			}
			empHeure = heureEmp(allEmp.get(i).getNom(),Back.minuteToHeure(Back.getHeuresEmploye(allCreneau, 1)),empColor);
			EmployePanel.add(empHeure);
		}
		
		Border grayline = BorderFactory.createLineBorder(new Color(190,190,190));
		
		JPanel EDT = new JPanel();
		EDT.setOpaque(false);
		this.add(EDT, BorderLayout.CENTER);
		GridBagLayout gbl_EDT = new GridBagLayout();
		gbl_EDT.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_EDT.rowHeights = new int[]{0, 0, 0, 0};
		gbl_EDT.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_EDT.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		EDT.setLayout(gbl_EDT);
		
		JPanel CouleurEmployesLundi_1 = new JPanel();
		CouleurEmployesLundi_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_CouleurEmployesLundi_1 = new GridBagConstraints();
		gbc_CouleurEmployesLundi_1.gridx = 1;
		gbc_CouleurEmployesLundi_1.gridy = 0;
		EDT.add(CouleurEmployesLundi_1, gbc_CouleurEmployesLundi_1);
		CouleurEmployesLundi_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		
		Label MardiPanel_1 = new Label("Lundi 3 Avril");
		MardiPanel_1.setFont(new Font("Nirmala UI", Font.BOLD, 13));
		MardiPanel_1.setBackground(Color.WHITE);
		MardiPanel_1.setAlignment(Label.CENTER);
		CouleurEmployesLundi_1.add(MardiPanel_1);
		
		Panel CouleurEmployesLundi_1_1 = new Panel();
		CouleurEmployesLundi_1_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_CouleurEmployesLundi_1_1 = new GridBagConstraints();
		gbc_CouleurEmployesLundi_1_1.gridx = 2;
		gbc_CouleurEmployesLundi_1_1.gridy = 0;
		EDT.add(CouleurEmployesLundi_1_1, gbc_CouleurEmployesLundi_1_1);
		CouleurEmployesLundi_1_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		
		Label MardiPanel_1_1 = new Label("Mardi 4 Avril");
		MardiPanel_1_1.setFont(new Font("Nirmala UI", Font.BOLD, 13));
		MardiPanel_1_1.setBackground(Color.WHITE);
		MardiPanel_1_1.setAlignment(Label.CENTER);
		CouleurEmployesLundi_1_1.add(MardiPanel_1_1);
		
		Panel CouleurEmployesLundi_1_2 = new Panel();
		CouleurEmployesLundi_1_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_CouleurEmployesLundi_1_2 = new GridBagConstraints();
		gbc_CouleurEmployesLundi_1_2.gridx = 3;
		gbc_CouleurEmployesLundi_1_2.gridy = 0;
		EDT.add(CouleurEmployesLundi_1_2, gbc_CouleurEmployesLundi_1_2);
		CouleurEmployesLundi_1_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		
		Label MardiPanel_1_2 = new Label("Mercredi 5 Avril");
		MardiPanel_1_2.setFont(new Font("Nirmala UI", Font.BOLD, 13));
		MardiPanel_1_2.setBackground(Color.WHITE);
		MardiPanel_1_2.setAlignment(Label.CENTER);
		CouleurEmployesLundi_1_2.add(MardiPanel_1_2);
		
		Panel CouleurEmployesLundi_1_3 = new Panel();
		CouleurEmployesLundi_1_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_CouleurEmployesLundi_1_3 = new GridBagConstraints();
		gbc_CouleurEmployesLundi_1_3.gridx = 4;
		gbc_CouleurEmployesLundi_1_3.gridy = 0;
		EDT.add(CouleurEmployesLundi_1_3, gbc_CouleurEmployesLundi_1_3);
		CouleurEmployesLundi_1_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		
		Label MardiPanel_1_3 = new Label("Jeudi 6 Avril");
		MardiPanel_1_3.setFont(new Font("Nirmala UI", Font.BOLD, 13));
		MardiPanel_1_3.setBackground(Color.WHITE);
		MardiPanel_1_3.setAlignment(Label.CENTER);
		CouleurEmployesLundi_1_3.add(MardiPanel_1_3);
		
		Panel CouleurEmployesLundi_1_4 = new Panel();
		CouleurEmployesLundi_1_4.setBackground(Color.WHITE);
		GridBagConstraints gbc_CouleurEmployesLundi_1_4 = new GridBagConstraints();
		gbc_CouleurEmployesLundi_1_4.gridx = 5;
		gbc_CouleurEmployesLundi_1_4.gridy = 0;
		EDT.add(CouleurEmployesLundi_1_4, gbc_CouleurEmployesLundi_1_4);
		CouleurEmployesLundi_1_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		
		Label MardiPanel_1_4 = new Label("Vendredi 7 Avril");
		MardiPanel_1_4.setFont(new Font("Nirmala UI", Font.BOLD, 13));
		MardiPanel_1_4.setBackground(Color.WHITE);
		MardiPanel_1_4.setAlignment(Label.CENTER);
		CouleurEmployesLundi_1_4.add(MardiPanel_1_4);
		
		Panel CouleurEmployesLundi_1_5 = new Panel();
		CouleurEmployesLundi_1_5.setBackground(Color.WHITE);
		GridBagConstraints gbc_CouleurEmployesLundi_1_5 = new GridBagConstraints();
		gbc_CouleurEmployesLundi_1_5.gridx = 6;
		gbc_CouleurEmployesLundi_1_5.gridy = 0;
		EDT.add(CouleurEmployesLundi_1_5, gbc_CouleurEmployesLundi_1_5);
		CouleurEmployesLundi_1_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		
		Label MardiPanel_1_5 = new Label("Samedi 8 Avril");
		MardiPanel_1_5.setFont(new Font("Nirmala UI", Font.BOLD, 13));
		MardiPanel_1_5.setBackground(Color.WHITE);
		MardiPanel_1_5.setAlignment(Label.CENTER);
		CouleurEmployesLundi_1_5.add(MardiPanel_1_5);
		
		Panel CouleurEmployesLundi_1_6 = new Panel();
		CouleurEmployesLundi_1_6.setBackground(Color.WHITE);
		GridBagConstraints gbc_CouleurEmployesLundi_1_6 = new GridBagConstraints();
		gbc_CouleurEmployesLundi_1_6.gridx = 7;
		gbc_CouleurEmployesLundi_1_6.gridy = 0;
		EDT.add(CouleurEmployesLundi_1_6, gbc_CouleurEmployesLundi_1_6);
		CouleurEmployesLundi_1_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		
		Label MardiPanel_1_6 = new Label("Dimanche 9 Avril");
		MardiPanel_1_6.setFont(new Font("Nirmala UI", Font.BOLD, 13));
		MardiPanel_1_6.setBackground(Color.WHITE);
		MardiPanel_1_6.setAlignment(Label.CENTER);
		CouleurEmployesLundi_1_6.add(MardiPanel_1_6);
		
		Panel CouleurEmployesLundi = new Panel();
		CouleurEmployesLundi.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_CouleurEmployesLundi = new GridBagConstraints();
		gbc_CouleurEmployesLundi.fill = GridBagConstraints.HORIZONTAL;
		gbc_CouleurEmployesLundi.gridx = 1;
		gbc_CouleurEmployesLundi.gridy = 1;
		EDT.add(CouleurEmployesLundi, gbc_CouleurEmployesLundi);
		CouleurEmployesLundi.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel CouleurContainer_2 = new JPanel();
		CouleurContainer_2.setBorder(grayline);
		FlowLayout fl_CouleurContainer_2 = (FlowLayout) CouleurContainer_2.getLayout();
		fl_CouleurContainer_2.setVgap(2);
		CouleurContainer_2.setBackground(new Color(255, 255, 255));
		CouleurEmployesLundi.add(CouleurContainer_2);
		
		Panel CouleurEmp = new Panel();
		CouleurEmp.setPreferredSize(new Dimension(20, 10));
		CouleurEmp.setBackground(new Color(128, 0, 0));
		CouleurContainer_2.add(CouleurEmp);
		
		JPanel CouleurContainer_3 = new JPanel();
		CouleurContainer_3.setBorder(grayline);
		FlowLayout fl_CouleurContainer_3 = (FlowLayout) CouleurContainer_3.getLayout();
		fl_CouleurContainer_3.setVgap(2);
		CouleurContainer_3.setBackground(new Color(255, 255, 255));
		CouleurEmployesLundi.add(CouleurContainer_3);
		
		Panel CouleurEmp_1 = new Panel();
		CouleurEmp_1.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_1.setBackground(new Color(0, 64, 128));
		CouleurContainer_3.add(CouleurEmp_1);
		
		JPanel CouleurContainer = new JPanel();
		CouleurContainer.setBorder(grayline);	
		FlowLayout fl_CouleurContainer = (FlowLayout) CouleurContainer.getLayout();
		fl_CouleurContainer.setVgap(2);
		CouleurContainer.setBackground(new Color(255, 255, 255));
		CouleurEmployesLundi.add(CouleurContainer);
		
		Panel CouleurEmp_2 = new Panel();
		CouleurEmp_2.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_2.setBackground(new Color(128, 128, 192));
		CouleurContainer.add(CouleurEmp_2);
		
		Panel CouleurEmployesMardi = new Panel();
		CouleurEmployesMardi.setBackground(Color.WHITE);
		GridBagConstraints gbc_CouleurEmployesMardi = new GridBagConstraints();
		gbc_CouleurEmployesMardi.fill = GridBagConstraints.HORIZONTAL;
		gbc_CouleurEmployesMardi.gridx = 2;
		gbc_CouleurEmployesMardi.gridy = 1;
		EDT.add(CouleurEmployesMardi, gbc_CouleurEmployesMardi);
		CouleurEmployesMardi.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel CouleurContainer_1 = new JPanel();
		CouleurContainer_1.setBorder(grayline);
		FlowLayout fl_CouleurContainer_1 = (FlowLayout) CouleurContainer_1.getLayout();
		fl_CouleurContainer_1.setVgap(2);
		CouleurContainer_1.setBackground(Color.WHITE);
		CouleurEmployesMardi.add(CouleurContainer_1);
		
		Panel CouleurEmp_3 = new Panel();
		CouleurEmp_3.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_3.setBackground(new Color(128, 0, 0));
		CouleurContainer_1.add(CouleurEmp_3);
		
		JPanel CouleurContainer_4 = new JPanel();
		CouleurContainer_4.setBorder(grayline);
		FlowLayout fl_CouleurContainer_4 = (FlowLayout) CouleurContainer_4.getLayout();
		fl_CouleurContainer_4.setVgap(2);
		CouleurContainer_4.setBackground(Color.WHITE);
		CouleurEmployesMardi.add(CouleurContainer_4);
		
		Panel CouleurEmp_4 = new Panel();
		CouleurEmp_4.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_4.setBackground(new Color(0, 64, 128));
		CouleurContainer_4.add(CouleurEmp_4);
		
		JPanel CouleurContainer_5 = new JPanel();
		CouleurContainer_5.setBorder(grayline);
		FlowLayout fl_CouleurContainer_5 = (FlowLayout) CouleurContainer_5.getLayout();
		fl_CouleurContainer_5.setVgap(2);
		CouleurContainer_5.setBackground(Color.WHITE);
		CouleurEmployesMardi.add(CouleurContainer_5);
		
		Panel CouleurEmp_5 = new Panel();
		CouleurEmp_5.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_5.setBackground(new Color(128, 128, 192));
		CouleurContainer_5.add(CouleurEmp_5);
		
		Panel CouleurEmployesMercredi = new Panel();
		CouleurEmployesMercredi.setBackground(Color.WHITE);
		GridBagConstraints gbc_CouleurEmployesMercredi = new GridBagConstraints();
		gbc_CouleurEmployesMercredi.fill = GridBagConstraints.HORIZONTAL;
		gbc_CouleurEmployesMercredi.gridx = 3;
		gbc_CouleurEmployesMercredi.gridy = 1;
		EDT.add(CouleurEmployesMercredi, gbc_CouleurEmployesMercredi);
		CouleurEmployesMercredi.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel CouleurContainer_6 = new JPanel();
		CouleurContainer_6.setBorder(grayline);
		FlowLayout fl_CouleurContainer_6 = (FlowLayout) CouleurContainer_6.getLayout();
		fl_CouleurContainer_6.setVgap(2);
		CouleurContainer_6.setBackground(Color.WHITE);
		CouleurEmployesMercredi.add(CouleurContainer_6);
		
		Panel CouleurEmp_6 = new Panel();
		CouleurEmp_6.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_6.setBackground(new Color(128, 0, 0));
		CouleurContainer_6.add(CouleurEmp_6);
		
		JPanel CouleurContainer_7 = new JPanel();
		CouleurContainer_7.setBorder(grayline);
		FlowLayout fl_CouleurContainer_7 = (FlowLayout) CouleurContainer_7.getLayout();
		fl_CouleurContainer_7.setVgap(2);
		CouleurContainer_7.setBackground(Color.WHITE);
		CouleurEmployesMercredi.add(CouleurContainer_7);
		
		Panel CouleurEmp_7 = new Panel();
		CouleurEmp_7.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_7.setBackground(new Color(0, 64, 128));
		CouleurContainer_7.add(CouleurEmp_7);
		
		JPanel CouleurContainer_8 = new JPanel();
		CouleurContainer_8.setBorder(grayline);
		FlowLayout fl_CouleurContainer_8 = (FlowLayout) CouleurContainer_8.getLayout();
		fl_CouleurContainer_8.setVgap(2);
		CouleurContainer_8.setBackground(Color.WHITE);
		CouleurEmployesMercredi.add(CouleurContainer_8);
		
		Panel panel_11_1_1 = new Panel();
		panel_11_1_1.setPreferredSize(new Dimension(20, 10));
		panel_11_1_1.setBackground(new Color(128, 128, 192));
		CouleurContainer_8.add(panel_11_1_1);
		
		Panel CouleurEmployesJeudi = new Panel();
		CouleurEmployesJeudi.setBackground(Color.WHITE);
		GridBagConstraints gbc_CouleurEmployesJeudi = new GridBagConstraints();
		gbc_CouleurEmployesJeudi.fill = GridBagConstraints.HORIZONTAL;
		gbc_CouleurEmployesJeudi.gridx = 4;
		gbc_CouleurEmployesJeudi.gridy = 1;
		EDT.add(CouleurEmployesJeudi, gbc_CouleurEmployesJeudi);
		CouleurEmployesJeudi.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel CouleurContainer_9 = new JPanel();
		CouleurContainer_9.setBorder(grayline);
		FlowLayout fl_CouleurContainer_9 = (FlowLayout) CouleurContainer_9.getLayout();
		fl_CouleurContainer_9.setVgap(2);
		CouleurContainer_9.setBackground(Color.WHITE);
		CouleurEmployesJeudi.add(CouleurContainer_9);
		
		Panel CouleurEmp_8 = new Panel();
		CouleurEmp_8.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_8.setBackground(new Color(128, 0, 0));
		CouleurContainer_9.add(CouleurEmp_8);
		
		JPanel CouleurContainer_10 = new JPanel();
		CouleurContainer_10.setBorder(grayline);
		FlowLayout fl_CouleurContainer_10 = (FlowLayout) CouleurContainer_10.getLayout();
		fl_CouleurContainer_10.setVgap(2);
		CouleurContainer_10.setBackground(Color.WHITE);
		CouleurEmployesJeudi.add(CouleurContainer_10);
		
		Panel CouleurEmp_9 = new Panel();
		CouleurEmp_9.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_9.setBackground(new Color(0, 64, 128));
		CouleurContainer_10.add(CouleurEmp_9);
		
		JPanel CouleurContainer_11 = new JPanel();
		CouleurContainer_11.setBorder(grayline);
		FlowLayout fl_CouleurContainer_11 = (FlowLayout) CouleurContainer_11.getLayout();
		fl_CouleurContainer_11.setVgap(2);
		CouleurContainer_11.setBackground(Color.WHITE);
		CouleurEmployesJeudi.add(CouleurContainer_11);
		
		Panel CouleurEmp_10 = new Panel();
		CouleurEmp_10.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_10.setBackground(new Color(128, 128, 192));
		CouleurContainer_11.add(CouleurEmp_10);
		
		Panel CouleurEmployesVendredi = new Panel();
		CouleurEmployesVendredi.setBackground(Color.WHITE);
		GridBagConstraints gbc_CouleurEmployesVendredi = new GridBagConstraints();
		gbc_CouleurEmployesVendredi.fill = GridBagConstraints.HORIZONTAL;
		gbc_CouleurEmployesVendredi.gridx = 5;
		gbc_CouleurEmployesVendredi.gridy = 1;
		EDT.add(CouleurEmployesVendredi, gbc_CouleurEmployesVendredi);
		CouleurEmployesVendredi.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel CouleurContainer_1_1 = new JPanel();
		CouleurContainer_1_1.setBorder(grayline);
		FlowLayout flowLayout_4 = (FlowLayout) CouleurContainer_1_1.getLayout();
		flowLayout_4.setVgap(2);
		CouleurContainer_1_1.setBackground(Color.WHITE);
		CouleurEmployesVendredi.add(CouleurContainer_1_1);
		
		Panel CouleurEmp_3_1 = new Panel();
		CouleurEmp_3_1.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_3_1.setBackground(new Color(128, 0, 0));
		CouleurContainer_1_1.add(CouleurEmp_3_1);
		
		JPanel CouleurContainer_4_1 = new JPanel();
		CouleurContainer_4_1.setBorder(grayline);
		FlowLayout flowLayout_3 = (FlowLayout) CouleurContainer_4_1.getLayout();
		flowLayout_3.setVgap(2);
		CouleurContainer_4_1.setBackground(Color.WHITE);
		CouleurEmployesVendredi.add(CouleurContainer_4_1);
		
		Panel CouleurEmp_4_1 = new Panel();
		CouleurEmp_4_1.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_4_1.setBackground(new Color(0, 64, 128));
		CouleurContainer_4_1.add(CouleurEmp_4_1);
		
		JPanel CouleurContainer_5_1 = new JPanel();
		CouleurContainer_5_1.setBorder(grayline);
		FlowLayout flowLayout_2 = (FlowLayout) CouleurContainer_5_1.getLayout();
		flowLayout_2.setVgap(2);
		CouleurContainer_5_1.setBackground(Color.WHITE);
		CouleurEmployesVendredi.add(CouleurContainer_5_1);
		
		Panel CouleurEmp_5_1 = new Panel();
		CouleurEmp_5_1.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_5_1.setBackground(new Color(128, 128, 192));
		CouleurContainer_5_1.add(CouleurEmp_5_1);
		
		Panel Employés_1_1_3 = new Panel();
		Employés_1_1_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_Employés_1_1_3 = new GridBagConstraints();
		gbc_Employés_1_1_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_Employés_1_1_3.gridx = 6;
		gbc_Employés_1_1_3.gridy = 1;
		EDT.add(Employés_1_1_3, gbc_Employés_1_1_3);
		Employés_1_1_3.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel CouleurContainer_12 = new JPanel();
		CouleurContainer_12.setBorder(grayline);
		FlowLayout fl_CouleurContainer_12 = (FlowLayout) CouleurContainer_12.getLayout();
		fl_CouleurContainer_12.setVgap(2);
		CouleurContainer_12.setBackground(Color.WHITE);
		Employés_1_1_3.add(CouleurContainer_12);
		
		Panel CouleurEmp_11 = new Panel();
		CouleurEmp_11.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_11.setBackground(new Color(128, 0, 0));
		CouleurContainer_12.add(CouleurEmp_11);
		
		JPanel CouleurContainer_13 = new JPanel();
		CouleurContainer_13.setBorder(grayline);
		FlowLayout fl_CouleurContainer_13 = (FlowLayout) CouleurContainer_13.getLayout();
		fl_CouleurContainer_13.setVgap(2);
		CouleurContainer_13.setBackground(Color.WHITE);
		Employés_1_1_3.add(CouleurContainer_13);
		
		Panel CouleurEmp_12 = new Panel();
		CouleurEmp_12.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_12.setBackground(new Color(0, 64, 128));
		CouleurContainer_13.add(CouleurEmp_12);
		
		JPanel CouleurContainer_14 = new JPanel();
		CouleurContainer_14.setBorder(grayline);
		FlowLayout fl_CouleurContainer_14 = (FlowLayout) CouleurContainer_14.getLayout();
		fl_CouleurContainer_14.setVgap(2);
		CouleurContainer_14.setBackground(Color.WHITE);
		Employés_1_1_3.add(CouleurContainer_14);
		
		Panel CouleurEmp_13 = new Panel();
		CouleurEmp_13.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_13.setBackground(new Color(128, 128, 192));
		CouleurContainer_14.add(CouleurEmp_13);
		
		Panel Employés_1_1_4 = new Panel();
		Employés_1_1_4.setBackground(Color.WHITE);
		GridBagConstraints gbc_Employés_1_1_4 = new GridBagConstraints();
		gbc_Employés_1_1_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_Employés_1_1_4.gridx = 7;
		gbc_Employés_1_1_4.gridy = 1;
		EDT.add(Employés_1_1_4, gbc_Employés_1_1_4);
		Employés_1_1_4.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel CouleurContainer_15 = new JPanel();
		CouleurContainer_15.setBorder(grayline);
		FlowLayout fl_CouleurContainer_15 = (FlowLayout) CouleurContainer_15.getLayout();
		fl_CouleurContainer_15.setVgap(2);
		CouleurContainer_15.setBackground(Color.WHITE);
		Employés_1_1_4.add(CouleurContainer_15);
		
		Panel CouleurEmp_16 = new Panel();
		CouleurEmp_16.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_16.setBackground(new Color(128, 0, 0));
		CouleurContainer_15.add(CouleurEmp_16);
		
		JPanel CouleurContainer_16 = new JPanel();
		CouleurContainer_16.setBorder(grayline);
		FlowLayout fl_CouleurContainer_16 = (FlowLayout) CouleurContainer_16.getLayout();
		fl_CouleurContainer_16.setVgap(2);
		CouleurContainer_16.setBackground(Color.WHITE);
		Employés_1_1_4.add(CouleurContainer_16);
		
		Panel CouleurEmp_15 = new Panel();
		CouleurEmp_15.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_15.setBackground(new Color(0, 64, 128));
		CouleurContainer_16.add(CouleurEmp_15);
		
		JPanel CouleurContainer_17 = new JPanel();
		CouleurContainer_17.setBorder(grayline);
		FlowLayout fl_CouleurContainer_17 = (FlowLayout) CouleurContainer_17.getLayout();
		fl_CouleurContainer_17.setVgap(2);
		CouleurContainer_17.setBackground(Color.WHITE);
		Employés_1_1_4.add(CouleurContainer_17);
		
		Panel CouleurEmp_14 = new Panel();
		CouleurEmp_14.setPreferredSize(new Dimension(20, 10));
		CouleurEmp_14.setBackground(new Color(128, 128, 192));
		CouleurContainer_17.add(CouleurEmp_14);
		
		Panel HeuresContainer = new Panel();
		HeuresContainer.setBackground(new Color(255, 255, 255));
		HeuresContainer.setPreferredSize(new Dimension(50, 10));
		GridBagConstraints gbc_HeuresContainer = new GridBagConstraints();
		gbc_HeuresContainer.fill = GridBagConstraints.VERTICAL;
		gbc_HeuresContainer.gridx = 0;
		gbc_HeuresContainer.gridy = 2;
		EDT.add(HeuresContainer, gbc_HeuresContainer);
		HeuresContainer.setLayout(new GridLayout(18, 1, 0, 0));
		
		JPanel huit = new JPanel();
		huit.setBorder(grayline);
		huit.setOpaque(false);
		huit.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		FlowLayout fl_huit = (FlowLayout) huit.getLayout();
		fl_huit.setAlignment(FlowLayout.RIGHT);
		HeuresContainer.add(huit);
		
		JLabel lblNewLabel_3 = new JLabel("8H");
		lblNewLabel_3.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit.add(lblNewLabel_3);
		
		JPanel huit_1 = new JPanel();
		huit_1.setBorder(grayline);
		FlowLayout flowLayout_5 = (FlowLayout) huit_1.getLayout();
		flowLayout_5.setAlignment(FlowLayout.RIGHT);
		huit_1.setOpaque(false);
		huit_1.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		HeuresContainer.add(huit_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("9H");
		lblNewLabel_3_1.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit_1.add(lblNewLabel_3_1);
		
		JPanel huit_2 = new JPanel();
		huit_2.setBorder(grayline);
		FlowLayout flowLayout_6 = (FlowLayout) huit_2.getLayout();
		flowLayout_6.setAlignment(FlowLayout.RIGHT);
		huit_2.setOpaque(false);
		huit_2.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		HeuresContainer.add(huit_2);
		
		JLabel lblNewLabel_3_2 = new JLabel("10H");
		lblNewLabel_3_2.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit_2.add(lblNewLabel_3_2);
		
		JPanel huit_3 = new JPanel();
		huit_3.setBorder(grayline);
		FlowLayout flowLayout_7 = (FlowLayout) huit_3.getLayout();
		flowLayout_7.setAlignment(FlowLayout.RIGHT);
		huit_3.setOpaque(false);
		huit_3.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		HeuresContainer.add(huit_3);
		
		JLabel lblNewLabel_3_3 = new JLabel("11H");
		lblNewLabel_3_3.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit_3.add(lblNewLabel_3_3);
		
		JPanel huit_4 = new JPanel();
		huit_4.setBorder(grayline);
		FlowLayout flowLayout_8 = (FlowLayout) huit_4.getLayout();
		flowLayout_8.setAlignment(FlowLayout.RIGHT);
		huit_4.setOpaque(false);
		huit_4.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		HeuresContainer.add(huit_4);
		
		JLabel lblNewLabel_3_4 = new JLabel("12H");
		lblNewLabel_3_4.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit_4.add(lblNewLabel_3_4);
		
		JPanel huit_5 = new JPanel();
		huit_5.setBorder(grayline);
		FlowLayout flowLayout_9 = (FlowLayout) huit_5.getLayout();
		flowLayout_9.setAlignment(FlowLayout.RIGHT);
		huit_5.setOpaque(false);
		huit_5.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		HeuresContainer.add(huit_5);
		
		JLabel lblNewLabel_3_5 = new JLabel("13H");
		lblNewLabel_3_5.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit_5.add(lblNewLabel_3_5);
		
		JPanel huit_6 = new JPanel();
		huit_6.setBorder(grayline);
		FlowLayout flowLayout_10 = (FlowLayout) huit_6.getLayout();
		flowLayout_10.setAlignment(FlowLayout.RIGHT);
		huit_6.setOpaque(false);
		huit_6.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		HeuresContainer.add(huit_6);
		
		JLabel lblNewLabel_3_6 = new JLabel("14H");
		lblNewLabel_3_6.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit_6.add(lblNewLabel_3_6);
		
		JPanel huit_7 = new JPanel();
		huit_7.setBorder(grayline);
		FlowLayout flowLayout_11 = (FlowLayout) huit_7.getLayout();
		flowLayout_11.setAlignment(FlowLayout.RIGHT);
		huit_7.setOpaque(false);
		huit_7.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		HeuresContainer.add(huit_7);
		
		JLabel lblNewLabel_3_7 = new JLabel("15H");
		lblNewLabel_3_7.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit_7.add(lblNewLabel_3_7);
		
		JPanel huit_8 = new JPanel();
		huit_8.setBorder(grayline);
		FlowLayout flowLayout_12 = (FlowLayout) huit_8.getLayout();
		flowLayout_12.setAlignment(FlowLayout.RIGHT);
		huit_8.setOpaque(false);
		huit_8.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		HeuresContainer.add(huit_8);
		
		JLabel lblNewLabel_3_8 = new JLabel("16H");
		lblNewLabel_3_8.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit_8.add(lblNewLabel_3_8);
		
		JPanel huit_9 = new JPanel();
		huit_9.setBorder(grayline);
		FlowLayout flowLayout_13 = (FlowLayout) huit_9.getLayout();
		flowLayout_13.setAlignment(FlowLayout.RIGHT);
		huit_9.setOpaque(false);
		huit_9.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		HeuresContainer.add(huit_9);
		
		JLabel lblNewLabel_3_9 = new JLabel("17H");
		lblNewLabel_3_9.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit_9.add(lblNewLabel_3_9);
		
		JPanel huit_10 = new JPanel();
		huit_10.setBorder(grayline);
		FlowLayout flowLayout_14 = (FlowLayout) huit_10.getLayout();
		flowLayout_14.setAlignment(FlowLayout.RIGHT);
		huit_10.setOpaque(false);
		huit_10.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		HeuresContainer.add(huit_10);
		
		JLabel lblNewLabel_3_10 = new JLabel("18H");
		lblNewLabel_3_10.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit_10.add(lblNewLabel_3_10);
		
		JPanel huit_11 = new JPanel();
		huit_11.setBorder(grayline);
		FlowLayout flowLayout_15 = (FlowLayout) huit_11.getLayout();
		flowLayout_15.setAlignment(FlowLayout.RIGHT);
		huit_11.setOpaque(false);
		huit_11.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		HeuresContainer.add(huit_11);
		
		JLabel lblNewLabel_3_11 = new JLabel("19H");
		lblNewLabel_3_11.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit_11.add(lblNewLabel_3_11);
		
		JPanel huit_12 = new JPanel();
		huit_12.setBorder(grayline);
		FlowLayout flowLayout_16 = (FlowLayout) huit_12.getLayout();
		flowLayout_16.setAlignment(FlowLayout.RIGHT);
		huit_12.setOpaque(false);
		huit_12.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		HeuresContainer.add(huit_12);
		
		JLabel lblNewLabel_3_12 = new JLabel("20H");
		lblNewLabel_3_12.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit_12.add(lblNewLabel_3_12);
		
		JPanel huit_13 = new JPanel();
		huit_13.setBorder(grayline);
		FlowLayout flowLayout_17 = (FlowLayout) huit_13.getLayout();
		flowLayout_17.setAlignment(FlowLayout.RIGHT);
		huit_13.setOpaque(false);
		huit_13.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		HeuresContainer.add(huit_13);
		
		JLabel lblNewLabel_3_13 = new JLabel("21H");
		lblNewLabel_3_13.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit_13.add(lblNewLabel_3_13);
		
		JPanel huit_14 = new JPanel();
		huit_14.setBorder(grayline);
		FlowLayout flowLayout_18 = (FlowLayout) huit_14.getLayout();
		flowLayout_18.setAlignment(FlowLayout.RIGHT);
		huit_14.setOpaque(false);
		huit_14.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		HeuresContainer.add(huit_14);
		
		JLabel lblNewLabel_3_14 = new JLabel("22H");
		lblNewLabel_3_14.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit_14.add(lblNewLabel_3_14);
		
		JPanel huit_15 = new JPanel();
		huit_15.setBorder(grayline);
		FlowLayout flowLayout_19 = (FlowLayout) huit_15.getLayout();
		flowLayout_19.setAlignment(FlowLayout.RIGHT);
		huit_15.setOpaque(false);
		huit_15.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		HeuresContainer.add(huit_15);
		
		JLabel lblNewLabel_3_15 = new JLabel("23H");
		lblNewLabel_3_15.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit_15.add(lblNewLabel_3_15);
		
		JPanel huit_16 = new JPanel();
		huit_16.setBorder(grayline);
		FlowLayout flowLayout_20 = (FlowLayout) huit_16.getLayout();
		flowLayout_20.setAlignment(FlowLayout.RIGHT);
		huit_16.setOpaque(false);
		huit_16.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		HeuresContainer.add(huit_16);
		
		JLabel lblNewLabel_3_16 = new JLabel("0H");
		lblNewLabel_3_16.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit_16.add(lblNewLabel_3_16);
		
		JPanel huit_17 = new JPanel();
		huit_17.setBorder(grayline);
		FlowLayout flowLayout_21 = (FlowLayout) huit_17.getLayout();
		flowLayout_21.setAlignment(FlowLayout.RIGHT);
		huit_17.setOpaque(false);
		huit_17.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		HeuresContainer.add(huit_17);
		
		JLabel lblNewLabel_3_17 = new JLabel("1H");
		lblNewLabel_3_17.setFont(new Font("Nirmala UI", Font.PLAIN, 11));
		huit_17.add(lblNewLabel_3_17);
		
		JPanel CreneauxContainer = new JPanel();
		CreneauxContainer.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_CreneauxContainer = new GridBagConstraints();
		gbc_CreneauxContainer.fill = GridBagConstraints.BOTH;
		gbc_CreneauxContainer.gridx = 1;
		gbc_CreneauxContainer.gridy = 2;
		EDT.add(CreneauxContainer, gbc_CreneauxContainer);
		CreneauxContainer.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel CreneauxEmp1 = new JPanel();
		CreneauxEmp1.setBorder(grayline);
		CreneauxContainer.add(CreneauxEmp1);
		CreneauxEmp1.setLayout(new BorderLayout(0, 0));
		
		CreneauCanvas creneauTest = new CreneauCanvas();
		CreneauxEmp1.add(creneauTest, BorderLayout.CENTER);
		
		JPanel CreneauxEmp2 = new JPanel();
		CreneauxEmp2.setBorder(grayline);
		CreneauxEmp2.setBackground(new Color(255, 255, 255));
		CreneauxContainer.add(CreneauxEmp2);
		CreneauxEmp2.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_1 = new Canvas();
		CreneauxEmp2.add(canvas_1, BorderLayout.CENTER);
		
		JPanel CreneauxEmp3 = new JPanel();
		CreneauxEmp3.setBorder(grayline);
		CreneauxContainer.add(CreneauxEmp3);
		CreneauxEmp3.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_2 = new Canvas();
		canvas_2.setBackground(new Color(255, 255, 255));
		CreneauxEmp3.add(canvas_2, BorderLayout.CENTER);
		
		Panel CreneauxContainer_1 = new Panel();
		CreneauxContainer_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_CreneauxContainer_1 = new GridBagConstraints();
		gbc_CreneauxContainer_1.fill = GridBagConstraints.BOTH;
		gbc_CreneauxContainer_1.gridx = 2;
		gbc_CreneauxContainer_1.gridy = 2;
		EDT.add(CreneauxContainer_1, gbc_CreneauxContainer_1);
		CreneauxContainer_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel CreneauxEmp1_1 = new JPanel();
		CreneauxEmp1_1.setBorder(grayline);
		CreneauxContainer_1.add(CreneauxEmp1_1);
		CreneauxEmp1_1.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_4 = new Canvas();
		canvas_4.setBackground(new Color(255, 255, 255));
		CreneauxEmp1_1.add(canvas_4, BorderLayout.CENTER);
		
		JPanel CreneauxEmp2_1 = new JPanel();
		CreneauxEmp2_1.setBorder(grayline);
		CreneauxContainer_1.add(CreneauxEmp2_1);
		CreneauxEmp2_1.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_3 = new Canvas();
		canvas_3.setBackground(new Color(255, 255, 255));
		CreneauxEmp2_1.add(canvas_3, BorderLayout.CENTER);
		
		JPanel CreneauxEmp3_1 = new JPanel();
		CreneauxEmp3_1.setBorder(grayline);
		CreneauxContainer_1.add(CreneauxEmp3_1);
		CreneauxEmp3_1.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_5 = new Canvas();
		canvas_5.setBackground(new Color(255, 255, 255));
		CreneauxEmp3_1.add(canvas_5, BorderLayout.CENTER);
		
		Panel CreneauxContainer_2 = new Panel();
		CreneauxContainer_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_CreneauxContainer_2 = new GridBagConstraints();
		gbc_CreneauxContainer_2.fill = GridBagConstraints.BOTH;
		gbc_CreneauxContainer_2.gridx = 3;
		gbc_CreneauxContainer_2.gridy = 2;
		EDT.add(CreneauxContainer_2, gbc_CreneauxContainer_2);
		CreneauxContainer_2.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel CreneauxEmp1_2 = new JPanel();
		CreneauxEmp1_2.setBorder(grayline);
		CreneauxContainer_2.add(CreneauxEmp1_2);
		CreneauxEmp1_2.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_6 = new Canvas();
		canvas_6.setBackground(new Color(255, 255, 255));
		CreneauxEmp1_2.add(canvas_6, BorderLayout.CENTER);
		
		JPanel CreneauxEmp2_2 = new JPanel();
		CreneauxEmp2_2.setBorder(grayline);
		CreneauxContainer_2.add(CreneauxEmp2_2);
		CreneauxEmp2_2.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_7 = new Canvas();
		canvas_7.setBackground(new Color(255, 255, 255));
		CreneauxEmp2_2.add(canvas_7, BorderLayout.CENTER);
		
		JPanel CreneauxEmp3_2 = new JPanel();
		CreneauxEmp3_2.setBorder(grayline);
		CreneauxContainer_2.add(CreneauxEmp3_2);
		CreneauxEmp3_2.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_8 = new Canvas();
		canvas_8.setBackground(new Color(255, 255, 255));
		CreneauxEmp3_2.add(canvas_8, BorderLayout.CENTER);
		
		Panel CreneauxContainer_3 = new Panel();
		CreneauxContainer_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_CreneauxContainer_3 = new GridBagConstraints();
		gbc_CreneauxContainer_3.fill = GridBagConstraints.BOTH;
		gbc_CreneauxContainer_3.gridx = 4;
		gbc_CreneauxContainer_3.gridy = 2;
		EDT.add(CreneauxContainer_3, gbc_CreneauxContainer_3);
		CreneauxContainer_3.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel CreneauxEmp1_3 = new JPanel();
		CreneauxEmp1_3.setBorder(grayline);
		CreneauxContainer_3.add(CreneauxEmp1_3);
		CreneauxEmp1_3.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_9 = new Canvas();
		canvas_9.setBackground(new Color(255, 255, 255));
		CreneauxEmp1_3.add(canvas_9, BorderLayout.CENTER);
		
		JPanel CreneauxEmp2_3 = new JPanel();
		CreneauxEmp2_3.setBorder(grayline);
		CreneauxContainer_3.add(CreneauxEmp2_3);
		CreneauxEmp2_3.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_10 = new Canvas();
		canvas_10.setBackground(new Color(255, 255, 255));
		CreneauxEmp2_3.add(canvas_10, BorderLayout.CENTER);
		
		JPanel CreneauxEmp3_3 = new JPanel();
		CreneauxEmp3_3.setBorder(grayline);
		CreneauxContainer_3.add(CreneauxEmp3_3);
		CreneauxEmp3_3.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_11 = new Canvas();
		canvas_11.setBackground(new Color(255, 255, 255));
		CreneauxEmp3_3.add(canvas_11, BorderLayout.CENTER);
		
		Panel CreneauxContainer_4 = new Panel();
		CreneauxContainer_4.setBackground(Color.WHITE);
		GridBagConstraints gbc_CreneauxContainer_4 = new GridBagConstraints();
		gbc_CreneauxContainer_4.fill = GridBagConstraints.BOTH;
		gbc_CreneauxContainer_4.gridx = 5;
		gbc_CreneauxContainer_4.gridy = 2;
		EDT.add(CreneauxContainer_4, gbc_CreneauxContainer_4);
		CreneauxContainer_4.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel CreneauxEmp1_4 = new JPanel();
		CreneauxEmp1_4.setBorder(grayline);
		CreneauxContainer_4.add(CreneauxEmp1_4);
		CreneauxEmp1_4.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_12 = new Canvas();
		canvas_12.setBackground(new Color(255, 255, 255));
		CreneauxEmp1_4.add(canvas_12, BorderLayout.CENTER);
		
		JPanel CreneauxEmp2_4 = new JPanel();
		CreneauxEmp2_4.setBorder(grayline);
		CreneauxContainer_4.add(CreneauxEmp2_4);
		CreneauxEmp2_4.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_13 = new Canvas();
		canvas_13.setBackground(new Color(255, 255, 255));
		CreneauxEmp2_4.add(canvas_13, BorderLayout.CENTER);
		
		JPanel CreneauxEmp3_4 = new JPanel();
		CreneauxEmp3_4.setBorder(grayline);
		CreneauxContainer_4.add(CreneauxEmp3_4);
		CreneauxEmp3_4.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_14 = new Canvas();
		canvas_14.setBackground(new Color(255, 255, 255));
		CreneauxEmp3_4.add(canvas_14, BorderLayout.CENTER);
		
		Panel CreneauxContainer_5 = new Panel();
		CreneauxContainer_5.setBackground(Color.WHITE);
		GridBagConstraints gbc_CreneauxContainer_5 = new GridBagConstraints();
		gbc_CreneauxContainer_5.fill = GridBagConstraints.BOTH;
		gbc_CreneauxContainer_5.gridx = 6;
		gbc_CreneauxContainer_5.gridy = 2;
		EDT.add(CreneauxContainer_5, gbc_CreneauxContainer_5);
		CreneauxContainer_5.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel CreneauxEmp1_5 = new JPanel();
		CreneauxEmp1_5.setBorder(grayline);
		CreneauxContainer_5.add(CreneauxEmp1_5);
		CreneauxEmp1_5.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_15 = new Canvas();
		canvas_15.setBackground(new Color(255, 255, 255));
		CreneauxEmp1_5.add(canvas_15, BorderLayout.CENTER);
		
		JPanel CreneauxEmp2_5 = new JPanel();
		CreneauxEmp2_5.setBorder(grayline);
		CreneauxContainer_5.add(CreneauxEmp2_5);
		CreneauxEmp2_5.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_16 = new Canvas();
		canvas_16.setBackground(new Color(255, 255, 255));
		CreneauxEmp2_5.add(canvas_16, BorderLayout.CENTER);
		
		JPanel CreneauxEmp3_5 = new JPanel();
		CreneauxEmp3_5.setBorder(grayline);
		CreneauxContainer_5.add(CreneauxEmp3_5);
		CreneauxEmp3_5.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_17 = new Canvas();
		canvas_17.setBackground(new Color(255, 255, 255));
		CreneauxEmp3_5.add(canvas_17, BorderLayout.CENTER);
		
		Panel CreneauxContainer_6 = new Panel();
		CreneauxContainer_6.setBackground(Color.WHITE);
		GridBagConstraints gbc_CreneauxContainer_6 = new GridBagConstraints();
		gbc_CreneauxContainer_6.fill = GridBagConstraints.BOTH;
		gbc_CreneauxContainer_6.gridx = 7;
		gbc_CreneauxContainer_6.gridy = 2;
		EDT.add(CreneauxContainer_6, gbc_CreneauxContainer_6);
		CreneauxContainer_6.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel CreneauxEmp1_6 = new JPanel();
		CreneauxEmp1_6.setBorder(grayline);
		CreneauxContainer_6.add(CreneauxEmp1_6);
		CreneauxEmp1_6.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_18 = new Canvas();
		canvas_18.setBackground(new Color(255, 255, 255));
		CreneauxEmp1_6.add(canvas_18, BorderLayout.CENTER);
		
		JPanel CreneauxEmp2_6 = new JPanel();
		CreneauxEmp2_6.setBorder(grayline);
		CreneauxContainer_6.add(CreneauxEmp2_6);
		CreneauxEmp2_6.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_19 = new Canvas();
		canvas_19.setBackground(new Color(255, 255, 255));
		CreneauxEmp2_6.add(canvas_19, BorderLayout.CENTER);
		
		JPanel CreneauxEmp3_6 = new JPanel();
		CreneauxEmp3_6.setBorder(grayline);
		CreneauxContainer_6.add(CreneauxEmp3_6);
		CreneauxEmp3_6.setLayout(new BorderLayout(0, 0));
		
		Canvas canvas_20 = new Canvas();
		canvas_20.setBackground(new Color(255, 255, 255));
		CreneauxEmp3_6.add(canvas_20, BorderLayout.CENTER);
		
	}
	
	private void creePanelHaut() {
		JPanel ButtonsPanel = new JPanel();
		ButtonsPanel.setPreferredSize(new Dimension(10, 35));
		ButtonsPanel.setOpaque(false);
		this.add(ButtonsPanel, BorderLayout.NORTH);
		ButtonsPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		ButtonsPanel.add(panel, BorderLayout.WEST);
		
		//COMBO BOX
		String[] s1 = new String[allEmp.size()+1];
		s1[0] = "Global";
		for(int i = 0; i < allEmp.size(); i++) {
			s1[i+1] = allEmp.get(i).getNom();
		}
		
		JComboBox comboBox = new JComboBox(s1);
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox.setPreferredSize(new Dimension(110, 20));
		panel.add(comboBox);
		
		JPanel panelBoutons = new JPanel();
		panelBoutons.setOpaque(false);
		ButtonsPanel.add(panelBoutons, BorderLayout.EAST);
		panelBoutons.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSemaine = new JPanel();
		panelSemaine.setOpaque(false);
		panelBoutons.add(panelSemaine, BorderLayout.CENTER);
		panelSemaine.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("Semaine");
		lblNewLabel_1.setFont(new Font("Nirmala UI", Font.PLAIN, 16));
		panelSemaine.add(lblNewLabel_1);
		
		JPanel panelBoutonGauche = new JPanel();
		panelBoutonGauche.setOpaque(false);
		panelBoutons.add(panelBoutonGauche, BorderLayout.WEST);
		
		JButton btnNewButton = new JButton("<");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setMargin(new Insets(0, 8, 0, 8));
		btnNewButton.setBackground(new Color(45, 106, 79));
		btnNewButton.setBorder(UIManager.getBorder("Button.border"));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 15));
		panelBoutonGauche.add(btnNewButton);
		
		JPanel panelBoutonDroit = new JPanel();
		panelBoutonDroit.setOpaque(false);
		panelBoutons.add(panelBoutonDroit, BorderLayout.EAST);
		
		JButton btnNewButton_1 = new JButton(">");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setMargin(new Insets(0, 8, 0, 8));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(new Color(45, 106, 79));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 15));
		panelBoutonDroit.add(btnNewButton_1);

	}

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
	}
	
	
	
}