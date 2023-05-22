package Tables;

import javax.swing.*;

import Front.Fonction.Employe;

import java.awt.*;
import java.sql.Statement;
import java.util.ArrayList;

public class TestTableFrame extends JFrame {
	
	//Attributs
		final static int WIDTH = 1024;
		final static int HEIGHT = 607;
		
		//Constructeur
		public TestTableFrame() {
			this.setBounds(100,100,WIDTH,HEIGHT);
			this.setTitle("A Tavola ! | Reunion3");
			ImageIcon img = new ImageIcon("src/img/italie.png");
			this.setIconImage(img.getImage());
			this.setOpacity(1);
			this.setLayout(null);
		
			/*
			TablePanel test = new TablePanel(18, Color.WHITE,1,2,"Indisponible");
			test.setBounds(10,10,380,114);
			test.setOpaque(false);
			this.getContentPane().add(test);
			
			TablePanel test1 = new TablePanel(18, Color.WHITE,2,4,"Disponible");
			test1.setBounds(400,10,380,114);
			test1.setOpaque(false);
			this.getContentPane().add(test1);
			
			TablePanel test2 = new TablePanel(18, Color.WHITE,3,8,"Reserve");
			test2.setBounds(10,150,380,114);
			test2.setOpaque(false);
			this.getContentPane().add(test2);
			*/
		}

}
