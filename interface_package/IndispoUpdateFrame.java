package interface_package;

import java.awt.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import EDT.SignalerListener;
import Front.Fonction.Creneau;

import interface_polo.GreenRoundButton;
import interface_polo.ModifierIndisponibleListener;
import interface_polo.SignalerIndisponibleListener;

public class IndispoUpdateFrame extends JFrame{
	
	//Attributs
		final static int WIDTH = 300;
		final static int HEIGHT = 450;

		private RoundJTextArea motif;

		private JTextField dd;
		private JTextField dm;
		private JTextField da;

		private JTextField hd;
		private JTextField hf;




	//Getter


	public RoundJTextArea getMotif() {
		return motif;
	}

	public JTextField getDd() {
		return dd;
	}

	public JTextField getDm() {
		return dm;
	}

	public JTextField getDa() {
		return da;
	}

	public Creneau getC() {
		return c;
	}

	public JTextField getHd() {
		return hd;
	}

	public JTextField getHf() {
		return hf;
	}

	private Creneau c;

	//Constructeur
	@SuppressWarnings("deprecation")
		public IndispoUpdateFrame(Statement st, Creneau c) {
			this.c = c;

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


			JLabel text = new JLabel("Veuillez compléter les informations suivantes : ");

			JLabel dateDebut = new JLabel("Date : ");

			JPanel pDateDebut = new JPanel();
			pDateDebut.setLayout(new FlowLayout());

			String day = getDay(c.getDateDebut().getDate());
			this.dd = new JTextField(day);
			this.dd.setPreferredSize(new Dimension(55,30));

			String month = getMonth(c.getDateDebut().getMonth());
			this.dm = new JTextField(month);
			this.dm.setPreferredSize(new Dimension(55,30));

			String year = String.valueOf(c.getDateDebut().getYear() + 1900);
			this.da = new JTextField(year);
			this.da.setPreferredSize(new Dimension(55,30));

			pDateDebut.add(dateDebut);
			pDateDebut.add(this.dd);
			pDateDebut.add(this.dm);
			pDateDebut.add(this.da);

			JLabel horaire = new JLabel("Horaires : ");

			JPanel pHour = new JPanel();
			pHour.setLayout(new FlowLayout());
			this.hd = new JTextField(heureSynthaxe(c.getDateDebut()));
			this.hd.setPreferredSize(new Dimension(50,30));
			this.hf = new JTextField(heureSynthaxe(c.getDateFin()));
			this.hf.setPreferredSize(new Dimension(50,30));

			pHour.add(horaire);
			pHour.add(this.hd);
			pHour.add(this.hf);

			JLabel lMotif = new JLabel("Motif :");

			this.motif = new RoundJTextArea(200,100,15);
			this.motif.setPreferredSize(new Dimension(200,100));

			GreenRoundButton add = new GreenRoundButton("Valider","Green",175,30,30);
			GreenRoundButton cancel = new GreenRoundButton("Annuler","Red",175,30,30);

			add.addActionListener(new ModifierIndisponibleListener(st,this));
			cancel.addActionListener(new AnnulerListener(this));

			panel.add(text);
			panel.add(pDateDebut);
			panel.add(pHour);
			panel.add(lMotif);
			panel.add(this.motif);
			panel.add(add);
			panel.add(cancel);

			this.getContentPane().add(panel,BorderLayout.CENTER);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
}
	@SuppressWarnings("deprecation")
	private String heureSynthaxe(Date a){
		System.out.println(a.toString());
		int heure = a.getHours();
		int min = a.getMinutes();


		String minute = "" + min;
		String heur = "" + heure;
		if (min<10){
			System.out.println("min inferieur a 10");
			minute = "0" + min;
			System.out.println(minute);
		}
		if(heure<10){
			System.out.println("heure inferieur a 10");
			heur = "0" + heure;
			System.out.println(heur);
		}


		return heur + "h" + minute;
	}
	@SuppressWarnings("deprecation")
	private String getDay(int d){
		if (d<10){
			return "0" + d;
		}
		return "" +d;
	}
	@SuppressWarnings("deprecation")
	private String getMonth(int m){
		if ((m+1)<10){
			return "0" + (m+1);
		}
		return "" + m;
	}




}
