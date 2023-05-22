package interface_polo;

import Back.Back;
import Front.Fonction.Employe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AfficheDonneEmployeListener implements ActionListener {

    private ChoixEmployer c;
    private ModifierEmploye1Frame f;

    AfficheDonneEmployeListener(ChoixEmployer c, ModifierEmploye1Frame f){
        this.c = c;
        this.f = f;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Employe f = Back.getEmployer(this.f.getSt() ,this.c.getSelect());
        this.f.getNom().setText(f.getNom());
        this.f.getPrenom().setText(f.getPrenom());
    }
}
