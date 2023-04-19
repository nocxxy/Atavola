package Back;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
            String dbUser = "roor";
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
                    + "    mdp VARCHAR(200),\r\n"
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
    
    
    /*Methode qui permet de récupérer un employé  
    	qui prend un statement et l'id de l'employé
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
   		 	
   		 	//On retourne l'employé
   		 	Employe res = new Employe (nom,prenom,login,rang);
   		 	return res;
		}
		
		} catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
		}
		return null;
	}

    /* 
     * Méthode pour insérer un employé dans la base de donnée
     * Prend un statement,le nom,le prenom,le login,le mot de passe et le rang 
     * et ne renvoie rien 
     * */
    public static void insertEmployer(Statement st,String nom,String prenom,String login,String mdp
    		,String rang) {
    	try {
    		//La requête sql
    		String insert = "INSERT INTO Employer (nom,prenom,login,mdp,rang) VALUES (";
    		String query = insert +(char)34 + nom  + (char)34 +",";
    		query += (char)34 + prenom +(char)34 +",";
    		query += (char)34 + login +(char)34 +",";
    		query += (char)34 + cryptePwd(mdp) +(char)34 +",";
    		query += (char)34 + rang +(char)34;
    		query+= ")";
    		
    		//On affiche la requête
    		
    		System.out.println(query);
    		
    		//Execution de la requête
    		
    		st.executeUpdate(query);
    		
    	} catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
		}
 
    	
    }
    /*
     * Méthode qui permet de récupérer tous les employés 
     * dans la base de donnée
     * prend un statement et renvoie une liste (ArrayList) des employés
     * */
    public static ArrayList<Employe> getAllEmployer (Statement st){
    	try {
    		//La requête sql
    		String query = "SELECT * FROM Employer";  		
    		
    		//Execution de la requête sql
    		ResultSet rs = st.executeQuery(query);
    		System.out.println(rs);
    		
    		//Traitement du résultat
    		
    		ArrayList<Employe> res = new ArrayList();
    		while (rs.next()) {
  
                //On stocke les données
                String nom = rs.getString("nom");
       		 	String prenom = rs.getString("prenom");
       		 	String login = rs.getString("login");
       		 	String rang = rs.getString("rang");
       		 	
       		 	//On ajoute l'employé
       		 	Employe e = new Employe (nom,prenom,login,rang);
       		 	res.add(e);
    		}
    		
    		
    		
    		for(Employe e : res) {
    			//On affiche les éléments de la liste
    			System.out.println(e.getNom());    			
    		}
    		return res;
    		
    		} catch (SQLException ex) {
    			//Exceptions 
    		    ex.printStackTrace();
    		}
    		return null;
    	
    	
    }
    
    /*
     * Méthode pour faire une verification lors de la connexion d'un employer
     * Prend un statement, le nom , le mot de passe
     * Renvoie un booléen qui dit si l'employer est inscrit ou non
     * */
    
    public static boolean connexionEmployer (Statement st, String nom, String mdp) {
    	
    	//Initialisation de la varible pour executer la requête 
    	ResultSet rs = null;
    	try {
    		//requête de vérification 
    		String verif = "SELECT nom,prenom,login,rang FROM Employer WHERE nom = ";
    		verif += (char)34 + nom + (char)34 + " AND mdp = ";
    		String mot_de_passe = (char)34 + cryptePwd(mdp) + (char)34;
    		verif += mot_de_passe;
    		
    		//execution de la requête
    		rs = st.executeQuery(verif);
    		
    		//affiche de la requête
    		System.out.println(verif);
    		
    		
    	}catch(SQLException ex) {
    		//Exception
    		ex.printStackTrace();
    	}
    	return rs!= null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
