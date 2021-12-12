package Sprzedawca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EkranZamowien extends JPanel {
    String[] kolumny = {"ID", "Tytuł"};
    Object[][] dane = new Object[][]{{1, "Stellaris"}};
    JTable wysylane = new JTable(dane, kolumny);
    JTable odbierane = new JTable(dane, kolumny);

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

        uklad.gridx = 0;
        uklad.gridy = 0;
        add(wysylane, uklad);

        uklad.gridx = 2;
        uklad.gridy = 0;
        add(odbierane, uklad);

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
        int index;
        if (tabela == "wyslane") {
            index = wysylane.getSelectedRow();

        } else {
            index = odbierane.getSelectedRow();
        }
        return index;
    }

}
