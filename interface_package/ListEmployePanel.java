package interface_package;

import Front.Fonction.Employe;

import javax.swing.*;
import java.sql.Statement;
import java.util.ArrayList;

public class ListEmployePanel extends JPanel {

    public  ListEmployePanel(ArrayList<Employe> e,Statement st)
    {
        JTable table = new JTable(new ListEmployeModelTable(e));
        JScrollPane tableau = new JScrollPane(table);
        JButton addEmployer = new JButton("Ajouter Employer");
        addEmployer.addActionListener(new AddEmployerFrameListener(st));

        this.add(tableau);
        this.add(addEmployer);
    }
}
