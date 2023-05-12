package Tables;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

import interface_package.RoundedBorder;

public class RoundButtonV2 extends JButton {
	
	private Shape shape;
	private String label;
	private String color;
	private int height;
	private int width;
	private int radius;
	
	
	public RoundButtonV2(String label,String color,int width,int height,int radius,int font) {
		
		super();
		this.label = label;
		this.color = color;
		this.width=width;
		this.height=height;
		this.radius=radius;
		
		this.setText(label);
		this.setFocusPainted(false);
		this.setFont(new Font("MontSerrat", Font.PLAIN, font));
		this.setPreferredSize(new Dimension(width+20, height));
		this.setMargin(new Insets(10, 50, 50, 50));
		this.setBorder(new RoundedBorder(10));
		this.setContentAreaFilled(false);
		this.setForeground(Color.decode("#000000"));
		
	}
	
    public void setHeight(int height) {
		this.height = height;
		this.setPreferredSize(new Dimension(width, height));
	}

	public void setWidth(int width) {
		this.width = width;
		this.setPreferredSize(new Dimension(width, height));
	}

	protected void paintComponent(Graphics g) {
		g.setColor(Color.decode("#E8E7E7"));
        g.fillRoundRect(0, 0, width + 20, height, radius, radius);
    	if (this.getModel().isArmed()) {
    		g.setColor(Color.decode("#FFFFFF"));
    		g.fillRoundRect(0, 0, width + 20, height, radius, radius);
    		
    	}else if (color=="Red"){
         g.setColor(Color.decode("#CC383B"));
         }
    	else if(color=="Green") {
    		g.setColor(Color.decode("#2D6A4F"));
    	}
    	g.fillOval(width -5, height/4, height/2, height/2);
         
         super.paintComponent(g);
    }
    
    	protected void paintBorder(Graphics g) {
         g.setColor(Color.decode("#E8E7E7"));
         g.drawRoundRect(0, 0, width+20-1, height-1, radius, radius);
    }
    
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, width+20, height, radius, radius);
         }
         return shape.contains(x, y);
    }
	
	

}
