package App;
import Back.*;

import java.sql.Date;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		Statement st = Back.connectionBase();

		Back.getCreneau(st, 1); // recuperation du creneau 1

		String hd = "2023-04-15 18:00:00";
		String hf = "2023-04-15 20:00:00";

		Back.insertCreneau(st, hd, hf, 1); // insertion d'un creneau avec une date de debut et date de fin

		Back.deleteCreneau(st,2); // suppresion du creneau 2

		Back.getAllWeeklyCreneau(st,"2023-04-21 00:00:00", "2023-04-28 00:00:00"); // recuperation de tout les creneau entre 2 dates


	}

}
