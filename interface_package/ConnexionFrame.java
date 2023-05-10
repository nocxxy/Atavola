package interface_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Statement;

import javax.swing.*;

public class ConnexionFrame extends JFrame implements KeyListener {
	//Attributs
	final static int WIDTH = 650;
	final static int HEIGHT = 750;

	private JTextField jlogin;
	private JTextField jmdp;
	private ConnexionButton connect;
	
	//Constructeur
	public ConnexionFrame(Statement st) {
		this.setBounds(100,100,WIDTH,HEIGHT);
		this.setTitle("A Tavola ! | Connexion");
		this.setLocationRelativeTo(null);
		ImageIcon img = new ImageIcon("src/img/italie.png");
		this.setIconImage(img.getImage());
		this.setOpacity(1);
		
		/*
		 * Panel Général qui va tout contenir
		 */
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#2D6A4F"));
		panel.setLayout(new GridLayout(2,1));
		
		/* 
		 * Texte au centre de la fenetre
		 * */
		JPanel brandPanel = new JPanel();
		brandPanel.setPreferredSize(new Dimension(1000, 550));
		brandPanel.setOpaque(false);
		brandPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 327, 191));
		
		JLabel brandname = new JLabel("A Tavola !");
		brandname.setForeground(Color.decode("#FFFFFF"));
		brandname.setFont(new Font("Times New Roman",Font.ITALIC,100));
		brandname.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		brandPanel.add(brandname);
		panel.add(brandPanel);
		
		
		
		/*
		 * Les zones de texte
		 */
		
		JPanel signIn = new JPanel();
		signIn.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 50));
		signIn.setBackground(Color.decode("#2D6A4F"));

		
		panel.add(signIn);

		this.connect = new ConnexionButton("Connexion",true);
		
		
		
		JTextField textFieldLogin = new RoundJTextField(35,455,50,15);
		textFieldLogin.setText("Nom d'utilisateur...");
		textFieldLogin.setPreferredSize(new Dimension(650,50));
		textFieldLogin.setMargin(new Insets(0, 0, 0, 0));
		textFieldLogin.setFont(new Font("Poppins",Font.PLAIN,15));
		textFieldLogin.setBackground(Color.decode("#D9D9D9"));
		textFieldLogin.setForeground(Color.GRAY);
		
		textFieldLogin.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				if (textFieldLogin.getText().equals("Nom d'utilisateur...")) {
					textFieldLogin.setText("");
					textFieldLogin.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldLogin.getText().isEmpty()) {
					textFieldLogin.setForeground(Color.GRAY);
					textFieldLogin.setText("Nom d'utilisateur...");
				}
			}
		});
		
		textFieldLogin.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					connect.doClick();
				}
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		signIn.add(textFieldLogin);
		
		RoundJPasswordField textFieldPwd = new RoundJPasswordField(35,455,50,15);
		textFieldPwd.setEchoChar((char)0);
		textFieldPwd.setText("Mot de Passe...");
		textFieldPwd.setPreferredSize(new Dimension(650,50));
		textFieldPwd.setMargin(new Insets(0, 0, 0, 0));
		textFieldPwd.setFont(new Font("Poppins",Font.PLAIN,15));
		textFieldPwd.setBackground(Color.decode("#D9D9D9"));
		signIn.add(textFieldPwd);
		textFieldPwd.setForeground(Color.GRAY);
		
		textFieldPwd.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				textFieldPwd.setEchoChar('•');
				if (String.valueOf(textFieldPwd.getPassword()).equals("Mot de Passe...")) {
					textFieldPwd.setText("");
					textFieldPwd.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (String.valueOf(textFieldPwd.getPassword()).equals("")) {

					textFieldPwd.setText("Mot de Passe...");
					textFieldPwd.setEchoChar((char)0);
					textFieldPwd.setForeground(Color.GRAY);
				}
			}
		});
		
		textFieldPwd.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					connect.doClick();
				}
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});

		this.jlogin = textFieldLogin;
		this.jmdp = textFieldPwd;
		
		connect.addActionListener(new ConnexionListener(this,st));
		connect.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					((JButton)e.getSource()).doClick();
				}
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		signIn.add(connect);
		
		
		
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(panel,BorderLayout.CENTER);
		this.setResizable(false);
		
		 KeyStroke us = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
	        panel.getInputMap().put(us, "enter");
	        panel.getActionMap().put("enter", new AbstractAction(){
	            @Override
	            public void actionPerformed(ActionEvent evt){
	               connect.doClick();
	            }
	        });
		
		
	}

	public JTextField getJlogin() {
		return jlogin;
	}

	public JTextField getJmdp() {
		return jmdp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
