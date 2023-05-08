package interface_package;

import Back.Back;
import interface_polo.RetirerCreneauFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class RetirerIndispoListener implements ActionListener {

    private RetirerIndispoFrame f;

    private Statement st;

    public RetirerIndispoListener(Statement st, RetirerIndispoFrame f){
        this.f = f;
        this.st = st;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id =this.f.getChoix().getSelect().getId();
        Back.deleteIndisp(this.st,id);
        this.f.dispose();
    }
}
