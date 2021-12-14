import Dyrektor.*;
import Sprzedawca.*;

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
    DodanieGry dodanieGry = new DodanieGry();
    EkranKoszyka ekranKoszyka = new EkranKoszyka();
    KupnoEgzemplarza kupnoEgzemplarza = new KupnoEgzemplarza();
    EkranZamowien ekranZamowien = new EkranZamowien();
    EkranSprzedawcy ekranSprzedawcy= new EkranSprzedawcy();
    DodaniePracownika dodaniePracownika=new DodaniePracownika();
    InterfejsDyrektora interfejsDyrektora=new InterfejsDyrektora();
    DyrektorPrzegladEgzemplarzy dyrektorPrzegladEgzemplarzy = new DyrektorPrzegladEgzemplarzy();
    DyrektorPrzegladLogow dyrektorPrzegladLogow = new DyrektorPrzegladLogow();
    InterfejsZmianyDanych interfejsZmianyDanych = new InterfejsZmianyDanych();
    PrzegladPlacowek przegladPlacowek = new PrzegladPlacowek();

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
        add(dodanieGry, "dodanieGry");
        add(ekranKoszyka, "ekranKoszyka");
        add(kupnoEgzemplarza, "kupnoEgzemplarza");
        add(ekranZamowien,"ekranZamowien");
        add(ekranSprzedawcy,"ekranSprzedawcy");
        add(dodaniePracownika,"dodaniePracownika");
        add(interfejsDyrektora,"interfejsDyrektora");
        add(dyrektorPrzegladEgzemplarzy,"dyrektorPrzegladEgzemplarzy");
        add(dyrektorPrzegladLogow, "dyrektorPrzegladLogow");
        add(interfejsZmianyDanych, "interfejsZmianyDanych");
        add(przegladPlacowek, "przegladPlacowek");
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
                        logowanie();
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
                    case "zmienPIN":
                        break;
                    case "anuluj":
                        layout.show(getContentPane(), "ekranLogowania");
                        break;
                }
            }
        });
        sprzedawca();
        dyrektor();
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


        layout.show(getContentPane(), "ekranLogowania");
        pack();
        setLocationRelativeTo(null);
    }

    void logowanie() {
        Statement zapytanie = null;
        ResultSet pracownik = null;
        String stanowisko = "";
//        try {
//            zapytanie = bazaDanych.createStatement();
//            pracownik = zapytanie.executeQuery("SELECT * FROM `00018732_kw`.Pracownicy WHERE idPracownika=103" + ekranLogowania.getId());
//            stanowisko = pracownik.getString("stanowisko");
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
        stanowisko=ekranLogowania.getId();
        switch (stanowisko) {
            case "Dyrektor":
                layout.show(getContentPane(), "interfejsDyrektora");
                break;
            case "Rzeczoznawca":
                layout.show(getContentPane(), "ekranRzeczoznawcy");
                break;
            case "Serwisant":
                layout.show(getContentPane(), "ekranSerwisanta");
                break;
            case "Sprzedawca":
                layout.show(getContentPane(), "ekranSprzedawcy");
                break;
        }
    }

    void sprzedawca() {
        dodanieGry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                switch (akcja) {
                    case "dodajGre":

                        break;
                    case "wroc":
                        layout.show(getContentPane(), "kupnoEgzemplarza");
                        break;
                }
            }
        });
        ekranKoszyka.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                switch (akcja) {
                    case "usun":

                        break;
                    case "sprzedaj":

                        break;
                    case "wroc":
                        layout.show(getContentPane(), "ekranSprzedawcy");
                        break;
                }
            }
        });
        kupnoEgzemplarza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                switch (akcja) {
                    case "dodajGre":
                        layout.show(getContentPane(), "dodanieGry");
                        break;
                    case "zakup":

                        break;
                    case "filtruj":

                        break;
                    case "wroc":
                        layout.show(getContentPane(), "ekranSprzedawcy");
                        break;
                }
            }
        });
        ekranZamowien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                switch (akcja) {
                    case "odebrano":

                        break;
                    case "wyslano":

                        break;
                    case "wroc":
                        layout.show(getContentPane(), "ekranSprzedawcy");
                        break;
                }
            }
        });
        ekranSprzedawcy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                switch (akcja) {
                    case "dodaj":

                        break;
                    case "koszyk":
                        layout.show(getContentPane(), "ekranKoszyka");
                        break;
                    case "zamowienia":
                        layout.show(getContentPane(), "ekranZamowien");
                        break;
                    case "szukaj":

                        break;
                    case "kup":
                        layout.show(getContentPane(), "kupnoEgzemplarza");
                        break;
                    case "zamow":

                        break;
                    case "wyloguj":
                        layout.show(getContentPane(), "ekranLogowania");
                        break;
                }
            }
        });
    }

    void dyrektor() {
        interfejsDyrektora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                System.out.println(command);
                switch (command) {
                    case "dyrektorPracownicy":
                        layout.show(getContentPane(), "dodaniePracownika");
                        break;
                    case "dyrektorGry":
                        layout.show(getContentPane(), "dyrektorPrzegladEgzemplarzy");
                        break;
                    case "dyrektorPlacowki":
                        layout.show(getContentPane(), "dodaniePlacowki");
                        break;
                    case "dyrektorLogiSprzedazy":
                        layout.show(getContentPane(), "dyrektorPrzegladLogow");
                        break;
                    case "dyrektorWyloguj":
                        layout.show(getContentPane(), "ekranLogowania");
                        break;
                }
            }
        });
        dyrektorPrzegladEgzemplarzy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                System.out.println(command);
                switch (command) {
                    case "filtruj":

                        break;
                    case "wroc":
                        layout.show(getContentPane(), "interfejsDyrektora");
                        break;
                    case "dodajGre":

                        break;
                    case "dodajEgzemplarze":

                        break;
                }
            }
        });
        dyrektorPrzegladLogow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                System.out.println(command);
                switch (command) {
                    case "powrot":
                        layout.show(getContentPane(), "interfejsDyrektora");
                        break;
                }
            }
        });
        dodaniePlacowki.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                System.out.println(command);
                switch (command) {
                    case "wroc":
                        layout.show(getContentPane(), "interfejsDyrektora");
                        break;
                }
            }
        });
        dodaniePracownika.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                System.out.println(command);
                switch (command) {
                    case "wroc":
                        layout.show(getContentPane(), "interfejsDyrektora");
                        break;
                }
            }
        });
    }

}
