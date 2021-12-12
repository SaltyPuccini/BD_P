package Dyrektor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DodaniePlacowki extends JPanel {
    private final JButton dodajPlacowke = new JButton("Dodaj Placówkę");
    private final JButton wroc = new JButton("Wróć");
    JButton generujID = new JButton("Generuj ID");


    JLabel napis = new JLabel("ID placówki:");
    JTextArea ID = new JTextArea(1, 3);
    JSplitPane idPlacowki = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, napis, ID);

    JLabel etykietaMiasto = new JLabel("Miasto:");
    JLabel etykietaUlica = new JLabel("Ulica:");
    JLabel etykietaNumer = new JLabel("Numer:");
    JLabel etykietaLokal = new JLabel("Lokal:");

    JTextField wpiszMiasto = new JTextField(20);
    JSplitPane miasto = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaMiasto, wpiszMiasto);
    JTextField wpiszUlica = new JTextField(20);
    JSplitPane ulica = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaUlica, wpiszUlica);
    JTextField wpiszNumer = new JTextField(20);
    JSplitPane numer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaNumer, wpiszNumer);
    JTextField wpiszLokal = new JTextField(20);
    JSplitPane lokal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaLokal, wpiszLokal);


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

    public String getEtykietaMiasto() {
        return wpiszMiasto.getText();
    }

    public String getEtykietaUlica() {
        return wpiszUlica.getText();
    }

    public String getEtykietaNumer() {
        return wpiszNumer.getText();
    }

    public String getEtykietaLokal() {
        return wpiszLokal.getText();
    }

    public void setID(Integer id) {
        ID.setText(id.toString());
    }

}
