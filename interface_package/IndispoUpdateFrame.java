package interface_package;

import java.awt.*;
import java.sql.Statement;

import javax.swing.*;

import EDT.SignalerListener;
import Front.Fonction.Employe;
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
		private JTextField fd;
		private JTextField fm;
		private JTextField fa;

		private  Employe e;


	//Getter

	public Employe getE() {
		return e;
	}

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

	public JTextField getFd() {
		return fd;
	}

	public JTextField getFm() {
		return fm;
	}

	public JTextField getFa() {
		return fa;
	}

	//Constructeur
		public IndispoUpdateFrame(Statement st, Employe e) {
			this.e = e;

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
			
			
			JLabel text = new JLabel("Veuillez modifier les informations suivantes : ");
			
			JLabel dateDebut = new JLabel("Date Début : ");
			
			JPanel pDateDebut = new JPanel();
			pDateDebut.setLayout(new FlowLayout());
			this.dd = new JTextField();
			this.dd.setPreferredSize(new Dimension(55,30));
			this.dm = new JTextField();
			this.dm.setPreferredSize(new Dimension(55,30));
			this.da = new JTextField();
			this.da.setPreferredSize(new Dimension(55,30));
			
			pDateDebut.add(dateDebut);
			pDateDebut.add(this.dd);
			pDateDebut.add(this.dm);
			pDateDebut.add(this.da);
			
			JLabel dateFin = new JLabel("Date Fin : ");
			
			JPanel pDateFin = new JPanel();
			pDateFin.setLayout(new FlowLayout());
			this.fd = new JTextField();
			this.fd.setPreferredSize(new Dimension(55,30));
			this.fm = new JTextField();
			this.fm.setPreferredSize(new Dimension(55,30));
			this.fa = new JTextField();
			this.fa.setPreferredSize(new Dimension(55,30));
			
			pDateFin.add(dateFin);
			pDateFin.add(this.fd);
			pDateFin.add(this.fm);
			pDateFin.add(this.fa);
			
			JLabel lMotif = new JLabel("Motif :");
			
			this.motif = new RoundJTextArea(200,100,15);
			this.motif.setPreferredSize(new Dimension(200,100));
			
			GreenRoundButton add = new GreenRoundButton("Valider","Green",175,30,30);
			GreenRoundButton cancel = new GreenRoundButton("Annuler","Red",175,30,30);

			add.addActionListener(new ModifierIndisponibleListener(st,this));
			cancel.addActionListener(new AnnulerListener(this));

			panel.add(text);
			panel.add(pDateDebut);
			panel.add(pDateFin);
			panel.add(lMotif);
			panel.add(this.motif);
			panel.add(add);
			panel.add(cancel);
			
			this.getContentPane().add(panel,BorderLayout.CENTER);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
}




}