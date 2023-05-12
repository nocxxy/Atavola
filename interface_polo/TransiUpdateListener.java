package interface_polo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class TransiUpdateListener implements ActionListener {

    private RetirerCreneauFrame f;
    private Statement st;

    public TransiUpdateListener(Statement st, RetirerCreneauFrame f){
        this.f = f;
        this.st = st;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UpdateCreneauFrame uf = new UpdateCreneauFrame(st,this.f.getChoix().getSelect());
        uf.setVisible(true);
        this.f.dispose();
    }
}
