package interface_polo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import static java.lang.Integer.parseInt;

public class Reunion2Listener implements ActionListener {
    private Reunion1Frame f;
    private Statement st;

    Reunion2Listener(Reunion1Frame f,Statement st){
        this.f = f;
        this.st = st;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.f.dispose();
        Reunion2Frame c = new Reunion2Frame(st,parseInt(this.f.getNb().getText()));
        c.setVisible(true);
    }
}
