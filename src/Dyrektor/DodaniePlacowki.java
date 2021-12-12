package Dyrektor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DodaniePlacowki extends JPanel {
    private final JButton dodajPlacowke = new JButton("Dodaj Placówkę");
    private final JButton wroc = new JButton("Wróć");
    private final JButton generujID = new JButton("Generuj ID");


    private final JTextArea napis = new JTextArea(1, 10);
    private final JTextArea ID = new JTextArea(1, 3);
    private final JSplitPane idPlacowki = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, napis, ID);

    private final JLabel etykietaMiasto = new JLabel("Miasto:");
    private final JTextField wpiszMiasto = new JTextField(20);
    private final JSplitPane miasto = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaMiasto, wpiszMiasto);

    private final JLabel etykietaUlica = new JLabel("Ulica:");
    private final JTextField wpiszUlica = new JTextField(20);
    private final JSplitPane ulica = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaUlica, wpiszUlica);

    private final JLabel etykietaNumer = new JLabel("Numer:");
    private final JTextField wpiszNumer = new JTextField(20);
    private final JSplitPane numer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaNumer, wpiszNumer);

    private final JLabel etykietaLokal = new JLabel("Lokal:");
    private final JTextField wpiszLokal = new JTextField(20);
    private final JSplitPane lokal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaLokal, wpiszLokal);


    public void addActionListener(ActionListener listener) {
        wroc.addActionListener(listener);
        dodajPlacowke.addActionListener(listener);
        generujID.addActionListener(listener);
    }

    public DodaniePlacowki() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);
        dodajPlacowke.setActionCommand("dodajPlacowke");
        wroc.setActionCommand("wroc");
        napis.setText("ID placówki:");
        ID.setText("");

        uklad.gridx=0;//
        uklad.gridy=0;
        add(miasto,uklad);

        uklad.gridx=0;
        uklad.gridy=1;
        add(ulica,uklad);

        uklad.gridx=0;
        uklad.gridy=2;
        add(numer,uklad);

        uklad.gridx=0;
        uklad.gridy=3;
        add(lokal,uklad);

        uklad.gridx=1;//id
        uklad.gridy=0;
        add(idPlacowki,uklad);

        uklad.gridx=1;
        uklad.gridy=1;
        add(generujID,uklad);

        uklad.gridx=1;//dodaj
        uklad.gridy=3;
        add(dodajPlacowke,uklad);

        uklad.gridx=1;//wroc
        uklad.gridy=4;
        add(wroc,uklad);
    }

    public String getMiasto() {
        return wpiszMiasto.getText();
    }

    public String getUlica() {
        return wpiszUlica.getText();
    }

    public String getNumer() {
        return wpiszNumer.getText();
    }

    public String getLokal() {
        return wpiszLokal.getText();
    }

    public void setID(Integer id) {
        ID.setText(id.toString());
    }

}
