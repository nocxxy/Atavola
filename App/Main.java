package App;
import Back.*;
import Front.Fonction.Employe;
import interface_package.*;

import java.sql.Statement;

public class Main {

	public static void main(String[] args) {

		Statement st = Back.connectionBase();
//		Back.creationTable(st);
//		Back.insertEmployer(st,"Test","elias","login","test","employe");
		AddEmployerFrame test = new AddEmployerFrame(st);
		test.setVisible(true);
		System.out.println("Hello World!");
		
	}

}
