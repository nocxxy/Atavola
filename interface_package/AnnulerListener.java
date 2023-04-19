package interface_package;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnnulerListener implements ActionListener {
    private JFrame f;
    AnnulerListener(JFrame f){
        this.f = f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.f.dispose();
    }
}
