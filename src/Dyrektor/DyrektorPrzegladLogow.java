package Dyrektor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DyrektorPrzegladLogow extends JPanel {

    private final JButton powrot = new JButton("Powrót");
    private final JButton filtruj = new JButton("Filtruj");

    JTable tabelaLogow;


    JLabel etykietaAkcja = new JLabel("Akcja:");
    JLabel etykietaData = new JLabel("Data:");
    JLabel etykietaPracownik = new JLabel("Pracownik:");

    String[] akcje = {"Zakup gry z rynku pierwotnego","Zakup gry z rynku wtórnego","Sprzedaż gry z placówki","Sprzedaż gry zamówionej","Zamówienie gry", "Odbiór zamówionej gry", "Dodanie pracownika", "Zwolnienie pracownika", "Dodanie placówki", "Zamknięcie placówki"};
    JComboBox wpiszAkcje = new JComboBox(akcje);

    JTextField wpiszData = new JTextField(10);
    JSplitPane data = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaData, wpiszData);

    JTextField wpiszPracownik = new JTextField(5);
    JSplitPane pracownik = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaPracownik, wpiszPracownik);

    DefaultTableModel model = new DefaultTableModel(new String[] { "ID", "Egzemplarz", "Pracownik", "Akcja", "Data"}, 0);


    public void addActionListener(ActionListener listener) {
        filtruj.addActionListener(listener);
        powrot.addActionListener(listener);
    }

    public void dodajDaneZBazy(Object[] obj){
        model.addRow(obj);
    }

    public void czyscTabele(){
        model.setRowCount(0);
    }

    public DyrektorPrzegladLogow() {
        tabelaLogow=new JTable(model);
        JScrollPane scrollPane_1 = new JScrollPane(tabelaLogow);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);

        filtruj.setActionCommand("filtruj");
        powrot.setActionCommand("powrot");

        uklad.gridx=1;//
        uklad.gridy=0;
        add(wpiszAkcje,uklad);

        uklad.gridx=1;//
        uklad.gridy=1;
        add(data,uklad);

        uklad.gridx=1;//
        uklad.gridy=2;
        add(pracownik,uklad);

        uklad.gridx=1;//
        uklad.gridy=3;
        add(filtruj,uklad);

        uklad.gridx=1;
        uklad.gridy=4;
        add(powrot,uklad);

        uklad.gridheight=5;
        uklad.gridx=0;//
        uklad.gridy=0;
        add(scrollPane_1,uklad);

    }
}
