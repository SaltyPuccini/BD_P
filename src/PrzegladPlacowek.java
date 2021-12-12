import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PrzegladPlacowek extends JPanel {

    private final JButton dodajPlacowke = new JButton("Dodaj placówkę");
    private final JButton zamknijPlacowke = new JButton("Zamknij placówkę");
    private final JButton wroc = new JButton("Wróć");
    private List<Placowka> listaPlacowek = new ArrayList<>();

    JTable tabelaPlacowek;
    JScrollPane scrollPane_1;

    public void addActionListener(ActionListener listener) {
        wroc.addActionListener(listener);
        dodajPlacowke.addActionListener(listener);
        zamknijPlacowke.addActionListener(listener);
    }

    public PrzegladPlacowek(){
        scrollPane_1 = new JScrollPane();
        tabelaPlacowek=new JTable();
        Placowka placowka = new Placowka(1, "xD", 2, "xDDD",3,true);
        listaPlacowek.add(placowka);
        tabelaPlacowek.setModel(new CustomTableModelPlacowki(listaPlacowek));
        scrollPane_1.setViewportView(tabelaPlacowek);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);

        dodajPlacowke.setActionCommand("dodajPlacowke");
        wroc.setActionCommand("wroc");
        zamknijPlacowke.setActionCommand("zamknijPlacowke");

        uklad.gridx=0;//
        uklad.gridy=2;
        add(scrollPane_1,uklad);

        uklad.gridx=1;
        uklad.gridy=0;
        add(dodajPlacowke,uklad);

        uklad.gridx=1;
        uklad.gridy=1;
        add(zamknijPlacowke,uklad);

        uklad.gridx=1;
        uklad.gridy=3;
        add(wroc,uklad);

    }



}
