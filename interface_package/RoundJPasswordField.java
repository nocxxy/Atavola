package interface_package;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

public class RoundJPasswordField extends JPasswordField {
    private Shape shape;
    private int width;
    private int height;
    private int radius;
    
    public RoundJPasswordField(int size,int width,int height, int radius) {
        super(size);
        setOpaque(false); // As suggested by @AVD in comment.
        this.width = width;
        this.height=height;
        this.radius=radius;
    }
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
}
