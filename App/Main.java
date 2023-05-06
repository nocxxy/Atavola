package App;
import java.sql.Date;
import java.sql.Statement;

import Back.Back;
import interface_package.ConnexionFrame;

public class Main {

	public static void main(String[] args) {
		Statement st = Back.connectionBase();
		ConnexionFrame c = new ConnexionFrame(st);
		//c.setVisible(true);
		
		//Back.insertCreneau(st, "2023-05-01 07:30:30", "2023-05-01 08:20:00", 1);
		Back.getCreneauxEmp(st, new Date(2023,05,01), 1);
		
	}

}
