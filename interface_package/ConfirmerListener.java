package interface_package;

import Back.Back;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class ConfirmerListener implements ActionListener {

    public AddEmployerFrame f;
    public JTextField nom;
    public JTextField prenom;
    public JTextField mdp;
    public Statement st;
    public ConfirmerListener(AddEmployerFrame f)
    {
        this.f = f;
        this.nom = this.f.getNom();
        this.prenom = this.f.getPrenom();
        this.mdp = this.f.getMdp();
        this.st = this.f.getSt();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pwd = this.mdp.getText();
        String name = this.nom.getText();
        String surname = this.prenom.getText();

        if(pwd.equals("") || name.equals("") || prenom.equals("")){
            JLabel error = new JLabel("Pas de Champ !");
            this.f.add(error);
        }else {
            String login = name + surname ;
            Back.insertEmployer(this.st,name,surname,login,pwd,"employe");
            this.f.dispose();
        }

    }
}
