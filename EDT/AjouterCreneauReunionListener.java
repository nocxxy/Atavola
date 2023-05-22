package EDT;

import interface_polo.PoloPopUp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class AjouterCreneauReunionListener implements ActionListener {
    private Statement st;
    private EDTPanel edtpan;
    public AjouterCreneauReunionListener (Statement st,EDTPanel edtpan){
        this.st = st;
        this.edtpan = edtpan;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PoloPopUp f =new PoloPopUp( this.st,edtpan);
        f.setVisible(true);
    }
}
