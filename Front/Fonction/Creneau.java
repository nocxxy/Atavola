package Front.Fonction;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Creneau {
    private String id;
    private Date heuredebut;
    private Date heurefin;
    //private Employe employe;
    private int employe;
    private boolean dispo;

    // ________________________________________________Constructeur/Setter_____________________________
    public Creneau (String id,Date hd, Date hf, int e ){//, boolean dispo) {
        this.id = id;
        this.heuredebut = hd;
        this.heurefin = hf;
        this.employe = e;
        //this.dispo = dispo;
    }

    //___________________________________________________getter/setter______________________________
    @SuppressWarnings("deprecation")
    public int getHeureDebut() {
        int res = this.heuredebut.getHours();
        return res;
    }
    @SuppressWarnings("deprecation")
    public int getHeureFin() {
        int res = this.heurefin.getHours();
        return res;
    }



    public Date getDateDebut() {
        return this.heuredebut;
    }
    public Date getDateFin() {
        return this.heurefin;
    }

    public void setHeuredebut(Date heuredebut) {
        this.heuredebut = heuredebut;
    }


    public void setHeurefin(Date heurefin) {
        this.heurefin = heurefin;
    }

    /*public Employe getEmploye() {
        return employe;
    }*/

    /*public void setEmploye(Employe employe) {
        this.employe = employe;
    }*/

    public boolean isDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    @SuppressWarnings("deprecation")
    public	String  getJourCreneau() {
        int jour = this.heuredebut.getDay();

        switch(jour) {
            case 0 : return "Dimanche";
            case 1 : return "Lundi";
            case 2 : return "Mardi";
            case 3 : return "Mercredi";
            case 4 : return "Jeudi";
            case 5 : return "Vendredi";
            case 6 : return "Samedi";
            default :
                System.out.println("Erreur");
                break;
        }
        return "";
    }

    public int getNumSemaineCreneau() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.heuredebut);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public static void main(String[] args) {
        /*Date d1 = new Date();
        Employe e = new Employe("Prenom","nom","couc","blabal");
        Creneau c = new Creneau(d1,d1,e,true);
        System.out.println(c.getJourCreneau());
        System.out.println(c.getNumSemaineCreneau());*/

    }
}
