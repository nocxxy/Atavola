package interface_polo;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import interface_package.AnnulerListener;
import interface_package.RoundJTextField;

public class Reunion3Frame extends JFrame {
	
	//Attributs
		final static int WIDTH = 288;
		final static int HEIGHT = 350;

		private JTextField d;
		private JTextField m;
		private JTextField a;
		private JTextField hd;
		private JTextField hf;

		private JCheckBox urgent;

		
		//Constructeur
		public Reunion3Frame(Statement st ,Reunion2Frame f2 ) {

			this.setBounds(100,100,WIDTH,HEIGHT);
			this.setTitle("A Tavola ! | Reunion3");
			ImageIcon img = new ImageIcon("src/img/italie.png");
			this.setIconImage(img.getImage());
			this.setOpacity(1);
			
			
			/*
			 * Panel Général qui va tout contenir
			 */
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 10));
			
			
			JLabel text = new JLabel("Création Réunion (3/3)");
			panel.add(text);
			JLabel question = new JLabel("Veuillez rentrer les informations de la réunion");
			panel.add(question);
			
			
			this.urgent = new JCheckBox("Réunion Urgente");
			
			
			JLabel date = new JLabel("Date : ");
			
			
			JPanel pDate = new JPanel();
			pDate.setLayout(new FlowLayout());
			this.d = new JTextField();
			this.d.setPreferredSize(new Dimension(55,30));
			this.m = new JTextField();
			this.m.setPreferredSize(new Dimension(55,30));
			this.a = new JTextField();
			this.a.setPreferredSize(new Dimension(55,30));
			
			pDate.add(this.d);
			pDate.add(this.m);
			pDate.add(this.a);
			
			JLabel horaire = new JLabel("Horaires : ");
			
			JPanel pHour = new JPanel();
			pHour.setLayout(new FlowLayout());
			 this.hd = new JTextField();
			this.hd.setPreferredSize(new Dimension(50,30));
			this.hf = new JTextField();
			this.hf.setPreferredSize(new Dimension(50,30));
			
			pHour.add(this.hd);
			pHour.add(this.hf);
			
			JPanel nav = new JPanel();
			nav.setLayout(new FlowLayout());
			
			GreenRoundButton suiv = new GreenRoundButton("➤","Green",75,30,30);
			GreenRoundButton prec = new GreenRoundButton("⮜","Red",75,30,30);


			prec.addActionListener(new Reunion3ListenerPrec(this,f2));
			suiv.addActionListener(new CreationReunion(this,st,f2));
			
			nav.add(prec);
			nav.add(suiv);
			
			
			
			panel.add(this.urgent);
			panel.add(date);
			panel.add(pDate);
			panel.add(horaire);
			panel.add(pHour);
			
			panel.add(nav);
			
//			prec.addActionListener(new Reunion2Listener(this,st));
			
			
			this.getContentPane().add(panel,BorderLayout.CENTER);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			this.setLocationRelativeTo(null); 
			
			
		}

		public JTextField getA() {
			return a;
		}

		public JTextField getD() {
			return d;
		}

		public JTextField getHd() {
			return hd;
		}

		public JTextField getHf() {
			return hf;
		}

		public JTextField getM() {
			return m;
		}

	public JCheckBox getUrgent() {
		return urgent;
	}
}
