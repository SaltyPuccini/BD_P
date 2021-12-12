import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class EkranSerwisanta extends JPanel {
    JButton wyloguj = new JButton("Wyloguj");
    JButton dyskwalifikacja = new JButton("Dyskwalifikacja");
    JButton serwis = new JButton("Dokonano serwis");

    Object[][] zawartosc = new Object[][]{{1, "Stellaris"}};
    String[] kolumny = {"ID", "Tytuł"};
    JTable listaEgzemplarzy = new JTable(zawartosc, kolumny);

    public void addActionListener(ActionListener listener) {
        wyloguj.addActionListener(listener);
        serwis.addActionListener(listener);
        dyskwalifikacja.addActionListener(listener);
    }

    public EkranSerwisanta() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);
        wyloguj.setActionCommand("wyloguj");
        serwis.setActionCommand("serwis");
        dyskwalifikacja.setActionCommand("dyskwalifikacja");

        uklad.gridx = 3;
        uklad.gridy = 2;
        add(serwis, uklad);

        uklad.gridx = 3;
        uklad.gridy = 3;
        add(dyskwalifikacja, uklad);

        uklad.gridx = 3;
        uklad.gridy = 4;
        add(wyloguj, uklad);


        uklad.gridwidth = 3;
        uklad.weightx = 5;
        uklad.gridx = 0;
        uklad.gridy = 0;
        add(listaEgzemplarzy, uklad);


    }
}
