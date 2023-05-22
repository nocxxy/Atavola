package interface_polo;

import Front.Fonction.Creneau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.Date;

import EDT.EDTPanel;

public class CreerReunion2Listener implements ActionListener {

    private ReunionBis1Frame f;
    private Statement st;
	private EDTPanel edtpan;

    CreerReunion2Listener(ReunionBis1Frame f, Statement st, EDTPanel edtpan){
        this.f = f;
        this.st = st;
        this.edtpan = edtpan;
    }
    @SuppressWarnings("deprecation")
    @Override
    public void actionPerformed(ActionEvent e) {
        this.f.dispose();
        int year = Integer.parseInt(this.f.getA().getText());
        int month= Integer.parseInt(this.f.getM().getText());
        int day = Integer.parseInt(this.f.getD().getText());
        Date d1 = new Date(year-1900,month,day);
        Creneau c = new Creneau(d1,d1);
        ReunionBis2Frame f = new ReunionBis2Frame(this.st,c,this.f,edtpan);
        f.setVisible(true);
    }
}
