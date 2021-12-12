import javax.print.attribute.standard.DateTimeAtProcessing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DyrektorPrzegladLogow extends JPanel {

    private final JButton powrot = new JButton("Powrót");
    JTable tabelaLogow;
    JScrollPane scrollPane_1;
    private List<Log> listaLogow = new ArrayList<>();

    public void addActionListener(ActionListener listener) {
        powrot.addActionListener(listener);
    }


    public DyrektorPrzegladLogow() {

        scrollPane_1 = new JScrollPane();
        tabelaLogow=new JTable();
        Date today = new Date();
        today.setDate(20);
        Log log = new Log(1, 2, 3,"sprzedaż",today);
        listaLogow.add(log);
        tabelaLogow.setModel(new CustomTableModelLogi(listaLogow));
        scrollPane_1.setViewportView(tabelaLogow);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200, 700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);

        powrot.setActionCommand("powrot");

        uklad.gridx=1;//
        uklad.gridy=0;
        add(scrollPane_1,uklad);

        uklad.gridx=1;
        uklad.gridy=1;
        add(powrot,uklad);

    }
}
