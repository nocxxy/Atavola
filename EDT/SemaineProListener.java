package EDT;

import Back.Back;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SemaineProListener implements ActionListener {
    private EDTPanel e;

    public SemaineProListener(EDTPanel e){
        this.e = e;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("COUCOU--------------------------------------------------------------------------------------");
        System.out.println(this.e.getDebut().toString());
        this.e.setDebut(Back.getSemaineSuivante(this.e.getDebut()));
        System.out.println(this.e.getDebut().toString());
        this.e.setAllCreneau(Back.getAllCreneauWeek(this.e.getSt(), this.e.getDebut()));
        this.e.creeCreneauxEDT(this.e.getEmpEDT(), this.e.getEmpConn());
        this.e.creePanelBas(this.e.getEmpEDT());




    }
}
