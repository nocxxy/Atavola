package Tables;

import Back.Back;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class CreationReservationListener implements ActionListener {

    private ReserverTableFrame f;

    public CreationReservationListener(ReserverTableFrame f){
        this.f=f;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String nomRes = this.f.getResTable().getText();
        Back.reserveTable(this.f.getTable().getId(),this.f.getTgp().getAjd(),this.f.getTgp().getService(),nomRes);
        this.f.dispose();
        this.f.getTgp().creePanelTables();

    }
}
