package interface_polo;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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
		d.setForeground(Color.GRAY);
		d.setText("DD");
		this.d.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				if (d.getText().equals("DD")) {
					d.setText("");
					d.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (d.getText().isEmpty()) {
					d.setForeground(Color.GRAY);
					d.setText("DD");
				}
			}
		});
		this.m = new JTextField();
		this.m.setPreferredSize(new Dimension(55,30));
		m.setForeground(Color.GRAY);
		m.setText("MM");
		this.m.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				if (m.getText().equals("MM")) {
					m.setText("");
					m.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (m.getText().isEmpty()) {
					m.setForeground(Color.GRAY);
					m.setText("MM");
				}
			}
		});
		this.a = new JTextField();
		this.a.setPreferredSize(new Dimension(55,30));
		a.setForeground(Color.GRAY);
		a.setText("YYYY");
		this.a.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				if (a.getText().equals("YYYY")) {
					a.setText("");
					a.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (a.getText().isEmpty()) {
					a.setForeground(Color.GRAY);
					a.setText("YYYY");
				}
			}
		});
		
		pDate.add(date);
		pDate.add(this.d);
		pDate.add(this.m);
		pDate.add(this.a);

		JLabel horaire = new JLabel("Horaires : ");

		JPanel pHour = new JPanel();
		pHour.setLayout(new FlowLayout());
		this.hd = new JTextField();
		this.hd.setPreferredSize(new Dimension(50,30));
		this.hd.setForeground(Color.GRAY);
		this.hd.setText("--h--");
		this.hd.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				if (hd.getText().equals("--h--")) {
					hd.setText("");
					hd.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (hd.getText().isEmpty()) {
					hd.setForeground(Color.GRAY);
					hd.setText("--h--");
				}
			}
		});
		this.hf = new JTextField();
		this.hf.setPreferredSize(new Dimension(50,30));
		this.hf.setForeground(Color.GRAY);
		this.hf.setText("--h--");
		this.hf.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				if (hf.getText().equals("--h--")) {
					hf.setText("");
					hf.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (hf.getText().isEmpty()) {
					hf.setForeground(Color.GRAY);
					hf.setText("--h--");
				}
			}
		});

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
		this.setLocationRelativeTo(null); 
		
		
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
