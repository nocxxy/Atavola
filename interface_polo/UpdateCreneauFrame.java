package interface_polo;

import Front.Fonction.Creneau;
import interface_package.AnnulerListener;

import javax.swing.*;
import java.awt.*;
import java.sql.Statement;
import java.util.Date;

public class UpdateCreneauFrame extends JFrame {

    final static int WIDTH = 300;
    final static int HEIGHT = 300;
    private Creneau creneau;

    private JTextField d;
    private JTextField m;
    private JTextField a;
    private JTextField hd;
    private JTextField hf;

    public Creneau getCreneau() {
        return creneau;
    }

    public JTextField getD() {
        return d;
    }

    public JTextField getM() {
        return m;
    }

    public JTextField getA() {
        return a;
    }

    public JTextField getHd() {
        return hd;
    }

    public JTextField getHf() {
        return hf;
    }
    @SuppressWarnings("deprecation")
    UpdateCreneauFrame(Statement st, Creneau c){
        this.creneau = c;
        this.setBounds(100,100,WIDTH,HEIGHT);
        this.setTitle("A Tavola ! | Retirer Employe");
        ImageIcon img = new ImageIcon("src/img/italie.png");
        this.setIconImage(img.getImage());
        this.setOpacity(1);

        /*
         * Panel Général qui va tout contenir
         */
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 15));

        JLabel date = new JLabel("Date : ");

        JPanel pDate = new JPanel();
        pDate.setLayout(new FlowLayout());
        String day = String.valueOf(c.getDateDebut().getDate());
        this.d = new JTextField(day);
        this.d.setPreferredSize(new Dimension(55,30));

        String month = String.valueOf(c.getDateDebut().getMonth());
        this.m = new JTextField(month);
        this.m.setPreferredSize(new Dimension(55,30));

        String year = String.valueOf(c.getDateDebut().getYear() + 1900);
        this.a = new JTextField(year);
        this.a.setPreferredSize(new Dimension(55,30));

        pDate.add(date);
        pDate.add(this.d);
        pDate.add(this.m);
        pDate.add(this.a);

        JLabel horaire = new JLabel("Horaires : ");

        JPanel pHour = new JPanel();
        pHour.setLayout(new FlowLayout());
        this.hd = new JTextField(heureSynthaxe(c.getDateDebut()));
        this.hd.setPreferredSize(new Dimension(50,30));
        this.hf = new JTextField(heureSynthaxe(c.getDateFin()));
        this.hf.setPreferredSize(new Dimension(50,30));

        pHour.add(horaire);
        pHour.add(this.hd);
        pHour.add(this.hf);

        GreenRoundButton add = new GreenRoundButton("Modifier","Green",175,30,30);
        GreenRoundButton cancel = new GreenRoundButton("Annuler","Red",175,30,30);

        add.addActionListener(new UpdateCreneauListener(st,this));
        cancel.addActionListener(new AnnulerListener(this));

        panel.add(pDate);
        panel.add(pHour);
        panel.add(add);
        panel.add(cancel);

        this.getContentPane().add(panel,BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public String heureSynthaxe(Date a){
        int heure = a.getHours();
        int min = a.getMinutes();
        return heure + "h" + min;
    }
}
