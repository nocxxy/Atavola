package interface_package;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class MenuButton extends JButton{
	private String texte;
	private MenuButtonIcon icon;
	private boolean selected;
	
	public MenuButton(String texte, String icon, boolean selected) {
		super();
		this.texte = texte;
		//this.icon = new MenuButtonIcon(icon,this,20,20);
		this.selected = selected;
		
		this.setText(texte);
		this.setFocusPainted(false);
		this.setIconTextGap(8);
		this.setBorder(null);
		this.setFont(new Font("Nirmala UI", Font.PLAIN, 14));
		this.setPreferredSize(new Dimension(200, 37));
		this.setMargin(new Insets(10, 50, 50, 50));
		this.setHorizontalAlignment(SwingConstants.LEFT);
		
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
	
	public boolean isSelected() {
		return selected;
	}
	
	

}
