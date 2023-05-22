package Tables;

import Back.Back;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteTableListener implements ActionListener {

    private ModifierTableFrame f;

    protected DeleteTableListener(ModifierTableFrame f){
        this.f =f ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Back.deleteTable(this.f.getT().getId());
        this.f.getTgp().creePanelTables();
    }
}
