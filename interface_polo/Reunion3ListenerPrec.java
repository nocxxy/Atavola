package interface_polo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reunion3ListenerPrec implements ActionListener {

    private Reunion2Frame f2;
    private Reunion3Frame f3;

    Reunion3ListenerPrec (Reunion3Frame f3,Reunion2Frame f2){
        this.f3 = f3;
        this.f2 = f2;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.f3.dispose();
        f2.setVisible(true);
    }
}
