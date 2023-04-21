package interface_package;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class ConnexionListener implements ActionListener {

    private Statement st;
    private JFrame f;

    public ConnexionListener(JFrame f, Statement st){
        this.st = st;
        this.f = f;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
