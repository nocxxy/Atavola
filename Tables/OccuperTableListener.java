package Tables;

import Back.Back;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OccuperTableListener implements ActionListener {


    private Table table;
    private TableGestionPanel tgp;

    protected OccuperTableListener(Table t, TableGestionPanel tgp){
        this.table = t;
        this.tgp = tgp;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Back.occupeTable(this.table.getId(),this.tgp.getService());
        this.tgp.creePanelTables();
    }
}
