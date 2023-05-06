package interface_polo;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import interface_package.RoundJTextField;

public class Reunion1Frame extends JFrame {
	
	//Attributs
		final static int WIDTH = 288;
		final static int HEIGHT = 274;
		
		private JTextField nb ;
		
		//Constructeur
		public Reunion1Frame(Statement st) {
			this.setBounds(100,100,WIDTH,HEIGHT);
			this.setTitle("A Tavola ! | Reunion1");
			ImageIcon img = new ImageIcon("src/img/italie.png");
			this.setIconImage(img.getImage());
			this.setOpacity(1);
			
			
			/*
			 * Panel Général qui va tout contenir
			 */
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 15));
			
			
			JLabel text = new JLabel("Création Réunion (1/3)");
			panel.add(text);
			
			JLabel question = new JLabel("Combien d'employés souhaitez vous convier ?");
			panel.add(question);
			
			JTextField number = new RoundJTextField(0,200,30,15);
			number.setText("Nombres d'utilisateurs");
			number.setPreferredSize(new Dimension(200,30));
			number.setMargin(new Insets(0,0, 0, 0));
			number.setFont(new Font("Poppins",Font.PLAIN,15));
			number.setBackground(Color.decode("#D9D9D9"));
			number.setForeground(Color.GRAY);

			number.addFocusListener(new FocusListener(){
				@Override
				public void focusGained(FocusEvent e) {
					if (number.getText().equals("Nombres d'utilisateurs")) {
						number.setText("");
						number.setForeground(Color.BLACK);
					}
				}
				@Override
				public void focusLost(FocusEvent e) {
					if (number.getText().isEmpty()) {
						number.setForeground(Color.GRAY);
						number.setText("Nombres d'utilisateurs");
					}
				}
			});
			this.nb = number;
			panel.add(this.nb);
			
			JPanel nav = new JPanel();
			nav.setLayout(new FlowLayout());
			
			GreenRoundButton suiv = new GreenRoundButton("➤","Green",75,30,30);
			GreenRoundButton prec = new GreenRoundButton("⮜","Red",75,30,30);
			
			nav.add(prec);
			nav.add(suiv);
			
			panel.add(nav);
			
			suiv.addActionListener(new Reunion2Listener(this,st));
			
			this.getContentPane().add(panel,BorderLayout.CENTER);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

}

	public JTextField getNb() {
		return nb;
	}
}
