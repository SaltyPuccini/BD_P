package Sprzedawca;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class EkranSprzedawcy extends JPanel {
    JLabel etykietaTytul = new JLabel("Tytuł:");
    JTextField wpiszTytul = new JTextField(20);
    JSplitPane tytul = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaTytul, wpiszTytul);

    JButton szukaj = new JButton("Szukaj");

    JLabel etykietaID = new JLabel("ID transakcji:");
    JTextField wpiszID = new JTextField(6);
    JSplitPane id = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaID, wpiszID);

    JButton dodaj = new JButton("Dodaj do koszyka");
    JButton koszyk = new JButton("Zobacz koszyk");
    JButton zamowienia = new JButton("Zamówienia");
    JButton kup = new JButton("Kup grę");
    JButton zamow = new JButton("Zamów");
    JButton wyloguj = new JButton("Wyloguj");

    Object [] lata = {"*",1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021};
    JLabel etykietaRok = new JLabel("Rok:");
    JComboBox wpiszRok = new JComboBox(lata);
    JSplitPane rok = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaRok, wpiszRok);

    private String[] gatunki = {"*","strzelanka", "strategiczne","akcji","bijatyki","logiczne","platformówki","przygodowe","RPG","sportowa","symulacje","wyścigowe"};
    JLabel etykietaGatunek = new JLabel("Gatunek:");
    JComboBox wpiszGatunek = new JComboBox(gatunki);
    JSplitPane gatunek = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaGatunek, wpiszGatunek);

    DefaultTableModel modelGry = new DefaultTableModel(new String[] { "ID", "Tytuł","Rok wydania", "Wydawca", "Gatunek"}, 0);
    DefaultTableModel modelEgzemplarza = new DefaultTableModel(new String[] { "ID", "Stan","Cena", "Placówka"}, 0);

    JTable gryLista = new JTable(modelGry);
    JTable egzemplarzeLista = new JTable(modelEgzemplarza);

    JScrollPane gry = new JScrollPane(gryLista);
    JScrollPane egzemplarze = new JScrollPane(egzemplarzeLista);

    public int getIdWybranejGry() {
        return idWybranejGry;
    }

    int idWybranejGry = -1;

    public void addActionListener(ActionListener listener) {
        dodaj.addActionListener(listener);
        koszyk.addActionListener(listener);
        zamowienia.addActionListener(listener);
        szukaj.addActionListener(listener);
        kup.addActionListener(listener);
        zamow.addActionListener(listener);
        wyloguj.addActionListener(listener);
    }

    public void addSelectionListener(ListSelectionListener listener) {
        gryLista.getSelectionModel().addListSelectionListener(listener);
    }

    public EkranSprzedawcy() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();

        dodaj.setActionCommand("dodaj");
        koszyk.setActionCommand("koszyk");
        zamowienia.setActionCommand("zamowienia");
        szukaj.setActionCommand("szukaj");
        kup.setActionCommand("kup");
        zamow.setActionCommand("zamow");
        wyloguj.setActionCommand("wyloguj");

        uklad.gridwidth = 1;
        uklad.gridheight = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);

        uklad.gridx = 0;
        uklad.gridy = 2;
        add(id, uklad);
        uklad.gridx = 0;
        uklad.gridy = 3;
        add(koszyk, uklad);
        uklad.gridx = 0;
        uklad.gridy = 4;
        add(kup, uklad);

        uklad.gridx = 1;
        uklad.gridy = 2;
        add(dodaj, uklad);
        uklad.gridx = 1;
        uklad.gridy = 3;
        add(zamowienia, uklad);
        uklad.gridx = 1;
        uklad.gridy = 4;
        add(zamow, uklad);

        uklad.gridx = 2;
        uklad.gridy = 0;
        add(rok, uklad);
        uklad.gridx = 3;
        uklad.gridy = 0;
        add(gatunek, uklad);

        uklad.gridwidth = 2;
        uklad.gridx = 0;
        uklad.gridy = 0;
        add(tytul, uklad);
        uklad.gridx = 0;
        uklad.gridy = 1;
        add(szukaj, uklad);
        uklad.gridx = 0;
        uklad.gridy = 6;
        add(wyloguj, uklad);

        gry.setPreferredSize(new Dimension(420,140));
        egzemplarze.setPreferredSize(new Dimension(420,140));

        uklad.gridwidth = 3;
        uklad.gridheight = 2;
        uklad.gridx = 2;
        uklad.gridy = 1;
        add(gry, uklad);
        uklad.gridx = 2;
        uklad.gridy = 3;
        add(egzemplarze, uklad);
    }

    public String getTytul() {
        return wpiszTytul.getText();
    }

    public String getID() {
        return wpiszID.getText();
    }

    public Integer getRok() {
        if(wpiszRok.getSelectedItem()=="*")
            return null;
        return (Integer) wpiszRok.getSelectedItem();
    }

    public String getGatunek() {
        if(wpiszGatunek.getSelectedItem()=="*")
            return null;
        return (String) wpiszGatunek.getSelectedItem();
    }

    public int getIDGry(){
        int index = gryLista.getSelectedRow();
        return (int) gryLista.getValueAt(index, 0);
    }

    public int getPlacowka(){
        int placowka,index = egzemplarzeLista.getSelectedRow();
        placowka= (int) egzemplarzeLista.getValueAt(index, 3);
        return placowka;
    }

    public void resetID(){
        wpiszID.setText("");
    }

    public void czyscTabeleGry(){
        modelGry.setRowCount(0);
    }

    public void czyscTabeleEgzemplarze(){
        modelEgzemplarza.setRowCount(0);
    }

    public void dodajEgzemplarzZBazy(Object[] object) {
        modelEgzemplarza.addRow(object);
    }

    public void dodajGreZBazy(Object[] object) {
        modelGry.addRow(object);
    }

    public int getIDEgzemplarza() {
        int index = egzemplarzeLista.getSelectedRow();
        return (int) egzemplarzeLista.getValueAt(index, 0);
    }
}