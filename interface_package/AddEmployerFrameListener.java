package interface_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class AddEmployerFrameListener implements ActionListener {
    private Statement st ;
    public AddEmployerFrameListener(Statement st)
    {
        this.st = st;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        AddEmployerFrame f = new AddEmployerFrame(this.st);
        f.setVisible(true);
    }
}
