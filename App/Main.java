package App;
import java.awt.Color;

import Back.*;
import interface_package.*;

public class Main {

	public static void main(String[] args) {
		Back.connectionBase();
		WindowFrame f = new WindowFrame();
		f.setVisible(true);
		ConnexionFrame c = new ConnexionFrame();
		c.setVisible(true);
		System.out.println("Hello World!");
		
	}

}
