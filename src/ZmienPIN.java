import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Objects;

public class ZmienPIN extends JPanel {
    private boolean[] flaga = {false,false,false,false};

    private final JLabel ID=new JLabel("ID:");
    private final JLabel PIN=new JLabel("Nowy PIN:");
    private final JLabel TOKEN=new JLabel("TOKEN:");

    private final JTextField idPracownika=new JTextField(20);
    private final JPasswordField nowyPIN=new JPasswordField(20);
    private final JPasswordField powtorzPIN=new JPasswordField(20);
    private final JPasswordField token=new JPasswordField(20);

    private final JButton zmienPIN=new JButton("Zmień PIN");
    private final JButton anuluj=new JButton("Anuluj");

    public void addActionListener(ActionListener listener) {
        anuluj.addActionListener(listener);
        zmienPIN.addActionListener(listener);
    }

    public ZmienPIN(){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1200,700));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);
        zmienPIN.setActionCommand("zmienPin");
        anuluj.setActionCommand("anuluj");

        setGuzik(false);
        idPracownika.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (idPracownika.getText().length()>0) {
                    flaga[0] = true;
                    zmienPIN.setEnabled(flaga[1] && flaga[2]&&flaga[3]);
                } else {
                    flaga[0] = false;
                    zmienPIN.setEnabled(false);
                }
            }
        });
        nowyPIN.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (nowyPIN.getPassword().length>0) {
                    flaga[1] = true;
                    zmienPIN.setEnabled(flaga[0] && flaga[2]&&flaga[3]);
                } else {
                    flaga[1] = false;
                    zmienPIN.setEnabled(false);
                }
            }
        });
        powtorzPIN.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (powtorzPIN.getPassword().length>0) {
                    flaga[2] = true;
                    zmienPIN.setEnabled(flaga[0] && flaga[1]&&flaga[3]);
                } else {
                    flaga[2] = false;
                    zmienPIN.setEnabled(false);
                }
            }
        });
        token.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (token.getPassword().length>0) {
                    flaga[3] = true;
                    zmienPIN.setEnabled(flaga[0] && flaga[1]&&flaga[2]);
                } else {
                    flaga[3] = false;
                    zmienPIN.setEnabled(false);
                }
            }
        });

        uklad.gridx=0;
        uklad.gridy=0;
        add(ID,uklad);

        uklad.gridx=1;
        uklad.gridy=0;
        add(PIN,uklad);

        uklad.gridx=2;
        uklad.gridy=0;
        add(TOKEN,uklad);

        uklad.gridx=0;
        uklad.gridy=1;
        add(idPracownika,uklad);

        uklad.gridx=1;
        uklad.gridy=1;
        add(nowyPIN,uklad);

        uklad.gridx=2;
        uklad.gridy=1;
        add(token,uklad);

        uklad.gridx=1;
        uklad.gridy=2;
        add(powtorzPIN,uklad);

        uklad.gridx=1;
        uklad.gridy=3;
        add(zmienPIN,uklad);

        uklad.gridx=1;
        uklad.gridy=4;
        add(anuluj,uklad);

    }

    public String getNowyPIN(){
        return String.valueOf(nowyPIN.getPassword());
    }
    public String getPowtorzPIN(){
        return String.valueOf(powtorzPIN.getPassword());
    }
    public String getTOKEN(){
        return String.valueOf(token.getPassword());
    }
    public String getID(){
        return idPracownika.getText();
    }
    public void resetTextFields(){
        idPracownika.setText("");
        nowyPIN.setText("");
        powtorzPIN.setText("");
        token.setText("");
        setGuzik(false);
        flaga[0]=false;
        flaga[1]=false;
        flaga[2]=false;
        flaga[3]=false;
    }

    public void setGuzik(boolean stan){
        zmienPIN.setEnabled(stan);
    }

}
