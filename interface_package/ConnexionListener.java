package interface_package;

import Back.Back;
import Front.Fonction.Employe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class ConnexionListener implements ActionListener {

    private Statement st;
    private ConnexionFrame f;
    private JTextField jlogin;
    private JTextField jmdp;

    public ConnexionListener(ConnexionFrame f, Statement st){
        this.st = st;
        this.f = f;
        this.jlogin = f.getJlogin();
        this.jmdp = f.getJmdp();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String login = this.jlogin.getText();
        String mdp = this.jmdp.getText();
        if (Back.connexionEmployer(this.st,login,mdp)){
            this.f.dispose();
            Employe employe = Back.getEmployer(this.st,login);
            WindowFrame frame = new WindowFrame(this.st, employe);
            frame.setVisible(true);
        }
    }
}
