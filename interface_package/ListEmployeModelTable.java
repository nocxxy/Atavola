package interface_package;

import Front.Fonction.Employe;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ListEmployeModelTable extends AbstractTableModel {

    private ArrayList<Employe> listE;

    public ListEmployeModelTable(ArrayList<Employe> listE)
    {
        this.listE = listE;
    }
    public Object getValueAt(int rowIndex , int columnIndex) {
        return getData(listE.get(rowIndex), columnIndex);
    }

    public Object getData(Employe employe, int col) {
        switch (col) {
            case 0: {
                return employe.getNom() ;
            }
            case 1: {
                return  employe.getPrenom();
            }
        }
        return null;
    }
    @Override
    public int getRowCount() {
        return listE.size(); // le nombre de lignes
    }
    @Override
    public int getColumnCount() {
        return 2; // le nombre de colonnes
    }
}
