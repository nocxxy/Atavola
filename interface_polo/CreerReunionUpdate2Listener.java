package interface_polo;

import Front.Fonction.Creneau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.Date;

public class CreerReunionUpdate2Listener implements ActionListener {

    private Statement st;
    private ModifierReunion1Frame f;

    public CreerReunionUpdate2Listener(Statement st,ModifierReunion1Frame f){
        this.st=st;
        this.f=f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.f.dispose();
        int year = Integer.parseInt(this.f.getA().getText());
        int month= Integer.parseInt(this.f.getM().getText());
        int day = Integer.parseInt(this.f.getD().getText());
        Date d1 = new Date(year-1900,month,day);
        Creneau c = new Creneau(d1,d1);
        ModifierReunion2Frame f = new ModifierReunion2Frame(this.st,c,this.f);
        f.setVisible(true);
    }
}
