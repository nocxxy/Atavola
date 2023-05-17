package Tables;

import Back.Back;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class CreationTableListener implements ActionListener {

    private AjouterTableFrame f;

    public CreationTableListener(AjouterTableFrame f){
        this.f=f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int num = Integer.parseInt(this.f.getNumTable().getText());
        int nb = Integer.parseInt(this.f.getNbPlace().getText());
        Back.creeTable(num,nb);
        this.f.dispose();
    }
}
