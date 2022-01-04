import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class EkranRzeczoznawcy extends JPanel {
    JButton wyloguj = new JButton("Wyloguj");
    JButton wycen = new JButton("Dokonano wyceny");
    JButton zamowienia = new JButton("Zamówienia");

    JTable listaEgzemplarzy;

    JTextField wpiszCena = new JTextField(3);
    JLabel etykietaCena = new JLabel("Cena:");
    JSplitPane cena = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, etykietaCena, wpiszCena);
    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Tytuł", "Gatunek"}, 0);
    private String[] gatunki = {"*", "Strzelanka", "Strategiczne", "Akcji", "Bijatyki", "Logiczne", "Platformowki", "Przygodowe", "RPG", "Sportowa", "Symulacje", "Wyscigowe"};
    JComboBox gatunek = new JComboBox(gatunki);

    String gatunekString=null;

    public EkranRzeczoznawcy() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);

        listaEgzemplarzy = new JTable(model);
        listaEgzemplarzy.getColumnModel().getColumn(0).setMaxWidth(50);
        JScrollPane tabelaEgzemplarzy = new JScrollPane(listaEgzemplarzy);
        tabelaEgzemplarzy.setPreferredSize(new Dimension(800, 400));

        wycen.setEnabled(false);
        listaEgzemplarzy.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                wycen.setEnabled(e.getFirstIndex()!=-1);
            }
        });

        wyloguj.setActionCommand("wyloguj");
        wycen.setActionCommand("wycen");
        zamowienia.setActionCommand("zamowienia");

        gatunek.setEnabled(false);
        listaEgzemplarzy.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                gatunek.setSelectedIndex(0);
                gatunek.setEnabled(false);
                if (listaEgzemplarzy.getSelectedRow() != -1) {
                    if(Objects.equals(model.getValueAt(listaEgzemplarzy.getSelectedRow(),2).toString(), "")){
                        System.out.println("mozna zmieniac, bo jest \"\"");
                        gatunek.setEnabled(true);
                    }
                } else {
                    gatunek.setEnabled(false);
                }
            }
        });


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
        add(zamowienia, uklad);

        uklad.gridx = 3;
        uklad.gridy = 5;
        add(wyloguj, uklad);


        uklad.gridheight = 5;
        uklad.gridx = 0;
        uklad.gridy = 0;
        add(tabelaEgzemplarzy, uklad);
    }

    public void addActionListener(ActionListener listener) {
        wyloguj.addActionListener(listener);
        wycen.addActionListener(listener);
        zamowienia.addActionListener(listener);
    }

    public void dodajDaneZBazy(Object[] obj) {
        model.addRow(obj);
    }

    public void czyscTabele() {
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        listaEgzemplarzy.setModel(model);
    }


    public Integer getCena() {
        return Integer.valueOf(wpiszCena.getText());
    }

    public String getGatunek() {
            return (String) gatunek.getSelectedItem();

    }

    public int getID() {
        int index = listaEgzemplarzy.getSelectedRow();
        return (int) listaEgzemplarzy.getValueAt(index, 0);
    }

    public void setWycen(boolean stan){
        wycen.setEnabled(stan);
    }

    public String getAktualnyGatunek(){
        return model.getValueAt(listaEgzemplarzy.getSelectedRow(),2).toString();
    }

}
