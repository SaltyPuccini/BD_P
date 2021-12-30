import Dyrektor.*;
import Sprzedawca.*;
import Szyfrowanie.Deszyfrator;
import Szyfrowanie.Szyfrator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;

public class Aplikacja extends JFrame {
    final CardLayout layout = new CardLayout();
    public Connection bazaDanych;
    int zalogowanyPracownik;
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
                        try {
                            bazaDanych.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        dispose();
                        break;
                    case "zaloguj":
                        try {
                            logowanie();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        break;
                }
                ekranLogowania.resetTextFields();
            }
        });
        zmienPIN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                switch (akcja) {
                    case "zmienPin":
                        try {
                            zmienianiePINU();
                            layout.show(getContentPane(), "ekranLogowania");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "anuluj":
                        layout.show(getContentPane(), "ekranLogowania");
                        break;
                }
                zmienPIN.resetTextFields();
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
                        zmienStatus("zdyskwalifikowana", ekranSerwisanta.getID());
                        dodajLog(ekranSerwisanta.getID(),zalogowanyPracownik,"dyskwalifikacja");
                        break;
                    case "wyloguj":
                        zalogowanyPracownik = -1;
                        layout.show(getContentPane(), "ekranLogowania");
                        break;
                    case "serwis":
                        try {
                            serwis(ekranSerwisanta.getID());
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
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
                        zalogowanyPracownik = -1;
                        layout.show(getContentPane(), "ekranLogowania");
                        break;
                    case "wycen":
                        int cena = ekranRzeczoznawcy.getCena();
                        String gatunek = ekranRzeczoznawcy.getGatunek();
                        int idEgzemplarza = ekranRzeczoznawcy.getID();

                        zmienCene(cena, idEgzemplarza);
                        try {
                            zmienGatunek(gatunek, idEgzemplarza);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        zmienStatus("gotowa do sprzedaży", idEgzemplarza);
                        break;
                }
                ekranRzeczoznawcy.czyscTabele();
                rzeczoznawcaZaladujTabele();
            }
        });

        layout.show(getContentPane(), "ekranLogowania");
        pack();
        setLocationRelativeTo(null);
    }

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
                        ladujPracownikow();
                        layout.show(getContentPane(), "przegladPracownikow");
                        break;
                    case "dyrektorGry":
                        layout.show(getContentPane(), "dyrektorPrzegladEgzemplarzy");
                        break;
                    case "dyrektorPlacowki":
                        ladujPlacowki();
                        layout.show(getContentPane(), "przegladPlacowek");
                        break;
                    case "dyrektorLogiSprzedazy":
                        layout.show(getContentPane(), "dyrektorPrzegladLogow");
                        break;
                    case "dyrektorWyloguj":
                        zalogowanyPracownik =-1;
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
                        int ilosc = dyrektorPrzegladEgzemplarzy.liczba();

                        int idGry = dyrektorPrzegladEgzemplarzy.gra();
                        int idPlacowki = 1;
                        int stan = 4;
                        int cena = dyrektorPrzegladEgzemplarzy.cena();
                        String status = "gotowa do sprzedaży";

                        for (int i = 0; i < ilosc; i++) {
                            dodajEgzemplarz(idGry, idPlacowki, stan, cena, status);
                        }
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
                    case "generujID":
                        String komenda = "SELECT COUNT(*) FROM 00018732_kw.Placówki;";
                        int index = 0;
                        try {
                            Statement zapytanie = bazaDanych.createStatement();
                            ResultSet wynik = zapytanie.executeQuery(komenda.toString());
                            wynik.next();
                            index = wynik.getInt(0);
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                        }
                        index++;
                        dodaniePlacowki.setID(index);
                        break;
                    case "dodajPlacowke":
                        String ulica = dodaniePlacowki.getUlica();
                        int numer = dodaniePlacowki.getNumer();
                        String miasto = dodaniePlacowki.getMiasto();
                        int numerLokalu = dodaniePlacowki.getLokal();
                        dodajPlacowke(ulica, numer, miasto, numerLokalu, true);

                        layout.show(getContentPane(), "przegladPlacowek");
                        break;
                    case "wroc":
                        przegladPlacowek.czyscTabele();
                        ladujPlacowki();
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
                int PIN;
                switch (command) {
                    case "generujPIN":
                        PIN = (int) (Math.random() * 1000000);
                        dodaniePracownika.setPIN(PIN);
                        break;
                    case "dodajPracownika":
                        int idPracownika = dodaniePracownika.getID();
                        int idPlacowki = dodaniePracownika.getPlacowka();
                        String imie = dodaniePracownika.getImie();
                        String nazwisko = dodaniePracownika.getNazwisko();
                        String stanowisko = dodaniePracownika.getStanowisko();
                        dodajPracownika(idPracownika, idPlacowki, imie, nazwisko, stanowisko, true);

                        PIN = dodaniePracownika.getPIN();
                        Szyfrator szyfrator = new Szyfrator(PIN);
                        int szyfr = szyfrator.szyfr();
                        dodajKodDostepu(idPracownika, szyfr);

                        layout.show(getContentPane(), "przegladPracownikow");
                        break;
                    case "wroc":
                        przegladPracownikow.czyscTabele();
                        ladujPracownikow();
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
                        przegladPracownikow.czyscTabele();
                        break;
                    case "zwolnijPracownika":
                        zwolnijPracownika(przegladPracownikow.getID());
                        break;
                    case "zmienDane":
                        //dodac zmiane danyhc
                        break;
                    case "dodajPracownika":
                        layout.show(getContentPane(), "dodaniePracownika");
                        break;
                    case "generujTOKEN":
                        int TOKEN = (int) (Math.random() * 1000000);
                        przegladPracownikow.setTOKEN(TOKEN);
                        int id = przegladPracownikow.getID();

                        Szyfrator szyfrator = new Szyfrator(TOKEN);
                        int szyfr = szyfrator.szyfr();
                        generujTOKEN(id, szyfr);
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
                        przegladPlacowek.czyscTabele();
                        break;
                    case "zamknijPlacowke":
                        zamknijPlacowke(przegladPlacowek.getPlacowka());
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
                    case "dodajGre":
                        String nazwa = dyrektorDodanieGry.getTytul();
                        String wydawca = dyrektorDodanieGry.getTytul();
                        String gatunek = dyrektorDodanieGry.getTytul();
                        String klasa = dyrektorDodanieGry.getTytul();
                        int rokWydania = dyrektorDodanieGry.getRok();
                        dodajGre(nazwa, rokWydania, wydawca, gatunek, klasa);

                        layout.show(getContentPane(), "dyrektorPrzegladEgzemplarzy");
                        break;
                }
            }
        });

    }


    void ladujPracownikow(){

        try (
                Statement zapytanie = bazaDanych.createStatement();
                ResultSet resultSet = zapytanie.executeQuery("SELECT idPracownika, imię, nazwisko, idPlacówki FROM 00018732_kw.Pracownicy;");
        ) {
            System.out.println("JD");
            while (resultSet.next()) {
                int idPracownika = resultSet.getInt("idPracownika");
                String imie = resultSet.getString("imię");
                String nazwisko = resultSet.getString("nazwisko");
                int idPlacowki = resultSet.getInt("idPlacówki");
                przegladPracownikow.dodajDaneZBazy(new Object[]{idPracownika, imie, nazwisko, idPlacowki});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void ladujLogi(){

        try (
                Statement zapytanie = bazaDanych.createStatement();
                ResultSet resultSet = zapytanie.executeQuery("SELECT idLogu, idEgzemplarza, idPracownika, akcja, data FROM 00018732_kw.Placówki;");
        ) {
            System.out.println("JD");
            while (resultSet.next()) {
                int idLogu = resultSet.getInt("idLogu");
                int idEgzemplarza = resultSet.getInt("idEgzemplarza");
                int idPracownika = resultSet.getInt("idPracownika");
                String akcja = resultSet.getString("akcja");
                String data = resultSet.getDate("data").toString();

                //DyrektorPrzegladLogow.dodajDaneZBazy(new Object[]{idLogu, idEgzemplarza, idPracownika, akcja, data});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void ladujPlacowki(){

        try (
                Statement zapytanie = bazaDanych.createStatement();
                ResultSet resultSet = zapytanie.executeQuery("SELECT idPlacówki, ulica, numer, numerLokalu, miasto FROM 00018732_kw.Placówki;");
        ) {
            System.out.println("JD");
            while (resultSet.next()) {
                int idPlacowki = resultSet.getInt("idPlacówki");
                String ulica = resultSet.getString("ulica");
                int numer = resultSet.getInt("numer");
                int numerLokalu = resultSet.getInt("numerLokalu");
                String miasto = resultSet.getString("miasto");

                przegladPlacowek.dodajDaneZBazy(new Object[]{idPlacowki, ulica, numer, numerLokalu, miasto});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void rzeczoznawcaZaladujTabele() {

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

    void serwisantZaladujTabele() {

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


    void logowanie() throws SQLException {
        String stanowisko = "";
        int id = ekranLogowania.getId();
        int szyfr;
        int PIN;


        Statement zapytanie = bazaDanych.createStatement();
        ResultSet pracownik = zapytanie.executeQuery("SELECT kd.PIN, p.status, p.stanowisko FROM 00018732_kw.Pracownicy p NATURAL JOIN 00018732_kw.KodyDostępu kd WHERE idPracownika=" + id);
        pracownik.next();
        szyfr = pracownik.getInt("PIN");
        Deszyfrator deszyfrator = new Deszyfrator(szyfr);
        PIN = deszyfrator.PIN();

        if (PIN != Integer.parseInt(ekranLogowania.getPIN())) {
            JOptionPane.showMessageDialog(null, "Błędny PIN lub ID.");
            return;
        }

        boolean status = pracownik.getBoolean("status");
        if (!status) {
            JOptionPane.showMessageDialog(null, "Użytkownik zablokowany.");
            return;
        }

        stanowisko = pracownik.getString("stanowisko");


        switch (stanowisko) {
            case "Dyrektor":
                layout.show(getContentPane(), "interfejsDyrektora");
                zalogowanyPracownik = id;
                break;
            case "Rzeczoznawca":
                rzeczoznawcaZaladujTabele();
                layout.show(getContentPane(), "ekranRzeczoznawcy");
                zalogowanyPracownik = id;
                break;
            case "Serwisant":
                serwisantZaladujTabele();
                layout.show(getContentPane(), "ekranSerwisanta");
                zalogowanyPracownik = id;
                break;
            case "Sprzedawca":
                layout.show(getContentPane(), "ekranSprzedawcy");
                zalogowanyPracownik = id;
                break;
            default:
                JOptionPane.showMessageDialog(null, "Oj, coś poszło nie tak. :(");
                break;
        }

    }

    void zmienianiePINU() throws SQLException {
        int TOKEN = 0;
        int id = Integer.parseInt(zmienPIN.getID());
        int PIN;
        int szyfr;

        Statement zapytanie = bazaDanych.createStatement();
        ResultSet pracownik = zapytanie.executeQuery("SELECT PIN FROM 00018732_kw.KodyDostępu WHERE idPracownika=" + id);
        pracownik.next();
        szyfr = pracownik.getInt("PIN");
        Deszyfrator deszyfrator = new Deszyfrator(szyfr);
        TOKEN = deszyfrator.PIN();
        if (TOKEN != Integer.parseInt(zmienPIN.getTOKEN())) {
            JOptionPane.showMessageDialog(null, "Nieprawidłowy TOKEN!");
            return;
        }


        PIN = Integer.parseInt(zmienPIN.getNowyPIN());
        if (PIN != Integer.parseInt(zmienPIN.getPowtorzPIN())) {
            JOptionPane.showMessageDialog(null, "Nowy PIN musi być zgodny z powtórzonym nowym PINEM!");
            return;
        }
        Szyfrator szyfrator = new Szyfrator(PIN);
        szyfr = szyfrator.szyfr();

        zapytanie = bazaDanych.createStatement();
        zapytanie.executeUpdate("UPDATE 00018732_kw.KodyDostępu SET PIN=" + szyfr + " WHERE idPracownika=" + id + ";");

        JOptionPane.showMessageDialog(null, "Poprawnie zmieniono PIN!");
        zmienPIN.resetTextFields();
        layout.show(getContentPane(), "ekranLogowania");
    }


    private void dodajGre(String nazwa, int rokWydania, String wydawca, String gatunek, String klasa) {
        StringBuilder komenda = new StringBuilder("INSERT INTO 00018732_kw.Gry (nazwa, rokWydania, wydawca, gatunek, klasa) VALUES (\"");
        komenda.append(nazwa);
        komenda.append("\",\"");
        komenda.append(rokWydania);
        komenda.append("\",\"");
        komenda.append(wydawca);
        komenda.append("\",\"");
        komenda.append(gatunek);
        komenda.append("\",\"");
        komenda.append(klasa);
        komenda.append("\");");
        System.out.println(komenda.toString());

        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dodajEgzemplarz(int idGry, int idPlacowki, int stan, int cena, String status) {
        StringBuilder komenda = new StringBuilder("INSERT INTO 00018732_kw.Egzemplarze (idGry, idPlacówki, stan, cena, status) VALUES (");
        komenda.append(idGry);
        komenda.append(",");
        komenda.append(idPlacowki);
        komenda.append(",");
        komenda.append(stan);
        komenda.append(",");
        komenda.append(cena);
        komenda.append(",\"");
        komenda.append(status);
        komenda.append("\");");
        System.out.println(komenda.toString());

        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda.toString());
            zapytanie = bazaDanych.createStatement();
            ResultSet ezgemplarz= zapytanie.executeQuery("SELECT max(idEgzemplarza) FROM 00018732_kw.Egzemplarze");
            ezgemplarz.next();
            int idEgzemplarza = ezgemplarz.getInt("idEgzemplarza");

            dodajLog(idEgzemplarza, zalogowanyPracownik, "dodano egzemplarz");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dodajPracownika(int idPracownika, int idPlacowki, String imie, String nazwisko, String stanowisko, boolean status) {
        StringBuilder komenda = new StringBuilder("INSERT INTO 00018732_kw.Pracownicy (idPracownika, idPlacówki, imię, nazwisko, stanowisko, status) VALUES (");
        komenda.append(idPracownika);
        komenda.append(",");
        komenda.append(idPlacowki);
        komenda.append(",\"");
        komenda.append(imie);
        komenda.append("\",\"");
        komenda.append(nazwisko);
        komenda.append("\",\"");
        komenda.append(stanowisko);
        komenda.append("\",");
        komenda.append(status);
        komenda.append(");");
        System.out.println(komenda.toString());

        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dodajPlacowke(String ulica, int numer, String miasto, int numerLokalu, boolean status) {
        StringBuilder komenda = new StringBuilder("INSERT INTO 00018732_kw.Placówki (ulica, numer, miasto, numerLokalu, status) VALUES (\"");
        komenda.append(ulica);
        komenda.append("\",");
        komenda.append(numer);
        komenda.append(",\"");
        komenda.append(miasto);
        komenda.append("\",");
        komenda.append(numerLokalu);
        komenda.append(",");
        komenda.append(status);
        komenda.append(");");
        System.out.println(komenda.toString());

        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dodajKodDostepu(int id, int PIN) {
        StringBuilder komenda = new StringBuilder("INSERT INTO 00018732_kw.KodyDostępu (idPracownika, PIN) VALUES (");
        komenda.append(id);
        komenda.append(",");

        Szyfrator szyfrator = new Szyfrator(PIN);
        int szyfr = szyfrator.szyfr();
        komenda.append(szyfr);
        komenda.append(");");

        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dodajZamowienie(int idEgzemplarza, int idPlacowkiWysylajacej, int idPlacowkiOdbierajacej) {
        StringBuilder komenda = new StringBuilder("INSERT INTO 00018732_kw.Zamówienia (idEgzemplarza, placówkaWysyłająca, placówkaOdbierająca) VALUES (");
        komenda.append(idEgzemplarza);
        komenda.append(",");
        komenda.append(idPlacowkiWysylajacej);
        komenda.append(",");
        komenda.append(idPlacowkiOdbierajacej);
        komenda.append(");");

        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda.toString());
            dodajLog(idEgzemplarza, zalogowanyPracownik, "zamowiono");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dodajLog(int idEgzemplarza, int idPracownika, String akcja) {
        StringBuilder komenda = new StringBuilder("INSERT INTO 00018732_kw.Logi (idEgzemplarza, idPracownika, akcja, data) VALUES (");
        komenda.append(idEgzemplarza);
        komenda.append(",");
        komenda.append(idPracownika);
        komenda.append(",\"");
        komenda.append(akcja);
        komenda.append("\",");
        komenda.append(LocalDateTime.now());
        komenda.append(");");

        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void zmienGatunek(String gatunek, int idEgzemplarza) throws SQLException {
        Statement zapytanie = bazaDanych.createStatement();
        ResultSet gra = zapytanie.executeQuery("SELECT idGry FROM 00018732_kw.Egzemplarze WHERE idEgzemplarza=" + idEgzemplarza + ";");
        gra.next();
        int idGry = gra.getInt("idGry");

        String komenda = "UPDATE 00018732_kw.Gry SET gatunek=" + gatunek + " WHERE idGry=" + idGry + ";";
        zapytanie = bazaDanych.createStatement();
        zapytanie.executeUpdate(komenda);
    }

    private void zmienCene(int cena, int idEgzemplarza) {
        String komenda = "UPDATE 00018732_kw.Egzemplarze SET cena=" + cena + " WHERE idEgzemplarza=" + idEgzemplarza + ";";
        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda);
            dodajLog(idEgzemplarza, zalogowanyPracownik, "zmiana ceny: "+ cena );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void zmienStatus(String status, int idEgzemplarza) {
        String komenda = "UPDATE 00018732_kw.Egzemplarze SET status=" + status + " WHERE idEgzemplarza=" + idEgzemplarza + ";";
        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda);
            dodajLog(idEgzemplarza, zalogowanyPracownik, "zmiana statusu: "+status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void zmienStan(int stan, int idEgzemplarza) {
        String komenda = "UPDATE 00018732_kw.Egzemplarze SET stan=" + stan + " WHERE idEgzemplarza=" + idEgzemplarza + ";";
        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda);
            dodajLog(idEgzemplarza, zalogowanyPracownik, "zmiana stanu: "+ stan);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void zwolnijPracownika(int idPracownika) {
        String komenda = "UPDATE 00018732_kw.Pracownicy SET status=false WHERE idPracownika=" + idPracownika + ";";
        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void zamknijPlacowke(int idPlacowki) {
        String komenda = "UPDATE 00018732_kw.Placówki SET status=false WHERE idPlacówki=" + idPlacowki + ";";
        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void generujTOKEN(int id, int TOKEN) {
        Szyfrator szyfrator = new Szyfrator(TOKEN);
        int szyfr = szyfrator.szyfr();
        String komenda = "UPDATE 00018732_kw.KodyDostępu SET PIN=" + szyfr + " WHERE idPracownika=" + id + ";";
        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void zmienDane(String imie, String nazwisko, int idPracownika) {
        String komenda = "UPDATE 00018732_kw.Pracownicy SET imie=" + imie + ", nazwisko =" + nazwisko + " WHERE idPracownika=" + idPracownika + ";";
        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void serwis(int idEgzemplarza) throws SQLException {
        Statement zapytanie = bazaDanych.createStatement();
        ResultSet egzemplarz = zapytanie.executeQuery("SELECT stan FROM 00018732_kw.Egzemplarze WHERE idEgzemplarza=" + idEgzemplarza + ";");
        egzemplarz.next();

        int stan = egzemplarz.getInt("stan");
        if (stan < 4) {
            stan++;
        }

        zmienStan(stan, idEgzemplarza);
        zmienStatus("do wyceny", idEgzemplarza);
        dodajLog(idEgzemplarza, zalogowanyPracownik, "serwis");
    }


    void rzeczoznawcaZaladujTabele() {

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

    void serwisantZaladujTabele() {

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

}