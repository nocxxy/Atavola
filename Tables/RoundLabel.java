package Tables;

import javax.swing.*;

import interface_package.RoundedBorder;

import java.awt.*;

public class RoundLabel extends JLabel {
	
	private Shape shape;
	private String label;
	private String color;
	private int height;
	private int width;
	private int radius;
	
	public RoundLabel(String label,String color,int width,int height,int radius,int font) {
		
		super();
		this.label = label;
		this.color = color;
		this.width=width;
		this.height=height;
		this.radius=radius;
		
		this.setText(label);
		this.setFont(new Font("MontSerrat", Font.PLAIN, font));
		this.setPreferredSize(new Dimension(width, height));
		this.setBorder(new RoundedBorder(10));
		this.setForeground(Color.decode("#FFFFFF"));
		
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
		if (color=="Red"){
         g.setColor(Color.decode("#CC383B"));
         }
    	else if(color=="Green") {
    		g.setColor(Color.decode("#2D6A4F"));
    	}
    	else if(color=="Gray") {
    		g.setColor(Color.decode("#526581"));
    	}
    	else if(color == "Orange") {
    		g.setColor(Color.decode("#F56F36"));
    	}
         g.fillRoundRect(0, 0, width, height, radius, radius);
         super.paintComponent(g);
    }
    
    	protected void paintBorder(Graphics g) {
         g.setColor(g.getColor());
         g.drawRoundRect(0, 0, width-1, height-1, radius, radius);
    }

}
