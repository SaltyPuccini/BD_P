package Dyrektor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CustomTableModelPlacowki extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private List<Placowka> listaPlacowek = new ArrayList<>();
    private String[] columns = { "ID", "Adres", "Numer", "Numer Lokalu", "Miasto"};

    public CustomTableModelPlacowki(List<Placowka> listaPlacowek) {
        this.listaPlacowek = listaPlacowek;
    }

    public int getRowCount() {
        return listaPlacowek.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public Object getValueAt(int row, int col) {
        if (col == 0)
            return listaPlacowek.get(row).getIdPlacowki();
        if (col == 1)
            return listaPlacowek.get(row).getUlica();
        if (col == 2)
            return listaPlacowek.get(row).getNumer();
        if (col == 3)
            return listaPlacowek.get(row).getNrLokalu();
        if (col == 4)
            return listaPlacowek.get(row).getMiasto();
        return null;
    }

    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public List<Placowka> getListaPlacowek() {
        return listaPlacowek;
    }

    public void setListaGier(List<Placowka> listaPlacowek) {
        this.listaPlacowek = listaPlacowek;
    }

    public void add(Placowka placowka) {
        this.listaPlacowek.add(placowka);
        this.fireTableRowsInserted(listaPlacowek.size() - 1, listaPlacowek.size());
    }

}
