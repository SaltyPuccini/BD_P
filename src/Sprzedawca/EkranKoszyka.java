package Sprzedawca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EkranKoszyka extends JPanel {
    String[] kolumny = {"ID", "Tytuł", "Stan", "Wydawca", "Rok", "Cena"};
    Object[][] dane = new Object[][]{{1, "Stellaris", "4", "Paradox", 2015, 150}};
    JTable koszyk = new JTable(dane,kolumny);

    JButton sprzedaj = new JButton("Sprzedaj");
    JButton usun = new JButton("Usuń egzemplarz");
    JButton wroc = new JButton("Wróć");

    public void addActionListener(ActionListener listener) {
        sprzedaj.addActionListener(listener);
        usun.addActionListener(listener);
        wroc.addActionListener(listener);
    }

    public EkranKoszyka() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 3;
        uklad.weightx = 3;
        uklad.insets = new Insets(10, 10, 10, 10);
        sprzedaj.setActionCommand("sprzedaj");
        usun.setActionCommand("usun");
        wroc.setActionCommand("wroc");

        uklad.gridx = 0;
        uklad.gridy = 0;
        add(koszyk, uklad);

        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.gridx = 0;
        uklad.gridy = 3;
        add(sprzedaj, uklad);

        uklad.gridx = 1;
        uklad.gridy = 3;
        add(usun, uklad);

        uklad.gridx = 2;
        uklad.gridy = 3;
        add(wroc, uklad);
    }
}
