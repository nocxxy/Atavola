package interface_polo;

import Back.Back;
import EDT.SignalerListener;
import interface_package.IndispoFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

public class SignalerIndisponibleListener implements ActionListener {

    private Statement st;
    private  IndispoFrame f;

    public SignalerIndisponibleListener(Statement st , IndispoFrame f){
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
        String horraireDebut = this.f.getDa().getText() + "-" + this.f.getDm().getText() + "-" + this.f.getDd().getText() + " " + getHeureSynthaxe(this.f.getHd().getText());
        String horraireFin = this.f.getDa().getText() + "-" + this.f.getDm().getText() + "-" + this.f.getDd().getText() + " " + getHeureSynthaxe(this.f.getHf().getText());
        String motif = this.f.getMotif().getText();
        Back.ajoutIndisp(this.st,this.f.getE().getId(),motif,horraireDebut,horraireFin);
        this.f.dispose();
    }
}
