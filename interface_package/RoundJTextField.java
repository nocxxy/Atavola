package interface_package;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

public class RoundJTextField extends JTextField{
    private Shape shape;
    private String text;
    private int width;
    private int height;
    private int radius;
    
    public RoundJTextField(int size,int width,int height, int radius) {
        super(size);
        setOpaque(false); // As suggested by @AVD in comment.
        this.width = width;
        this.height=height;
        this.radius=radius;
    }
    
    /*public RoundJTextField(int size,String text) {
    	super(size);
    	setOpaque(false);
    	this.text=text;
    }*/
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, width, height, radius, radius);
         super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
         g.setColor(getBackground());
         g.drawRoundRect(0, 0, width, height, radius, radius);
    }
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, width, height, radius, radius);
         }
         return shape.contains(x, y);
    }

	/*@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if (this.getText().equals(text)) {
			this.setText("");
			this.setForeground(Color.BLACK);
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if (this.getText().isEmpty()) {
			this.setForeground(Color.GRAY);
			this.setText(text);
		}
	}*/
}
