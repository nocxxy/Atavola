package Tables;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TablePanel extends RoundedPanel {
	private Table table;
	private TableGestionPanel tgp;
	private Statement st;
	
	public TablePanel(Table t, TableGestionPanel tgp, Statement st) {
		super(15,Color.WHITE);
		this.table = t;
		this.tgp = tgp;
		this.st = st;
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		//this.setBackground(Color.WHITE);
		
		JPanel PanelHaut = new JPanel();
		PanelHaut.setOpaque(false);
		this.add(PanelHaut, BorderLayout.NORTH);
		PanelHaut.setLayout(new BorderLayout(0, 0));
		
		JPanel PanelNumDispo = new JPanel();
		PanelNumDispo.setOpaque(false);
		PanelHaut.add(PanelNumDispo, BorderLayout.NORTH);
		PanelNumDispo.setLayout(new BorderLayout(0, 0));
		
		JPanel PanelDispo = new JPanel();
		PanelDispo.setOpaque(false);
		PanelNumDispo.add(PanelDispo, BorderLayout.EAST);
		
		creeLabelEtat(PanelDispo);
		
		
		JLabel LabelNumTable = new JLabel(" Table "+table.getNumero());
		LabelNumTable.setFont(new Font("Nirmala UI", Font.BOLD, 14));
		PanelNumDispo.add(LabelNumTable, BorderLayout.WEST);
		
		JPanel PlacesModifPanel = new JPanel();
		PlacesModifPanel.setOpaque(false);
		PanelHaut.add(PlacesModifPanel, BorderLayout.SOUTH);
		PlacesModifPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel LabelPlaces = new JLabel(" "+table.getNbPlaces()+" Places");
		LabelPlaces.setForeground(new Color(82, 101, 129));
		LabelPlaces.setFont(new Font("Tahoma", Font.BOLD, 10));
		PlacesModifPanel.add(LabelPlaces);
		
		JButton BouttonModif = new JButton("Modifier table");
		BouttonModif.addActionListener(new ModifierTableListener(this.table,this.tgp));
		
		BouttonModif.setBorderPainted(false);
		BouttonModif.setFocusable(false);
		BouttonModif.setOpaque(false);
		BouttonModif.setForeground(new Color(82, 101, 129));
		BouttonModif.setContentAreaFilled(false);
		BouttonModif.setFont(new Font("Nirmala UI", Font.BOLD, 8));
		PlacesModifPanel.add(BouttonModif, BorderLayout.EAST);
		
		JPanel BouttonsPanel = new JPanel();
		BouttonsPanel.setOpaque(false);
		BouttonsPanel.setBackground(new Color(255, 255, 255));
		this.add(BouttonsPanel, BorderLayout.SOUTH);
		BouttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		creeBouttons(BouttonsPanel);
	}
	
	/* Procedure creeLabelEtat
	 * cree l'etiquette d'etat de la table
	 * puis l'ajoute dans le conteneur
	 * */
	private void creeLabelEtat(JPanel container) {
		RoundedPanel Dispo;
		JLabel DispoLabel;
		
		switch(table.getEtat()) {
			case "libre":
				Dispo = new RoundedPanel(25,new Color(30,148,20));
				Dispo.setOpaque(false);
				Dispo.setPreferredSize(new Dimension(105, 23));
				container.add(Dispo);

				DispoLabel = new JLabel("Disponible");
				DispoLabel.setFont(new Font("Nirmala UI", Font.BOLD, 10));
				DispoLabel.setForeground(new Color(255, 255, 255));
				Dispo.add(DispoLabel);
				break;
			case "occup":
				Dispo = new RoundedPanel(25,new Color(156,25,27));
				Dispo.setOpaque(false);
				Dispo.setPreferredSize(new Dimension(105, 23));
				container.add(Dispo);

				DispoLabel = new JLabel("Indisponible");
				DispoLabel.setFont(new Font("Nirmala UI", Font.BOLD, 10));
				DispoLabel.setForeground(new Color(255, 255, 255));
				Dispo.add(DispoLabel);
				break;
			case "reserv":
				Dispo = new RoundedPanel(25,new Color(245,111,54));
				Dispo.setOpaque(false);
				Dispo.setPreferredSize(new Dimension(105, 23));
				container.add(Dispo);

				DispoLabel = new JLabel("Réservée");
				DispoLabel.setFont(new Font("Nirmala UI", Font.BOLD, 10));
				DispoLabel.setForeground(new Color(255, 255, 255));
				Dispo.add(DispoLabel);
				break;
		}
	}
	
	/* Procedure creeBouttons
	 * cree les bouttons en fonction de l'etat
	 * puis les ajoutes dans le container
	 * */
	private void creeBouttons(JPanel container) {
		RoundButtonV2 button;
		switch(table.getEtat()) {
			case "libre":
				button = new RoundButtonV2("   Attribuer","Red",80,20,15,12);
				button.setBounds(20, 85, 110, 20);
				button.addActionListener(new OccuperTableListener(this.table,this.tgp));
				container.add(button);
				
				button = new RoundButtonV2("   Réserver","Orange",80,20,15,12);
				button.setBounds(120, 85, 110, 20);
				button.addActionListener(new ReserverListener(this.table,this.tgp));
				container.add(button);
				break;
			case "occup":
				button = new RoundButtonV2("   Libérer","Green",80,20,15,12);
				button.setBounds(20, 85, 110, 20);
				button.addActionListener(new LibererListener(this.table,this.tgp));
				this.add(button);
				break;
			case "reserv":
				button = new RoundButtonV2("   Libérer","Green",80,20,15,12);
				button.setBounds(20, 85, 110, 20);
				button.addActionListener(new LibererListener(this.table,this.tgp));
				this.add(button);
				break;
		}

	}

}
