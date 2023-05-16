package interface_polo;

import Back.Back;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class RetirerReunionListener implements ActionListener {

    private Statement st;
    private RetirerReunionFrame f;

    public RetirerReunionListener(RetirerReunionFrame f, Statement st){
        this.f = f;
        this.st = st;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Back.retireReunions(this.st,this.f.getChoix().getSelect().getReunion());
        this.f.dispose();
    }
}
