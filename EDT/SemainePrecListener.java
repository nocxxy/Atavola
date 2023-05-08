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
        this.e.setDebut(Back.getSemainePrecedente(this.e.getDebut()));
        this.e.setAllCreneau(Back.getAllCreneauWeek(this.e.getSt(), this.e.getDebut()));

        System.out.println("Prec");
        this.e.remove(this.e.getEdt());
        this.e.remove(this.e.getPanelBas());

        this.e.setEdt(this.e.creeCreneauxEDT(this.e.getEmpEDT(), this.e.getEmpConn()));
        this.e.add(this.e.getEdt(), BorderLayout.CENTER);
        this.e.setPanelBas(this.e.creePanelBas(this.e.getEmpEDT()));
        this.e.add(this.e.getEdt(),BorderLayout.SOUTH);


    }
}
