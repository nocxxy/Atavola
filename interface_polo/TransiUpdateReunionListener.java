package interface_polo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class TransiUpdateReunionListener implements ActionListener {

    private RetirerReunionFrame f;
    private Statement st;

    public TransiUpdateReunionListener(Statement st,RetirerReunionFrame f){
        this.f= f;
        this.st = st;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.f.dispose();
        ModifierReunion1Frame u =new ModifierReunion1Frame(this.st);
        u.setVisible(true);
    }
}
