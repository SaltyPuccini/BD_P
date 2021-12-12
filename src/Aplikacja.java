import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Aplikacja extends JFrame {

    Connection bazaDanych;

    final CardLayout layout = new CardLayout();


    EkranLogowania ekranLogowania = new EkranLogowania();
    ZmienPIN zmienPIN = new ZmienPIN();
    DodaniePlacowki dodaniePlacowki = new DodaniePlacowki();
    EkranSerwisanta ekranSerwisanta = new EkranSerwisanta();
    EkranRzeczoznawcy ekranRzeczoznawcy = new EkranRzeczoznawcy();


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

    private void polaczenie() {
        try {
            bazaDanych = DriverManager.getConnection("jdbc:mysql://@czaplinek.home.pl:3306", "00018732_kw", "Kajet@nW0j25");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void inicjaliacja() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(layout);
        add(ekranLogowania, "ekranLogowania");
        add(zmienPIN, "zmienPIN");
        add(dodaniePlacowki, "dodaniePlacowki");
        add(ekranSerwisanta, "ekranSerwisanta");
        add(ekranRzeczoznawcy, "ekranRzeczoznawcy");
    }

    public Aplikacja() {
        polaczenie();
        inicjaliacja();

        ekranLogowania.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                switch (akcja) {
                    case "zmienPIN":
                        layout.show(getContentPane(), "zmienPIN");
                        break;
                    case "wyjdz":
                        dispose();
                        break;
                    case "zaloguj":
                        Statement zapytanie = null;
                        ResultSet pracownik = null;
                        String stanowisko = "";
                        try {
                            zapytanie = bazaDanych.createStatement();
                            pracownik = zapytanie.executeQuery("SELECT * FROM `00018732_kw`.Pracownicy WHERE idPracownika=103" + ekranLogowania.getId());
                            stanowisko = pracownik.getString("stanowisko");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        switch (stanowisko){
                            case "Dyrektor":

                                break;
                            case "Rzeczoznawca":
                                layout.show(getContentPane(), "ekranRzeczoznawcy");
                                break;
                            case "Serwisant":
                                layout.show(getContentPane(), "ekranSerwisanta");
                                break;
                            case "Sprzedawca":

                                break;
                        }
                        break;
                }
            }
        });
        zmienPIN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                switch (akcja) {
                    case "zmianPIN":
                        break;
                    case "anuluj":
                        layout.show(getContentPane(), "ekranLogowania");
                        break;
                }
            }
        });
        dodaniePlacowki.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                switch (akcja) {
                    case "dodajPlacowke":
                        break;
                    case "wroc":
                        break;
                }
            }
        });
        ekranSerwisanta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                switch (akcja) {
                    case "dyskwalifikacja":
                        break;
                    case "wyloguj":
                        layout.show(getContentPane(), "ekranLogowania");
                        break;
                    case "serwis":
                        break;
                }
            }
        });
        ekranRzeczoznawcy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                switch (akcja) {
                    case "wyloguj":
                        layout.show(getContentPane(), "ekranLogowania");
                        break;
                    case "wycen":
                        break;
                }
            }
        });


        layout.show(getContentPane(), "ekranRzeczoznawcy");
        pack();
        setLocationRelativeTo(null);
    }

}
