package interface_polo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import EDT.EDTPanel;

public class CreerReunion1Listener implements ActionListener {

    private PoloPopUp f;
    private Statement st;
	private EDTPanel edtpan;

    public CreerReunion1Listener(PoloPopUp f, Statement st,EDTPanel edtpan){
        this.f = f;
        this.st = st;
        this.edtpan = edtpan;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.f.dispose();
        ReunionBis1Frame rf = new ReunionBis1Frame(this.st,edtpan);
        rf.setVisible(true);

    }
}
