package Dyrektor;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PrzegladPracownikow extends JPanel {

    private final JButton dodajPracownika = new JButton("Dodaj Pracownika");
    private final JButton zwolnijPracownika = new JButton("Zwolnij Pracownika");
    private final JButton generujTOKEN = new JButton("Generuj TOKEN");
    private final JButton zmienDane = new JButton("Zmień Dane");
    private final JButton wroc = new JButton("Wróć");
    JLabel TOKEN = new JLabel("");

    DefaultTableModel model = new DefaultTableModel(new String[] {"ID", "Imie", "Nazwisko", "Placówka"}, 0);

    JTable tabelaPracownikow;

    public void addActionListener(ActionListener listener) {
        wroc.addActionListener(listener);
        dodajPracownika.addActionListener(listener);
        zwolnijPracownika.addActionListener(listener);
        generujTOKEN.addActionListener(listener);
        zmienDane.addActionListener(listener);
    }

    public void dodajDaneZBazy(Object[] obj){
        model.addRow(obj);
    }

    public void czyscTabele(){
        model.setRowCount(0);
    }

    public PrzegladPracownikow(){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);

        tabelaPracownikow = new JTable(model);
        tabelaPracownikow.getColumnModel().getColumn(0).setMaxWidth(50);
        tabelaPracownikow.getColumnModel().getColumn(3).setMaxWidth(80);
        JScrollPane scrollPane_1 = new JScrollPane(tabelaPracownikow);

        wroc.setActionCommand("wroc");
        dodajPracownika.setActionCommand("dodajPracownika");
        zwolnijPracownika.setActionCommand("zwolnijPracownika");
        generujTOKEN.setActionCommand("generujTOKEN");
        zmienDane.setActionCommand("zmienDane");

        tabelaPracownikow.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                setGuziki(e.getFirstIndex()!=-1);
            }
        });
        setGuziki(false);

        uklad.gridx=1;
        uklad.gridy=0;
        add(dodajPracownika,uklad);

        uklad.gridx=2;
        uklad.gridy=0;
        add(zwolnijPracownika,uklad);

        uklad.gridx=1;
        uklad.gridy=1;
        add(generujTOKEN,uklad);

        uklad.gridx=1;
        uklad.gridy=2;
        add(zmienDane,uklad);

        uklad.gridx=2;
        uklad.gridy=2;
        add(wroc,uklad);

        uklad.gridx=2;
        uklad.gridy=1;
        add(TOKEN,uklad);

        uklad.gridheight=3;
        uklad.gridx=0;//
        uklad.gridy=0;
        scrollPane_1.setPreferredSize(new Dimension(600,400));
        add(scrollPane_1,uklad);
    }

    public void setTOKEN(int TOKEN){
        this.TOKEN.setText(String.valueOf(TOKEN));
    }

    public int getID() {
        int index=tabelaPracownikow.getSelectedRow();
        return (int) tabelaPracownikow.getValueAt(index, 0);
    }

    public void setGuziki(boolean stan){
        generujTOKEN.setEnabled(stan);
        zmienDane.setEnabled(stan);
        zwolnijPracownika.setEnabled(stan);
    }
}
