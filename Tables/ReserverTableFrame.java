package Tables;

import javax.swing.*;

import interface_package.AnnulerListener;
import interface_polo.CreationCreneauListener;
import interface_polo.GreenRoundButton;

import java.awt.*;
import java.sql.Statement;

public class ReserverTableFrame extends JFrame {
	
	//Attributs
			final static int WIDTH = 300;
			final static int HEIGHT = 220;

			private JTextField resTable;
			private Table table;
			private TableGestionPanel tgp;
			
	//Constructeur
		public ReserverTableFrame(Statement st,Table table,TableGestionPanel tgp) {
			this.table = table;
			this.tgp = tgp;
			this.setBounds(100,100,WIDTH,HEIGHT);
			this.setTitle("A Tavola ! | Reservation Table");
			ImageIcon img = new ImageIcon("src/img/italie.png");
			this.setIconImage(img.getImage());
			this.setOpacity(1);
			
			/*
			 * Panel Général qui va tout contenir
			 */
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20000, 20));
			
			JLabel text = new JLabel("Nom de la réservation : ");
			panel.add(text);
			
			this.resTable = new JTextField(20);
			panel.add(this.resTable);
			
			
			GreenRoundButton add = new GreenRoundButton("Réserver la table","Green",175,30,30);
			GreenRoundButton cancel = new GreenRoundButton("Annuler","Red",175,30,30);

			cancel.addActionListener(new AnnulerListener(this));
			add.addActionListener(new CreationReservationListener(this));
			
			panel.add(add);
			panel.add(cancel);
			
			
			this.getContentPane().add(panel,BorderLayout.CENTER);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			this.setUndecorated(true); //Suppression de la barre supérieure
			this.setLocationRelativeTo(null); 
			
			
			

		}

	public Table getTable() {
		return table;
	}

	public JTextField getResTable() {
		return resTable;
	}

	public TableGestionPanel getTgp() {
		return tgp;
	}
}
