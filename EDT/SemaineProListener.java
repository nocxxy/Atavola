package EDT;

import Back.Back;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class SemaineProListener implements ActionListener {
    private EDTPanel e;

    public SemaineProListener(EDTPanel e){
        this.e = e;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.e.setDebut(Back.getSemaineSuivante(this.e.getDebut()));
        this.e.creeCreneauxEDT(this.e.getEmpEDT(), this.e.getEmpConn());
        this.e.creePanelBas(this.e.getEmpEDT());

    }
}
