package interface_polo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class Reunion2Listener implements ActionListener {
    private JFrame f;
    private Statement st;

    Reunion2Listener(JFrame f,Statement st){
        this.f = f;
        this.st = st;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.f.dispose();
        Reunion2Frame c = new Reunion2Frame(st,2);
        c.setVisible(true);
    }
}
