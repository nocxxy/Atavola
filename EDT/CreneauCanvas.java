package EDT;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import Front.Fonction.Creneau;
import Front.Fonction.Employe;

public class CreneauCanvas extends Canvas implements MouseListener{
	private Color couleurEmp;
	private ArrayList<Creneau> creneaux;
	private Employe empConn;
	
	public CreneauCanvas(Color color, ArrayList<Creneau> creneaux, Employe empConn) {
		this.setBackground(new Color(255,255,255));
		this.couleurEmp = color;
		this.creneaux = creneaux;
		this.empConn = empConn;
		addMouseListener(this);

	}
	
	public void paint(Graphics g) {
		g.setColor(couleurEmp);
		
		for(int i = 0; i < creneaux.size(); i++) {
			System.out.println("draw creneau");
		}

		g.fillRoundRect(0,(this.getHeight()/10)*2,this.getWidth(),(this.getHeight()/10)*2,10,10);

	}
	
    public void mouseClicked(MouseEvent e) {
        if(empConn.getRang().equals("chef")) {
        	System.out.println("Modifie les creneaux du jour");
        } else {
        	System.out.println("Modifie les indispos du jour du jour");
        }
    }

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
