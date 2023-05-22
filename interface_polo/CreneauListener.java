package interface_polo;

import javax.swing.*;

import EDT.EDTPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class CreneauListener implements ActionListener {
    private JFrame f;
    private Statement st;
    private EDTPanel edtpan;

    CreneauListener(JFrame f,Statement st,EDTPanel edtpan){
        this.f = f;
        this.st = st;
        this.edtpan = edtpan;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.f.dispose();
        CreneauFrame c = new CreneauFrame(this.st,edtpan);
        c.setVisible(true);
    }
}
