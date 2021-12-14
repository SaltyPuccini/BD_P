package Sprzedawca;

import javax.swing.table.AbstractTableModel;

public class CustomTableModelGra extends AbstractTableModel {
    String[] columns = {"Tytuł", "Rok","Gatunek"};
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
