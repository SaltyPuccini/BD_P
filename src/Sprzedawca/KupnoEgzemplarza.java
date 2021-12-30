package Sprzedawca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class KupnoEgzemplarza extends JPanel {
    JButton dodajGre=new JButton("Dodaj grę");
    JButton zakup = new JButton("Zakup");
    JButton filtruj = new JButton("Filtruj");
    JButton wroc = new JButton("Wróć");

    JTable gryLista = new JTable(new CustomTableModelKZ());//id gry, tytuł, rok wydawca
    JScrollPane gry = new JScrollPane(gryLista);

    String[] stany={"dobry"};
    JLabel etykietaStan= new JLabel("Stan");
    JComboBox comboStan = new JComboBox(stany);
    JSplitPane stan= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaStan,comboStan);

    JLabel etykietaCena = new JLabel("Cena:");
    JTextArea generowanaCena = new JTextArea(1,3);
    JSplitPane cena = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaCena,generowanaCena);

    JLabel etykietaTytul = new JLabel("tytuł:");
    JTextField wpiszTytul = new JTextField(20);
    JSplitPane tytul = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaTytul,wpiszTytul);

    public void addActionListener(ActionListener listener) {
        wroc.addActionListener(listener);
        dodajGre.addActionListener(listener);
        zakup.addActionListener(listener);
        filtruj.addActionListener(listener);
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
        generowanaCena.setText("");

        gryLista.getColumnModel().getColumn(0).setHeaderValue("Tytuł");
        gryLista.getColumnModel().getColumn(1).setHeaderValue("Rok");
        gryLista.getColumnModel().getColumn(2).setHeaderValue("Wydawca");
        gryLista.removeColumn(gryLista.getColumnModel().getColumn(4));
        gryLista.removeColumn(gryLista.getColumnModel().getColumn(3));

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
        add(zakup,uklad);

        uklad.gridx=2;
        uklad.gridy=4;
        add(wroc,uklad);

        uklad.gridwidth = 2;
        uklad.gridheight=4;
        uklad.gridx=0;
        uklad.gridy=1;
        add(gry,uklad);
    }

    public int getStan(){
        return (int) comboStan.getSelectedItem();
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
