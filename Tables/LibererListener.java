package Tables;

import Back.Back;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibererListener implements ActionListener {

    private Table t;
    private TableGestionPanel tgp;

    protected LibererListener(Table t, TableGestionPanel tgp){
        this.t = t;
        this.tgp = tgp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Back.libereTable(this.t.getId(),this.tgp.getService());
        this.tgp.creePanelTables();
    }
}
