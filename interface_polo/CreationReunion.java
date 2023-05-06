package interface_polo;

import Back.Back;
import Front.Fonction.Employe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.ArrayList;

public class CreationReunion implements ActionListener {

    private Reunion3Frame f3;
    private Reunion2Frame f2;
    private Statement st;
    CreationReunion(Reunion3Frame f3, Statement st , Reunion2Frame f2){
        this.f2 = f2;
        this.f3 =f3;
        this.st = st;
    }
    private String getHeureSynthaxe(String h){
        String heure =h.substring(0,2);
        String min = h.substring(3,5);
        return heure + ":"+min+":00";
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String horraireDebut = this.f3.getA().getText() + "-" + this.f3.getM().getText() + "-" + this.f3.getD().getText() + " " + getHeureSynthaxe(this.f3.getHd().getText());
        String horraireFin = this.f3.getA().getText() + "-" + this.f3.getM().getText() + "-" + this.f3.getD().getText() + " " + getHeureSynthaxe(this.f3.getHf().getText());
        ArrayList<Employe> le = f2.getEmployeSelect();
//        System.out.println("test");
        Back.ajout_reunion(this.st,le,horraireDebut,horraireFin,false);
    }
}
