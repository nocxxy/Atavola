package interface_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;

public class MenuPanel extends JPanel {
	//Attributs
	final static int WIDTH = 230;
	String utilisateur;
	
	//Constructeur
	public MenuPanel(String utilisateur) {
		super();
		this.utilisateur = utilisateur;
		this.setPreferredSize(new Dimension(WIDTH,10));
		this.setBackground(new Color(238,238,238));
		this.setLayout(new BorderLayout(0,0));
		
		/* 
		 * Texte en haut de la fenetre
		 * */
		JPanel brandname = new JPanel();
		brandname.setPreferredSize(new Dimension(10, 70));
		brandname.setOpaque(false);
		brandname.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 14));
		JLabel lblNewLabel = new JLabel("A Tavola !");
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setForeground(new Color(45, 106, 79));
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 38));
		brandname.add(lblNewLabel);
		
		/* 
		 * Boutons
		 * */
		JPanel buttonscontainer = new JPanel();
		buttonscontainer.setBackground(new Color(238, 238, 238));
		buttonscontainer.setOpaque(false);
		buttonscontainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		MenuButton btnTable = new MenuButton("Gestion des tables", "maison",true);
		MenuButton btnEdt = new MenuButton("Employés","utilisateur",false);
		buttonscontainer.add(btnTable);
		buttonscontainer.add(btnEdt);
		
		JPanel connection = new JPanel();
		FlowLayout flowLayout = (FlowLayout) connection.getLayout();
		flowLayout.setVgap(23);
		connection.setPreferredSize(new Dimension(10, 70));
		connection.setBackground(new Color(45, 106, 79));
		this.add(connection, BorderLayout.SOUTH);
		
		
		/*
		 * Deconnection
		 * */
		JLabel lblNewLabel_2 = new JLabel(this.utilisateur);
		lblNewLabel_2.setFocusTraversalKeysEnabled(false);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Nirmala UI", Font.PLAIN, 16));
		connection.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		ImageIcon imageIcon = (new ImageIcon("src/img/disconnect.png")); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);
		btnNewButton_1.setIcon(imageIcon);
		connection.add(btnNewButton_1);
		
		
		this.add(buttonscontainer, BorderLayout.CENTER);
		this.add(brandname, BorderLayout.NORTH);
		
		
		
	}
	
	
}