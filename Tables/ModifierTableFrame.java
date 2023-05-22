package Tables;

import javax.swing.*;

import Back.Back;
import interface_package.AnnulerListener;
import interface_polo.CreationCreneauListener;
import interface_polo.GreenRoundButton;

import java.awt.*;
import java.sql.Statement;

public class ModifierTableFrame extends JFrame {
	
	//Attributs
			final static int WIDTH = 300;
			final static int HEIGHT = 350;

			private JTextField numTab;
			private JTextField tailleTab;
			private Table t;
			private TableGestionPanel tgp;
			
	//Constructeur
		public ModifierTableFrame(Table t, TableGestionPanel tgp) {
			this.t = t;
			this.tgp = tgp;
			this.setBounds(100,100,WIDTH,HEIGHT);
			this.setTitle("A Tavola ! | Modifier Table");
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
			
			this.numTab = new JTextField(20);
			this.numTab.setText(this.t.getNumero()+"");
			panel.add(this.numTab);
			
			JLabel table = new JLabel("Taille de la Table: ");
			panel.add(table);
			
			this.tailleTab = new JTextField(20);
			this.tailleTab.setText(this.t.getNbPlaces()+"");
			panel.add(this.tailleTab);
			
			RoundButtonV2 del = new RoundButtonV2("Supprimer la table","Red",175,30,30,15);
			del.addActionListener(new DeleteTableListener(this));
			panel.add(del);
			GreenRoundButton add = new GreenRoundButton("Modifier la table","Green",175,30,30);
			GreenRoundButton cancel = new GreenRoundButton("Annuler","Red",175,30,30);

			cancel.addActionListener(new AnnulerListener(this));
			add.addActionListener(new ModifierTableVraiListener(this));
			
			
			panel.add(add);
			panel.add(cancel);
			
			
			this.getContentPane().add(panel,BorderLayout.CENTER);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			this.setLocationRelativeTo(null); 
			
			
			

		}

	public JTextField getNumTab() {
		return numTab;
	}

	public JTextField getTailleTab() {
		return tailleTab;
	}

	public Table getT() {
		return t;
	}

	public TableGestionPanel getTgp() {
		return tgp;
	}
}
