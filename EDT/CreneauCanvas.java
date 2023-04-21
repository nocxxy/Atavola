package EDT;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import Front.Fonction.Creneau;

public class CreneauCanvas extends Canvas{
	
	public CreneauCanvas() {
		this.setBackground(new Color(255,255,255));

	}
	
	public void paint(Graphics g) {
		System.out.println("paint");
		g.setColor(new Color(128, 0, 0));

		g.fillRoundRect(0,(this.getHeight()/10)*2,this.getWidth(),(this.getHeight()/10)*2,10,10);

	}
	
}
