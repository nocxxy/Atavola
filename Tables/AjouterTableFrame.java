package Tables;

import javax.swing.*;

import interface_package.AnnulerListener;
import interface_polo.CreationCreneauListener;
import interface_polo.GreenRoundButton;

import java.awt.*;
import java.sql.Statement;

public class AjouterTableFrame extends JFrame {
	
	//Attributs
			final static int WIDTH = 300;
			final static int HEIGHT = 320;

			private JTextField numTable;
			private JTextField nbPlace;
			
	//Constructeur
		public AjouterTableFrame(Statement st) {
			this.setBounds(100,100,WIDTH,HEIGHT);
			this.setTitle("A Tavola ! | Ajouter Table");
			ImageIcon img = new ImageIcon("src/img/italie.png");
			this.setIconImage(img.getImage());
			this.setOpacity(1);
			
			/*
			 * Panel Général qui va tout contenir
			 */
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20000, 20));
			
			JLabel text = new JLabel("Numéro de la Table: ");
			panel.add(text);
			
			this.numTable = new JTextField(20);
			panel.add(this.numTable);
			
			JLabel table = new JLabel("Taille de la Table: ");
			panel.add(table);
			
			this.nbPlace = new JTextField(20);
			panel.add(this.nbPlace);
			
			GreenRoundButton add = new GreenRoundButton("Ajouter la table","Green",175,30,30);
			GreenRoundButton cancel = new GreenRoundButton("Annuler","Red",175,30,30);

			cancel.addActionListener(new AnnulerListener(this));
			add.addActionListener(new CreationTableListener(this));
			
			panel.add(add);
			panel.add(cancel);
			
			
			this.getContentPane().add(panel,BorderLayout.CENTER);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			this.setLocationRelativeTo(null); 
			
			
			

		}

	public JTextField getNbPlace() {
		return nbPlace;
	}

	public JTextField getNumTable() {
		return numTable;
	}
}
