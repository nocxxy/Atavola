package interface_polo;

import Back.Back;
import EDT.EDTPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class RetirerCreneauListener implements ActionListener {

    private RetirerCreneauFrame f;

    private Statement st;

	private EDTPanel edtpan;

    RetirerCreneauListener(RetirerCreneauFrame f , Statement st,EDTPanel edtpan){
        this.f = f;
        this.st = st;
        this.edtpan = edtpan;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id =this.f.getChoix().getSelect().getId();
        Back.deleteCreneau(this.st,id);
        edtpan.creePanelBas(edtpan.getEmpEDT());
        edtpan.creeCreneauxEDT(edtpan.getEmpEDT(), edtpan.getEmpConn());
        this.f.dispose();
    }
}
