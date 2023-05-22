package interface_polo;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import EDT.AjouterCreneauReunionListener;
import EDT.EDTPanel;
import interface_package.RoundJTextField;

public class ReunionBis1Frame extends JFrame {
	
	//Attributs
			final static int WIDTH = 300;
			final static int HEIGHT = 274;
			
			private JTextField d;
			private JTextField m;
			private JTextField a;
			private JCheckBox urgent;
			private EDTPanel edtpan;

	public JTextField getD() {
		return d;
	}

	public JTextField getM() {
		return m;
	}

	public JTextField getA() {
		return a;
	}

	public JCheckBox getUrgent() {
		return urgent;
	}

	//Constructeur
			public ReunionBis1Frame(Statement st,EDTPanel edtpan) {
				this.setBounds(100,100,WIDTH,HEIGHT);
				this.setTitle("A Tavola ! | Reunion1");
				ImageIcon img = new ImageIcon("src/img/italie.png");
				this.setIconImage(img.getImage());
				this.setOpacity(1);
				this.edtpan = edtpan;
				
				
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
				d.setForeground(Color.GRAY);
				d.setText("DD");
				this.d.addFocusListener(new FocusListener(){
					@Override
					public void focusGained(FocusEvent e) {
						if (d.getText().equals("DD")) {
							d.setText("");
							d.setForeground(Color.BLACK);
						}
					}
					@Override
					public void focusLost(FocusEvent e) {
						if (d.getText().isEmpty()) {
							d.setForeground(Color.GRAY);
							d.setText("DD");
						}
					}
				});
				this.m = new JTextField();
				this.m.setPreferredSize(new Dimension(55,30));
				m.setForeground(Color.GRAY);
				m.setText("MM");
				this.m.addFocusListener(new FocusListener(){
					@Override
					public void focusGained(FocusEvent e) {
						if (m.getText().equals("MM")) {
							m.setText("");
							m.setForeground(Color.BLACK);
						}
					}
					@Override
					public void focusLost(FocusEvent e) {
						if (m.getText().isEmpty()) {
							m.setForeground(Color.GRAY);
							m.setText("MM");
						}
					}
				});
				this.a = new JTextField();
				this.a.setPreferredSize(new Dimension(55,30));
				a.setForeground(Color.GRAY);
				a.setText("YYYY");
				this.a.addFocusListener(new FocusListener(){
					@Override
					public void focusGained(FocusEvent e) {
						if (a.getText().equals("YYYY")) {
							a.setText("");
							a.setForeground(Color.BLACK);
						}
					}
					@Override
					public void focusLost(FocusEvent e) {
						if (a.getText().isEmpty()) {
							a.setForeground(Color.GRAY);
							a.setText("YYYY");
						}
					}
				});
				
				pDate.add(date);
				pDate.add(this.d);
				pDate.add(this.m);
				pDate.add(this.a);
				
				panel.add(pDate);
				
				JPanel nav = new JPanel();
				nav.setLayout(new FlowLayout());
				
				GreenRoundButton suiv = new GreenRoundButton("➤","Green",75,30,30);
				GreenRoundButton prec = new GreenRoundButton("⮜","Red",75,30,30);

				suiv.addActionListener(new CreerReunion2Listener(this,st,edtpan));
				prec.addActionListener(new AjouterCreneauReunionListener(st,edtpan));
				
				nav.add(prec);
				nav.add(suiv);
				
				panel.add(nav);
				
				//suiv.addActionListener(new Reunion2Listener(this,st));
				
				this.getContentPane().add(panel,BorderLayout.CENTER);
				this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				this.setLocationRelativeTo(null); 

			}}
