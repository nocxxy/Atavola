package Front.Fonction;

public class Table {
    // ________________________________attribut_______________________
    private int nbpersonne;
    private String statut ;

    //_________________________________Constructor________________________

    public Table(int nbpersonne , String statut) {
        this.nbpersonne=nbpersonne;
        this.statut=statut;
    }
    //_________________________________Getter/Setter_____________________
    public int getNbpersonne() {
        return nbpersonne;
    }

    public void setNbpersonne(int nbpersonne) {
        this.nbpersonne = nbpersonne;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void additionTable(Table t){
        this.nbpersonne= t.getNbpersonne()+this.nbpersonne;
    }
}
