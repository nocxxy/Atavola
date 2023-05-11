package interface_polo;

import Back.Back;
import Front.Fonction.Creneau;
import Front.Fonction.Employe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class CreerReunionListener implements ActionListener {

    private Statement st;
    private ReunionBis3Frame f;

    public CreerReunionListener(Statement st, ReunionBis3Frame f){
        this.f = f;
        this.st = st;
    }

    private String getDateSynthaxe(Date d){
        int year = d.getYear() + 1900;
        int month = d.getMonth() +1;
        int day = d.getDate();
        int hours = d.getHours();
        int min = d.getMinutes();
        String res = year +"-"+month+"-"+day+" "+hours+":"+min+":00";
        return res;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Creneau c = this.f.getF().getCreneau().getSelect();
        ArrayList<JCheckBox> s = this.f.getSelect();
        ArrayList<Employe> listE= this.f.getListEmploye();
        Back.ajout_reunion(this.st,listE,getDateSynthaxe(c.getDateDebut()),getDateSynthaxe(c.getDateFin()),this.f.getF().getF().getUrgent().isSelected());
        this.f.dispose();

    }
}
