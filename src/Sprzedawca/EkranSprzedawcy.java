package Sprzedawca;

import javax.swing.*;
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

    Integer[] lata = {1990, 1991};
    JLabel etykietaRok = new JLabel("Rok:");
    JComboBox wpiszRok = new JComboBox(lata);
    JSplitPane rok = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaRok, wpiszRok);

    private String[] gatunki = {"strzelanki", "strategiczne"};
    JLabel etykietaGatunek = new JLabel("Gatunek:");
    JComboBox wpiszGatunek = new JComboBox(gatunki);
    JSplitPane gatunek = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaGatunek, wpiszGatunek);

    JTable gry = new JTable();
    JTable egzemplarze = new JTable();

    public void addActionListener(ActionListener listener) {
        dodaj.addActionListener(listener);
        koszyk.addActionListener(listener);
        zamowienia.addActionListener(listener);
        szukaj.addActionListener(listener);
        kup.addActionListener(listener);
        zamow.addActionListener(listener);
        wyloguj.addActionListener(listener);
    }

    public EkranSprzedawcy(){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);
        dodaj.setActionCommand("dodaj");
        koszyk.setActionCommand("koszyk");
        zamowienia.setActionCommand("zamowienia");
        szukaj.setActionCommand("szukaj");
        kup.setActionCommand("kup");
        zamow.setActionCommand("zamow");
        wyloguj.setActionCommand("wyloguj");

        uklad.gridx=0;
        uklad.gridy=2;
        add(id,uklad);
        uklad.gridx=0;
        uklad.gridy=3;
        add(koszyk,uklad);
        uklad.gridx=0;
        uklad.gridy=4;
        add(kup,uklad);

        uklad.gridx=1;
        uklad.gridy=2;
        add(dodaj,uklad);
        uklad.gridx=1;
        uklad.gridy=3;
        add(zamowienia,uklad);
        uklad.gridx=1;
        uklad.gridy=4;
        add(zamow,uklad);

        uklad.gridx=2;
        uklad.gridy=0;
        add(rok,uklad);
        uklad.gridx=3;
        uklad.gridy=0;
        add(gatunek,uklad);

        uklad.gridwidth = 2;
        uklad.gridx=0;
        uklad.gridy=0;
        add(tytul,uklad);
        uklad.gridx=0;
        uklad.gridy=1;
        add(szukaj,uklad);
        uklad.gridx=0;
        uklad.gridy=6;
        add(wyloguj,uklad);

        uklad.gridheight = 3;
        uklad.gridx=2;
        uklad.gridy=1;
        add(gry,uklad);
        uklad.gridx=2;
        uklad.gridy=4;
        add(egzemplarze,uklad);
    }

    public String getTytul(){
        return wpiszTytul.getText();
    }
    public Integer getID(){
        return Integer.valueOf(wpiszID.getText());
    }
    public Integer getRok(){
        return (Integer) wpiszRok.getSelectedItem();
    }
    public String getGatunek(){
        return (String) wpiszGatunek.getSelectedItem();
    }

}
