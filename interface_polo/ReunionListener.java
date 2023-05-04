package interface_polo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReunionListener implements ActionListener {
    private JFrame f;

    ReunionListener(javax.swing.JFrame f){
        this.f = f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.f.dispose();
        ReunionFrame c = new ReunionFrame();
        c.setVisible(true);
    }
}
