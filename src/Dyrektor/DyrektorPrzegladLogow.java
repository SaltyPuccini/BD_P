package Dyrektor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DyrektorPrzegladLogow extends JPanel {

    private final JButton powrot = new JButton("Powrót");
    private final JButton filtruj = new JButton("Filtruj");

    JTable tabelaLogow;


    JLabel etykietaAkcja = new JLabel("Akcja:");
    JLabel etykietaData = new JLabel("Data(rok-miesiąc-dzień):");
    JLabel etykietaPracownik = new JLabel("Pracownik:");

    String[] akcje = {"*", "dyskwalifikacja", "dodano egzemplarz", "zamowiono", "zmiana ceny", "zmiana stanu", "zmiana statusu", "serwis"};
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
        scrollPane_1.setPreferredSize(new Dimension(600,400));
        add(scrollPane_1,uklad);
    }

    public void czysc(){
        wpiszData.setText("");
        wpiszPracownik.setText("");
        wpiszAkcje.setSelectedIndex(0);
    }

    public String getAkcja(){
        String akcja=wpiszAkcje.getSelectedItem().toString();
        if(Objects.equals(akcja, "*")){
            return null;
        }else{
            return akcja;
        }
    }

    public String getID(){
        String id=wpiszPracownik.getText().toString();
        if(id.equals("")){
            return null;
        }else{
            return id;
        }
    }

    public String getData(){
        String data = wpiszData.getText().toString();
        if(data.equals("")){
            return null;
        }else{
            return data;
        }
    }

}
