package Tables;

import javax.swing.*;

import interface_polo.GreenRoundButton;

import java.awt.*;

public class TablePanel extends RoundedPanel {

	public TablePanel(int radius, Color bgColor) {
		super(radius, bgColor);
		// TODO Auto-generated constructor stub
		
		GreenRoundButton indispo = new GreenRoundButton("Indisponible", "Red", 95, 25, 15,10);
		
		indispo.setBounds(250, 20, 95, 25);
		this.add(indispo);
		
		GreenRoundButton dispo = new GreenRoundButton("Disponible", "Green", 95, 25, 15,10);
		
		indispo.setBounds(250, 20, 95, 25);
		this.add(indispo);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(380,114));
	}

}
