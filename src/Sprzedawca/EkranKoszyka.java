package Sprzedawca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EkranKoszyka extends JPanel {
    JTable koszykLista = new JTable(new CustomTableModelKZ());//id, tytuł, stan,cena,rok ,wydawca
    JScrollPane koszyk = new JScrollPane(koszykLista);

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
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);
        sprzedaj.setActionCommand("sprzedaj");
        usun.setActionCommand("usun");
        wroc.setActionCommand("wroc");

        koszykLista.getColumnModel().getColumn(0).setHeaderValue("Tytuł");
        koszykLista.getColumnModel().getColumn(1).setHeaderValue("Rok");
        koszykLista.getColumnModel().getColumn(2).setHeaderValue("Wydawca");
        koszykLista.getColumnModel().getColumn(3).setHeaderValue("Stan");
        koszykLista.getColumnModel().getColumn(4).setHeaderValue("Cena");

        uklad.gridx = 0;
        uklad.gridy = 0;
        add(koszyk, uklad);

        uklad.gridwidth = 1;
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

    public int getID(){
        int index = koszykLista.getSelectedRow();
        return  (int) koszykLista.getValueAt(index, 0);
    }
}
