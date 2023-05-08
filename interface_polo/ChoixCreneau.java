package interface_polo;

import Front.Fonction.Creneau;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ChoixCreneau extends JPanel {

    private JComboBox choixCreneau;
    private ArrayList<Creneau> c;

    public ChoixCreneau(ArrayList<Creneau> c){
        this.c = c;
        String s1[] = this.getListCreneau();
        this.choixCreneau = new JComboBox(s1);
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
