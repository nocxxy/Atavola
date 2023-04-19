package interface_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainContentContainer extends JPanel {
	private MenuPanel menu;
	private String utilisateur;
	
	
	private JPanel title; //Titre du main
	private JLabel titleText;
	private JPanel content;
	
	public MainContentContainer(MenuPanel menu,String utilisateur) {
		super();
		this.menu = menu;
		this.utilisateur = utilisateur;

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
				table.setBackground(Color.RED);
				this.add(table,BorderLayout.CENTER);
				break;
			case "employe":
				titleText.setText("Gestion des employés");
				JPanel employe = new JPanel();
				employe.setBackground(Color.BLUE);
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
