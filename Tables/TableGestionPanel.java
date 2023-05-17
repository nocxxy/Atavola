package Tables;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TableGestionPanel extends JPanel{
	Statement st;
	private JPanel tableContainer;
	private JLabel jourService;
	private String service;
	private Date ajd;

	public TableGestionPanel(Statement st) {
		super();
		this.st = st;
		this.service = "midi_1";
		
		
		creePanelHaut();
		creePanelTables();
	}

	private void creePanelTables() {
		tableContainer.setBackground(new Color(255,0,0));
		/*
		TableContainer.setOpaque(false);
		TableContainer.setBorder(new EmptyBorder(25, 25, 25, 25));
		ServicesTablesContainer.add(TableContainer, BorderLayout.CENTER);
		TableContainer.setLayout(new GridLayout(4, 2, 40, 25));
		*/
	}

	private void creePanelHaut() {
		this.setLayout(new BorderLayout());
		JPanel ButtonJourContainer = new JPanel();
		ButtonJourContainer.setOpaque(false);
		ButtonJourContainer.setBackground(new Color(255, 255, 255));
		this.add(ButtonJourContainer, BorderLayout.NORTH);
		ButtonJourContainer.setLayout(new BorderLayout(0, 0));
		
		JLabel JourLabel = new JLabel("Vendredi 12 mai | Midi premier service");
		this.jourService = JourLabel;
		JourLabel.setFont(new Font("Nirmala UI", Font.PLAIN, 14));
		ButtonJourContainer.add(JourLabel, BorderLayout.WEST);
		
		JPanel ButtonContainer = new JPanel();
		ButtonJourContainer.add(ButtonContainer, BorderLayout.EAST);
		ButtonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton AjouterTableButton = new JButton("AjouterTable");
		ButtonContainer.add(AjouterTableButton);
		
		JPanel ServicesTablesContainer = new JPanel();
		ServicesTablesContainer.setBackground(new Color(128, 128, 64));
		ServicesTablesContainer.setOpaque(false);
		this.add(ServicesTablesContainer, BorderLayout.CENTER);
		ServicesTablesContainer.setLayout(new BorderLayout(0, 0));
		
		JPanel ServicesContainer = new JPanel();
		ServicesContainer.setOpaque(false);
		ServicesTablesContainer.add(ServicesContainer, BorderLayout.NORTH);
		ServicesContainer.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel ServicesMidiContainer = new JPanel();
		ServicesMidiContainer.setOpaque(false);
		ServicesContainer.add(ServicesMidiContainer);
		ServicesMidiContainer.setLayout(new BorderLayout(0, 0));
		
		JPanel MidiLabelContainer = new JPanel();
		MidiLabelContainer.setOpaque(false);
		MidiLabelContainer.setBackground(new Color(240, 240, 240));
		ServicesMidiContainer.add(MidiLabelContainer, BorderLayout.NORTH);
		MidiLabelContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel MidiLabel = new JLabel("Midi");
		MidiLabelContainer.add(MidiLabel);
		
		JPanel BoutonsServiceMidiContainer = new JPanel();
		BoutonsServiceMidiContainer.setOpaque(false);
		ServicesMidiContainer.add(BoutonsServiceMidiContainer, BorderLayout.SOUTH);
		BoutonsServiceMidiContainer.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel MidiService1Container = new JPanel();
		MidiService1Container.setOpaque(false);
		BoutonsServiceMidiContainer.add(MidiService1Container);
		MidiService1Container.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton MidiService1Button = new JButton("Service 1");
		MidiService1Container.add(MidiService1Button);
		
		JPanel MidiService2Container = new JPanel();
		MidiService2Container.setOpaque(false);
		BoutonsServiceMidiContainer.add(MidiService2Container);
		MidiService2Container.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton MidiService2Button = new JButton("Service 2");
		MidiService2Container.add(MidiService2Button);
		
		JPanel ServicesSoirContainer = new JPanel();
		ServicesSoirContainer.setOpaque(false);
		ServicesContainer.add(ServicesSoirContainer);
		ServicesSoirContainer.setLayout(new BorderLayout(0, 0));
		
		JPanel MidiLabelContainer_1 = new JPanel();
		MidiLabelContainer_1.setOpaque(false);
		ServicesSoirContainer.add(MidiLabelContainer_1, BorderLayout.NORTH);
		MidiLabelContainer_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel MidiLabel_1 = new JLabel("Soir");
		MidiLabelContainer_1.add(MidiLabel_1);
		
		JPanel BoutonsServiceMidiContainer_1 = new JPanel();
		BoutonsServiceMidiContainer_1.setOpaque(false);
		ServicesSoirContainer.add(BoutonsServiceMidiContainer_1, BorderLayout.SOUTH);
		BoutonsServiceMidiContainer_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel MidiService1Container_1 = new JPanel();
		MidiService1Container_1.setOpaque(false);
		BoutonsServiceMidiContainer_1.add(MidiService1Container_1);
		MidiService1Container_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton MidiService1Button_1 = new JButton("Service 1");
		MidiService1Container_1.add(MidiService1Button_1);
		
		JPanel MidiService2Container_1 = new JPanel();
		MidiService2Container_1.setOpaque(false);
		BoutonsServiceMidiContainer_1.add(MidiService2Container_1);
		MidiService2Container_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton MidiService2Button_1 = new JButton("Service 2");
		MidiService2Container_1.add(MidiService2Button_1);
		
		JPanel TableContainer = new JPanel();
		this.tableContainer = TableContainer;
	}
	
	public void setTexteJour() {
		
	}
	
}
