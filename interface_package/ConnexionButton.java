package interface_package;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

@SuppressWarnings("serial")
public class ConnexionButton extends JButton {
	
	private Shape shape;
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
		this.setPreferredSize(new Dimension(250, 40));
		this.setMargin(new Insets(10, 50, 50, 50));
		this.setBorder(new RoundedBorder(10));
		this.setContentAreaFilled(false);
		
		updateButton(null, this);
		
	}
	    protected void paintComponent(Graphics g) {
	    	if (this.getModel().isArmed()) {
	    		g.setColor(Color.decode("#FFFFFF"));	    		
	    	}else {
	         g.setColor(Color.decode("#CC383B"));
	         }
	         g.fillRoundRect(0, 0, 250, 40, 15, 15);
	         super.paintComponent(g);
	    }
	    
	    protected void paintBorder(Graphics g) {
	         g.setColor(Color.decode("#FFFFFF"));
	         g.drawRoundRect(0, 0, 249, 39, 15, 15);
	    }
	    public boolean contains(int x, int y) {
	         if (shape == null || !shape.getBounds().equals(getBounds())) {
	             shape = new RoundRectangle2D.Float(0, 0, 455, 50, 15, 15);
	         }
	         return shape.contains(x, y);
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
