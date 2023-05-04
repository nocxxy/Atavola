package Back;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Calendar;


import Front.Fonction.Creneau;
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
                    + "    id_employer INT NOT NULL, \r\n"
                    + "    CONSTRAINT pk_employe PRIMARY KEY (id),\r\n"
                    + "    CONSTRAINT fk_creneau_employe FOREIGN KEY (id_employer) REFERENCES Employer (id)\r\n"
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
    
    
    

    /*
       Methode qui recupere un creneau et prend un statement et l'id du creneau
       et renvoie un objet
    */
    public static Creneau getCreneau(Statement st,int id) {
        try {
            //La requête sql
            String select = "SELECT c.date_heure_debut, c.date_heure_fin FROM Creneau c INNER JOIN Employer e ON c.id_employer = e.id WHERE e.id = ";
            String query = select + id;

            //Execution de la requête sql
            ResultSet rs = st.executeQuery(query);
            System.out.println(rs);

            //Traitement du résultat
            while(rs.next()) {
                //Affichage des données
                System.out.println(rs.getString("date_heure_debut")
                        + ", " + rs.getString("date_heure_fin"));

                //On stocke les données
                Date hd = rs.getDate("date_heure_debut");
                Date hf = rs.getDate("date_heure_fin");

                //On retourne le creneau
                Creneau res = new Creneau(hd, hf);
                return res;
            }

        } catch(SQLException ex) {
            //Exceptions
            ex.printStackTrace();
        }
        return null;
    }

    /*
     * Méthode pour insérer un Creneau dans la base de donnée
     * Prend un statement, une heure de debut, une heure de fin et l'id d'un employer
     * et ne renvoie rien
     * */
    public static void insertCreneau(Statement st, String date_heure_debut, String date_heure_fin, int id_employer) {
        try {
            //La requête sql
            String sql = "INSERT INTO Creneau (date_heure_debut,date_heure_fin, id_employer) VALUES (";
            String query = sql + (char)34 + date_heure_debut + (char)34 + ",";
            query += (char)34 + date_heure_fin + (char)34 + ",";
            query += id_employer;
            query += ")";
            System.out.println(query);

            //Execution de la requête
            st.executeUpdate(query);

        } catch (SQLException ex) {
            //Exceptions
            ex.printStackTrace();
        }
    }

    /*
     * Méthode pour supprimer un Creneau dans la base de donnée
     * Prend un statement et l'id du creneau à supprimer
     * et ne renvoie rien
     */
    public static void deleteCreneau(Statement st, int id) {
        try {
            //La requête sql
            String sql = "DELETE FROM Creneau WHERE Creneau.id = ";
            String query = sql + id;

            System.out.println(query);

            //Execution de la requête
            st.executeUpdate(query);

        } catch (SQLException ex) {
            //Exceptions
            ex.printStackTrace();
        }
    }

    /*
     * Méthode qui permet de récupérer tous les creneaux entre 2 dates dans la base de donnée
     * prend un statement et renvoie une liste (ArrayList) des creneaux
     * */
    public static ArrayList<Creneau> getAllWeeklyCreneau (Statement st, String date1, String date2){
        try {
            //La requête sql
            String sql = "SELECT * FROM Creneau WHERE date_heure_debut  >= ";
            String query = sql + (char)34 + date1 + (char)34 + " AND date_heure_debut < ";
            query += (char)34 + date2 + (char)34;

            //Execution de la requête sql
            ResultSet rs = st.executeQuery(query);

            //Traitement du résultat
            ArrayList<Creneau> res = new ArrayList();
            while (rs.next()) {

                //On stocke les données
                Date hd = rs.getDate("date_heure_debut");
                Date hf = rs.getDate("date_heure_fin");
                //int id_employer = rs.getInt("id_employer");

                //On ajoute le creneau
                Creneau c = new Creneau(hd,hf);
                res.add(c);
            }

            for(Creneau c : res) {
                //On affiche les éléments de la liste
                System.out.println("Creneau:");
                System.out.println(c.getDateDebut() + ", " + c.getDateFin());
            }
            return res;

        } catch (SQLException ex) {
            //Exceptions
            ex.printStackTrace();
        }
        return null;
    }
    
    public String minuteToHeure(int min) {
        int heur,minu ;
        heur = min / 60;
        minu = min % 60;
        return heur + " Heure(s) "+ minu + " minute(s)";
    }
    
    /*
     * Méthode qui permet de calculer les heures d'un employé
     * Prend un ArrayList de creneau et un integer
     * renvoie un integer
     * 
     * */
    public static int getAllCreneauxEmploye (ArrayList<Creneau>
    creneau, int idEmploye){
        
        int cpt =0;
        for (int i=0; i<creneau.size();i++) {
            if (creneau.get(i).getEmploye() == idEmploye) {
                cpt += (creneau.get(i).getTempCreneau());
            }
        }
        return cpt;
    }
    
    public static Employe getEmployer (Statement st,String elogin) {
		try {
		//La requête sql
		String select = "SELECT nom,prenom,login,rang FROM Employer WHERE login = ";
		String query = select + (char)34 + elogin + (char)34;
		
		System.out.println(query);
		
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
    
    public static void insertEmployer(Statement st,String nom,String prenom,String login,String mdp,String rang) {
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

    public static String convertDatetoString(java.util.Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);

    }
    public static ArrayList<Creneau> getAllCreneauWeek(Statement st,Date d){
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(cal.DATE,6);
        java.util.Date d1 = new java.util.Date (cal.YEAR-1900,cal.MONTH,cal.DATE);
        return getAllWeeklyCreneau(st,convertDatetoString(d),convertDatetoString(d1));
    }
    
    public static ArrayList<Employe> getAllEmployer (Statement st){
    	try {
    		//La requête sql
    		String query = "SELECT * FROM Employer";  		
    		
    		//Execution de la requête sql
    		ResultSet rs = st.executeQuery(query);
    		System.out.println(rs);
    		
    		//Traitement du résultat
    		
    		ArrayList<Employe> res = new ArrayList();
    		while (rs.next()) {                //On stocke les données
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
    
    public static boolean connexionEmployer (Statement st, String login, String mdp) throws SQLException {
    	
    	//Initialisation de la varible pour executer la requête 
    	ResultSet rs = null;
    	try {
    		//requête de vérification 
    		String verif = "SELECT nom,prenom,login,rang FROM Employer WHERE login = ";
    		verif += (char)34 + login + (char)34 + " AND mdp = ";
    		String mot_de_passe = (char)34 + cryptePwd(mdp) + (char)34;
    		verif += mot_de_passe;
    		
    		//execution de la requête
    		rs = st.executeQuery(verif);
    		
    		//affiche de la requête
    		System.out.println(verif);
            return (rs.next());
    		
    	}catch(SQLException ex) {
    		//Exception
    		ex.printStackTrace();
            return false;
    	}
    }
}
