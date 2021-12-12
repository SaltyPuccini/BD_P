import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Aplikacja extends JFrame {

    Connection bazaDanych;

    final CardLayout layout = new CardLayout();


    EkranLogowania ekranLogowania = new EkranLogowania();
    ZmienPIN zmienPIN = new ZmienPIN();
    DodaniePlacowki dodaniePlacowki=new DodaniePlacowki();
    InterfejsDyrektora interfejsDyrektora=new InterfejsDyrektora();


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Aplikacja frame = new Aplikacja();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void polaczenie(){
        try{
            bazaDanych=DriverManager.getConnection("jdbc:mysql://@czaplinek.home.pl:3306", "00018732_kw", "Kajet@nW0j25");
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                if(bazaDanych!=null){
                    bazaDanych.close();
                }
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    private void inicjaliacja(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(layout);
        add(ekranLogowania, "ekranLogowania");
        add(zmienPIN, "zmienPIN");
        add(dodaniePlacowki,"dodaniePlacowki");
        add(interfejsDyrektora,"interfejsDyrektora");
    }

    public Aplikacja() {
        polaczenie();
        inicjaliacja();

        ekranLogowania.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                System.out.println(command);
                switch (command) {
                    case "zmienPIN":
                        layout.show(getContentPane(), "zmienPIN");
                        break;
                    case "wyjdz":
                        dispose();
                        break;
                    case "zaloguj":

                        layout.show(getContentPane(), "managerLogin");
                        break;
                }
            }
        });

        layout.show(getContentPane(), "interfejsDyrektora");

        pack();
        setLocationRelativeTo(null);
    }

}
