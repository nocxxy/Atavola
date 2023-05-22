package Tables;

import javax.swing.*;

import interface_polo.GreenRoundButton;

import java.awt.*;

public class TablePanel2 extends RoundedPanel {
	private int num;
	private int nb;
	private String etat;

	public TablePanel2(int radius, Color bgColor,int num,int nb,String etat) {
		super(radius, bgColor);
		this.num=num;
		this.nb=nb;
		this.etat=etat;
		// TODO Auto-generated constructor stub
		
		JLabel numTable = new JLabel("Table " + num);
		numTable.setBounds(22, 16, 95, 25);
		numTable.setFont(new Font("Poppins", Font.PLAIN,20));
		this.add(numTable);
		
		JLabel nbPlace = new JLabel(nb + " places");
		nbPlace.setBounds(22, 35, 95, 25);
		nbPlace.setForeground(Color.decode("#526581"));
		nbPlace.setFont(new Font("Poppins", Font.BOLD,13));
		this.add(nbPlace);
		
		if (etat == "Indisponible") {		
		
		RoundLabel indispo = new RoundLabel("Indisponible", "Red", 95, 25,30,13);
		
		indispo.setBounds(250, 20, 95, 25);
		this.add(indispo);
		
		RoundButtonV2 liberer = new RoundButtonV2("Libérer","Green",80,20,15,12);
		liberer.setBounds(20, 85, 110, 20);
		this.add(liberer);
		}
		else if(etat == "Disponible") {
			
			
			RoundLabel indispo = new RoundLabel("Disponible", "Green", 95, 25,30,13);
			
			indispo.setBounds(250, 20, 95, 25);
			this.add(indispo);
			
			
			RoundButtonV2 liberer = new RoundButtonV2("Attribuer","Red",80,20,15,12);
			liberer.setBounds(20, 85, 110, 20);
			this.add(liberer);
			
			RoundButtonV2 reserver = new RoundButtonV2("Resever","Orange",80,20,15,12);
			reserver.setBounds(120, 85, 110, 20);
			this.add(reserver);
			
		}
		else if (etat == "Reserve") {
			
			RoundLabel indispo = new RoundLabel("Reservé", "Orange", 95, 25,30,13);
			indispo.setBounds(250, 20, 95, 25);
			this.add(indispo);
			
			RoundButtonV2 liberer = new RoundButtonV2("Libérer","Green",80,20,15,12);
			liberer.setBounds(20, 85, 110, 20);
			this.add(liberer);
			
		}
		
		GreenRoundButton modifier = new GreenRoundButton("Modifier Table", "Gray", 95, 25, 15,12);
		modifier.setBounds(250, 55, 95, 25);
		this.add(modifier);
		
		this.setLayout(null);
		this.setPreferredSize(new Dimension(380,114));
	}

}
