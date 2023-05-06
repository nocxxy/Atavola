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
		final static int HEIGHT = 274;
		
		//Constructeur
		public Reunion3Frame(Statement st) {
			this.setBounds(100,100,WIDTH,HEIGHT);
			this.setTitle("A Tavola ! | Reunion3");
			ImageIcon img = new ImageIcon("src/img/italie.png");
			this.setIconImage(img.getImage());
			this.setOpacity(1);
			
			
			/*
			 * Panel Général qui va tout contenir
			 */
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 2000, 150));
			
			
			JLabel text = new JLabel("Création Réunion (3/3)");
			panel.add(text);
			JLabel question = new JLabel("Veuillez rentrer les informations de la réunion");
			panel.add(question);
			
			JLabel date = new JLabel("Date : ");
			
			JPanel pDate = new JPanel();
			panel.setLayout(new FlowLayout());
			JTextField dJour = new JTextField();
			dJour.setPreferredSize(new Dimension(55,30));
			JTextField dMois = new JTextField();
			dMois.setPreferredSize(new Dimension(55,30));
			JTextField dAn = new JTextField();
			dAn.setPreferredSize(new Dimension(55,30));
			
			pDate.add(dJour);
			pDate.add(dMois);
			pDate.add(dAn);
			
			JLabel horaire = new JLabel("Horaires : ");
			
			JPanel pHour = new JPanel();
			panel.setLayout(new FlowLayout());
			JTextField dBegin = new JTextField();
			dBegin.setPreferredSize(new Dimension(50,30));
			JTextField dEnd = new JTextField();
			dEnd.setPreferredSize(new Dimension(50,30));
			
			pHour.add(dBegin);
			pHour.add(dEnd);
			
			JPanel nav = new JPanel();
			nav.setLayout(new FlowLayout());
			
			GreenRoundButton suiv = new GreenRoundButton("➤","Green",75,30,30);
			GreenRoundButton prec = new GreenRoundButton("⮜","Red",75,30,30);
			
			nav.add(prec);
			nav.add(suiv);
			
			
			
			
			panel.add(date);
			panel.add(pDate);
			panel.add(horaire);
			panel.add(pHour);
			
			panel.add(nav);
			
			prec.addActionListener(new Reunion2Listener(this,st));
			
			
			this.getContentPane().add(panel,BorderLayout.CENTER);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			
			
		}
}
