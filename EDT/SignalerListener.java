package EDT;

import Front.Fonction.Employe;
import interface_package.IndispoFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class SignalerListener implements ActionListener {

    private Statement st;
    private Employe e;
    public SignalerListener(Statement st, Employe e){

        this.st = st;
        this.e = e;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        IndispoFrame f = new IndispoFrame(this.st,this.e);
        f.setVisible(true);
    }
}
