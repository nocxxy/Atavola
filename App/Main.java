package App;
import java.sql.Statement;

import Back.Back;
import interface_package.ConnexionFrame;


public class Main {

	public static void main(String[] args) {
		
		Statement st = Back.connectionBase();
		ConnexionFrame c = new ConnexionFrame(st);
		c.setVisible(true);
		
	}

}
