package Dyrektor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CustomTableModelPracownik extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private List<Pracownik> listaPracownikow = new ArrayList<>();
    private String[] columns = { "ID", "Imie", "Nazwisko", "Placówka"};

    public CustomTableModelPracownik(List<Pracownik> listaPracownikow) {
        this.listaPracownikow = listaPracownikow;
    }

    public int getRowCount() {
        return listaPracownikow.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public Object getValueAt(int row, int col) {
        if (col == 0)
            return listaPracownikow.get(row).getIdPracownika();
        if (col == 1)
            return listaPracownikow.get(row).getImie();
        if (col == 2)
            return listaPracownikow.get(row).getNazwisko();
        if (col == 3)
            return listaPracownikow.get(row).getIdPlacowki();
        return null;
    }

    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public List<Pracownik> getListaPracownikow() {
        return listaPracownikow;
    }

    public void setListaPracownikow(List<Pracownik> listaPracownik) {
        this.listaPracownikow = listaPracownik;
    }

    public void add(Pracownik pracownik) {
        this.listaPracownikow.add(pracownik);
        this.fireTableRowsInserted(listaPracownikow.size() - 1, listaPracownikow.size());
    }

}
