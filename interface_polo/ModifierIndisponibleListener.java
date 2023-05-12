package interface_polo;

import Back.Back;
import EDT.SignalerListener;
import interface_package.IndispoFrame;
import interface_package.IndispoUpdateFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class ModifierIndisponibleListener implements ActionListener {

    private Statement st;
    private  IndispoUpdateFrame f;

    public ModifierIndisponibleListener(Statement st , IndispoUpdateFrame f){
        this.st = st;
        this.f = f;
    }

    private String getHeureSynthaxe(String h){
        String heure =h.substring(0,2);
        String min = h.substring(3,5);
        return heure + ":"+min+":00";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int employe  = this.f.getC().getEmploye();
        int id = this.f.getC().getId();
        String horraireDebut = this.f.getDa().getText() + "-" + this.f.getDm().getText() + "-" + this.f.getDd().getText() + " " + getHeureSynthaxe(this.f.getHd().getText());
        String horraireFin = this.f.getDa().getText() + "-" + this.f.getDm().getText() + "-" + this.f.getDd().getText() + " " + getHeureSynthaxe(this.f.getHf().getText());
        Back.updateIndisp(this.st,id,employe,this.f.getMotif().getText(),horraireDebut,horraireFin);
        this.f.dispose();
    }
}
