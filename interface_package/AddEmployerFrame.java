package interface_package;

import Back.Back;
import Front.Fonction.Employe;

import javax.swing.*;
import java.awt.*;
import java.sql.Statement;

public class AddEmployerFrame extends JFrame {

    final static int WIDTH = 350;
    final static int HEIGHT = 250;

    private JTextField nom = new JTextField();
    private JTextField prenom = new JTextField();
    private JTextField mdp = new JTextField();
    private Statement st;

    public AddEmployerFrame(Statement st)
    {
        super();
        this.st = st;
        this.setBounds(100,100,WIDTH,HEIGHT);
        this.setTitle("Ajouter un Employer");
        this.setLocationRelativeTo(null);
        JLabel n = new JLabel("Nom :");
        JLabel p = new JLabel("Prenom :");
        JLabel motdepasse = new JLabel("Mot de passe :");
        JButton confirmer = new JButton("confirmer");
        JButton annuler = new JButton("annuler");

        annuler.addActionListener(new AnnulerListener(this));
        confirmer.addActionListener(new ConfirmerListener(this));

        JPanel panelForm = new JPanel();

        panelForm.setLayout(new GridLayout(6,1));
        panelForm.add(n);
        panelForm.add((this.nom));
        panelForm.add(p);
        panelForm.add(this.prenom);
        panelForm.add(motdepasse);
        panelForm.add(this.mdp);

        JPanel panelButton = new JPanel();
        panelButton.add(annuler);
        panelButton.add(confirmer);

        this.setLayout(new GridLayout(3, 1));
        this.add(panelForm);
        this.add(panelButton);
        

        this.pack();

    }

    public JTextField getMdp() {
        return mdp;
    }

    public JTextField getPrenom() {
        return prenom;
    }

    public JTextField getNom() {
        return nom;
    }

    public Statement getSt() {
        return st;
    }
}
