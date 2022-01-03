package Dyrektor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DyrektorDodanieGry extends JPanel {


    JTextField wpiszTytul = new JTextField(30);
    JLabel etykietaTytul =  new JLabel("Tytuł:");
    JSplitPane tytul = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaTytul,wpiszTytul);

    JTextField wpiszWydawce = new JTextField(30);
    JLabel etykietaWydawca =  new JLabel("Wydawca:");
    JSplitPane wydawca = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaWydawca,wpiszWydawce);

    Integer [] lata = {1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021};
    JComboBox wpiszRok = new JComboBox(lata);
    JLabel etykietaRok =  new JLabel("Rok:");
    JSplitPane rok = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaRok,wpiszRok);

    String [] klasy = {"AAA","Srednia","Niska","Edukacyjne","Edycje Specjalne"};
    JComboBox wpiszKlase = new JComboBox(klasy);
    JLabel etykietaKlasa =  new JLabel("Klasa:");
    JSplitPane klasa = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaKlasa,wpiszKlase);

    String [] gatunki = {"Przygodowe","Rougelike","MMO","MOBA","Battle Royal","Zręcznościowe","RPG","FPS","Strategiczne","Bijatyki","JRPG","Platformówki","Symulacyjne","Skradanki","Sportowe"};
    JComboBox wpiszGatunek = new JComboBox(gatunki);
    JLabel etykietaGatunek =  new JLabel("Gatunek:");
    JSplitPane gatunek = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaGatunek,wpiszGatunek);

    JButton dodajGre = new JButton("Dodaj grę");
    JButton wroc=new JButton("Wróć");

    public void addActionListener(ActionListener listener) {
        wroc.addActionListener(listener);
        dodajGre.addActionListener(listener);
    }

    public DyrektorDodanieGry() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);
        dodajGre.setActionCommand("dodajGre");
        wroc.setActionCommand("wroc");

        uklad.gridx=0;
        uklad.gridy=0;
        add(tytul,uklad);

        uklad.gridx=0;
        uklad.gridy=1;
        add(rok,uklad);

        uklad.gridx=0;
        uklad.gridy=2;
        add(wydawca,uklad);

        uklad.gridx=0;
        uklad.gridy=3;
        add(klasa,uklad);

        uklad.gridx=0;
        uklad.gridy=4;
        add(gatunek,uklad);

        uklad.gridx=1;
        uklad.gridy=1;
        add(dodajGre,uklad);

        uklad.gridx=1;
        uklad.gridy=3;
        add(wroc,uklad);
    }

    public String getTytul() {
        return wpiszTytul.getText().toString();
    }

    public String getWydawce() {
        return wpiszWydawce.getText().toString();
    }

    public Integer getRok() {
        return (Integer) wpiszRok.getSelectedItem();
    }

    public String getKlase() {
        return wpiszKlase.getSelectedItem().toString();
    }

    public String getGatunek(){
        return wpiszGatunek.getSelectedItem().toString();
    }
}

