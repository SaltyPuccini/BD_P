package Dyrektor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ZmienDane extends JPanel {
    JButton wroc=new JButton("Wróć");
    JButton zmienDane=new JButton("Zmień dane");

    JLabel etykietaImie = new JLabel("Imię:");
    JTextField wpiszImie= new JTextField("");
    JSplitPane imie = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaImie,wpiszImie);

    JLabel etykietaNazwisko= new JLabel("Nazwisko:");
    JTextField wpiszNazwisko= new JTextField("");
    JSplitPane nazwisko = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaNazwisko,wpiszNazwisko);

    public void addActionListener(ActionListener listener) {
        wroc.addActionListener(listener);
        zmienDane.addActionListener(listener);
    }

    public ZmienDane() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);

        wroc.setActionCommand("wroc");
        zmienDane.setActionCommand("zmienDane");

        uklad.gridwidth=2;
        uklad.gridx=0;
        uklad.gridy=0;
        add(imie,uklad);

        uklad.gridx=0;
        uklad.gridy=1;
        add(nazwisko,uklad);

        uklad.gridwidth=1;
        uklad.gridx=1;
        uklad.gridy=2;
        add(zmienDane,uklad);

        uklad.gridx=0;
        uklad.gridy=2;
        add(wroc,uklad);
    }

    public void czysc(){
        wpiszImie.setText("");
        wpiszNazwisko.setText("");
    }

    public String getImie(){
        return wpiszImie.getText();
    }

    public String getNazwisko(){
        return wpiszNazwisko.getText();
    }
}
