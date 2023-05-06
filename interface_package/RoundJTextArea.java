package interface_package;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.Stroke;


import javax.swing.*;

public class RoundJTextArea extends JTextArea{

	private Shape shape;
    private int width;
    private int height;
    private int radius;
    
    public RoundJTextArea(int width,int height, int radius) {
    	super();
        this.width = width;
        this.height=height;
        this.radius=radius;
        setOpaque(false);
    }
    
    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRoundRect(0, 0, width, height, radius, radius);
        super.paintComponent(g);
   }
   protected void paintBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRoundRect(0, 0, width-1, height-1, radius, radius);
   }
   public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, width+1, height+1, radius, radius);
        }
        return shape.contains(x, y);
   }
}
