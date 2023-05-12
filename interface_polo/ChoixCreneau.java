package interface_polo;

import Front.Fonction.Creneau;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ChoixCreneau extends JPanel {

    private JComboBox choixCreneau;
    private ArrayList<Creneau> c;

    public ChoixCreneau(ArrayList<Creneau> c){
        this.c = c;
        String s1[] = this.getListCreneau();
        this.choixCreneau = new JComboBox(s1);
        this.add(this.choixCreneau);
    }
    @SuppressWarnings("deprecation")
    public ChoixCreneau(Creneau c,ReunionBis2Frame f){
        ArrayList<Creneau> temp = new ArrayList<Creneau>();


        for (int i = 7; i<23 ; i++){
            Date d1 = new Date(c.getDateDebut().getYear(),c.getDateDebut().getMonth()-1,c.getDateDebut().getDate(),i,0);
            Date d2 = new Date(c.getDateFin().getYear(),c.getDateFin().getMonth()-1,c.getDateFin().getDate(),i+1,0);
            Creneau h = new Creneau(d1,d2);
            System.out.println(h);
            temp.add(h);

        }
        System.out.println(temp.get(0).toString());
        this.c = temp;
        System.out.println(this.c);
        String s1[] = this.getListCreneau();
        this.choixCreneau = new JComboBox(s1);

        this.choixCreneau.addActionListener(new RecupEmployeDispoListener(this,f));
        this.add(this.choixCreneau);
    }
    public String[] getListCreneau() {
        String nom;
        String temp[];
        String res[] = {};
        String test[];
        int len1, len2;
        for (int i = 0; i < c.size(); i++) {
            nom = c.get(i).toString();
            temp = new String[]{nom};
            test = Arrays.copyOf(res, res.length + temp.length);
            System.arraycopy(temp, 0, test, res.length, temp.length);
            res = test;

        }
        return res;
    }
    public Creneau getSelect(){
        Creneau res = null;
        String choix = (String)this.choixCreneau.getSelectedItem();
        for (int i=0;i<this.c.size();i++){
            if (this.c.get(i).toString().equals(choix)){
                res = this.c.get(i);
            }
        }
        return res;
    }

}
