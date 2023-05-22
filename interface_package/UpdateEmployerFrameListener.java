package interface_package;

import interface_polo.ModifierEmploye1Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class UpdateEmployerFrameListener implements ActionListener {

    private Statement st;

    UpdateEmployerFrameListener(Statement st){
        this.st= st;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ModifierEmploye1Frame f= new ModifierEmploye1Frame(this.st);
        f.setVisible(true);
    }
}
