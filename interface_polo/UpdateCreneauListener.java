package interface_polo;

import Back.Back;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class UpdateCreneauListener implements ActionListener {

    private UpdateCreneauFrame f;
    private Statement st;

    public UpdateCreneauListener(Statement st , UpdateCreneauFrame f){
        this.st = st;
        this.f = f;
    }

    private String getHeureSynthaxe(String h){
        String heure =h.substring(0,2);
        String min = h.substring(3,5);
        return heure + ":"+min+":00";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int employe  = this.f.getCreneau().getEmploye();
        int id = this.f.getCreneau().getId();
        String horraireDebut = this.f.getA().getText() + "-" + this.f.getM().getText() + "-" + this.f.getD().getText() + " " + getHeureSynthaxe(this.f.getHd().getText());
        String horraireFin = this.f.getA().getText() + "-" + this.f.getM().getText() + "-" + this.f.getD().getText() + " " + getHeureSynthaxe(this.f.getHf().getText());

        Back.updateCreneau(this.st,id,employe,horraireDebut,horraireFin);

        this.f.dispose();
    }
}
