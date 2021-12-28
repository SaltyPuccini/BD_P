import Dyrektor.*;
import Sprzedawca.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class Aplikacja extends JFrame {
    public Connection bazaDanych;
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
    EkranSprzedawcy ekranSprzedawcy = new EkranSprzedawcy();
    DodaniePracownika dodaniePracownika = new DodaniePracownika();
    InterfejsDyrektora interfejsDyrektora = new InterfejsDyrektora();
    DyrektorPrzegladEgzemplarzy dyrektorPrzegladEgzemplarzy = new DyrektorPrzegladEgzemplarzy();
    DyrektorPrzegladLogow dyrektorPrzegladLogow = new DyrektorPrzegladLogow();
    InterfejsZmianyDanych interfejsZmianyDanych = new InterfejsZmianyDanych();
    PrzegladPlacowek przegladPlacowek = new PrzegladPlacowek();
    PrzegladPracownikow przegladPracownikow = new PrzegladPracownikow();
    DyrektorDodanieGry dyrektorDodanieGry = new DyrektorDodanieGry();

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
        add(ekranZamowien, "ekranZamowien");
        add(ekranSprzedawcy, "ekranSprzedawcy");
        add(dodaniePracownika, "dodaniePracownika");
        add(interfejsDyrektora, "interfejsDyrektora");
        add(dyrektorPrzegladEgzemplarzy, "dyrektorPrzegladEgzemplarzy");
        add(dyrektorPrzegladLogow, "dyrektorPrzegladLogow");
        add(interfejsZmianyDanych, "interfejsZmianyDanych");
        add(przegladPlacowek, "przegladPlacowek");
        add(przegladPracownikow, "przegladPracownikow");
        add(dyrektorDodanieGry, "dyrektorDodanieGry");
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
                        ekranLogowania.resetTextFields();
                        break;
                    case "wyjdz":
                        dispose();
                        break;
                    case "zaloguj":
                        logowanie();
                        ekranLogowania.resetTextFields();
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
                    case "zmienPin":
                        zmienianiePINU();
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

                        ekranSerwisanta.czyscTabele();
                        layout.show(getContentPane(), "ekranLogowania");

                        break;
                    case "serwis":

                        ekranSerwisanta.czyscTabele();
                        //serwisantZaladujTabele();

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

                        ekranRzeczoznawcy.czyscTabele();
                        layout.show(getContentPane(), "ekranLogowania");

                        break;
                    case "wycen":

                        ekranRzeczoznawcy.czyscTabele();
                        rzeczoznawcaZaladujTabele();

                        break;
                }
            }
        });


        layout.show(getContentPane(), "ekranLogowania");
        pack();
        setLocationRelativeTo(null);

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
                        layout.show(getContentPane(), "przegladPracownikow");
                        break;
                    case "dyrektorGry":
                        layout.show(getContentPane(), "dyrektorPrzegladEgzemplarzy");
                        break;
                    case "dyrektorPlacowki":
                        layout.show(getContentPane(), "przegladPlacowek");
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
                        layout.show(getContentPane(), "dyrektorDodanieGry");
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
                if ("powrot".equals(command)) {
                    layout.show(getContentPane(), "interfejsDyrektora");
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
                        layout.show(getContentPane(), "przegladPlacowek");
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
                        layout.show(getContentPane(), "przegladPracownikow");
                        break;
                }
            }
        });
        przegladPracownikow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                System.out.println(command);
                switch (command) {
                    case "wroc":
                        layout.show(getContentPane(), "interfejsDyrektora");
                        break;
                    case "dodajPracownika":
                        layout.show(getContentPane(), "dodaniePracownika");
                        break;
                }
            }
        });

        przegladPlacowek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                System.out.println(command);
                switch (command) {
                    case "wroc":
                        layout.show(getContentPane(), "interfejsDyrektora");
                        break;
                    case "dodajPlacowke":
                        layout.show(getContentPane(), "dodaniePlacowki");
                        break;
                }
            }
        });

        dyrektorDodanieGry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                System.out.println(command);
                switch (command) {
                    case "wroc":
                        layout.show(getContentPane(), "dyrektorPrzegladEgzemplarzy");
                        break;
                }
            }
        });

    }


    void rzeczoznawcaZaladujTabele(){

        try (
                Statement zapytanie = bazaDanych.createStatement();
                ResultSet resultSet = zapytanie.executeQuery("SELECT e.idEgzemplarza, g.nazwa FROM 00018732_kw.Gry g JOIN 00018732_kw.Egzemplarze e ON e.idGry=g.idGry;");
        ) {
            System.out.println("JD");
            while (resultSet.next()) {
                int idEgzemplarza = resultSet.getInt("idEgzemplarza");
                String nazwa = resultSet.getString("nazwa");
                ekranRzeczoznawcy.dodajDaneZBazy(new Object[]{idEgzemplarza, nazwa});
                System.out.println(idEgzemplarza);
                System.out.println(nazwa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    void serwisantZaladujTabele(){

        try (
                Statement zapytanie = bazaDanych.createStatement();
                ResultSet resultSet = zapytanie.executeQuery("SELECT e.idEgzemplarza, g.nazwa FROM 00018732_kw.Gry g JOIN 00018732_kw.Egzemplarze e ON e.idGry=g.idGry;");
        ) {
            System.out.println("JD");
            while (resultSet.next()) {
                int idEgzemplarza = resultSet.getInt("idEgzemplarza");
                String nazwa = resultSet.getString("nazwa");
                ekranSerwisanta.dodajDaneZBazy(new Object[]{idEgzemplarza, nazwa});
                System.out.println(idEgzemplarza);
                System.out.println(nazwa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    void logowanie() {
        String stanowisko = "";
        int haslo = 0;
        String id = ekranLogowania.getId();

        try (
                Statement zapytanie = bazaDanych.createStatement();
                ResultSet pracownik = zapytanie.executeQuery("SELECT stanowisko FROM 00018732_kw.Pracownicy WHERE idPracownika=" + id);
        ) {
            if (pracownik.next())
                stanowisko = pracownik.getString("stanowisko");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try (
                Statement zapytanie = bazaDanych.createStatement();
                ResultSet rshaslo = zapytanie.executeQuery("SELECT PIN FROM 00018732_kw.KodyDostępu WHERE idPracownika=" + id);
        ) {
            if (rshaslo.next())
                haslo = rshaslo.getInt("PIN");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (haslo == Integer.parseInt(String.valueOf(ekranLogowania.getPIN()))) {
            switch (stanowisko) {
                case "Dyrektor":
                    layout.show(getContentPane(), "interfejsDyrektora");
                    break;
                case "Rzeczoznawca":
                    rzeczoznawcaZaladujTabele();
                    layout.show(getContentPane(), "ekranRzeczoznawcy");
                    break;
                case "Serwisant":
                    serwisantZaladujTabele();
                    layout.show(getContentPane(), "ekranSerwisanta");
                    break;
                case "Sprzedawca":
                    layout.show(getContentPane(), "ekranSprzedawcy");
                    break;
            }
        }
    }

    void zmienianiePINU(){
        int TOKEN = 0;
        String actualID = zmienPIN.getID();
        try (
                Statement zapytanie = bazaDanych.createStatement();
                ResultSet resultSet = zapytanie.executeQuery("SELECT PIN FROM 00018732_kw.KodyDostępu WHERE idPracownika=" + zmienPIN.getID());
        ) {
            if (resultSet.next()) {
                TOKEN = resultSet.getInt("PIN");
            }
            if (String.valueOf(zmienPIN.getNowyPIN()).equals(String.valueOf(zmienPIN.getPowtorzPIN()))) {
                if (TOKEN == Integer.parseInt(String.valueOf(zmienPIN.getTOKEN()))) {
                    zapytanie.executeUpdate("UPDATE 00018732_kw.KodyDostępu SET PIN=" + Integer.parseInt(String.valueOf(zmienPIN.getNowyPIN())) + " WHERE idPracownika=" + actualID + ";");
                    JOptionPane.showMessageDialog(null, "Poprawnie zmieniono PIN!");
                    zmienPIN.resetTextFields();
                    layout.show(getContentPane(), "ekranLogowania");
                } else {
                    JOptionPane.showMessageDialog(null, "Nieprawidłowy TOKEN!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nowy PIN musi być zgodny z powtórzonym nowym PINEM!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
