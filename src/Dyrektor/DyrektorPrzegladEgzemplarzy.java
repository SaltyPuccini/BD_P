package Dyrektor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DyrektorPrzegladEgzemplarzy extends JPanel {

    private final JButton filtruj = new JButton("Filtruj");
    private final JButton dodajGre = new JButton("Dodaj grę");
    private final JButton dodajEgzemplarze = new JButton("Dodaj egzemplarze");
    private final JButton wroc = new JButton("Wróć");
    private List<Gra> listaGier = new ArrayList<>();

    JTable tabelaGier=new JTable(new DefaultTableModel(new String[]{"ID", "Tytuł","Wydawca","Rok"}, 0) );;
    JScrollPane scrollPane_1 = new JScrollPane(tabelaGier);

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
    public int gra(){
        int index = tabelaGier.getSelectedRow();
        return (int) tabelaGier.getValueAt(index,0);
    }
    public int cena(){
        return Integer.parseInt(wpiszCena.getText());
    }
    public int liczba(){
        return Integer.parseInt(wpiszLiczba.getText());
    }
}
