package Back;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import Front.Fonction.Creneau;
import Front.Fonction.Employe;
import Tables.Table;

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
	            String dbUser = "elias";
	            String dbPwd = "admin";

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
                    + "    CONSTRAINT pk_employe PRIMARY KEY (id,login)\r\n"
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

            // Creation de la table reunion dans la base de donnée
            sql = "CREATE TABLE IF NOT EXISTS reunion(\r\n"
            		+ "    id INT DEFAULT 0,\r\n "
                    + "    id_creneau INT,\r\n"
                    + "    id_employer INT,\r\n"
                    + "    CONSTRAINT fk_reunion_employe FOREIGN KEY (id_employer) REFERENCES Employer (id),\r\n"
                    + "    CONSTRAINT fk_reunion_creneau FOREIGN KEY (id_creneau) REFERENCES Creneau (id)\r\n"
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

            // Creation de la table Tables dans la base de donnée
            sql = "CREATE TABLE IF NOT EXISTS Tables(\r\n"
                    + "id INT NOT NULL AUTO_INCREMENT, \r\n"
                    + "numero INT NOT NULL, \r\n"
                    + "nb_places INT NOT NULL, \r\n"
                    + "CONSTRAINT pk_tables PRIMARY KEY (id)\r\n"
                    + ");";
            //Envoie de la requete
            st.executeUpdate(sql);

            // Creation de la table Tables_prises dans la base de donnée
            sql = "CREATE TABLE IF NOT EXISTS Tables_prises(\r\n"
                    + "id_table INT NOT NULL, \r\n"
                    + "service ENUM('midi_1','midi_2','soir_1','soir_2'),\r\n"
                    + "CONSTRAINT pk_tables_prises PRIMARY KEY (id_table,service), \r\n"
                    + "CONSTRAINT fk_tables_prises FOREIGN KEY (id_table) REFERENCES Tables (id)\r\n"
                    + ");";
            //Envoie de la requete
            st.executeUpdate(sql);

            // Creation de la table Reservations dans la base de donnée
            sql = "CREATE TABLE IF NOT EXISTS Reservations(\r\n" +
                    "id_table INT NOT NULL, \r\n" +
                    "service ENUM('midi_1','midi_2','soir_1','soir_2'),\r\n" +
                    "jour DATE,\r\n" +
                    "nom_client VARCHAR(50), \r\n" +
                    "CONSTRAINT pk_reservations PRIMARY KEY (id_table,service,jour), \r\n" +
                    "CONSTRAINT fk_reservations FOREIGN KEY (id_table) REFERENCES Tables (id)\r\n" +
                    ");";
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
            String select = "SELECT c.id, c.id_employer, c.date_heure_debut, c.date_heure_fin FROM Creneau c INNER JOIN Employer e ON c.id_employer = e.id WHERE e.id = ";
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
                int id_creneau = rs.getInt("id");
                Date hd = rs.getDate("date_heure_debut");
                Date hf = rs.getDate("date_heure_fin");
                int id_employe = rs.getInt("id_employer");
                
                //On retourne le creneau
                Creneau res = new Creneau(hd, hf);
                res.setEmploye(id_employe);
                res.setId(id_creneau);
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
        	deleteIndisp(st,id);
        	deleteReunion(st,id);
        	
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
                int id_creneau = rs.getInt("id");
            	Date hd = rs.getDate("date_heure_debut");
                Date hf = rs.getDate("date_heure_fin");
                int id_employer = rs.getInt("id_employer");

                //On ajoute le creneau
                Creneau c = new Creneau(hd,hf);
                c.setEmploye(id_employer);
                c.setId(id_creneau);

                res.add(c);
            }
            /*
            for(Creneau c : res) {
                //On affiche les éléments de la liste
                System.out.println("Creneau:");
                System.out.println(c.getDateDebut() + ", " + c.getDateFin() + ", "+c.getId() 
                +", " + c.getEmploye());
            }
            
            */
            return res;

        } catch (SQLException ex) {
            //Exceptions
            ex.printStackTrace();
        }
        return null;
    }
    
    public static String minuteToHeure(int min) {
        int heur,minu ;
        heur = min / 60;
        minu = min % 60;
        return heur + "h"+ minu;
    }
    
    /*
     * Méthode qui permet de calculer les heures d'un employé
     * Prend un ArrayList de creneau et un integer
     * renvoie un integer
     * 
     * */
    public static int getHeuresEmploye (ArrayList<Creneau>
    creneau, int idEmploye){
        
        int cpt =0;
        for (int i=0; i<creneau.size();i++) {
            if (creneau.get(i).getEmploye() == idEmploye) {
                cpt += (creneau.get(i).getTempCreneau());
            }
        }
        return cpt;
    }
    
    
    /*
     * Méthode qui récupère l'employé
     * Prend un Statement, le nom de l'employé, le prénom 
     * de l'employé 
     * Renvoie l'objet Employé
     * */

    public static Employe getEmployer (Statement st,String nom,String prenom) {
		try {
		//La requête sql
		String select = "SELECT * FROM Employer WHERE nom = ";
		String query = select + (char)34 + nom + (char)34;
		query+= (char)34 + prenom + (char)34;
		
		
		//Execution de la requête sql
		ResultSet rs = st.executeQuery(query);
		
		
		//Traitement du résultat
		while (rs.next()) {
		   
			//On stocke les données
            
   		 	String login = rs.getString("login");
   		 	String rang = rs.getString("rang");
   		 	int id = rs.getInt("id");
   		 	//On retourne l'employé
   		 	Employe res = new Employe (id,nom,prenom,login,rang);
   		 	return res;
		}
		
		} catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
		}
		return null;
	}
    
    /*
     * Méthode qui récupère l'employé
     * Prend un statement, le login de l'employé
     * Renvoie l'objet Employé
     * */
    
    public static Employe getEmployer (Statement st,String elogin) {
		try {
		//La requête sql
		String select = "SELECT * FROM Employer WHERE login = ";
		String query = select + (char)34 + elogin + (char)34;
		
		
		
		//Execution de la requête sql
		ResultSet rs = st.executeQuery(query);
		
		
		//Traitement du résultat
		while (rs.next()) {
		   
			//On stocke les données
            
            String nom = rs.getString("nom");
   		 	String prenom = rs.getString("prenom");
   		 	String login = rs.getString("login");
   		 	String rang = rs.getString("rang");
   		 	int id = rs.getInt("id");
   		 	//On retourne l'employé
   		 	Employe res = new Employe (id,nom,prenom,login,rang);
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
    		
    		
    		//Execution de la requête
    		
    		st.executeUpdate(query);
    		
    	} catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
		}
    }

    public static void updateEmployer(Statement st,int id,String nom,String prenom,String login,String mdp,String rang) {
        try {
            //La requête sql
            String update = "UPDATE Employer SET nom = ";

            String query = update +(char)34 + nom  + (char)34 +", prenom = ";
            query += (char)34 + prenom +(char)34 +", login = ";
            query += (char)34 + login +(char)34 +", mdp = ";
            query += (char)34 + cryptePwd(mdp) +(char)34 +", rang =";
            query += (char)34 + rang +(char)34;
            query += "WHERE id = " + id;

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
        ArrayList<Creneau> res = getAllWeeklyCreneau(st,convertDatetoString(d),convertDatetoString(d1));
        /*
        for(Creneau c : res) {
        	System.out.println(c.getId()+ ", "+c.getHeureDebut()+ ", "+
        			c.getHeureFin());
        }
        */
        return res;
    }
    
    /*
     * Méthode qui récupère les employés
     * Prend un statement et renvoie 
     * la liste des employés
     * */
    
    public static ArrayList<Employe> getAllEmployer (Statement st){
    	try {
    		//La requête sql
    		String query = "SELECT * FROM Employer";  		
    		
    		//Execution de la requête sql
    		ResultSet rs = st.executeQuery(query);
    		
    		//Traitement du résultat
    		
    		ArrayList<Employe> res = new ArrayList();
    		while (rs.next()) {                
    			//On stocke les données
                int id = Integer.parseInt(rs.getString("id"));
                String nom = rs.getString("nom");
       		 	String prenom = rs.getString("prenom");
       		 	String login = rs.getString("login");
       		 	String rang = rs.getString("rang");
       		 	
       		 	//On ajoute l'employé
       		 	Employe e = new Employe (id,nom,prenom,login,rang);
       		 	res.add(e);
    		}
    		
    		return res;
    		
    		} catch (SQLException ex) {
    			//Exceptions 
    		    ex.printStackTrace();
    		}
    		return null;
    	}
    
    /*
     * Méthode qui vérifie le login et le mot de passe 
     * de l'employé 
     * Prend le login et le mot de passe
     * Renvoie un booléen 
     * 
     * */
    public static boolean connexionEmployer (Statement st, String login, String mdp) throws SQLException {
    	
    	//Initialisation de la variable pour executer la requête 
    	ResultSet rs = null;
    	try {
    		//requête de vérification 
    		String verif = "SELECT * FROM Employer WHERE login = ";
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
    
    /*
     * Permet de récupérer le lundi de la semaine actuelle
     */
    public static Date getLundi() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date jour = new Date(cal.getTime().getTime());
        return jour;
    }

    /*
     * Renvoie liste des jours a afficher d'une semaine
     */
    public static ArrayList<String> jourSemaineAffichage(Date debut) {
        ArrayList<String> jours = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(debut);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE d MMMM", Locale.FRENCH);
        for (int i = 0; i < 7; i++) {
            jours.add(sdf.format(cal.getTime()));
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return jours;
    }

    public static Date jourPlusi(Date jour, int i) {
        Calendar cal = Calendar.getInstance();
        java.util.Date temp = new java.util.Date(jour.getTime());
        cal.setTime(jour);
        cal.add(Calendar.DAY_OF_MONTH, i);
        //System.out.println(cal.getTime().toString());
        Date jourPlus = new Date(cal.getTime().getTime());
        return jourPlus;
    }

    public static Date getSemaineSuivante(Date jour) {
        return jourPlusi(jour, 7);
    }

    public static Date getSemainePrecedente(Date jour) {
        return jourPlusi(jour, -7);
    }
    
    /*
     * Méthode qui récupère les créneaux d'un employé
     * Prend un statement, le jour, l'id de l'employé
     * Renvoie la liste des créneaux
     * 
     * */
    public static ArrayList<Creneau> getCreneauxEmp(Statement st,Date jour, int id){
        try {
            //La requête sql
        	String date = jour.getYear()+1900+"-"+ (jour.getMonth()+1)+"-"+jour.getDate();
            String sql = "SELECT * FROM Creneau WHERE DATE(date_heure_debut) = ";
            String query = sql + (char)34 + date + (char)34 + " AND DATE(date_heure_fin) = "+
                    (char)34 + date + (char)34 + " AND id_employer = " +id;
            
            //System.out.println(query);
            //Execution de la requête sql
            ResultSet rs = st.executeQuery(query);

            //Traitement du résultat
            ArrayList<Creneau> res = new ArrayList();
            while (rs.next()) {

                //On stocke les données
            	int id_creneau = rs.getInt("id");
            	Timestamp hd = rs.getTimestamp("date_heure_debut");
            	Timestamp hf = rs.getTimestamp("date_heure_fin");
                

                //On ajoute le creneau
                Creneau c = new Creneau(hd,hf);
                c.setEmploye(id);
                c.setId(id_creneau);
                
                res.add(c);
            }

            /*
            for(Creneau c : res) {
                //On affiche les éléments de la liste
                System.out.println("Creneau:");
                System.out.println(c.getDateDebut() + ", " + c.getDateFin() +", "
                		+c.getId() + ", " + c.getEmploye());
            }
            */
            return res;

        } catch (SQLException ex) {
            //Exceptions
            ex.printStackTrace();
        }
        return null;
    }
    
    /*
     * Méthode qui vérifie si un creneau existe
     * Prend l'id du créneau et un statement 
     * Renvoie un booléen 
     * 
     * */
    public static boolean creneauExiste(Statement st, int id) {
		ResultSet rs = null;
		try {
			//la requête sql
			 String query = "SELECT * FROM Creneau WHERE id_employer = ";
			
			 query+= id;
			 
			 //execution de la requête
			 rs = st.executeQuery(query);
			 return (rs.next());
			 
			 
			 
		}catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
		    return false;
    	}	
    }
    
    /*
     * Méthode générique qui permet de supprimer dans la table 
     * Soit la table indisponible soit réunion 
     * Prend l'id de l'employé et le nom de la table 
     * 
     * */
    
    public static void retireIndispOureunion (Statement st,int id,String indispOureunion) {
    	try {
	  	        //la requête     
	            String delete = "DELETE FROM " +indispOureunion +" WHERE id_employer = "+id;
	            
	            //execution de la requête
	            st.executeUpdate(delete);
    
    		
    	} catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
    	}	
    }  
   
    /*
     * Methode qui supprime un employé dans la base
     * Prend un statement et un id 
     * 
     * */
    
    
    public static void retireEmploye(Statement st, int id) {
    	try {
    		//On vérifie s'il y a un créneau rattaché à l'employé
    		if(creneauExiste(st,id)) {
    			//On retire l'indisponibilité
    			retireIndispOureunion(st,id,"Indisponible");
    			
    			//On retire la réunion 
    			retireIndispOureunion(st,id,"reunion");
    			
    			//On retire le créneau 
    			String sql = "DELETE FROM Creneau WHERE id_employer = ";
    			sql+= id;
    			st.executeUpdate(sql);
    		}
    		//La requête sql
    		String insert = "DELETE FROM Employer WHERE id = ";
    		String query = insert + id ;
    		   		
    		//Execution de la requête
    		
    		st.executeUpdate(query);
    		
    	} catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
		}
    	
    	
    	
    } 
    
    
    
    /*
     * Méthode qui vérifie si un employé existe 
     * Prend un statement et son id
     * Renvoie un booléen
     * 
     * */
    public static boolean employeExiste(Statement st, int id) {
    	ResultSet rs = null;
		try {
			//la requête sql
			 String query = "SELECT * FROM Employer WHERE id = ";
			
			 query+= id;
			 
			 //execution de la requête
			 rs = st.executeQuery(query);
			 return (rs.next());
			 
			 
			 
		}catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
		    return false;
    	}	
    }
    
    /*
     * Méthode qui vérifie si un creneau existe 
     * Prend un statement et son id
     * Renvoie un booléen
     * 
     * */
    
    public static boolean creneauExiste(Statement st, String debut,String fin,int id) {
    		ResultSet rs = null;
    		try {
    			//la requête sql
    			 String query = "SELECT * FROM Creneau WHERE date_heure_debut = ";
    			
    			 query+= (char)34 + debut  + (char)34 + "AND date_heure_fin = ";
    			 query += (char)34 + fin  + (char)34 + "AND id_employer = " + id;
    			 
    			 //execution de la requête
    			 rs = st.executeQuery(query);
    			 return (rs.next());
    			 
    			 
    			 
    		}catch (SQLException ex) {
    			//Exceptions 
    		    ex.printStackTrace();
    		    return false;
        	}	
    }
    
    /*
     * Méthode qui récupère l'id d'un creneau 
     * Prend une heure de debut, une heure de fin et l'id de l'employé
     * Renvoie un entier 
     * 
     * */
    public static int getIdCreneau(Statement st, String debut,String fin,int id) {
    	int res = 0;
    	try {
    		//La requête pour récupérer l'id
    		 String getId = "SELECT id FROM Creneau WHERE date_heure_debut = ";
             
             getId += (char)34 + debut  + (char)34;
             getId += " AND date_heure_fin = ";
             getId += (char)34 + fin  + (char)34;
             getId += " AND id_employer = ";
             getId += id;
             
             //execution de la requête
             ResultSet rs = st.executeQuery(getId);
             
             
             while(rs.next()) {
             	res = rs.getInt("id");          	              
             } 
			
			 
			 
		}catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
		    
		}return res;
    }
    
    
    
    /*
     * Ajoute un creneau d'indisponibilité d'un employé
     * Prend son id, le motif, l'heure de debut,l'heure de fin 
     * 
     * 
     * */
    public static void ajoutIndisp (Statement st, int id, String motif,String debut, String fin) {
    	try {
    		//On fait l'ajout d'indisponibilité sur un employé qui existe déjà 
    		if(employeExiste(st,id) && !estIndisponible(st,id,debut,fin)) {
    			
    		if(! creneauExiste(st,debut,fin,id)) {
    			//Si le créneau n'existe pas on l'ajoute dans la base
    			insertCreneau(st,debut,fin,id);
    		}
    		 
            int id_creneau = getIdCreneau(st,debut,fin,id);
            
            
            //la requête pour ajouter l'indisponibilité
            String indisp = "INSERT INTO Indisponible (id_creneau,id_employer,motif) VALUES (";
            indisp += id_creneau +",";
            indisp += id +",";
            indisp += (char)34 + motif + (char)34 ;
            indisp += ")";
            
            
            st.executeUpdate(indisp);
    		}
    		
    	} catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
    	}	
    }  
    /*
     * Méthode pour savoir si un employé est indisponible
     * Prend l'id de l'employé, une heure de début et de fin 
     * Renvoie un booléen
     * 
     * 
     * */
    public static boolean estIndisponible (Statement st, int id,String debut,String fin) {
    	ResultSet rs = null;
    	try {
    		//On vérifie si l'employé existe et si le creneau existe
    		if(employeExiste(st,id) && creneauExiste(st,debut,fin,id)) {
    			
    			//On récupère l'id du creneau 
    			int id_creneau = getIdCreneau(st,debut,fin,id);
    			
    			//La requête pour verifier si l'employé est indisponible
    			String query = "SELECT * FROM Indisponible WHERE id_creneau =";
    			query += id_creneau + " AND id_employer = ";
    			query += id;
    			
    			rs = st.executeQuery(query);
   			 	return (rs.next());
    		}
    		
    	}catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
    	}	return false;
    	
    }
    
    /*
     * Méthode pour vérifier que les employés sont disponibles
     * Prend une liste de type arraylist,une heure de debut,de fin 
     * Renvoie un booléen
     * 
     * */
    
    public static boolean sontDisponibles(Statement st,ArrayList<Employe> e ,String debut,String fin) {
    	boolean res =false;
     	int id_employer;
    	for(Employe e1 : e) {
    		 id_employer = e1.getId();
    		 if(!estIndisponible(st,id_employer,debut,fin)) {
   				 res = true;
   			 }else {
   				 res= false;
   			 }
   		}
   		return res;
    }
    
    /*
     * Méthode pour vérifier si un employé travaille
     * Prend l'id de l'employé, une heure de début, de fin
     * Renvoie un booléen
     * 
     * */
    public static boolean reunion (Statement st, int id,String debut,String fin) {
    	ResultSet rs = null;
    	try {
    		//Si l'employé existe et que le creneau existe
    		if(employeExiste(st,id) && creneauExiste(st,debut,fin,id)) {
    			
    			int id_creneau = getIdCreneau(st,debut,fin,id);
    			
    			//On vérifie si l'employé travaille
    			String query = "SELECT * FROM reunion WHERE id_creneau =";
    			query += id_creneau + " AND id_employer = ";
    			query += id;
    			
    			rs = st.executeQuery(query);
   			 	return (rs.next());
    		}
    		
    	}catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
    	}	return false;
    	
    }
    
    /*
     * Méthode pour insérer une réunion dans la base de donnée
     * Prend l'id de la réunion, du créneau et de l'employé
     * */
    public static void insertionReunion(int id,int id_creneau,int id_employer) {
    	try {
    			//la requête
				String reunion = "INSERT INTO reunion (id,id_creneau,id_employer) VALUES (";
				reunion += id +",";
				reunion += id_creneau +",";
				reunion += id_employer ;
				reunion += ")";
			
				Back.connectionBase().executeUpdate(reunion);
    	}catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
    	}	
    }
    
    /*
     * Méthode qui vérifie s'il existe déjà une réunion à ce créneau
     * Prend la date de début et de fin 
     * Renvoie un booléen
     * 
     * */
    public static boolean memeReunion(String debut,String fin) {
    	ResultSet rs = null;
    	try {
    		//la requête
    		String sql = "SELECT * FROM reunion WHERE id_creneau IN "
					+ "(SELECT id FROM Creneau WHERE date_heure_debut =";
					sql += (char)34 +debut +(char)34;
					sql+= " AND date_heure_fin = ";
					sql += (char)34 + fin +(char)34 +")" +"LIMIT 1 ";
			System.out.println(sql);
			
			//execution de la requête
			rs= Back.connectionBase().executeQuery(sql);
			
			return rs.next();
    	}catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
    	}return false;
    }
    /*
     * Méthode qui renvoie l'id de la réunion qui correspond à ce créneau
     * Prend l'heure de début et de fin
     * Renvoie un integer
     * */
    
    public static int idMemeReunion(String debut,String fin) {
    	int id = 0;
    	try {
    		//la requête 
    		String sql = "SELECT * FROM reunion WHERE id_creneau IN "
					+ "(SELECT id FROM Creneau WHERE date_heure_debut =";
					sql += (char)34 +debut +(char)34;
					sql+= " AND date_heure_fin = ";
					sql += (char)34 + fin +(char)34 +")" +"LIMIT 1 ";
			System.out.println(sql);
			
			//execution de la requête
			ResultSet rs = Back.connectionBase().executeQuery(sql);
			
			while(rs.next()) {
				//récupère l'id 
				id = rs.getInt("id");
			}
    	}catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
    	}return id;
    }
    /*
     * Méthode pour ajouter un creneau de reunion
     * Prend une liste de type arraylist, une heure de debut et de fin
     *
     * */
    public static void ajoutreunion (Statement st, ArrayList<Employe> e,String debut, String fin) {
    	
    	//Gestion de l'incrémentation
    	
    	int indice = getIdLastReunion(st) +1;
    		
    		for (Employe e1 :e) {
    			int id = e1.getId();
    			//On fait l'ajout de creneau de reunion sur un employé qui existe déjà
    			if(employeExiste(st,id) && ! reunion(st,id,debut,fin)) {
    			
    				if(! creneauExiste(st,debut,fin,id)) {
    					//Si le créneau n'existe pas on l'ajoute dans la base
    					insertCreneau(st,debut,fin,id);
    				}
    				int id_creneau = getIdCreneau(st,debut,fin,id);
            
    				//si c'est la même réunion, on prend le même id
    				if (memeReunion(debut,fin)) {
    					indice = idMemeReunion(debut,fin);
    					insertionReunion(indice,id_creneau,id);
    				}else {
    					//sinon on incrémente l'id
    					insertionReunion(indice,id_creneau,id);
    				}
    			}
    		}
    		
    		
    	
    }  
    
    /*
     * Méthode pour retirer l'indisponibilité d'un employé
     * Prend une liste de type arraylist,une heure de type et de fin 
     * 
     * */
    public static void retireIndisp (Statement st, ArrayList<Employe> e, String debut, String fin) {
    	try {
    		for (Employe e1 :e) {
    			int id = e1.getId();
    			
    			//On vérifie si l'employé est indisponible 
	    		if(employeExiste(st,id) && estIndisponible(st,id,debut,fin)) {
	    			
	    		 
	            int id_creneau = getIdCreneau(st,debut,fin,id);
	            
	            
	            //la requête pour retirer l'indisponibilité
	            String delete = "DELETE FROM Indisponible WHERE id_creneau = ";
	            delete += id_creneau +" AND id_employer = ";
	            delete += id ;
	       
	            
	            //execution de la requête
	            st.executeUpdate(delete);
	    		}
    		}
    		
    	} catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
    	}	
    }  
    
    /*
     * Méthode pour ajouter une réunion
     * Prend une liste de type arraylist, une heure de debut et de fin 
     * 
     * */
    
    public static void ajout_reunion (Statement st,ArrayList<Employe> e,String debut, String fin , boolean urgent) {
    		if (!urgent  ) {
    			//si la réunion n'est pas urgente, on vérifie si les employés sont tous disponibles
    			if (sontDisponibles(st,e,debut,fin)) {
    				ajoutreunion(st,e,debut,fin);
    			}
    		}else {
    			//si la réunion est urgente, on l'ajoute peu importe
    			retireIndisp(st,e,debut,fin);
    			ajoutreunion(st,e,debut,fin);
    			
    		}
    		
    }
    
    /*
     * Méthode qui met à jour l'indisponibilité 
     * Prend un statement, l'id du créneau, de l'employé, le motif
     * l'heure de début, de fin 
     * 
     * 
     * */
    public static void updateIndisp (Statement st, int idcreneau,int idemploye,String motif,String debut, String fin) {
    	try {
    		
    		//On regarde si l'employé existe
    		if(employeExiste(st,idemploye)) {
    		
    		//la requête pour mettre à jour le créneau d'indisponibilité l'employé
            String query = "UPDATE Creneau SET date_heure_debut = ";
            query+= (char)34 + debut + (char)34 + ",date_heure_fin = ";
            query+= (char)34 + fin + (char)34 ;
            query+= " WHERE id_employer = "+idemploye;
            query+= " AND id = "+idcreneau;         
            
            //la requête pour mettre à jour le motif
            String update = "UPDATE Indisponible SET motif = ";
            update+= (char)34 + motif + (char)34;
            update+= " WHERE id_employer = "+idemploye;
            update+= " AND id_creneau = "+idcreneau;
            
            
            System.out.println(query);
            System.out.println(update);
            
            //execution de la requête
            st.executeUpdate(update);
            st.executeUpdate(query);
    		}
    		
    	} catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
    	}	
    }
    
    /*
     * Méthode qui récupère les créneaux d'indisponibilités
     * Prend le statement, la date du jour, l'id de l'employé
     * Renvoie la liste des créneaux
     * */
    public static ArrayList<Creneau> getCreneauxIndisp(Statement st, Date jour, int id){
    	ResultSet rs = null;
    	try {
    		//Transformer la date en string
			String jourStr = jour.getYear()+1900+"-"+ (jour.getMonth()+1)+"-"+jour.getDate();
    		String date =  (char)34 + jourStr  + (char)34;
    		
    		//Requête pour récupérer les indisponibilités
    		String sql = "SELECT * FROM Creneau WHERE id ";
    		sql += "IN (SELECT id_creneau FROM Indisponible WHERE id_employer = ";
    		sql += id + ")";
    		sql += " AND DATE(date_heure_debut) = " + date;
    		sql+= " AND DATE(date_heure_fin) = " + date; 
    		sql += "AND id_employer = " + id;

    		
    		//execution de la requête
    		rs = st.executeQuery(sql);
    		
    		
    		ArrayList<Creneau> liste = new ArrayList();
        		
            while (rs.next()) {
            	//On stocke les données
            	int id_creneau = rs.getInt("id");
                Timestamp hd = rs.getTimestamp("date_heure_debut");
                Timestamp hf = rs.getTimestamp("date_heure_fin");
                
                Creneau c = new Creneau(hd,hf);
                c.setEmploye(id);
                c.setId(id_creneau);
                c.setDispo(false);
                	
                liste.add(c);
                
    		}
    		
            /*
    		for(Creneau c : liste) {
    			 System.out.println("Creneau:");
                 System.out.println(c.getDateDebut() + ", " + c.getDateFin() + 
                		 ", "+ c.getId() + ", "+ c.getEmploye());
    		}
    		*/
    		return liste;
    		
    		
    	}catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
    	}	return null;
    }
    
    /*
     * Méthode pour mettre à jour un créneau 
     * Prend un statement, l'id du créneau, de l'employé,
     * l'heure de début et de fin 
     * 
     * */
    public static void updateCreneau (Statement st, int idcreneau,int idemploye,String debut, String fin) {
    	try {
    		//la requête 
            String query = "UPDATE Creneau SET date_heure_debut = ";
            query+= (char)34 + debut + (char)34 + ",date_heure_fin = ";
            query+= (char)34 + fin + (char)34 ;
            query+= " WHERE id_employer = "+idemploye;
            query+= " AND id = "+idcreneau;         
            
            
            System.out.println(query);
            //execution de la requête
            
            st.executeUpdate(query);
    		
    		
    	} catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
    	}	
    }
    
    /*
     * Méthode pour supprimer l'indisponibilité 
     * Prend un statement et l'id du créneau 
     * 
     * */
    public static void deleteIndisp (Statement st, int id_creneau) {
    	try {           
    			//La requête pour supprimer l'indisponibilité
	            String delete = "DELETE FROM Indisponible WHERE id_creneau = ";
	            delete += id_creneau ;
	            
	            //la requête pour supprimer le créneau relié à l'indisponibilité
	            String sql = "DELETE FROM Creneau WHERE id = ";
	            String query = sql + id_creneau;
	            
	            System.out.println(delete);
	            
	            st.executeUpdate(delete);
	            st.executeUpdate(query);
    		
    		
    	} catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
    	}	
    }  
    
    /*
     * Méthode pour supprimer la réunion 
     * Prend un statement et l'id du créneau 
     * 
     * */
    public static void deleteReunion (Statement st, int id_creneau) {
    	try {           
    			//La requête pour supprimer la réunion 	
	            String delete = "DELETE FROM reunion WHERE id_creneau = ";
	            delete += id_creneau ;
	            
	            //la requête pour supprimer le créneau relié à l'indisponibilité
	            String sql = "DELETE FROM Creneau WHERE id = ";
	            String query = sql + id_creneau;
	            
	            System.out.println(delete);
	            
	            st.executeUpdate(delete);
	            st.executeUpdate(query);
    		
    		
    	} catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
    	}	
    }  
    
    /*
     * Méthode qui récupère les créneaux du jour donné en paramètre
     * Prend un statement et le jour 
     * Renvoie la liste des créneaux
     *  
     * */
    public static ArrayList<Creneau> getAllCreneau (Statement st, Date jour){
        try {
            //La requête sql
        	
        	
        	//Transformer la date de début et de fin en string 
        	String jourdebut = jour.getYear()+1900+"-"+ (jour.getMonth()+1)+"-"+jour.getDate();
        	String jourfin = jour.getYear()+1900+"-"+ (jour.getMonth()+1)+"-"+(jour.getDate()+6);
    		
        	String debut =  (char)34 + jourdebut  + (char)34;
        	String fin = (char)34 + jourfin  + (char)34;
    		
        	//la requête
            String sql = "SELECT * FROM Creneau WHERE DATE(date_heure_debut)  >= ";
            String query = sql + debut + " AND DATE (date_heure_fin) <= " +fin;
            query += " AND id NOT IN (SELECT id_creneau FROM Indisponible)";
            
            System.out.println(query);
            
            //Execution de la requête sql
            ResultSet rs = st.executeQuery(query);

            //Traitement du résultat
            ArrayList<Creneau> res = new ArrayList();
            while (rs.next()) {

                //On stocke les données
                int id_creneau = rs.getInt("id");
                Timestamp hd = rs.getTimestamp("date_heure_debut");
                Timestamp hf = rs.getTimestamp("date_heure_fin");
                int id_employer = rs.getInt("id_employer");

                //On ajoute le creneau
                Creneau c = new Creneau(hd,hf);
                c.setEmploye(id_employer);
                c.setId(id_creneau);

                res.add(c);
            }

            /*
            for(Creneau c : res) {
                //On affiche les éléments de la liste
                System.out.println("Creneau:");
                System.out.println(c.getDateDebut() + ", " + c.getDateFin() + ", "+c.getId() 
                +", " + c.getEmploye());
            }
            */
            return res;

        } catch (SQLException ex) {
            //Exceptions
            ex.printStackTrace();
        }
        return null;
    }
    
   
   /*
    * Méthode qui récupère les créneaux du jour 
    * Prend un string jour 
    * Renvoie la liste des créneaux 
    * */ 
    public static ArrayList<Creneau> getAllCreneauDay (String jour){
    	
    	//la liste 
    	ArrayList<Creneau> res = new ArrayList();
    	
    	//le statement 
    	Statement st1 = Back.connectionBase();
    	try {
    		
    		//la requête
    		String sql = "SELECT * FROM Creneau WHERE DATE(date_heure_debut) = ";
    		sql+= jour ;
    		
    		//execution de la requête
    		ResultSet rs = st1.executeQuery(sql);
    		
    		while(rs.next()) {
    			 //On stocke les données
                int id_creneau = rs.getInt("id");
                Timestamp hd = rs.getTimestamp("date_heure_debut");
                Timestamp hf = rs.getTimestamp("date_heure_fin");
                int id_employer = rs.getInt("id_employer");

                //On ajoute le creneau
                Creneau c = new Creneau(hd,hf);
                c.setEmploye(id_employer);
                c.setId(id_creneau);

                res.add(c);
    		}
    		
    		
    	}catch (SQLException ex) {
            //Exceptions
            ex.printStackTrace();
        }return res;
    }
    
    /*
     * Méthode qui récupère tous les employés disponbiles 
     * Prend un creneau 
     * Renvoie la liste des employés
     * 
     * */
    public static ArrayList<Employe> getEmployeDispo(Creneau creneau){
    	
    	//On récupère tous les employés
    	ArrayList<Employe> e = getAllEmployer(Back.connectionBase());
    	
    	//On initialise le résultat
    	ArrayList<Employe>res =new ArrayList<Employe>();
    	
    	for (Employe e1 : e) {
    		//Si l'employé est disponible, on l'ajoute au résultat
    		if(estDispo(e1,creneau)) {
    			res.add(e1);
    		}
    	}return res;
    	
    }
    
    /*
     * Méthode pour savoir si un employé est disponible à un créneau 
     * Prend un employé, un créneau 
     * Renvoie un booléen 
     * */
    public static boolean estDispo(Employe e,Creneau c) {
    	
    	//On récupère la date de début
    	Date temp = new Date(c.getDateDebut().getTime());
    	
    	//On récupère les créneaux de l'employé à cette date de début
    	ArrayList<Creneau> creneaux = getCreneauxEmp(Back.connectionBase(),temp,e.getId());
        System.out.println(creneaux.toString());
    	boolean res=false;
    	int i = 0;
    	while(!res && i<creneaux.size()){    
    		// Si le créneau n'est pas dans la liste, l'employé est disponible
    		if(c.estPasDedans(creneaux.get(i))) {
    			res =true;
    		}i++;
    	}
        if (creneaux.size()==0){
            res=true;
        }
    	return res;
    }
    
    /*
     * Méthode qui récupère les créneaux de réunion d'un employé du jour  
     * Prend un statement, un jour et l'id de l'employé
     * Renvoie la liste des créneaux 
     * 
     * */
    public static ArrayList<Creneau> getCreneauxReunion(Statement st, Date jour, int id){
    	ResultSet rs = null;
    	try {
    		//transforme la date en string 
			String jourStr = jour.getYear()+1900+"-"+ (jour.getMonth()+1)+"-"+jour.getDate();
    		String date =  (char)34 + jourStr  + (char)34;
    		
    		//la requête 
    		String sql = "SELECT r.id,c.id,c.date_heure_debut,"
    				+ "c.date_heure_fin FROM Creneau c"
    				+ " JOIN Reunion r ON r.id_creneau = c.id"
    				+ " WHERE r.id_employer = "+id;
    		sql += " AND DATE(c.date_heure_debut) = " + date;
    		sql += " AND DATE(c.date_heure_fin) = " + date; 
    		sql += " AND c.id_employer = " + id;

    		System.out.println(sql);
    		
    		//execution de la requête
    		rs = st.executeQuery(sql);
    		
    		
    		ArrayList<Creneau> liste = new ArrayList();
        		
            while (rs.next()) {
            	//on stcoke le résultat et on ajoute dans la liste
            	int id_reunion = rs.getInt("r.id");
            	int id_creneau = rs.getInt("c.id");
         
            	
                Timestamp hd = rs.getTimestamp("date_heure_debut");
                Timestamp hf = rs.getTimestamp("date_heure_fin");
                
                Creneau c = new Creneau(hd,hf);
                c.setEmploye(id);
                c.setId(id_creneau);
                c.setReunion(id_reunion);
           
                	
                liste.add(c);
                
    		}
    		
    		for(Creneau c : liste) {
    			 System.out.println("Creneau:");
                 System.out.println(c.getDateDebut() + ", " + c.getDateFin() +
                		 ", "+ c.getId() + ", "+ c.getEmploye());
    		}
    		return liste;
    		
    		
    	}catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
    	}	return null;
    }

    /*
     * Méthode qui vérifie si une reunion existe
     * Prend un statement et son id
     * Renvoie un booléen
     *
     * */
    public static boolean reunionExiste(Statement st, int id_reunion) {
        ResultSet rs = null;
        try {
            //la requête sql
            String query = "SELECT * FROM reunion WHERE id = ";

            query+= id_reunion;

            //execution de la requête
            rs = st.executeQuery(query);
            return (rs.next());

        }catch (SQLException ex) {
            //Exceptions
            ex.printStackTrace();
            return false;
        }
    }
    
    /*
     * Méthode qui met à jour une réunion 
     * Prend un statement , l'id de la réunion , la liste des employés
     * la date de debut et de fin 
     * 
     * */
    public static void updateReunion(Statement st, int id_reunion, ArrayList<Employe> e, String debut, String fin) {
       try {
    	   //On itère la liste des employés
    	   for(Employe e1:e) {
    		   int idemploye = e1.getId();
    		   if(employeExiste(st,idemploye)) {
    	    		
    			   //On met à jour le créneau de réunion 
    			   
    	            String query = "UPDATE Creneau SET date_heure_debut = ";
    	            query+= (char)34 + debut + (char)34 + ",date_heure_fin = ";
    	            query+= (char)34 + fin + (char)34 ;
    	            
    	            query+= " WHERE id_employer = "+idemploye;
    	            query+= " AND id IN "
    	            		+ "(SELECT id_creneau FROM reunion "
    	            		+ "WHERE id = "+id_reunion+")" ;         
    	        
    	         
    	            st.executeUpdate(query);
    	    		}
    		   
    	   }
       }catch (SQLException ex) {
           ex.printStackTrace();
       }
    }

    public static int getIdLastReunion(Statement st) {
        try {
            String query = "SELECT id FROM reunion ORDER BY id DESC LIMIT 1";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    public static void retireReunions(Statement st, int id_reunion) {
    	try {
    		String sql = "SELECT id_creneau FROM reunion WHERE id =";
    		sql += id_reunion;
    		
    		ResultSet rs = st.executeQuery(sql);
    		
    		while (rs.next()) {
    			int id_creneau = rs.getInt("id_creneau");
    			deleteReunion(Back.connectionBase(),id_creneau);
    		}
    	}catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public static boolean estReserve(int id, Date jour,String service){
    	ResultSet rs = null;
    	String jourStr = jour.getYear()+1900+"-"+ (jour.getMonth()+1)+"-"+jour.getDate();
 		String date =  (char)34 + jourStr  + (char)34;
 		
    	try {
    		String sql = "SELECT * FROM Reservations WHERE id_table = "+id;
    		sql += " AND jour = " +  date;
    		sql += " AND service = " +   (char)34 + service + (char)34 ;
    		System.out.println(sql);
    		rs = Back.connectionBase().executeQuery(sql);
            return (rs.next());
    	}catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }  
    }
    
    public static boolean estReserve(int id, String service){
    	ResultSet rs = null;
    	try {
    		String sql = "SELECT * FROM Reservations WHERE id_table = "+id;
    		rs = Back.connectionBase().executeQuery(sql);
            return (rs.next());
    	}catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }  
    }
    
    public static void retireReservation(int id, String service) {
    	try {
    		String sql = "DELETE FROM reservations WHERE id_table = "+id;
    		sql += " AND service = "+ (char)34 + service + (char)34;
   
    		Back.connectionBase().executeUpdate(sql);
    	}catch (SQLException ex) {
            ex.printStackTrace();
        }  
    }
    public static void occupeTable(int id, String service) {
    	try {
    		if(estReserve(id,service)) {
    			retireReservation(id,service);
    		}
    		
    		String sql = "INSERT INTO Tables_prises (id_table,service) VALUES (";
    		sql += id + ", " + (char)34 + service + (char)34 + ")";
    		
    		System.out.println(sql);
    		
    		Back.connectionBase().executeUpdate(sql);
    		
    	}catch (SQLException ex) {
            ex.printStackTrace();
        }  	
    }
    
    
    public static void reserveTable(int id, Date jour, String service, String nom_client) {
    	try {
    		String jourStr = jour.getYear()+1900+"-"+ (jour.getMonth()+1)+"-"+jour.getDate();
    		String date =  (char)34 + jourStr  + (char)34;
    		
    		String sql = "INSERT INTO Reservations (id_table,service,jour,nom_client) VALUES (";
    		sql += id + ", " + (char)34 + service + (char)34 + ", " + date + ", " +
    				(char)34 + nom_client + (char)34 + ")";
    		
    		Back.connectionBase().executeUpdate(sql);
    	}catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean estOccupe(int id,String service) {
    	ResultSet rs = null;
    	try {
    		String sql = "SELECT * FROM Tables_prises WHERE id_table = "+id;
    		sql += " AND service = "+(char)34 + service + (char)34;
    		System.out.println(sql);
    		rs = Back.connectionBase().executeQuery(sql);
    		return rs.next();
    	}catch (SQLException ex) {
            ex.printStackTrace();
            return false;
    	}
    }
    
    
    public static void deleteOccupe(int id, String service) {
        try {
            String sql = "DELETE FROM tables_prises WHERE id_table = " + id;
            sql += " AND service = " + (char) 34 + service + (char) 34;
            Back.connectionBase().executeUpdate(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public static void deleteReserve(int id, String service) {
        try {
            String sql = "DELETE FROM reservations WHERE id_table = " + id;
            sql += " AND service = " + (char) 34 + service + (char) 34;
            Back.connectionBase().executeUpdate(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /* cree la table */
    public static void creeTable(int numero, int nbPlaces) {
        try {
            //La requête
            String sql = "INSERT INTO Tables (numero,nb_places) VALUES(";
            sql += numero + ",";
            sql += nbPlaces;
            sql += ")";

            System.out.println(sql);

            Back.connectionBase().executeUpdate(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateTable(int id, int numero, int nbPlaces) {
        try {
            //La requête
            String sql = "UPDATE Tables SET numero = ";
            sql += numero;
            sql += ", nb_places = ";
            sql += nbPlaces;
            sql += " WHERE Tables.id = ";
            sql += id;

            System.out.println(sql);

            Back.connectionBase().executeUpdate(sql);


        } catch (SQLException ex) {
            //Exceptions
            ex.printStackTrace();
        }
    }
    
    /*libere la table*/
    public static void libereTable(int id, String service) {
            if (estOccupe(id, service)) {
                deleteOccupe(id, service);
            }
            
            if(estReserve(id,service)) {
            	deleteReserve(id,service);
            }        

    }
    
    public static Date getAJD() {
    	java.util.Date d1 = new java.util.Date();
    	
    	
    	return new Date(d1.getTime());
    }
    
    
    private static String getJour(java.util.Date date) {
    	 int jour = date.getDay(); 
    	 switch(jour) {
         case 0 : return "Dimanche";
         case 1 : return "Lundi";
         case 2 : return "Mardi";
         case 3 : return "Mercredi";
         case 4 : return "Jeudi";
         case 5 : return "Vendredi";
         case 6 : return "Samedi";
         default :
             return "";
    	 }
    }
    
    private static	String  getMonth(java.util.Date date) {
        int jour = date.getMonth();

        switch(jour) {

            case 0 : return "Janvier";
            case 1 : return "Fevrier";
            case 2 : return "Mars";
            case 3 : return "Avril";
            case 4 : return "Mai";
            case 5 : return "Juin";
            case 6 : return "Juillet";
            case 7 : return "Août";
            case 8 : return "Septembre";
            case 9 : return "Octobre";
            case 10 : return "Novembre";
            case 11 : return "Decembre";

            default :
                System.out.println("Erreur");
                break;
        }
        return "";
    }
    
    public static String dateToString(Date date) {
    	java.util.Date d = new java.util.Date(date.getTime());
    	
    	String res = getJour(d) +" " + d.getDate() + " "+ getMonth(d);
    	
    	return res;
    	
    }

    /*recupere les tables d'un service donné
    il faut verifier si la table est occupé ou reservée*/
    public static ArrayList<Table> getTables(Date jour, String service) {
        ResultSet rs = null;
        
        String jourStr = jour.getYear()+1900+"-"+ (jour.getMonth()+1)+"-"+jour.getDate();
		String date =  (char)34 + jourStr  + (char)34;
		
        ArrayList<Table> tables = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Tables";
            rs = Back.connectionBase().executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int numero = rs.getInt("numero");
                int nbPlaces = rs.getInt("nb_places");

                Table table = new Table(id,numero,nbPlaces,null, null);

                // Vérifier si la table est occupée ou réservée
                if (estOccupe(id, service)) {
                    table.setEtat("occup");
                } else if (estReserve(id,jour,service)) {
                    String sql2 = "SELECT nom_client FROM Reservations WHERE id_table = "+id;
                    sql2 += " AND service = ";
                    sql2 += (char) 34 + service + (char) 34;
                    sql2 += " AND jour = ";
                    sql2 += date;
                    
              
                    ResultSet rs2 = Back.connectionBase().executeQuery(sql2);

                    while (rs2.next()) {
                        String nom = rs2.getString("nom_client");
                        table.setReservation(nom);
                        
                    }
                    table.setEtat("reserv");
                    
                } else {
                    table.setEtat("libre");
                }

                // Ajouter la table à la liste
                tables.add(table);

                for(Table t : tables) {
                    System.out.println(t.getEtat() + " " + t.getReservation() + " " + t.getId() + " " + t.getNbPlaces() + " " + t.getNumero());
                }
            }

            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        return tables;
    }
    
    public static void updateTableReserve(int id,String service,Date jour,String nom_client) {
    	
    	String jourStr = jour.getYear()+1900+"-"+ (jour.getMonth()+1)+"-"+jour.getDate();
		String date =  (char)34 + jourStr  + (char)34;
		
    	try {
    		String sql = "UPDATE Reservations SET service = ";
    		sql += (char) 34 + service + (char) 34 +
    				" , jour = " + date + 
    				", nom_client = " +
    				(char) 34 +  nom_client+ (char) 34 ;
    		sql += " WHERE id_table = " + id;
    		Back.connectionBase().executeUpdate(sql);
    		
    		
    		
    		
    	} catch (SQLException ex) {
            ex.printStackTrace();
    	}
    }
    
    public static void deleteGen (int id, String table,String idTable) {
    	try {
    		String sql = "DELETE FROM ";
    		sql += table;
    		sql += " WHERE " + idTable + " = " +id;
  
    		Back.connectionBase().executeUpdate(sql);
    		
    	}catch (SQLException ex) {
            ex.printStackTrace();
    	}
    }
    
    
    
    public static void deleteTable(int id) {
    	deleteGen(id,"tables_prises","id_table");
    	deleteGen(id,"reservations","id_table");
    	deleteGen(id,"tables","id");
    }
}


