import Sprzedawca.CustomTableModelSR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EkranRzeczoznawcy extends JPanel {
    JButton wyloguj = new JButton("Wyloguj");
    JButton wycen = new JButton("Dokonano serwis");

    JTextField wpiszCena = new JTextField(3);
    JLabel etykietaCena = new JLabel("Cena:");
    JSplitPane cena = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaCena, wpiszCena);

    private String[] gatunki = {"strzelanki", "strategiczne"};
    JComboBox gatunek = new JComboBox(gatunki);


    JTable listaEgzemplarzy = new JTable(new CustomTableModelSR());
    JScrollPane tabelaEgzemplarzy = new JScrollPane(listaEgzemplarzy);

    public void addActionListener(ActionListener listener) {
        wyloguj.addActionListener(listener);
        wycen.addActionListener(listener);
    }

    public EkranRzeczoznawcy() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);
        wyloguj.setActionCommand("wyloguj");
        wycen.setActionCommand("wycen");
        listaEgzemplarzy.getColumnModel().getColumn(0).setHeaderValue("ID");
        listaEgzemplarzy.getColumnModel().getColumn(1).setHeaderValue("Tytuł");

        uklad.gridx = 3;
        uklad.gridy = 1;
        add(cena, uklad);

        uklad.gridx = 3;
        uklad.gridy = 2;
        add(gatunek, uklad);

        uklad.gridx = 3;
        uklad.gridy = 3;
        add(wycen, uklad);

        uklad.gridx = 3;
        uklad.gridy = 4;
        add(wyloguj, uklad);


        uklad.gridheight = 5;
        uklad.gridx = 0;
        uklad.gridy = 0;
        add(tabelaEgzemplarzy, uklad);
    }

    public Integer getCena() {
        return Integer.valueOf(wpiszCena.getText());
    }

    public String getGatunek() {
        return (String) gatunek.getSelectedItem();
    }

}
