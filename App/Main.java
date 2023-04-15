package App;
import Back.*;

import java.sql.Date;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		Statement st = Back.connectionBase();
		System.out.println("Hello World!");
		Back.getCreneau(st, 1);
		/*Date hd = new Date(2002,11,30); besoin d'aide pour le format Date sql
		Date hf = new Date(2002,11,31);
		Back.insertCreneau(st, hd, hf, 5);*/
		Back.deleteCreneau(st,2);

	}

}
