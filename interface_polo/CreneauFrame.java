package interface_polo;
import java.awt.*;
import javax.swing.*;

public class CreneauFrame extends JFrame {
	
	//Attributs
	final static int WIDTH = 288;
	final static int HEIGHT = 305;
	
	//Constructeur
	public CreneauFrame() {
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola ! | Créneau");
		ImageIcon img = new ImageIcon("src/img/italie.png");
		this.setIconImage(img.getImage());
		this.setOpacity(1);
		
		/*
		 * Panel Général qui va tout contenir
		 */
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 25));
		
		JLabel text = new JLabel("Sélectionner employé : ");
		//ChoixEmployer employe = new ChoixEmployer();
		//panel.add(employe);
		
		JLabel date = new JLabel("Date : ");
		
		
		
		panel.add(text);
		panel.add(date);
		
		
		this.getContentPane().add(panel,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

}
