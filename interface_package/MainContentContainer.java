package interface_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Front.Fonction.Employe;

@SuppressWarnings("serial")
public class MainContentContainer extends JPanel {
	private MenuPanel menu;
	private Employe e;
	private Statement st;
	
	private JPanel title; //Titre du main
	private JLabel titleText;
	
	public MainContentContainer(MenuPanel menu,Employe e,Statement st) {
		super();
		this.menu = menu;
		this.e = e;
		this.st = st;

		this.setBackground(new Color(245, 245, 245));
		this.setLayout(new BorderLayout());
		
		this.title = new JPanel();
		FlowLayout flowLayoutTitle = (FlowLayout) title.getLayout();
		flowLayoutTitle.setHgap(25);
		flowLayoutTitle.setHgap(10);
		flowLayoutTitle.setAlignment(FlowLayout.LEFT);
		title.setOpaque(false);
		
		this.add(title,BorderLayout.NORTH);
		this.titleText = new JLabel();
		titleText.setFont(new Font("Segoe UI", Font.BOLD, 16));
		title.add(titleText);

	
		updateMain();
	}

	public void updateMain() {
		String selected = menu.getSelectedButton();
		switch(selected) {
			case "table":
				titleText.setText("Tables");
				JPanel table = new JPanel();
				table.setBackground(Color.GRAY);
				this.add(table,BorderLayout.CENTER);
				break;
			case "employe":
				titleText.setText("Gestion des employés");
				ListEmployePanel employe = new ListEmployePanel(st);
				employe.setBackground(new Color(245, 245, 245));
				this.add(employe,BorderLayout.CENTER);
				break;
			case "edt":
				titleText.setText("Emplois du temps");
				JPanel edt = new JPanel();
				edt.setBackground(Color.GREEN);
				this.add(edt,BorderLayout.CENTER);
				break;
			default:
				titleText.setText("Defaut");
				break;
		}
	}
}
