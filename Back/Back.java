package Back;

import Front.Fonction.Creneau;
import Front.Fonction.Employe;

import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Back {
    //methode permettant de se connecter à la base de donnée
    //ne prend aucun paramètre mais renvoie la connection qui nous permettra de faire des requetes
    public static Statement connectionBase() {
        try {
            //Chargement driver
            Class.forName("com.mysql.jdbc.Driver");

            //Créer connection
            String dbName = "atavola";
            String dbIP = "localhost";
            String dbUser = "atavola";
            String dbPwd = "9F0";

            String url = "jdbc:mysql://" + dbIP + ":3306/" + dbName;

            Connection con = DriverManager.getConnection(url, dbUser, dbPwd);

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
    public static void insertCreneau(Statement st, Date date_heure_debut, Date date_heure_fin, int id_employer) {
        try {
            //La requête sql
            String sql = "INSERT INTO Creneau (date_heure_debut,date_heure_fin, id_employer) VALUES (";
            String query = sql + date_heure_debut + ",";
            query += date_heure_fin + ",";
            query += id_employer;
            query += ")";

            /*
            Besoin d'aide pour le format de la date, quand on appelle la foncton dans main.java
             */
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
}