package interface_polo;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

import interface_package.RoundedBorder;

public class GreenRoundButton extends JButton {
	
	private Shape shape;
	private String label;
	private String color;
	
	public GreenRoundButton(String label,String color) {
		
		super();
		this.label = label;
		this.color = color;
		
		this.setText(label);
		this.setFocusPainted(false);
		this.setFont(new Font("MontSerrat", Font.PLAIN, 16));
		this.setPreferredSize(new Dimension(250, 40));
		this.setMargin(new Insets(10, 50, 50, 50));
		this.setBorder(new RoundedBorder(10));
		this.setContentAreaFilled(false);
		this.setForeground(Color.decode("#FFFFFF"));
		
	}
	
    protected void paintComponent(Graphics g) {
    	if (this.getModel().isArmed()) {
    		g.setColor(Color.decode("#FFFFFF"));	    		
    	}else if (color=="Red"){
         g.setColor(Color.decode("#CC383B"));
         }
    	else if(color=="Green") {
    		g.setColor(Color.decode("#2D6A4F"));
    	}
         g.fillRoundRect(0, 0, 250, 40, 15, 15);
         super.paintComponent(g);
    }
    
    //
    //Bordures
    //
    /*protected void paintBorder(Graphics g) {
         g.setColor(Color.decode("#FFFFFF"));
         g.drawRoundRect(0, 0, 249, 39, 15, 15);
    }*/
    
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, 455, 50, 15, 15);
         }
         return shape.contains(x, y);
    }
	
	

}
