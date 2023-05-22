package Tables;

import Back.Back;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReserverListener implements ActionListener {

    private Table table;
    private TableGestionPanel tgp;


    public ReserverListener(Table t,TableGestionPanel tgp){
        this.table = t;
        this.tgp = tgp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ReserverTableFrame f = new ReserverTableFrame(Back.connectionBase(),this.table,this.tgp);
        f.setVisible(true);
    }
}
