package EDT;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import Back.Back;
import Front.Fonction.Creneau;
import Front.Fonction.Employe;
import interface_package.RetirerIndispoFrame;
import interface_polo.RetirerCreneauFrame;
import interface_polo.RetirerReunionFrame;

@SuppressWarnings("serial")
public class CreneauCanvas extends Canvas implements MouseListener{
	private Color couleurEmp;
	private ArrayList<Creneau> creneaux;
	private ArrayList<Creneau> indispo;
	private ArrayList<Creneau> reunion;
	private Employe empConn;
	private EDTPanel edtpan;
	
	public CreneauCanvas(EDTPanel edtpan, Color color, ArrayList<Creneau> creneaux,  ArrayList<Creneau> indispo, ArrayList<Creneau> reunion, Employe empConn) {
		this.setBackground(new Color(255,255,255));
		this.couleurEmp = color;
		this.creneaux = creneaux;
		this.empConn = empConn;
		this.indispo = indispo;
		this.reunion = reunion;
		this.edtpan = edtpan;
		addMouseListener(this);
		System.out.println("------------------------------------------");
		System.out.println(indispo);

		
	}

	public void paint(Graphics g) {
		g.setColor(couleurEmp);
		
		//Dessine creneaux
		for(int i = 0; i < creneaux.size(); i++) {
			drawCreneau(g,creneaux.get(i).getHeureDebut(),0,creneaux.get(i).getHeureFin(),0,"travail");
		}
		//Dessine indispo
		for(int i = 0; i < indispo.size();i++) {
			drawCreneau(g,indispo.get(i).getHeureDebut(),0,indispo.get(i).getHeureFin(),0,"indispo");
		}
		//Dessine reunion
		for(int i = 0; i < reunion.size();i++) {
			drawCreneau(g,reunion.get(i).getHeureDebut(),0,reunion.get(i).getHeureFin(),0,"reunion");
		}
		//drawCreneau(g,14,0,18,0,false);
		//g.fillRoundRect(0,(this.getHeight()/10)*2,this.getWidth(),(this.getHeight()/10)*2,10,10);

	}
	
	public void drawCreneau(Graphics g,int heureDebut, int minuteDebut, int heureFin, int MinuteFin, String type) {
		g.setColor(couleurEmp);
		g.fillRoundRect(0,(this.getHeight()/18)*(heureDebut-7),this.getWidth(),(this.getHeight()/18)*(heureFin-heureDebut),10,10);
		if(type == "indispo") {
			g.setColor(Color.RED);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(3));
			g2d.drawRoundRect(0,(this.getHeight()/18)*(heureDebut-7),this.getWidth(),(this.getHeight()/18)*(heureFin-heureDebut),10,10);
		} else if(type == "reunion") {
			g.setColor(Color.ORANGE);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(3));
			g2d.drawRoundRect(0,(this.getHeight()/18)*(heureDebut-7),this.getWidth(),(this.getHeight()/18)*(heureFin-heureDebut),10,10);
		}
	}
	
    public void mouseClicked(MouseEvent e) {
        if(empConn.getRang().equals("chef")) {

			if(creneaux.size()!=0){
				if(reunion.size()==0 && indispo.size()==0){
					System.out.println("Modifie les creneaux du jour");
					RetirerCreneauFrame f = new RetirerCreneauFrame(Back.connectionBase(),this.creneaux,edtpan);
					f.setVisible(true);
				} else if (reunion.size()!=0) {
					RetirerReunionFrame f = new RetirerReunionFrame(Back.connectionBase(),reunion);
					f.setVisible(true);
				}

			}
        } else {
			if (indispo.size()!=0){
				System.out.println("Modifie les indispos du jour du jour");
				RetirerIndispoFrame f = new RetirerIndispoFrame(Back.connectionBase(),this.indispo);
				f.setVisible(true);
			}

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
