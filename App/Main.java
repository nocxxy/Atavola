package App;
import java.sql.Date;
import java.sql.Statement;

import Back.Back;
import interface_package.ConnexionFrame;


public class Main {

	public static void main(String[] args) {
		
		Statement st = Back.connectionBase();
		Back.connectionBase();

		ConnexionFrame c = new ConnexionFrame(st);
		//c.setVisible(true);
		
		Back.deleteIndisp(st, 4);

		
	}

}
