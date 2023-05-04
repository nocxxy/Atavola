package interface_package;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

public class RoundJTextField extends JTextField{
    private Shape shape;
    private String text;
    
    public RoundJTextField(int size) {
        super(size);
        setOpaque(false); // As suggested by @AVD in comment.
    }
    
    /*public RoundJTextField(int size,String text) {
    	super(size);
    	setOpaque(false);
    	this.text=text;
    }*/
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, 455, 50, 15, 15);
         super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
         g.setColor(getBackground());
         g.drawRoundRect(0, 0, 455, 50, 15, 15);
    }
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, 455, 50, 15, 15);
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
