package Sprzedawca;

import javax.swing.table.AbstractTableModel;

public class CustomTableModelKZ extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    String[] columns = {"Tytuł","Rok","Wydawca","Stan","Cena"};
    Object[][] dane = new Object[][]{};

    @Override
    public int getRowCount() {
        return dane.length;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return dane[rowIndex][columnIndex];
    }
}
