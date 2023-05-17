package Tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class AjouterTableListener implements ActionListener {

    private Statement st;

    public AjouterTableListener(Statement st){
        this.st = st;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AjouterTableFrame f = new AjouterTableFrame(this.st);
        f.setVisible(true);
    }
}
