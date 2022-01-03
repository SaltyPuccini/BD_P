package Dyrektor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PrzegladPlacowek extends JPanel {

    private final JButton dodajPlacowke = new JButton("Dodaj placówkę");
    private final JButton zamknijPlacowke = new JButton("Zamknij placówkę");
    private final JButton wroc = new JButton("Wróć");

    DefaultTableModel model = new DefaultTableModel(new String[] { "ID", "Adres", "Numer", "Numer Lokalu", "Miasto"}, 0);

    JTable tabelaPlacowek;

    public void addActionListener(ActionListener listener) {
        wroc.addActionListener(listener);
        dodajPlacowke.addActionListener(listener);
        zamknijPlacowke.addActionListener(listener);
    }

    public int getPlacowka() {
        int index=tabelaPlacowek.getSelectedRow();
        return (int) tabelaPlacowek.getValueAt(index, 0);
    }

    public void dodajDaneZBazy(Object[] obj){
        model.addRow(obj);
    }

    public void czyscTabele(){
        model.setRowCount(0);
    }

    public PrzegladPlacowek(){
        tabelaPlacowek=new JTable(model);
        JScrollPane scrollPane_1 = new JScrollPane(tabelaPlacowek);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);

        dodajPlacowke.setActionCommand("dodajPlacowke");
        wroc.setActionCommand("wroc");
        zamknijPlacowke.setActionCommand("zamknijPlacowke");

        uklad.gridx=1;
        uklad.gridy=0;
        add(dodajPlacowke,uklad);

        uklad.gridx=1;
        uklad.gridy=1;
        add(zamknijPlacowke,uklad);

        uklad.gridx=1;
        uklad.gridy=2;
        add(wroc,uklad);

        uklad.gridheight=3;
        uklad.gridx=0;//
        uklad.gridy=0;
        scrollPane_1.setPreferredSize(new Dimension(600,400));
        add(scrollPane_1,uklad);
    }



}
