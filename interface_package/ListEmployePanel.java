package interface_package;

import Front.Fonction.Employe;

import javax.swing.*;

import Back.Back;

import java.awt.FlowLayout;
import java.sql.Statement;
import java.util.ArrayList;

import interface_polo.GreenRoundButton;

public class ListEmployePanel extends JPanel {

    public  ListEmployePanel(Statement st)
    {
    	this.setLayout(new FlowLayout(FlowLayout.CENTER,300,20));
        JTable table = new JTable(new ListEmployeModelTable(Back.getAllEmployer(st)));
        JScrollPane tableau = new JScrollPane(table);
        //JButton addEmployer = new JButton("Ajouter Employer");
        GreenRoundButton addEmployer = new GreenRoundButton("Ajouter un Employé","Green", 200, 30, 15);
        addEmployer.addActionListener(new AddEmployerFrameListener(st));
        GreenRoundButton delEmployer = new GreenRoundButton("Supprimer un Employé","Red", 200, 30, 15);

        this.add(tableau);
        this.add(addEmployer);
        this.add(delEmployer);
    }
}
