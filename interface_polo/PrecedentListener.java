package interface_polo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrecedentListener implements ActionListener {

    private JFrame prec;
    private JFrame suiv;

    public PrecedentListener(JFrame prec, JFrame suiv){
        this.prec = prec;
        this.suiv = suiv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.prec.setVisible(true);
        this.suiv.dispose();
    }
}
