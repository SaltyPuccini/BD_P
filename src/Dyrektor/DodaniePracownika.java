package Dyrektor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DodaniePracownika extends JPanel {
    private final JButton dodajPracownika = new JButton("Dodaj Pracownika");
    private final JButton wroc = new JButton("Wróć");
    JButton generujPIN = new JButton("Generuj PIN");


    JLabel napis = new JLabel("PIN pracownika:");
    JTextArea PIN = new JTextArea(1, 3);
    JSplitPane PINPracownika = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, napis, PIN);

    JLabel etykietaImie = new JLabel("Imię:");
    JLabel etykietaNazwisko = new JLabel("Nazwisko:");
    JLabel etykietaID = new JLabel("ID:");
    JLabel etykietaPlacowka = new JLabel("Lokal:");

    JTextField wpiszImie = new JTextField(20);
    JSplitPane imie = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaImie, wpiszImie);
    JTextField wpiszNazwisko = new JTextField(20);
    JSplitPane nazwisko = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaNazwisko, wpiszNazwisko);
    JTextField wpiszID = new JTextField(20);
    JSplitPane ID = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaID, wpiszID);
    JTextField wpiszPlacowka = new JTextField(20);
    JSplitPane placowka = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaPlacowka, wpiszPlacowka);


    public void addActionListener(ActionListener listener) {
        wroc.addActionListener(listener);
        dodajPracownika.addActionListener(listener);
        generujPIN.addActionListener(listener);
    }

    public DodaniePracownika() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);
        dodajPracownika.setActionCommand("dodajPracownika");
        wroc.setActionCommand("wroc");

        uklad.gridx=0;//
        uklad.gridy=0;
        add(imie,uklad);

        uklad.gridx=0;
        uklad.gridy=1;
        add(nazwisko,uklad);

        uklad.gridx=0;
        uklad.gridy=2;
        add(ID,uklad);

        uklad.gridx=0;
        uklad.gridy=3;
        add(placowka,uklad);

        uklad.gridx=1;//id
        uklad.gridy=0;
        add(PINPracownika,uklad);

        uklad.gridx=1;
        uklad.gridy=1;
        add(generujPIN,uklad);

        uklad.gridx=1;//dodaj
        uklad.gridy=3;
        add(dodajPracownika,uklad);

        uklad.gridx=1;//wroc
        uklad.gridy=4;
        add(wroc,uklad);
    }

    public String getEtykietaImie() {
        return wpiszImie.getText();
    }

    public String getEtykietaNazwisko() {
        return wpiszNazwisko.getText();
    }

    public String getEtykietaID() {
        return wpiszID.getText();
    }

    public String getEtykietaPlacowka() {
        return wpiszPlacowka.getText();
    }

    public void setPIN(Integer id) {
        PIN.setText(id.toString());
    }

}
