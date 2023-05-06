package interface_polo;

import Back.Back;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class CreationCreneauListener implements ActionListener {

    private CreneauFrame f;
    private Statement st;

    CreationCreneauListener(Statement st, CreneauFrame frame){
        this.f = frame ;
        this.st = st;
    }
    private String getHeureSynthaxe(String h){
        String heure =h.substring(0,2);
       String min = h.substring(3,5);
        return heure + ":"+min+":00";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String login  = this.f.getE().getSelect();
        String horraireDebut = this.f.getA().getText() + "-" + this.f.getM().getText() + "-" + this.f.getD().getText() + " " + getHeureSynthaxe(this.f.getHd().getText());
        String horraireFin = this.f.getA().getText() + "-" + this.f.getM().getText() + "-" + this.f.getD().getText() + " " + getHeureSynthaxe(this.f.getHf().getText());
        int id = Back.getEmployer(this.st,login).getId();
        Back.insertCreneau(this.st,horraireDebut,horraireFin,id);
        this.f.dispose();
    }


}
