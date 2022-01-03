import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class EkranSerwisanta extends JPanel {
    JButton wyloguj = new JButton("Wyloguj");
    JButton dyskwalifikacja = new JButton("Dyskwalifikacja");
    JButton serwis = new JButton("Dokonano serwis");

    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Tytuł"}, 0);
    JTable listaEgzemplarzy = new JTable(model);


    public void addActionListener(ActionListener listener) {
        wyloguj.addActionListener(listener);
        serwis.addActionListener(listener);
        dyskwalifikacja.addActionListener(listener);
    }

    public void dodajDaneZBazy(Object[] obj){
        model.addRow(obj);
    }

    public EkranSerwisanta() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);

        dyskwalifikacja.setEnabled(false);
        serwis.setEnabled(false);

        listaEgzemplarzy.getColumnModel().getColumn(0).setMaxWidth(50);
        JScrollPane tabelaEgzemplarzy = new JScrollPane(listaEgzemplarzy);
        tabelaEgzemplarzy.setPreferredSize(new Dimension(800,400));

        wyloguj.setActionCommand("wyloguj");
        serwis.setActionCommand("serwis");
        dyskwalifikacja.setActionCommand("dyskwalifikacja");

        listaEgzemplarzy.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                dyskwalifikacja.setEnabled(e.getFirstIndex()!=-1);
                serwis.setEnabled(e.getFirstIndex()!=-1);
            }
        });

        uklad.gridx = 3;
        uklad.gridy = 2;
        add(serwis, uklad);

        uklad.gridx = 3;
        uklad.gridy = 3;
        add(dyskwalifikacja, uklad);

        uklad.gridx = 3;
        uklad.gridy = 4;
        add(wyloguj, uklad);


        uklad.gridwidth =1;
        uklad.gridheight = 5;
        uklad.gridx = 0;
        uklad.gridy = 0;
        add(tabelaEgzemplarzy, uklad);


    }

    public void czyscTabele() {
        model.setRowCount(0);
    }

    public int getID() {
        int index = listaEgzemplarzy.getSelectedRow();
        return (int) listaEgzemplarzy.getValueAt(index, 0);
    }

    public void setWycen(boolean stan) {
        dyskwalifikacja.setEnabled(stan);
        serwis.setEnabled(stan);
    }
}
