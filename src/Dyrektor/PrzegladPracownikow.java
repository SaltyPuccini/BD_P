package Dyrektor;

import javax.swing.*;
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
    private List<Pracownik> listaPracownikow = new ArrayList<>();

    JTable tabelaPracownikow;
    JScrollPane scrollPane_1;

    public void addActionListener(ActionListener listener) {
        wroc.addActionListener(listener);
        dodajPracownika.addActionListener(listener);
        zwolnijPracownika.addActionListener(listener);
        generujTOKEN.addActionListener(listener);
        zmienDane.addActionListener(listener);
    }

    public PrzegladPracownikow(){

        scrollPane_1 = new JScrollPane();
        tabelaPracownikow=new JTable();

        tabelaPracownikow.setModel(new CustomTableModelPracownik(listaPracownikow));
        scrollPane_1.setViewportView(tabelaPracownikow);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);

        wroc.setActionCommand("wroc");
        dodajPracownika.setActionCommand("dodajPracownika");
        zwolnijPracownika.setActionCommand("zwolnijPracownika");
        generujTOKEN.setActionCommand("generujTOKEN");
        zmienDane.setActionCommand("zmienDane");

        uklad.gridx=0;//
        uklad.gridy=0;
        add(scrollPane_1,uklad);

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
    }

    public void setTOKEN(int TOKEN){
        this.TOKEN.setText(String.valueOf(TOKEN));
    }

    public int getID() {
        int index=tabelaPracownikow.getSelectedRow();
        return (int) tabelaPracownikow.getValueAt(index, 0);
    }
}
