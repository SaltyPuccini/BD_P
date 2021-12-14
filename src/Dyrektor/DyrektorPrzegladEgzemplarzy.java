package Dyrektor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

public class DyrektorPrzegladEgzemplarzy extends JPanel {

    private final JButton filtruj = new JButton("Filtruj");
    private final JButton dodajGre = new JButton("Dodaj grę");
    private final JButton dodajEgzemplarze = new JButton("Dodaj egzemplarze");
    private final JButton wroc = new JButton("Wróć");
    private List<Gra> listaGier = new ArrayList<>();

    JTable tabelaGier;
    JScrollPane scrollPane_1;

    JLabel etykietaTytul = new JLabel("Tytuł:");
    JLabel etykietaCena = new JLabel("Cena:");
    JLabel etykietaLiczba = new JLabel("Liczba:");

    JTextField wpiszTytul = new JTextField(20);
    JSplitPane tytul = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaTytul, wpiszTytul);
    JTextField wpiszCena = new JTextField(20);
    JSplitPane cena = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaCena, wpiszCena);
    JTextField wpiszLiczba = new JTextField(20);
    JSplitPane liczba = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaLiczba, wpiszLiczba);

    public void addActionListener(ActionListener listener) {
        wroc.addActionListener(listener);
        filtruj.addActionListener(listener);
        dodajGre.addActionListener(listener);
        dodajEgzemplarze.addActionListener(listener);
    }

    public DyrektorPrzegladEgzemplarzy() {

        scrollPane_1 = new JScrollPane();
        tabelaGier=new JTable();
        Gra gra = new Gra(1, "xD", "XD", "xDDD","xdd",2000);
        listaGier.add(gra);
        tabelaGier.setModel(new CustomTableModelGry(listaGier));
        scrollPane_1.setViewportView(tabelaGier);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);

        filtruj.setActionCommand("filtruj");
        wroc.setActionCommand("wroc");
        dodajGre.setActionCommand("dodajGre");
        dodajEgzemplarze.setActionCommand("dodajEgzemplarze");

        uklad.gridx=0;//
        uklad.gridy=0;
        add(tytul,uklad);

        uklad.gridx=1;
        uklad.gridy=0;
        add(filtruj,uklad);

        uklad.gridx=3;
        uklad.gridy=0;
        add(dodajGre,uklad);

        uklad.gridx=3;
        uklad.gridy=0;
        add(dodajGre,uklad);

        uklad.gridx=0;
        uklad.gridy=4;
        add(scrollPane_1,uklad);

        uklad.gridx=3;
        uklad.gridy=1;
        add(cena,uklad);

        uklad.gridx=3;
        uklad.gridy=2;
        add(liczba,uklad);

        uklad.gridx=3;
        uklad.gridy=3;
        add(dodajEgzemplarze,uklad);

        uklad.gridx=3;
        uklad.gridy=5;
        add(wroc,uklad);
    }
}
