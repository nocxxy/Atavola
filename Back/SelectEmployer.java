package Back;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Front.Fonction.Employe;



public abstract class SelectEmployer {
	/*
	 * Méthode qui récupère un employer dans la table et qui renvoie 
	 * l'objet correspondant à cet employer
	 * Cette méthode prend en paramètre l'id de l'employer et 
	 * retourne un objet 
	 * */
	public static Employe getEmployer (int id) {
		try {
		//La requête sql
		String select = "SELECT nom,prenom,login,rang FROM Employer WHERE id =";
		String query = select + id;
		
		//Etablir la connexion à la base de donnée
		Statement st = Back.connectionBase();
		
		//Execution de la requête sql
		ResultSet result = st.executeQuery(query);
		
		//On stocke les résultats
		 String nom = result.getString("nom");
		 String prenom = result.getString("prenom");
		 String login = result.getString("login");
		 String rang = result.getString("rang");
		 
		 //Affichage sur la console
		 String output = "User: %s - %s - %s - %s";
		 System.out.println(String.format(output, nom, prenom, login, rang));
		 
		 Employe res = new Employe (nom,prenom,login,rang);
		 return res;
	
		
		} catch (SQLException ex) {
		    ex.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		//Un petit test du fonctionnement du code
		getEmployer(1);

	}

}
