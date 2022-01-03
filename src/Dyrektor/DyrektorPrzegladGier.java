package Dyrektor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DyrektorPrzegladGier extends JPanel {

    private final JButton filtruj = new JButton("Filtruj");
    private final JButton dodajGre = new JButton("Dodaj grę");
    private final JButton dodajEgzemplarze = new JButton("Dodaj egzemplarze");
    private final JButton wroc = new JButton("Wróć");

    JTable tabelaGier;
    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Tytuł", "Rok wydania", "Wydawca", "Gatunek"}, 0);

    JLabel etykietaTytul = new JLabel("Tytuł:");
    JLabel etykietaCena = new JLabel("Cena:");
    JLabel etykietaLiczba = new JLabel("Liczba:");

    JTextField wpiszTytul = new JTextField(20);
    JSplitPane tytul = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaTytul, wpiszTytul);
    JTextField wpiszCena = new JTextField(20);
    JSplitPane cena = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaCena, wpiszCena);
    JTextField wpiszLiczba = new JTextField(20);
    JSplitPane liczba = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaLiczba, wpiszLiczba);

    public void addActionListener(ActionListener listener) {
        wroc.addActionListener(listener);
        filtruj.addActionListener(listener);
        dodajGre.addActionListener(listener);
        dodajEgzemplarze.addActionListener(listener);
    }

    public DyrektorPrzegladGier() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);

        tabelaGier = new JTable(model);
        tabelaGier.getColumnModel().getColumn(0).setMaxWidth(50);
        tabelaGier.getColumnModel().getColumn(2).setMaxWidth(130);
        tabelaGier.getColumnModel().getColumn(4).setMaxWidth(130);
        JScrollPane scrollPane_1 = new JScrollPane(tabelaGier);
        scrollPane_1.setPreferredSize(new Dimension(800,400));

        filtruj.setActionCommand("filtruj");
        wroc.setActionCommand("wroc");
        dodajGre.setActionCommand("dodajGre");
        dodajEgzemplarze.setActionCommand("dodajEgzemplarze");

        uklad.gridx = 0;
        uklad.gridy = 0;
        add(tytul, uklad);

        uklad.gridx = 1;
        uklad.gridy = 0;
        add(filtruj, uklad);

        uklad.gridx = 3;
        uklad.gridy = 0;
        add(dodajGre, uklad);

        uklad.gridx = 3;
        uklad.gridy = 1;
        add(cena, uklad);

        uklad.gridx = 3;
        uklad.gridy = 2;
        add(liczba, uklad);

        uklad.gridx = 3;
        uklad.gridy = 3;
        add(dodajEgzemplarze, uklad);

        uklad.gridx = 3;
        uklad.gridy = 5;
        add(wroc, uklad);

        uklad.gridwidth=2;
        uklad.gridheight=3;
        uklad.gridx = 0;
        uklad.gridy = 1;
        add(scrollPane_1, uklad);
    }

    public int gra() {
        int index = tabelaGier.getSelectedRow();
        return (int) tabelaGier.getValueAt(index, 0);
    }

    public int cena() {
        return Integer.parseInt(wpiszCena.getText());
    }

    public int liczba() {
        return Integer.parseInt(wpiszLiczba.getText());
    }

    public String getTytul(){
        return wpiszTytul.getText().toString();
    }

    public void dodajDaneZBazy(Object[] obj) {

        model.addRow(obj);

    }

    public void czyscTabele() {
        model.setRowCount(0);
    }
}
