package interface_polo;

import Back.Back;
import Front.Fonction.Employe;

import javax.swing.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class ChoixEmployer extends JPanel {

	private JComboBox inputChoice;
    ChoixEmployer(Statement st) {
        ArrayList<Employe> listeEmployer = Back.getAllEmployer(st);
        String s1[] = this.getListEmploye(listeEmployer);
        JComboBox inputChoice = new JComboBox(s1);
        this.inputChoice = inputChoice;
        this.add(inputChoice);
    }


    ChoixEmployer(Statement st,ModifierEmploye1Frame f){
        ArrayList<Employe> listeEmployer = Back.getAllEmployer(st);
        String s1[] = this.getListEmploye(listeEmployer);
        JComboBox inputChoice = new JComboBox(s1);
        inputChoice.addActionListener(new AfficheDonneEmployeListener(this,f));
        this.inputChoice = inputChoice;
        this.add(inputChoice);
    }

    public String[] getListEmploye(ArrayList<Employe> l) {
        String nom;
        String temp[];
        String res[] = {};
        String test[];
        int len1, len2;
        for (int i = 0; i < l.size(); i++) {
            nom = l.get(i).getLogin();
            temp = new String[]{nom};
            test = Arrays.copyOf(res, res.length + temp.length);
            System.arraycopy(temp,0,test,res.length,temp.length);
            res = test ;

        }
        return res;
    }
    
    public String getSelect() {
    	return (String) this.inputChoice.getSelectedItem();
    }
 

}
