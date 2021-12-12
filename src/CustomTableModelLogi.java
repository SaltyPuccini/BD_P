import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CustomTableModelLogi extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private List<Log> listaLogow = new ArrayList<>();
    private String[] columns = { "ID", "Pracownik", "Data", "Akcja"};

    public CustomTableModelLogi(List<Log> listaLogow) {
        this.listaLogow = listaLogow;
    }

    public int getRowCount() {
        return listaLogow.size();
    }

    public int getColumnCount() {
        return columns.length;
    }

    public String getColumnName(int col) {
        return columns[col];
    }

    public Object getValueAt(int row, int col) {
        if (col == 0)
            return listaLogow.get(row).getIdLogu();
        if (col == 1)
            return listaLogow.get(row).getIdPracownika();
        if (col == 2)
            return listaLogow.get(row).getData();
        if (col == 3)
            return listaLogow.get(row).getAkcja();
        return null;
    }

    public Class<?> getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public List<Log> getListaLogow() {
        return listaLogow;
    }

    public void setListaLogow(List<Log> listaLogow) {
        this.listaLogow = listaLogow;
    }

    public void add(Log log) {
        this.listaLogow.add(log);
        this.fireTableRowsInserted(listaLogow.size() - 1, listaLogow.size());
    }

}
