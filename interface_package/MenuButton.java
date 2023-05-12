package interface_package;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MenuButton extends JButton{
	private String texte;
	private MenuButtonIcon icon;
	private boolean selected;
	private MenuPanel menu;
	
	public MenuButton(String texte, String icon, boolean selected,MenuPanel menu) {
		super();
		this.texte = texte;
		//this.icon = new MenuButtonIcon(icon,this,20,20);
		this.selected = selected;
		this.menu = menu;
		
		this.setText(texte);
		this.setFocusPainted(false);
		this.setIconTextGap(8);
		this.setBorder(null);
		this.setFont(new Font("Nirmala UI", Font.PLAIN, 14));
		this.setPreferredSize(new Dimension(180, 37));
		this.setMargin(new Insets(10, 50, 50, 50));
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		ImageIcon imageIcon = new ImageIcon("src/img/"+icon+"_s.png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);
		this.setIcon(imageIcon);

		updateButton();
		
		
	}
	
	public void updateButton(){
		if(selected) {
			this.setBackground(new Color(204, 56, 59));
			this.setForeground(new Color(255, 255, 255));
			//this.setIcon(icon.getIcon());
		} else {
			this.setBackground(new Color(255, 255, 255));
			this.setForeground(new Color(82, 101, 129));
			//this.setIcon(icon.getIcon());
		}
	};
	
	public void setSelected(boolean b) {
		this.selected = b;
	}
	

}
