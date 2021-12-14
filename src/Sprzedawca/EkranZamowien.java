package Sprzedawca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EkranZamowien extends JPanel {
    JTable wysylaneLista = new JTable(new CustomTableModelSR());
    JTable odbieraneLista = new JTable(new CustomTableModelSR());
    
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

    public EkranZamowien() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 3;
        uklad.insets = new Insets(10, 10, 10, 10);
        wyslano.setActionCommand("wyslano");
        odebrano.setActionCommand("odebrano");
        wroc.setActionCommand("wroc");

        wysylaneLista.getColumnModel().getColumn(0).setHeaderValue("ID");
        wysylaneLista.getColumnModel().getColumn(1).setHeaderValue("Tytuł");
        odbieraneLista.getColumnModel().getColumn(0).setHeaderValue("ID");
        odbieraneLista.getColumnModel().getColumn(1).setHeaderValue("Tytuł");

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
        if (tabela == "wyslane") {
            //index = wyslane.getS

        } else {
            //index = odebrane.getSelectedRow();
        }
        return index;
    }

}
