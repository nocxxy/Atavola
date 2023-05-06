package interface_polo;

import Back.Back;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class RetirerListener implements ActionListener {

    private  RetirerEmployeFrame f;
    private Statement st;
    RetirerListener(RetirerEmployeFrame f, Statement st){
        this.f = f;
        this.st = st;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id = Back.getEmployer(this.st ,this.f.getChoix().getSelect()).getId() ;
        Back.retireEmploye(st,id);
        this.f.dispose();
    }
}
