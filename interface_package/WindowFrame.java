package interface_package;

import Front.Fonction.Employe;

import java.awt.BorderLayout;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class WindowFrame extends JFrame {
	//Attributs
	final static int WIDTH = 1150;
	final static int HEIGHT = 700;
	String utilisateur;

	private Statement st;
	private Employe e;
	
	//Constructeur
	public WindowFrame(Statement st , Employe e) {
		super();
		this.st = st;
		this.e = e;
		this.utilisateur = "Polo";

		//MENU
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola !");
		this.setLocationRelativeTo(null);
		ImageIcon img = new ImageIcon("src/img/italie.png");
		this.setIconImage(img.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MenuPanel menu = new MenuPanel(e,this);
		MainContentContainer mainContent = new MainContentContainer(menu,e,st);
		menu.setMain(mainContent);
		
		this.getContentPane().add(menu, BorderLayout.WEST);
		this.getContentPane().add(mainContent,BorderLayout.CENTER);
		
	}

}
