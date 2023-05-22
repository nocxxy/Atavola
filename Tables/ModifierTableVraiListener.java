package Tables;

import Back.Back;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierTableVraiListener implements ActionListener {

    private ModifierTableFrame f;

    ModifierTableVraiListener(ModifierTableFrame f){
        this.f=f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int numTable = Integer.parseInt(this.f.getNumTab().getText());
        int tailleTable = Integer.parseInt(this.f.getTailleTab().getText());
        Back.updateTable(this.f.getT().getId(),numTable,tailleTable);
        this.f.getTgp().creePanelTables();
        this.f.dispose();
    }
}
