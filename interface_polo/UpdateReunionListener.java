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

public class UpdateReunionListener implements ActionListener {


    private ModifierReunion3Frame f;
    private Statement st;


    public UpdateReunionListener(ModifierReunion3Frame f, Statement st){
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
        int id_reunion = this.f.getF().getF().getF().getCreneau().get(0).getReunion();
        ArrayList<Employe> temp =new ArrayList<Employe>();

        for(int i=0; i<listE.size();i++){
            if(s.get(i).isSelected()){
                temp.add(listE.get(i));
            }
        }
        Back.updateReunion(this.st,id_reunion,temp,getDateSynthaxe(c.getDateDebut()),getDateSynthaxe(c.getDateFin()));
        this.f.dispose();
    }
}
