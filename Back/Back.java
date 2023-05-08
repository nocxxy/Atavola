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

            for(Creneau c : res) {
                //On affiche les éléments de la liste
                System.out.println("Creneau:");
                System.out.println(c.getDateDebut() + ", " + c.getDateFin() + ", "+c.getId() 
                +", " + c.getEmploye());
            }
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
    		
    		//Traitement du résultat
    		
    		ArrayList<Employe> res = new ArrayList();
    		while (rs.next()) {                //On stocke les données
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
    
    public static boolean connexionEmployer (Statement st, String login, String mdp) throws SQLException {
    	
    	//Initialisation de la varible pour executer la requête 
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
        System.out.println(cal.getTime().toString());
        Date jourPlus = new Date(cal.getTime().getTime());
        return jourPlus;
    }

    public static Date getSemaineSuivante(Date jour) {
        return jourPlusi(jour, 7);
    }

    public static Date getSemainePrecedente(Date jour) {
        return jourPlusi(jour, -7);
    }
    public static ArrayList<Creneau> getCreneauxEmp(Statement st,Date jour, int id){
        try {
            //La requête sql
        	String date = jour.getYear()+1900+"-"+ (jour.getMonth()+1)+"-"+jour.getDate();
            String sql = "SELECT * FROM Creneau WHERE DATE(date_heure_debut) = ";
            String query = sql + (char)34 + date + (char)34 + " AND DATE(date_heure_fin) = "+
                    (char)34 + date + (char)34 + " AND id_employer = " +id;
            
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
                

                //On ajoute le creneau
                Creneau c = new Creneau(hd,hf);
                c.setEmploye(id);
                c.setId(id_creneau);
                
                res.add(c);
            }

            for(Creneau c : res) {
                //On affiche les éléments de la liste
                System.out.println("Creneau:");
                System.out.println(c.getDateDebut() + ", " + c.getDateFin() +", "
                		+c.getId() + ", " + c.getEmploye());
            }
            return res;

        } catch (SQLException ex) {
            //Exceptions
            ex.printStackTrace();
        }
        return null;
    }
    
    
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
    
    public static void retireIndispOuTravail (Statement st,int id,String indispOuTravail) {
    	try {
	  	            
	            String delete = "DELETE FROM " +indispOuTravail +" WHERE id_employer = "+id;
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
    		if(creneauExiste(st,id)) {
    			retireIndispOuTravail(st,id,"Indisponible");
    			retireIndispOuTravail(st,id,"Travail");
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
    public static boolean travail (Statement st, int id,String debut,String fin) {
    	ResultSet rs = null;
    	try {
    		//Si l'employé existe et que le creneau existe
    		if(employeExiste(st,id) && creneauExiste(st,debut,fin,id)) {
    			
    			int id_creneau = getIdCreneau(st,debut,fin,id);
    			
    			//On vérifie si l'employé travaille
    			String query = "SELECT * FROM travail WHERE id_creneau =";
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
     * Méthode pour ajouter un creneau de travail 
     * Prend une liste de type arraylist, une heure de debut et de fin
     *
     * */
    public static void ajoutTravail (Statement st, ArrayList<Employe> e,String debut, String fin) {
    	try {
    		for (Employe e1 :e) {
    			int id = e1.getId();
    			//On fait l'ajout de creneau de travail sur un employé qui existe déjà 
    			if(employeExiste(st,id) && ! travail(st,id,debut,fin)) {
    			
    				if(! creneauExiste(st,debut,fin,id)) {
    					//Si le créneau n'existe pas on l'ajoute dans la base
    					insertCreneau(st,debut,fin,id);
    				}
    		 
    				int id_creneau = getIdCreneau(st,debut,fin,id);
            
            
    				//la requête pour ajouter le creneau de travail
    				String travail = "INSERT INTO travail (id_creneau,id_employer) VALUES (";
    				travail += id_creneau +",";
    				travail += id ;
    				travail += ")";
            
            
    				st.executeUpdate(travail);
    			}
    		}
    	} catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
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
	    		if(employeExiste(st,id) && estIndisponible(st,id,debut,fin)) {
	    			
	    		 
	            int id_creneau = getIdCreneau(st,debut,fin,id);
	            
	            
	            //la requête pour retirer l'indisponibilité
	            String delete = "DELETE FROM Indisponible WHERE id_creneau = ";
	            delete += id_creneau +" AND id_employer = ";
	            delete += id ;
	       
	            
	            
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
    				ajoutTravail(st,e,debut,fin);
    			}
    		}else {
    			//si la réunion est urgente, on l'ajoute peu importe
    			retireIndisp(st,e,debut,fin);
    			ajoutTravail(st,e,debut,fin);
    			
    		}
    		
    }
    
    public static void updateIndisp (Statement st, int idcreneau,int idemploye,String motif,String debut, String fin) {
    	try {
    		 
    		if(employeExiste(st,idemploye)) {
    		
            String query = "UPDATE Creneau SET date_heure_debut = ";
            query+= (char)34 + debut + (char)34 + ",date_heure_fin = ";
            query+= (char)34 + fin + (char)34 ;
            query+= " WHERE id_employer = "+idemploye;
            query+= " AND id = "+idcreneau;         
            
            String update = "UPDATE Indisponible SET motif = ";
            update+= (char)34 + motif + (char)34;
            update+= " WHERE id_employer = "+idemploye;
            update+= " AND id_creneau = "+idcreneau;
            
            
            System.out.println(query);
            System.out.println(update);
            st.executeUpdate(update);
            st.executeUpdate(query);
    		}
    		
    	} catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
    	}	
    }
    
    public static ArrayList<Creneau> getCreneauxIndisp(Statement st, Date jour, int id){
    	ResultSet rs = null;
    	try {

			String jourStr = jour.getYear()+1900+"-"+ (jour.getMonth()+1)+"-"+jour.getDate();
    		String date =  (char)34 + jourStr  + (char)34;
    		
    		String sql = "SELECT * FROM Creneau WHERE id ";
    		sql += "IN (SELECT id_creneau FROM Indisponible WHERE id_employer = ";
    		sql += id + ")";
    		sql += " AND DATE(date_heure_debut) = " + date;
    		sql+= " AND DATE(date_heure_fin) = " + date; 
    		sql += "AND id_employer = " + id;

    		
    		rs = st.executeQuery(sql);
    		
    		
    		ArrayList<Creneau> liste = new ArrayList();
        		
            while (rs.next()) {
            	int id_creneau = rs.getInt("id");
                Timestamp hd = rs.getTimestamp("date_heure_debut");
                Timestamp hf = rs.getTimestamp("date_heure_fin");
                
                Creneau c = new Creneau(hd,hf);
                c.setEmploye(id);
                c.setId(id_creneau);
                	
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
    public static void updateCreneau (Statement st, int idcreneau,int idemploye,String debut, String fin) {
    	try {
    		
            String query = "UPDATE Creneau SET date_heure_debut = ";
            query+= (char)34 + debut + (char)34 + ",date_heure_fin = ";
            query+= (char)34 + fin + (char)34 ;
            query+= " WHERE id_employer = "+idemploye;
            query+= " AND id = "+idcreneau;         
            
            
            System.out.println(query);
            st.executeUpdate(query);
    		
    		
    	} catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
    	}	
    }
    
    public static void deleteIndisp (Statement st, int id_creneau) {
    	try {           
	            String delete = "DELETE FROM Indisponible WHERE id_creneau = ";
	            delete += id_creneau ;
	            
	            System.out.println(delete);
	            
	            st.executeUpdate(delete);
    		
    		
    	} catch (SQLException ex) {
			//Exceptions 
		    ex.printStackTrace();
    	}	
    }  
    
    
    
     
}
