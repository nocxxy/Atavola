package interface_polo;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import interface_package.RoundJTextField;

public class ReunionBis1Frame extends JFrame {
	
	//Attributs
			final static int WIDTH = 300;
			final static int HEIGHT = 274;
			
			private JTextField d;
			private JTextField m;
			private JTextField a;
			private JCheckBox urgent;
			
			//Constructeur
			public ReunionBis1Frame(Statement st) {
				this.setBounds(100,100,WIDTH,HEIGHT);
				this.setTitle("A Tavola ! | Reunion1");
				ImageIcon img = new ImageIcon("src/img/italie.png");
				this.setIconImage(img.getImage());
				this.setOpacity(1);
				
				
				/*
				 * Panel Général qui va tout contenir
				 */
				JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 15));
				
				
				JLabel text = new JLabel("Création Réunion (1/3)");
				panel.add(text);
				
				this.urgent = new JCheckBox("Réunion Urgente");
				panel.add(this.urgent);
				
				JLabel question = new JLabel("Quand souhaitez vous planifier cette réunion ?");
				panel.add(question);
				
				
				JLabel date = new JLabel("Date : ");
				
				
				JPanel pDate = new JPanel();
				pDate.setLayout(new FlowLayout());
				this.d = new JTextField();
				this.d.setPreferredSize(new Dimension(55,30));
				this.m = new JTextField();
				this.m.setPreferredSize(new Dimension(55,30));
				this.a = new JTextField();
				this.a.setPreferredSize(new Dimension(55,30));
				
				pDate.add(date);
				pDate.add(this.d);
				pDate.add(this.m);
				pDate.add(this.a);
				
				
				panel.add(pDate);
				
				JPanel nav = new JPanel();
				nav.setLayout(new FlowLayout());
				
				GreenRoundButton suiv = new GreenRoundButton("➤","Green",75,30,30);
				GreenRoundButton prec = new GreenRoundButton("⮜","Red",75,30,30);
				
				nav.add(prec);
				nav.add(suiv);
				
				panel.add(nav);
				
				//suiv.addActionListener(new Reunion2Listener(this,st));
				
				this.getContentPane().add(panel,BorderLayout.CENTER);
				this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				this.setLocationRelativeTo(null); 

			}}
