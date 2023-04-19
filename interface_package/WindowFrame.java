package interface_package;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class WindowFrame extends JFrame {
	//Attributs
	final static int WIDTH = 1150;
	final static int HEIGHT = 700;
	String utilisateur;
	
	//Constructeur
	public WindowFrame() {
		super();
		this.utilisateur = "Polo";

		//MENU
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola !");
		ImageIcon img = new ImageIcon("src/img/italie.png");
		this.setIconImage(img.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MenuPanel menu = new MenuPanel(utilisateur);
		MainContentContainer mainContent = new MainContentContainer(menu,utilisateur);
		menu.setMain(mainContent);
		
		this.getContentPane().add(menu, BorderLayout.WEST);
		this.getContentPane().add(mainContent,BorderLayout.CENTER);
		
	}

}
