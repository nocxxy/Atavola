package interface_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Statement;

import javax.swing.*;

public class ConnexionFrame extends JFrame {
	//Attributs
	final static int WIDTH = 1150;
	final static int HEIGHT = 700;
	
	//Constructeur
	public ConnexionFrame(Statement st) {
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola ! | Connexion");
		this.setOpacity(1);
		
		/*
		 * Panel Général qui va tout contenir
		 */
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#2D6A4F"));
		panel.setLayout(new GridLayout(2,1));
		
		/* 
		 * Texte au centre de la fenetre
		 * */
		JPanel brandPanel = new JPanel();
		brandPanel.setPreferredSize(new Dimension(1000, 550));
		brandPanel.setOpaque(false);
		brandPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 327, 191));
		
		JLabel brandname = new JLabel("A Tavola !");
		brandname.setForeground(Color.decode("#FFFFFF"));
		brandname.setFont(new Font("Times New Roman",Font.ITALIC,150));
		brandname.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		brandPanel.add(brandname);
		panel.add(brandPanel);
		
		
		
		/*
		 * Les zones de texte
		 */
		
		JPanel signIn = new JPanel();
		signIn.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 50));
		signIn.setBackground(Color.decode("#2D6A4F"));
		
		/*JTextField user = new JTextField();
		user.setText("Nom d'utilisateur...");
		user.setPreferredSize(new Dimension(700,50));
		user.setBorder(null);
		user.setFont(new Font("Poppins",Font.PLAIN,35));
		user.setBackground(Color.decode("#D9D9D9"));
		user.setMargin(new Insets(0, 70, 0, 0));
		signIn.add(user);
		
		
		
		JTextField password = new JTextField();
		password.setText("Mot de Passe...");
		password.setPreferredSize(new Dimension(700,50));
		password.setBorder(null);
		password.setFont(new Font("Poppins",Font.PLAIN,35));
		password.setBackground(Color.decode("#D9D9D9"));
		password.setMargin(new Insets(0, 70, 0, 0));
		signIn.add(password);*/
		
		
		panel.add(signIn);
		JButton connect2 = new JButton("Connexion");
		connect2.setFont(new Font("Poppins", Font.PLAIN, 35));
		connect2.setPreferredSize(new Dimension(200, 37));
		connect2.setMargin(new Insets(10, 50, 50, 50));
		
		JTextField test = new RoundJTextField(25);
		test.setText("Nom d'utilisateur...");
		test.setPreferredSize(new Dimension(650,50));
		test.setMargin(new Insets(0, 0, 0, 0));
		test.setFont(new Font("Poppins",Font.PLAIN,35));
		test.setBackground(Color.decode("#D9D9D9"));
		signIn.add(test);
		
		JTextField test2 = new RoundJTextField(25);
		test2.setText("Mot de Passe...");
		test2.setPreferredSize(new Dimension(650,50));
		test2.setMargin(new Insets(0, 0, 0, 0));
		test2.setFont(new Font("Poppins",Font.PLAIN,35));
		test2.setBackground(Color.decode("#D9D9D9"));
		signIn.add(test2);
		
		
		
		ConnexionButton connect = new ConnexionButton("Connexion",true);
		connect.addActionListener(new ConnexionListener(this,st));
		signIn.add(connect);
		
		
		this.getContentPane().add(panel,BorderLayout.CENTER);
		
		
	}

}
