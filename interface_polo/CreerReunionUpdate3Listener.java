package interface_polo;

import Back.Back;
import Front.Fonction.Creneau;
import Front.Fonction.Employe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.ArrayList;

public class CreerReunionUpdate3Listener implements ActionListener {

    private Statement st;
    private ModifierReunion2Frame f;

    public CreerReunionUpdate3Listener(Statement st,ModifierReunion2Frame f){
        this.st = st;
        this.f = f;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Creneau temp = this.f.getCreneau().getSelect();
        ArrayList<Employe> listeE = Back.getEmployeDispo(temp);
        ModifierReunion3Frame suiv =new ModifierReunion3Frame(st,listeE,this.f);
        suiv.setVisible(true);
        this.f.dispose();
    }
}
