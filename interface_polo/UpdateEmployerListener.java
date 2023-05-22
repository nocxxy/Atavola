package interface_polo;

import Back.Back;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateEmployerListener implements ActionListener {

    private ModifierEmploye1Frame f;

    UpdateEmployerListener(ModifierEmploye1Frame f){
        this.f = f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pwd = this.f.getMdp().getText();
        String name = this.f.getNom().getText();
        String surname = this.f.getPrenom().getText();

        if(pwd.equals("") || name.equals("") || surname.equals("")){
            JLabel error = new JLabel("Pas de Champ !");
            this.f.add(error);
        }else {
            String login = name + surname ;
            int id = Back.getEmployer(this.f.getSt() ,this.f.getChoix().getSelect()).getId();
            Back.updateEmployer(this.f.getSt(),id,name,surname,login,pwd,"employe");
            this.f.dispose();
        }
    }
}
