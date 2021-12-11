import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class EkranLogowania extends JPanel {

    private final JTextField id;
    private final JPasswordField PIN;
    private final JButton zaloguj;
    private final JButton wyjdz;
    private final JButton zmienPIN;
    private final JLabel etykietaID;
    private final JLabel etykietaPIN;

    public void addActionListener(ActionListener listener) {
        zaloguj.addActionListener(listener);
        wyjdz.addActionListener(listener);
        zmienPIN.addActionListener(listener);
    }

    public void removeActionListener(ActionListener listener) {
        zaloguj.addActionListener(listener);
        wyjdz.addActionListener(listener);
        zmienPIN.addActionListener(listener);
    }

    public EkranLogowania() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200,700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);

        uklad.gridx = 0;
        uklad.gridy = 0;
        etykietaID=new JLabel("ID:");
        add(etykietaID, uklad);

        uklad.gridx = 1;
        uklad.gridy = 0;
        id=new JTextField(30);
        add(id, uklad);

        uklad.gridx = 0;
        uklad.gridy = 1;
        etykietaPIN=new JLabel("PIN:");
        add(etykietaPIN, uklad);

        uklad.gridx = 1;
        uklad.gridy = 1;
        PIN=new JPasswordField(30);
        add(PIN,uklad);

        uklad.gridx = 1;
        uklad.gridy = 2;
        zaloguj=new JButton("Zaloguj");
        zaloguj.setActionCommand("zaloguj");
        add(zaloguj,uklad);

        uklad.gridx = 0;
        uklad.gridy = 3;
        zmienPIN=new JButton("Zmień PIN");
        zmienPIN.setActionCommand("zmienPIN");
        add(zmienPIN,uklad);

        uklad.gridx = 2;
        uklad.gridy = 3;
        wyjdz=new JButton("Wyjdź");
        wyjdz.setActionCommand("wyjdz");
        add(wyjdz,uklad);
    }

    public char[] getPIN() {
        return PIN.getPassword();
    }

    public String getId() {
        return id.getText();
    }
}
