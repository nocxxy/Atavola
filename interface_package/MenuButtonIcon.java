package interface_package;

import java.awt.Image;

import javax.swing.ImageIcon;

public class MenuButtonIcon {
	ImageIcon selected;
	ImageIcon notSelected;
	MenuButton button;
	int height;
	int width;
	
	public MenuButtonIcon(String icon, MenuButton button,int height, int width) {
		this.selected = new ImageIcon("src/img/"+icon+"_s.png");
		this.notSelected = new ImageIcon("src/img/"+icon+"_ns.png");
		this.button = button;
		this.height = height;
		this.width = width;
		
	}
	
	public ImageIcon getIcon() {
		ImageIcon res;
		if(button.isSelected()){
			res = selected;
		} else {
			res = notSelected;
		}
		return res;
	}
	
	
	public ImageIcon resizeIcon(ImageIcon icon) {
		Image img = icon.getImage();
		ImageIcon newIcon = icon;
		Image newImg = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
		newIcon = new ImageIcon(newImg);
		return newIcon;
	}
}
