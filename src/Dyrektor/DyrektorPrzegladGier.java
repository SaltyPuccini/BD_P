package Dyrektor;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class DyrektorPrzegladGier extends JPanel {
    private final JButton filtruj = new JButton("Filtruj");
    private final JButton dodajGre = new JButton("Dodaj grę");
    private final JButton dodajEgzemplarze = new JButton("Dodaj egzemplarze");
    private final JButton wroc = new JButton("Wróć");
    JTable tabelaGier;
    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Tytuł", "Rok wydania", "Wydawca", "Gatunek"}, 0);
    JLabel etykietaTytul = new JLabel("Tytuł:");
    JLabel etykietaCena = new JLabel("Cena:");
    JLabel etykietaLiczba = new JLabel("Liczba:");
    JTextField wpiszTytul = new JTextField(20);
    JSplitPane tytul = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaTytul, wpiszTytul);
    JTextField wpiszCena = new JTextField(20);
    JSplitPane cena = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaCena, wpiszCena);
    JTextField wpiszLiczba = new JTextField(20);
    JSplitPane liczba = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaLiczba, wpiszLiczba);
    private final boolean[] flaga = {false, false, false};

    public DyrektorPrzegladGier() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);

        tabelaGier = new JTable(model);
        tabelaGier.getColumnModel().getColumn(0).setMaxWidth(50);
        tabelaGier.getColumnModel().getColumn(2).setMaxWidth(130);
        tabelaGier.getColumnModel().getColumn(4).setMaxWidth(130);
        JScrollPane scrollPane_1 = new JScrollPane(tabelaGier);
        scrollPane_1.setPreferredSize(new Dimension(800, 400));

        filtruj.setActionCommand("filtruj");
        wroc.setActionCommand("wroc");
        dodajGre.setActionCommand("dodajGre");
        dodajEgzemplarze.setActionCommand("dodajEgzemplarze");

        dodajEgzemplarze.setEnabled(false);
        tabelaGier.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getFirstIndex() != -1) {
                    flaga[0] = true;
                    dodajEgzemplarze.setEnabled(flaga[1] && flaga[2]);
                } else {
                    flaga[0] = false;
                    dodajEgzemplarze.setEnabled(false);
                }
            }
        });
        wpiszCena.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (!Objects.equals(wpiszCena.getText(), "")) {
                    flaga[1] = true;
                    dodajEgzemplarze.setEnabled(flaga[0] && flaga[2]);
                } else {
                    flaga[1] = false;
                    dodajEgzemplarze.setEnabled(false);
                }
            }
        });
        wpiszLiczba.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {

            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (!Objects.equals(wpiszLiczba.getText(), "")) {
                    flaga[2] = true;
                    dodajEgzemplarze.setEnabled(flaga[0] && flaga[1]);
                } else {
                    flaga[2] = false;
                    dodajEgzemplarze.setEnabled(false);
                }
            }
        });

        uklad.gridx = 0;
        uklad.gridy = 0;
        add(tytul, uklad);

        uklad.gridx = 1;
        uklad.gridy = 0;
        add(filtruj, uklad);

        uklad.gridx = 3;
        uklad.gridy = 0;
        add(dodajGre, uklad);

        uklad.gridx = 3;
        uklad.gridy = 1;
        add(cena, uklad);

        uklad.gridx = 3;
        uklad.gridy = 2;
        add(liczba, uklad);

        uklad.gridx = 3;
        uklad.gridy = 3;
        add(dodajEgzemplarze, uklad);

        uklad.gridx = 3;
        uklad.gridy = 5;
        add(wroc, uklad);

        uklad.gridwidth = 2;
        uklad.gridheight = 3;
        uklad.gridx = 0;
        uklad.gridy = 1;
        add(scrollPane_1, uklad);
    }

    public void addActionListener(ActionListener listener) {
        wroc.addActionListener(listener);
        filtruj.addActionListener(listener);
        dodajGre.addActionListener(listener);
        dodajEgzemplarze.addActionListener(listener);
    }

    public int gra() {
        int index = tabelaGier.getSelectedRow();
        return (int) tabelaGier.getValueAt(index, 0);
    }

    public int cena() {
        return Integer.parseInt(wpiszCena.getText());
    }

    public int liczba() {
        return Integer.parseInt(wpiszLiczba.getText());
    }

    public String getTytul() {
        return wpiszTytul.getText();
    }

    public void dodajDaneZBazy(Object[] obj) {

        model.addRow(obj);

    }

    public void czyscTabele() {
        model.setRowCount(0);
    }

    public void setGuzik(boolean stan) {
        dodajEgzemplarze.setEnabled(stan);
    }

    public void czysc(){
        wpiszLiczba.setText("");
        wpiszCena.setText("");
        wpiszTytul.setText("");
        flaga[0]=false;
        flaga[1]=false;
        flaga[2]=false;
    }
}
