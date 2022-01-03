import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EkranLogowania extends JPanel {
    private final boolean[] flaga ={false,false};

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

        setGuzik(false);
        id.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (id.getText().length()>0) {
                    flaga[0] = true;
                    zaloguj.setEnabled(flaga[1]);
                } else {
                    flaga[0] = false;
                    zaloguj.setEnabled(false);
                }
            }
        });
        PIN.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (PIN.getPassword().length > 0) {
                    flaga[1] = true;
                    zaloguj.setEnabled(flaga[0]);
                } else {
                    flaga[1] = false;
                    zaloguj.setEnabled(false);
                }
            }
        });


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

    public String getPIN() {
        return String.valueOf(PIN.getPassword());
    }

    public int getId() {
        return Integer.parseInt(id.getText());
    }

    public void resetTextFields(){
        id.setText("");
        PIN.setText("");
        flaga[0]=false;
        flaga[1]=false;
    }

    public void setGuzik(boolean stan){
        zaloguj.setEnabled(stan);
    }
}