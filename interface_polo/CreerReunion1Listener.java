package interface_polo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class CreerReunion1Listener implements ActionListener {

    private PoloPopUp f;
    private Statement st;

    public CreerReunion1Listener(PoloPopUp f, Statement st){
        this.f = f;
        this.st = st;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.f.dispose();
        ReunionBis1Frame rf = new ReunionBis1Frame(this.st);
        rf.setVisible(true);

    }
}
