package interface_polo;

import Back.Back;
import Front.Fonction.Creneau;
import Front.Fonction.Employe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class RecupEmployeDispoListener implements ActionListener {

    private ChoixCreneau c ;
    private ReunionBis2Frame f;

    RecupEmployeDispoListener(ChoixCreneau c , ReunionBis2Frame f){
        this.c = c;
        this.f = f;
    }


    private String afficheListeEmployer(ArrayList<Employe> listeE){
        String res ="<html>";
        for (Employe e : listeE){
            res+= (e.getPrenom() + " "+e.getNom()+"<br> ");
        }
        res+="</html>";
        return res;
    }

    @SuppressWarnings("deprecation")
    private Date copyDate(Date d){
        int year = d.getYear();
        int month = d.getMonth();
        int day = d.getDate();
        int hours = d.getHours();
        int min = d.getMinutes();
        return new Date(year,month,day,hours,min);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Creneau temp = new Creneau(copyDate(this.c.getSelect().getDateDebut()),copyDate(this.c.getSelect().getDateFin()));
        ArrayList<Employe> listeE = Back.getEmployeDispo(temp);
        this.f.getNbEmployer().setText(listeE.size()+"");
        this.f.getListeNomEmploye().setText(afficheListeEmployer(listeE));
    }
}
