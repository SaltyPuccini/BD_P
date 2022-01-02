package Sprzedawca;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class KupnoEgzemplarza extends JPanel {
    JButton dodajGre=new JButton("Dodaj grę");
    JButton zakup = new JButton("Zakup");
    JButton filtruj = new JButton("Filtruj");
    JButton wroc = new JButton("Wróć");
    JButton generujCene = new JButton("Generuj cenę");

    DefaultTableModel model = new DefaultTableModel(new String[] { "ID", "Tytuł", "Rok wydania", "Wydawca"}, 0);
    JTable gryLista = new JTable(model);
    JScrollPane gry = new JScrollPane(gryLista);


    JLabel etykietaStan= new JLabel("Stan");
    JComboBox comboStan = new JComboBox(new String [] {"wzorowy","dobry","używany","zły","fatalny"});
    JSplitPane stan= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaStan,comboStan);

    JLabel etykietaCena = new JLabel("Cena:");
    JTextArea generowanaCena = new JTextArea(1,3);
    JSplitPane cena = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaCena,generowanaCena);

    JLabel etykietaTytul = new JLabel("tytuł:");
    JTextField wpiszTytul = new JTextField(20);
    JSplitPane tytul = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaTytul,wpiszTytul);

    public void dodajDaneZBazy(Object[] obj) {

        model.addRow(obj);

    }

    public void czyscTabele() {
        model.setRowCount(0);
    }

    public void addActionListener(ActionListener listener) {
        wroc.addActionListener(listener);
        dodajGre.addActionListener(listener);
        zakup.addActionListener(listener);
        filtruj.addActionListener(listener);
        generujCene.addActionListener(listener);
    }

    public KupnoEgzemplarza(){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);
        dodajGre.setActionCommand("dodajGre");
        zakup.setActionCommand("zakup");
        wroc.setActionCommand("wroc");
        filtruj.setActionCommand("filtruj");
        generujCene.setActionCommand("generujCene");
        generowanaCena.setText("");

        uklad.gridx=0;
        uklad.gridy=0;
        add(tytul,uklad);

        uklad.gridx=1;
        uklad.gridy=0;
        add(filtruj,uklad);

        uklad.gridx=2;
        uklad.gridy=0;
        add(dodajGre,uklad);

        uklad.gridx=2;
        uklad.gridy=1;
        add(stan,uklad);

        uklad.gridx=2;
        uklad.gridy=2;
        add(cena,uklad);

        uklad.gridx=2;
        uklad.gridy=3;
        add(generujCene, uklad);

        uklad.gridx=2;
        uklad.gridy=4;
        add(zakup,uklad);

        uklad.gridx=2;
        uklad.gridy=5;
        add(wroc,uklad);

        uklad.gridwidth = 2;
        uklad.gridheight=5;
        uklad.gridx=0;
        uklad.gridy=1;
        add(gry,uklad);
    }

    public int getStan(){
        switch (comboStan.getSelectedItem().toString()) {
            case "fatalny":
                return 0;
            case "zły":
                return 1;

            case "używany":
                return 2;

            case "dobry":
                return 3;

            case "wzorowy":
                return 4;

        }
        return 0;
    }



    public void setCena(int cena){
        generowanaCena.setText(String.valueOf(cena));
    }
    public int getCena(){
        return Integer.parseInt(generowanaCena.getText());
    }
    public String getTytul(){
        return wpiszTytul.getText();
    }
    public int getID(){
        int index = gryLista.getSelectedRow();
        return (int) gryLista.getValueAt(index, 0);
    }
}
