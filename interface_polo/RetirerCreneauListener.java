package interface_polo;

import Back.Back;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class RetirerCreneauListener implements ActionListener {

    private RetirerCreneauFrame f;

    private Statement st;

    RetirerCreneauListener(RetirerCreneauFrame f , Statement st){
        this.f = f;
        this.st = st;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id =this.f.getChoix().getSelect().getId();
        Back.deleteCreneau(this.st,id);
        this.f.dispose();
    }
}
