import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class EkranZamowienRzeczoznawcy extends JPanel {
    DefaultTableModel modelWyslane = new DefaultTableModel(new String[] { "IDZamówienia","IDEgzemplarza", "Tytuł", "PlacówkaDocelowa"}, 0);
    DefaultTableModel modelOdebrane = new DefaultTableModel(new String[] { "IDZamówienia","IDEgzemplarza", "Tytuł", "PlacówkaWysyłająca"}, 0);
    JTable wysylaneLista = new JTable(modelWyslane);
    JTable odbieraneLista = new JTable(modelOdebrane);

    JScrollPane wyslane = new JScrollPane(wysylaneLista);
    JScrollPane odebrane = new JScrollPane(odbieraneLista);

    JButton wyslano = new JButton("Wysłano");
    JButton odebrano = new JButton("Odebrano");
    JButton wroc = new JButton("Wróć");

    public void addActionListener(ActionListener listener) {
        wyslano.addActionListener(listener);
        odebrano.addActionListener(listener);
        wroc.addActionListener(listener);
    }

    public EkranZamowienRzeczoznawcy() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 3;
        uklad.insets = new Insets(10, 10, 10, 10);
        wyslano.setActionCommand("wyslano");
        odebrano.setActionCommand("odebrano");
        wroc.setActionCommand("wroc");

        uklad.gridx = 0;
        uklad.gridy = 0;
        add(wyslane, uklad);

        uklad.gridx = 2;
        uklad.gridy = 0;
        add(odebrane, uklad);

        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.gridx = 0;
        uklad.gridy = 3;
        add(wyslano, uklad);

        uklad.gridx = 1;
        uklad.gridy = 3;
        add(wroc, uklad);

        uklad.gridx = 2;
        uklad.gridy = 3;
        add(odebrano, uklad);
    }

    public int getID(String tabela) {
        int index = 0;
        if (Objects.equals(tabela, "wyslane")) {
            index = wysylaneLista.getSelectedRow();
            return (int) wysylaneLista.getValueAt(index,0);

        } else {
            index = odbieraneLista.getSelectedRow();
            return (int) odbieraneLista.getValueAt(index,0);
        }
    }

    public void dodajEgzemlarzDoOdbioru(Object[] object) {
        modelOdebrane.addRow(object);
    }

    public void dodajEgzemlarzDoWyslania(Object[] object) {
        modelWyslane.addRow(object);
    }

    public void czyscTabeleDoOdbioru(){
        while(modelOdebrane.getRowCount() > 0)
        {
            modelOdebrane.removeRow(0);
        }
        odbieraneLista.setModel(modelOdebrane);
    }

    public void czyscTabeleDoWysylki(){
        while(modelWyslane.getRowCount() > 0)
        {
            modelWyslane.removeRow(0);
        }
        wysylaneLista.setModel(modelWyslane);
    }

}
