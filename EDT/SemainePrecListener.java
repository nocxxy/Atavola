package EDT;

import Back.Back;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SemainePrecListener implements ActionListener {

    private EDTPanel e;

    public SemainePrecListener(EDTPanel e){
        this.e = e;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("EN revoir--------------------------------------------------------------------------------------");
        System.out.println(this.e.getDebut().toString());
        this.e.setDebut(Back.getSemainePrecedente(this.e.getDebut()));
        System.out.println(this.e.getDebut().toString());
        this.e.setAllCreneau(Back.getAllCreneauWeek(this.e.getSt(), this.e.getDebut()));
        this.e.creeCreneauxEDT(this.e.getEmpEDT(), this.e.getEmpConn());
        this.e.creePanelBas(this.e.getEmpEDT());




    }
}
