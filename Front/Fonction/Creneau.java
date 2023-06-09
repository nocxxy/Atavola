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
    private int reunion;
    
   

	public int getReunion() {
		return reunion;
	}

	public void setReunion(int reunion) {
		this.reunion = reunion;
	}

	// ________________________________________________Constructeur/Setter_____________________________
    public Creneau (Date hd, Date hf) {//, int e ){//, boolean dispo) {
        this.heuredebut = hd;
        this.heurefin = hf;
    }

    // ________________________________________________Constructeur/Setter_____________________________
    public Creneau (Date hd, Date hf,int e, boolean dispo,int id) {//, int e ){//, boolean dispo) {
        this.heuredebut = hd;
        this.heurefin = hf;
        this.employe = e;
        this.dispo = dispo;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

    @SuppressWarnings("deprecation")
    public	String  getJourCreneau() {
        int jour = this.heuredebut.getDay();

        switch(jour) {
            case 0 : return "Dim";
            case 1 : return "Lun";
            case 2 : return "Mar";
            case 3 : return "Mer";
            case 4 : return "Jeu";
            case 5 : return "Ven";
            case 6 : return "Sam";
            default :
                System.out.println("Erreur");
                break;
        }
        return "";
    }
    @SuppressWarnings("deprecation")
    public	String  getMonth() {
        int jour = this.heuredebut.getMonth();

        switch(jour) {

            case 0 : return "Jan";
            case 1 : return "Fev";
            case 2 : return "Mar";
            case 3 : return "Avr";
            case 4 : return "Mai";
            case 5 : return "Juin";
            case 6 : return "Juil";
            case 7 : return "Aou";
            case 8 : return "Sep";
            case 9 : return "Oct";
            case 10 : return "Nov";
            case 11 : return "Dec";

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
    	res+= this.heurefin.getMinutes() - this.heuredebut.getMinutes();
    	
    	return res;
    }

    /*
    public static void main(String[] args) {
        Date d1 = new Date();
        Employe e = new Employe("Prenom","nom","couc","blabal");
        Creneau c = new Creneau(d1,d1,e,true);
        System.out.println(c.getJourCreneau());
        System.out.println(c.getNumSemaineCreneau());

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

        // test de la fonction getSemaineSuivante() et getSemainePrecedente()
        Creneau c4 = new Creneau(new Date(), new Date());
        Date lundiSemaineSuivante = c4.getSemaineSuivante(new Date());
        System.out.println(lundiSemaineSuivante); // Mon May 08 17:02:15 CEST 2023

        Date lundiSemainePrecedente = c4.getSemainePrecedente(new Date());
        System.out.println(lundiSemainePrecedente); // Mon Apr 24 17:02:15 CEST 2023
    }
    */

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  this.getJourCreneau() + " " + this.getDateDebut().getDate()+ " " +this.getMonth() + " "+ (this.heuredebut.getYear()+1900) +" "+ this.heuredebut.getHours() +"h"+ this.heuredebut.getMinutes() + " -> " + this.heurefin.getHours() +"h"+ this.heurefin.getMinutes();
    }
    
    public boolean estPasDedans(Creneau c) {
    	boolean res=false;
    	int hd = c.getHeureDebut();
    	int md = c.getDateDebut().getMinutes();
    	
    	int hf = c.getHeureFin();
    	int mf = c.getDateFin().getMinutes();
    	
    	
    	if((this.getHeureDebut()>=hf) && (this.getDateDebut().getMinutes()>=mf)){
    		res = true;
    	}if((this.getHeureFin()<=hd) && (this.getDateFin().getMinutes()<=md)) {
    		res=true;
    	}
    	
    	return res;
    }
    
    
    
}
