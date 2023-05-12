package interface_package;

import Back.Back;
import Front.Fonction.Employe;
import interface_polo.GreenRoundButton;

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
        this.setTitle("Ajouter un Employé");
        this.setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 8));
        
        JLabel n = new JLabel("Nom :");
        JLabel p = new JLabel("Prenom :");
        JLabel motdepasse = new JLabel("Mot de passe :");
        GreenRoundButton confirmer = new GreenRoundButton("Confirmer","Green",150,30,30);
		GreenRoundButton annuler = new GreenRoundButton("Annuler","Red",150,30,30);

        annuler.addActionListener(new AnnulerListener(this));
        confirmer.addActionListener(new ConfirmerListener(this));

        /*JPanel panelForm = new JPanel();

        panelForm.setLayout(new FlowLayout());
        panelForm.add(n);
        nom.setPreferredSize(new Dimension(100,20));
        panelForm.add((this.nom));
        panelForm.add(p);
        prenom.setPreferredSize(new Dimension(100,20));
        panelForm.add(this.prenom);
        panelForm.add(motdepasse);
        mdp.setPreferredSize(new Dimension(100,20));
        panelForm.add(this.mdp);*/

        JPanel panelButton = new JPanel();
        panelButton.add(annuler);
        panelButton.add(confirmer);

        panel.add(n);
        nom.setPreferredSize(new Dimension(300,20));
        panel.add((this.nom));
        panel.add(p);
        prenom.setPreferredSize(new Dimension(300,20));
        panel.add(this.prenom);
        panel.add(motdepasse);
        mdp.setPreferredSize(new Dimension(300,20));
        panel.add(this.mdp);
        panel.add(panelButton);
        
        this.getContentPane().add(panel,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        

        //this.pack();

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
