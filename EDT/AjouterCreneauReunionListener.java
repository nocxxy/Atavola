package EDT;

import interface_polo.PoloPopUp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class AjouterCreneauReunionListener implements ActionListener {
    private Statement st;
    AjouterCreneauReunionListener (Statement st){
        this.st = st;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PoloPopUp f =new PoloPopUp( this.st);
        f.setVisible(true);
    }
}
