package interface_package;

import Front.Fonction.Employe;

import javax.swing.*;
import java.util.ArrayList;

public class ListEmployePanel extends JScrollPane {

    public JScrollPane PaneTable(ArrayList<Employe> e)
    {
        JTable table = new JTable(new ListEmployeModelTable(e));
        return new JScrollPane(table);
    }
}
