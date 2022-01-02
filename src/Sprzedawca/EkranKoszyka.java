package Sprzedawca;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class EkranKoszyka extends JPanel {

    DefaultTableModel model = new DefaultTableModel(new String[] { "ID", "Tytuł","Stan","Cena","Rok wydania", "Wydawca"}, 0);
    JTable koszykLista = new JTable(model);
    JScrollPane koszyk = new JScrollPane(koszykLista);

    JButton sprzedaj = new JButton("Sprzedaj");
    JButton usun = new JButton("Usuń egzemplarz");
    JButton wroc = new JButton("Wróć");

    public void addActionListener(ActionListener listener) {
        sprzedaj.addActionListener(listener);
        usun.addActionListener(listener);
        wroc.addActionListener(listener);
    }

    public void dodajDaneZBazy(Object[] obj){
        model.addRow(obj);
    }

    public void czyscTabele(){
        model.setRowCount(0);
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
