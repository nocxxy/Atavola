package Back;
import Front.Fonction.*;
import java.sql.Connection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Front.Fonction.Employe;
public abstract class Back {
    //methode permettant de se connecter à la base de donnée
    //ne prend aucun paramètre mais renvoie la connection qui nous permettra de faire des requetes
    public static Statement connectionBase() {
        try {
            //Chargement driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("DRIVER OK");

            //Créer connection
            String dbName = "atavola";
            String dbIP = "localhost";
            String dbUser = "root";
            String dbPwd = "root";

            String url = "jdbc:mysql://" + dbIP + ":3306/" + dbName;
            
            System.out.println(url);

            Connection con = DriverManager.getConnection(url, dbUser, dbPwd);
            System.out.println("Connection ok");

            //Etat de connection
            Statement st = con.createStatement();
            return st;

        } catch (Exception e) {
            System.out.println("Erreur " + e.getMessage());
        }
        return null;
    }

    //methode qui prend une connection à la base pour ensuite envoyer les requetes de création de tables
    public static void creationTable(Statement st) {
        try {
            //Creation de la table Employer dans la base de donnée
            String sql = "CREATE TABLE IF NOT EXISTS Employer(\r\n"
                    + "    id INT NOT NULL AUTO_INCREMENT,\r\n"
                    + "    nom VARCHAR(25),\r\n"
                    + "    prenom VARCHAR(25),\r\n"
                    + "    login VARCHAR(25),\r\n"
                    + "    mdp VARCHAR(30),\r\n"
                    + "    rang VARCHAR(10),\r\n"
                    + "    CONSTRAINT pk_employe PRIMARY KEY (id)\r\n"
                    + ");";
            //on envoie la requete
            st.executeUpdate(sql);

            // Creation de la table Creneau dans la base de donnée
            sql = "CREATE TABLE IF NOT EXISTS Creneau(\r\n"
                    + "    id INT NOT NULL AUTO_INCREMENT,\r\n"
                    + "    date_heure_debut DATETIME,\r\n"
                    + "    date_heure_fin DATETIME,\r\n"
                    + "    CONSTRAINT pk_employe PRIMARY KEY (id)\r\n"
                    + ");";
            //Envoie de la requete
            st.executeUpdate(sql);

            // Creation de la table Travail dans la base de donnée
            sql = "CREATE TABLE IF NOT EXISTS travail(\r\n"
                    + "    id_creneau INT,\r\n"
                    + "    id_employer INT,\r\n"
                    + "    CONSTRAINT fk_travail_employe FOREIGN KEY (id_employer) REFERENCES Employer (id),\r\n"
                    + "    CONSTRAINT fk_travail_creneau FOREIGN KEY (id_creneau) REFERENCES Creneau (id)\r\n"
                    + ");";
            //Envoie de la requete
            st.executeUpdate(sql);

            // Creation de la table Indisponibilité dans la base de donnée
            sql = "CREATE TABLE IF NOT EXISTS Indisponible(\r\n"
                    + "    id_creneau INT NOT NULL,\r\n"
                    + "    id_employer INT NOT NULL,\r\n"
                    + "    motif VARCHAR(100) NOT NULL,\r\n"
                    + "    CONSTRAINT fk_indisponible_employe FOREIGN KEY (id_employer) REFERENCES Employer (id),\r\n"
                    + "    CONSTRAINT fk_indisponible_creneau FOREIGN KEY (id_creneau) REFERENCES Creneau (id)\r\n"
                    + ");";
            //Envoie de la requete
            st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erreur " + e.getMessage());
        }

    }

    public static String cryptePwd(String pwd) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(pwd.getBytes());

            byte byteData[] = md.digest();

            //convertir le tableau de bits en une format hexadécimal - méthode 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    
    /*Methode qui permet de récupérer un employer  
    	qui prend en paramètre un statement et l'id de l'employer
    	et renvoie un objet 
    */
    public static Employe getEmployer (Statement st,int id) {
		try {
		//La requête sql
		String select = "SELECT nom,prenom,login,rang FROM Employer WHERE id = ";
		String query = select + id;
		
		
		//Execution de la requête sql
		ResultSet rs = st.executeQuery(query);
		System.out.println(rs);
		
		//Traitement du résultat
		while (rs.next()) {
			//Affichage des données 
            System.out.println(rs.getString("nom") + ", " + rs.getString("prenom") + ", " + rs.getString("login")
                    + ", " + rs.getString("rang"));
            //On stocke les données
            String nom = rs.getString("nom");
   		 	String prenom = rs.getString("prenom");
   		 	String login = rs.getString("login");
   		 	String rang = rs.getString("rang");
   		 	
   		 	//On retourne l'employer
   		 	Employe res = new Employe (nom,prenom,login,rang);
   		 	return res;
		}
		
		} catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
		}
		return null;
	}
}
