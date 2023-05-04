package interface_polo;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

import interface_package.RoundedBorder;

public class GreenRoundButton extends JButton {
	
	private Shape shape;
	private String label;
	private String color;
	private int height;
	private int width;
	private int radius;
	
	public GreenRoundButton(String label,String color,int width,int height,int radius) {
		
		super();
		this.label = label;
		this.color = color;
		this.width=width;
		this.height=height;
		this.radius=radius;
		
		this.setText(label);
		this.setFocusPainted(false);
		this.setFont(new Font("MontSerrat", Font.PLAIN, 16));
		this.setPreferredSize(new Dimension(width, height));
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
         g.fillRoundRect(0, 0, width, height, radius, radius);
         super.paintComponent(g);
    }
    
    	protected void paintBorder(Graphics g) {
         g.setColor(g.getColor());
         g.drawRoundRect(0, 0, width-1, height-1, radius, radius);
    }
    
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, width, height, radius, radius);
         }
         return shape.contains(x, y);
    }
	
	

}
