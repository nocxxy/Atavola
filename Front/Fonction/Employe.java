package Front.Fonction;

public class Employe {
    //_________________________Attribut_________________________________

    private String nom;
    private String prenom;
    private String login;
    private String rg;

    // ________________________Constructeur/getteur____________________________

    public Employe(String nom, String prenom, String login, String rg) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.rg = rg;
    }

    public String getPrenom() {
        return this.prenom;
    }
    public String getNom() {
        return this.nom;
    }
    public String getLogin() {
        return this.login;
    }
    public String getRang() {
        return this.rg;
    }
}
