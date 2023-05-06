package interface_polo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class CreneauListener implements ActionListener {
    private JFrame f;
    private Statement st;

    CreneauListener(JFrame f,Statement st){
        this.f = f;
        this.st = st;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.f.dispose();
        CreneauFrame c = new CreneauFrame(this.st);
        c.setVisible(true);
    }
}
