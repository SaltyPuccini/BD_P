package Dyrektor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CustomTableModelGry extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private List<Gra> listaGier = new ArrayList<>();
    private String[] columns = { "Tytuł", "Rok", "Wydawca"};

    public CustomTableModelGry(List<Gra> listaGier) {
        this.listaGier = listaGier;
    }

    public int getRowCount() {
        return listaGier.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public Object getValueAt(int row, int col) {
        if (col == 0)
            return listaGier.get(row).getNazwa();
        if (col == 1)
            return listaGier.get(row).getRokWydania();
        if (col == 2)
            return listaGier.get(row).getWydawca();
        return null;
    }

    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public List<Gra> getListaGier() {
        return listaGier;
    }

    public void setListaGier(List<Gra> listaGier) {
        this.listaGier = listaGier;
    }

    public void add(Gra gra) {
        this.listaGier.add(gra);
        this.fireTableRowsInserted(listaGier.size() - 1, listaGier.size());
    }

}
