package interface_polo;

import java.awt.*;
import java.sql.Statement;

import javax.swing.*;

public class RetirerEmployeFrame extends JFrame {
	
	//Attributs
	final static int WIDTH = 650;
	final static int HEIGHT = 800;
	
	//Constructeur
	public RetirerEmployeFrame() {
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola ! | Retirer Employe");
		ImageIcon img = new ImageIcon("src/img/italie.png");
		this.setIconImage(img.getImage());
		this.setOpacity(1);
		
		
		
	}

}
