package interface_package;

import java.awt.*;
import java.sql.Statement;

import javax.swing.*;

@SuppressWarnings("serial")
public class ConnexionButton extends JButton {
	
	private String texte;
	private boolean selected;
	
	public ConnexionButton(String texte, boolean selected) {
		super();
		this.texte = texte;
		//this.icon = new MenuButtonIcon(icon,this,20,20);
		this.selected = selected;
		
		this.setText(texte);
		this.setFocusPainted(false);
		this.setFont(new Font("Poppins", Font.PLAIN, 35));
		this.setPreferredSize(new Dimension(200, 37));
		this.setMargin(new Insets(10, 50, 50, 50));
		this.setBorder(new RoundedBorder(10));

		
		updateButton(null, this);
		
		
	}
	
	public void updateButton(Graphics g, JComponent c){
		if(selected) {
			this.setBackground(Color.decode("#CC383B"));
			this.setForeground(new Color(255, 255, 255));
			//this.setIcon(icon.getIcon());
		} else {
			this.setBackground(new Color(255, 255, 255));
			this.setForeground(new Color(82, 101, 129));
			//this.setIcon(icon.getIcon());
		}
	};
	
	public boolean isSelected() {
		return selected;
	}

}
