package interface_polo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class Reunion3Listener implements ActionListener {
    private JFrame f;
    private Statement st;

    Reunion3Listener(JFrame f,Statement st){
        this.f = f;
        this.st = st;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.f.dispose();
        Reunion3Frame c = new Reunion3Frame(st);
        c.setVisible(true);
    }
}
