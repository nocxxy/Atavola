package Tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierTableListener implements ActionListener {

    private Table t;
    private TableGestionPanel tgp;

    ModifierTableListener(Table t, TableGestionPanel tgp){
        this.t = t;
        this.tgp=tgp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ModifierTableFrame f = new ModifierTableFrame(this.t,this.tgp);
        f.setVisible(true);
    }
}
