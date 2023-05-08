package interface_package;

import interface_polo.RetirerCreneauFrame;
import interface_polo.TransiUpdateListener;
import interface_polo.UpdateCreneauFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class TransiUpdateIndispoListener implements ActionListener {

    private RetirerIndispoFrame f;
    private Statement st;

    public TransiUpdateIndispoListener(Statement st, RetirerIndispoFrame f){
        this.st = st;
        this.f = f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        IndispoUpdateFrame uf = new IndispoUpdateFrame(st,this.f.getChoix().getSelect());
        uf.setVisible(true);
        this.f.dispose();
    }
}
