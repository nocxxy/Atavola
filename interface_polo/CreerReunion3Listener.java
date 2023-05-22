package interface_polo;

import Back.Back;
import EDT.EDTPanel;
import Front.Fonction.Creneau;
import Front.Fonction.Employe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class CreerReunion3Listener implements ActionListener {

    private ReunionBis2Frame f;
    private Statement st;
	private EDTPanel edtpan;

    public CreerReunion3Listener(ReunionBis2Frame f, Statement st, EDTPanel edtpan){
        this.st = st;
        this.f = f;
        this.edtpan = edtpan;
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        Creneau temp = this.f.getCreneau().getSelect();
        ArrayList<Employe> listeE = Back.getEmployeDispo(temp);
        ReunionBis3Frame suiv =new ReunionBis3Frame(st,listeE,this.f,edtpan);
        suiv.setVisible(true);
        this.f.dispose();

    }
}
