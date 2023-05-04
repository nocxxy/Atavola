package Front.Fonction;
import java.text.SimpleDateFormat;
import java.util.*;

public class Creneau {
    private Date heuredebut;
    private Date heurefin;
    //private Employe employe;
    private int employe;
    private boolean dispo;
    private int id;

    // ________________________________________________Constructeur/Setter_____________________________
    public Creneau (Date hd, Date hf) {//, int e ){//, boolean dispo) {
        this.heuredebut = hd;
        this.heurefin = hf;
        //this.employe = e;
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

    public int getEmploye() {
        return employe;
    }

    public void setEmploye(int employe) {
        this.employe = employe;
    }
    
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
    
    public int getTempCreneau() {
    	int res =0;
    	res += this.heurefin.getHours() * 60 - this.heuredebut.getHours() *60 ;
    	res+= this.heurefin.getMinutes() * 60 - this.heuredebut.getMinutes() *60;
    	
    	return res;
    }

    public static void main(String[] args) {
        /*Date d1 = new Date();
        Employe e = new Employe("Prenom","nom","couc","blabal");
        Creneau c = new Creneau(d1,d1,e,true);
        System.out.println(c.getJourCreneau());
        System.out.println(c.getNumSemaineCreneau());*/

        // test de la fonction getLundi()
        // resultat au format : EEE MMM dd HH:mm:ss zzz yyyy
        Creneau c1 = new Creneau(new Date(), new Date());
        Date lundi = c1.getLundi();
        System.out.println(lundi);

        // test de la fonction jourSemaineAffichage()
        // resultat pour lundi 8 mai :
        //[lundi 8 mai, mardi 9 mai, mercredi 10 mai, jeudi 11 mai, vendredi 12 mai, samedi 13 mai, dimanche 14 mai]
        Creneau c2 = new Creneau(new Date(), new Date());
        Date debut = new GregorianCalendar(2023, Calendar.MAY, 8).getTime();
        ArrayList<String> jours = c2.jourSemaineAffichage(debut);
        System.out.println(jours);

        // test de la fonction jourPlusi()
        Creneau c3 = new Creneau(new Date(), new Date());
        Date date = new GregorianCalendar(2023, Calendar.MAY, 1).getTime();
        System.out.println(date); // affiche "Mon May 01 00:00:00 CEST 2023"
        Date datePlus2 = c3.jourPlusi(date, 2);
        System.out.println(datePlus2); // affiche "Wed May 03 00:00:00 CEST 2023"
        Date dateMoins3 = c3.jourPlusi(date, -3);
        System.out.println(dateMoins3);// affiche "Fri Apr 28 00:00:00 CEST 2023"

        Creneau c4 = new Creneau(new Date(), new Date());
        Date lundiSemaineSuivante = c4.getSemaineSuivante(new Date());
        System.out.println(lundiSemaineSuivante);
    }

    public void setId(int id) {
        this.id = id;
    }

    /*
     * Permet de récupérer le lundi de la semaine actuelle
     */
    public Date getLundi() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /*
     * Renvoie liste des jours a afficher d'une semaine
     */
    public ArrayList<String> jourSemaineAffichage(Date debut) {
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

    public Date jourPlusi(Date jour, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(jour);
        cal.add(Calendar.DAY_OF_MONTH, i);
        return cal.getTime();
    }

    public Date getSemaineSuivante(Date jour) {
        return jourPlusi(getLundi(), 7);
    }
}
