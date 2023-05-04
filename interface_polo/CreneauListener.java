package interface_polo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreneauListener implements ActionListener {
    private JFrame f;

    CreneauListener(JFrame f){
        this.f = f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.f.dispose();
        CreneauFrame c = new CreneauFrame();
        c.setVisible(true);
    }
}
