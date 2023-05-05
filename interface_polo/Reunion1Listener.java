package interface_polo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class Reunion1Listener implements ActionListener {
    private JFrame f;
    private Statement st;

    Reunion1Listener(JFrame f,Statement st){
        this.f = f;
        this.st = st;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.f.dispose();
        Reunion1Frame c = new Reunion1Frame(st);
        c.setVisible(true);
    }
}
