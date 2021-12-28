import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class EkranLogowania extends JPanel {

    private final JTextField id=new JTextField(30);
    private final JPasswordField PIN=new JPasswordField(30);
    private final JButton zaloguj=new JButton("Zaloguj");
    private final JButton wyjdz=new JButton("Wyjdź");
    private final JButton zmienPIN=new JButton("Zmień PIN");
    private final JLabel etykietaID=new JLabel("ID:");
    private final JLabel etykietaPIN=new JLabel("PIN:");

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
        wyjdz.setActionCommand("wyjdz");
        zaloguj.setActionCommand("zaloguj");
        zmienPIN.setActionCommand("zmienPIN");

        uklad.gridx = 0;
        uklad.gridy = 0;
        add(etykietaID, uklad);

        uklad.gridx = 1;
        uklad.gridy = 0;
        add(id, uklad);

        uklad.gridx = 0;
        uklad.gridy = 1;
        add(etykietaPIN, uklad);

        uklad.gridx = 1;
        uklad.gridy = 1;
        add(PIN,uklad);

        uklad.gridx = 1;
        uklad.gridy = 2;
        add(zaloguj,uklad);

        uklad.gridx = 0;
        uklad.gridy = 3;
        add(zmienPIN,uklad);

        uklad.gridx = 2;
        uklad.gridy = 3;
        add(wyjdz,uklad);
    }

    public char[] getPIN() {
        return PIN.getPassword();
    }

    public String getId() {
        return id.getText();
    }

    public void resetTextFields(){
        id.setText("");
        PIN.setText("");
    }
}
