import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class EkranRzeczoznawcy extends JPanel {
    JButton wyloguj = new JButton("Wyloguj");
    JButton wycen = new JButton("Dokonano wyceny");

    JTable listaEgzemplarzy;

    JTextField wpiszCena = new JTextField(3);
    JLabel etykietaCena = new JLabel("Cena:");
    JSplitPane cena = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaCena, wpiszCena);

    private String[] gatunki = {"strzelanka", "strategiczne","akcji","bijatyki","logiczne","platformówki","przygodowe","RPG","sportowa","symulacje","wyścigowe"};
    JComboBox gatunek = new JComboBox(gatunki);


    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Tytuł"}, 0);


    public void addActionListener(ActionListener listener) {
        wyloguj.addActionListener(listener);
        wycen.addActionListener(listener);
    }

    public void dodajDaneZBazy(Object[] obj){
        model.addRow(obj);
    }

    public void czyscTabele(){
        model.setRowCount(0);
    }

    public EkranRzeczoznawcy() {
        listaEgzemplarzy = new JTable(model);
        JScrollPane tabelaEgzemplarzy = new JScrollPane(listaEgzemplarzy);
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

    public int getID(){
        int index = listaEgzemplarzy.getSelectedRow();
        return (int) listaEgzemplarzy.getValueAt(index,0);
    }

}
