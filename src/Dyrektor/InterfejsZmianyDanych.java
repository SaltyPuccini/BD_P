package Dyrektor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InterfejsZmianyDanych extends JPanel {

    private final JButton zapisz = new JButton("Zapisz");
    private final JButton wroc = new JButton("Wróć");

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
        zapisz.addActionListener(listener);
    }

    public InterfejsZmianyDanych(){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);
        zapisz.setActionCommand("zapisz");
        wroc.setActionCommand("wroc");

        uklad.gridx=0;//
        uklad.gridy=0;
        add(imie,uklad);

        uklad.gridx=1;
        uklad.gridy=0;
        add(nazwisko,uklad);

        uklad.gridx=0;
        uklad.gridy=1;
        add(ID,uklad);

        uklad.gridx=1;
        uklad.gridy=1;
        add(placowka,uklad);

        uklad.gridx=0;//id
        uklad.gridy=2;
        add(zapisz,uklad);

        uklad.gridx=1;
        uklad.gridy=2;
        add(wroc,uklad);


    }



}
