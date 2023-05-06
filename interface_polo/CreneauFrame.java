package interface_polo;
import java.awt.*;
import javax.swing.*;

import interface_package.AnnulerListener;

import java.sql.Statement;

public class CreneauFrame extends JFrame {
	
	//Attributs
	final static int WIDTH = 300;
	final static int HEIGHT = 390;

	private ChoixEmployer e;
	private JTextField d;
	private JTextField m;
	private JTextField a;
	private JTextField hd;
	private JTextField hf;
	
	//Constructeur
	public CreneauFrame(Statement st) {
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola ! | Créneau");
		ImageIcon img = new ImageIcon("src/img/italie.png");
		this.setIconImage(img.getImage());
		this.setOpacity(1);
		
		/*
		 * Panel Général qui va tout contenir
		 */
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		
		JLabel text = new JLabel("Sélectionner employé : ");
		this.e = new ChoixEmployer(st);
		
		
		JLabel date = new JLabel("Date : ");
		
		JPanel pDate = new JPanel();
		pDate.setLayout(new FlowLayout());
		this.d = new JTextField();
		this.d.setPreferredSize(new Dimension(55,30));
		this.m = new JTextField();
		this.m.setPreferredSize(new Dimension(55,30));
		this.a = new JTextField();
		this.a.setPreferredSize(new Dimension(55,30));
		
		pDate.add(date);
		pDate.add(this.d);
		pDate.add(this.m);
		pDate.add(this.a);
		
		JLabel horaire = new JLabel("Horaires : ");
		
		JPanel pHour = new JPanel();
		pHour.setLayout(new FlowLayout());
		this.hd = new JTextField();
		this.hd.setPreferredSize(new Dimension(50,30));
		this.hf = new JTextField();
		this.hf.setPreferredSize(new Dimension(50,30));
		
		pHour.add(horaire);
		pHour.add(this.hd);
		pHour.add(this.hf);
		
		GreenRoundButton add = new GreenRoundButton("Ajouter le créneau","Green",175,30,30);
		GreenRoundButton cancel = new GreenRoundButton("Annuler","Red",175,30,30);

		cancel.addActionListener(new AnnulerListener(this));
		add.addActionListener(new CreationCreneauListener(st,this));

		panel.add(text);
		panel.add(this.e);
		panel.add(pDate);
		panel.add(pHour);
		
		panel.add(add);
		panel.add(cancel);
		

		
		this.getContentPane().add(panel,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		
	}

	public ChoixEmployer getE() {
		return e;
	}

	public JTextField getA() {
		return a;
	}

	public JTextField getD() {
		return d;
	}

	public JTextField getHd() {
		return hd;
	}

	public JTextField getHf() {
		return hf;
	}

	public JTextField getM() {
		return m;
	}
}
