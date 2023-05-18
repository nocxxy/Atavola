package Tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class AjouterTableListener implements ActionListener {

    private Statement st;
    private TableGestionPanel tgp;

    public AjouterTableListener(Statement st,TableGestionPanel tgp){
        this.st = st;
        this.tgp = tgp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AjouterTableFrame f = new AjouterTableFrame(this.st,this.tgp);
        f.setVisible(true);
    }
}
