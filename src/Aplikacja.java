import Dyrektor.*;
import Sprzedawca.*;
import Szyfrowanie.Deszyfrator;
import Szyfrowanie.Szyfrator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

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
    DyrektorPrzegladGier dyrektorPrzegladGier = new DyrektorPrzegladGier();
    DyrektorPrzegladLogow dyrektorPrzegladLogow = new DyrektorPrzegladLogow();
    InterfejsZmianyDanych interfejsZmianyDanych = new InterfejsZmianyDanych();
    PrzegladPlacowek przegladPlacowek = new PrzegladPlacowek();
    PrzegladPracownikow przegladPracownikow = new PrzegladPracownikow();
    DyrektorDodanieGry dyrektorDodanieGry = new DyrektorDodanieGry();
    EkranZamowienRzeczoznawcy ekranZamowienRzeczoznawcy = new EkranZamowienRzeczoznawcy();
    int[][] ceny = {
            {20, 40, 60, 80, 100},
            {0, 4, 15, 30, 50},
            {0, 0, 5, 10, 25},
            {0, 0, 0, 5, 10},
            {80, 100, 120, 150, 200},
    };
    private List<Integer> koszyk = new ArrayList();
    private List<Integer[]> zamowienia = new ArrayList();

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
                        dodajLog(ekranSerwisanta.getID(), zalogowanyPracownik, "dyskwalifikacja");
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
                    case "zamowienia":
                        layout.show(getContentPane(), "ekranZamowienRzeczoznawcy");
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
        ekranZamowienRzeczoznawcy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                int id;
                switch (akcja) {
                    case "odebrano":
                        id =ekranZamowien.getID("odebrane");
                        zmienSatusZamowienia("dostarczone",id);
                        break;
                    case "wyslano":
                        id =ekranZamowien.getID("wyslane");
                        zmienSatusZamowienia("do odebrania",id);
                        break;
                    case "wroc":
                        layout.show(getContentPane(), "ekranRzeczoznawcy");
                        break;
                }
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
        add(dyrektorPrzegladGier, "dyrektorPrzegladEgzemplarzy");
        add(dyrektorPrzegladLogow, "dyrektorPrzegladLogow");
        add(interfejsZmianyDanych, "interfejsZmianyDanych");
        add(przegladPlacowek, "przegladPlacowek");
        add(przegladPracownikow, "przegladPracownikow");
        add(dyrektorDodanieGry, "dyrektorDodanieGry");
        add(ekranZamowienRzeczoznawcy,"ekranZamowienRzeczoznawcy");
    }

    void sprzedawca() {
        ekranSprzedawcy.addSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getFirstIndex()!=-1){
                ekranSprzedawcy.czyscTabeleEgzemplarze();
                sprzedawcaLadujEgzemplarze();
            }
            }
        });
        dodanieGry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                switch (akcja) {
                    case "dodajGre":
                        String nazwa = dodanieGry.getTytul();
                        int rokWydania = dodanieGry.getRok();
                        String wydawca = dodanieGry.getWydawce();
                        String klasa = dodanieGry.getKlase();
                        dodajGre(nazwa, rokWydania, wydawca, null, klasa);
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
                        for (Integer[] integers : zamowienia) {
                            if (integers[0] == ekranKoszyka.getID()) {
                                usunZamowienie(integers[1]);
                            }
                        }
                        koszyk.remove((Object)ekranKoszyka.getID());
                        ekranKoszyka.czyscTabele();
                        wyswietlenieKoszyka();
                        break;
                    case "sprzedaj":
                        for (int i : koszyk) {
                            zmienStatus("\"sprzedana\"", i);

                            for (Integer[] integers : zamowienia) {
                                if (integers[0] == i) {
                                    zmienSatusZamowienia("sprzedana",integers[1]);
                                }
                            }
                        }
                        koszyk.clear();
                        zamowienia.clear();
                        ekranKoszyka.czyscTabele();
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
                int stan, cena;
                switch (akcja) {
                    case "dodajGre":
                        layout.show(getContentPane(), "dodanieGry");
                        break;
                    case "zakup":
                        int idGry = kupnoEgzemplarza.getID();
                        stan = kupnoEgzemplarza.getStan();
                        cena = kupnoEgzemplarza.getCena();
                        int idPlacowki = placowka(zalogowanyPracownik);
                        int idEgzemplarza = dodajEgzemplarz(idGry, idPlacowki, stan, cena, "do serwisu");
                        dodajZamowienie(idEgzemplarza,idPlacowki,1);
                        break;
                    case "generujCene":
                        int klasa = klasaGry(kupnoEgzemplarza.getID());
                        stan = kupnoEgzemplarza.getStan();
                        cena = ceny[klasa][stan];
                        kupnoEgzemplarza.setCena(cena);
                        break;
                    case "filtruj":
                        kupnoEgzemplarza.czyscTabele();
                        String nazwa = kupnoEgzemplarza.getTytul();
                        List<Object[]>gryPrzefiltrowane = filtrujGry(nazwa, null, null, ladujGry()) ;
                        sprzedawcaLadujGryKupno(gryPrzefiltrowane);
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
                int id;
                switch (akcja) {
                    case "odebrano":
                        id =ekranZamowien.getID("odebrane");
                        zmienSatusZamowienia("do sprzedaży",id);
                        break;
                    case "wyslano":
                        id =ekranZamowien.getID("wyslane");
                        zmienSatusZamowienia("do odebrania",id);
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
                        String id = ekranSprzedawcy.getID();
                        if (Objects.equals(id, "")) {
                            id = String.valueOf(ekranSprzedawcy.getIDEgzemplarza());
                            koszyk.add(Integer.valueOf(id));
                        } else {
                            zamowienieDoKoszyka(Integer.parseInt(id));
                        }
                        ekranSprzedawcy.resetID();
                        ekranSprzedawcy.czyscTabeleGry();
                        ekranSprzedawcy.czyscTabeleEgzemplarze();
                        sprzedawcaLadujGry(ladujGry());
                        break;
                    case "koszyk":
                        layout.show(getContentPane(), "ekranKoszyka");
                        ekranKoszyka.czyscTabele();
                        wyswietlenieKoszyka();
                        break;
                    case "zamowienia":
                        layout.show(getContentPane(), "ekranZamowien");
                        break;
                    case "szukaj"://aktualizacja tablic
                        ekranSprzedawcy.czyscTabeleGry();
                        ekranSprzedawcy.czyscTabeleEgzemplarze();
                        String nazwa = ekranSprzedawcy.getTytul();
                        String gatunek = ekranSprzedawcy.getGatunek();
                        Integer rokwydania = ekranSprzedawcy.getRok();
                        String rok;
                        if(rokwydania==null)
                            rok = null;
                        else
                            rok = rokwydania.toString();
                        List<Object[]>gryPrzefiltrowane = filtrujGry(nazwa, gatunek, rok, ladujGry()) ;
                        sprzedawcaLadujGry(gryPrzefiltrowane);
                        break;
                    case "kup":
                        kupnoEgzemplarza.czyscTabele();
                        sprzedawcaLadujGryKupno(ladujGry());
                        layout.show(getContentPane(), "kupnoEgzemplarza");
                        break;
                    case "zamow":
                        int idEgzemplarza = ekranSprzedawcy.getIDEgzemplarza();
                        int idPlacowkiWysylajacej = ekranSprzedawcy.getPlacowka();
                        int idPlacowkiOdbierajacej = placowka(zalogowanyPracownik);
                        dodajZamowienie(idEgzemplarza, idPlacowkiWysylajacej, idPlacowkiOdbierajacej);
                        zmienStatus("zamówiona", idEgzemplarza);
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
                        przegladPracownikow.czyscTabele();
                        ladujPracownikow();
                        layout.show(getContentPane(), "przegladPracownikow");
                        break;
                    case "dyrektorGry":
                        dyrektorPrzegladGier.czyscTabele();
                        dyrektorLadujGry(ladujGry());
                        layout.show(getContentPane(), "dyrektorPrzegladEgzemplarzy");
                        break;
                    case "dyrektorPlacowki":
                        ladujPlacowki();
                        layout.show(getContentPane(), "przegladPlacowek");
                        break;
                    case "dyrektorLogiSprzedazy":
                        dyrektorPrzegladLogow.czyscTabele();
                        ladujLogi();
                        layout.show(getContentPane(), "dyrektorPrzegladLogow");
                        break;
                    case "dyrektorWyloguj":
                        zalogowanyPracownik = -1;
                        layout.show(getContentPane(), "ekranLogowania");
                        break;
                }
            }
        });
        dyrektorPrzegladGier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                System.out.println(command);
                switch (command) {
                    case "filtruj":
                        dyrektorPrzegladGier.czyscTabele();
                        String nazwa = dyrektorPrzegladGier.getTytul();
                        List<Object[]>gryPrzefiltrowane = filtrujGry(nazwa, null, null, ladujGry()) ;
                        dyrektorLadujGry(gryPrzefiltrowane);
                        break;
                    case "wroc":
                        layout.show(getContentPane(), "interfejsDyrektora");
                        break;
                    case "dodajGre":
                        layout.show(getContentPane(), "dyrektorDodanieGry");
                        break;
                    case "dodajEgzemplarze":
                        int ilosc = dyrektorPrzegladGier.liczba();

                        int idGry = dyrektorPrzegladGier.gra();
                        int idPlacowki = 1;
                        int stan = 4;
                        int cena = dyrektorPrzegladGier.cena();
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
                    case "dodajPlacowke":
                        String ulica = dodaniePlacowki.getUlica();
                        int numer = dodaniePlacowki.getNumer();
                        String miasto = dodaniePlacowki.getMiasto();
                        int numerLokalu = dodaniePlacowki.getLokal();
                        dodajPlacowke(ulica, numer, miasto, numerLokalu, true);

                        layout.show(getContentPane(), "przegladPlacowek");
                        break;
                    case "wroc":

                        layout.show(getContentPane(), "przegladPlacowek");
                        break;
                }
                przegladPlacowek.czyscTabele();
                ladujPlacowki();
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
                        layout.show(getContentPane(), "przegladPracownikow");
                        break;
                }
                przegladPracownikow.czyscTabele();
                ladujPracownikow();
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

                        generujTOKEN(id, TOKEN);
                        break;
                }
                przegladPracownikow.czyscTabele();
                ladujPracownikow();
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
                    case "zamknijPlacowke":
                        zamknijPlacowke(przegladPlacowek.getPlacowka());
                        break;
                    case "dodajPlacowke":
                        layout.show(getContentPane(), "dodaniePlacowki");
                        break;
                }
                przegladPlacowek.czyscTabele();
                ladujPlacowki();
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
                        String wydawca = dyrektorDodanieGry.getWydawce();
                        String gatunek = dyrektorDodanieGry.getGatunek();
                        String klasa = dyrektorDodanieGry.getKlase();
                        int rokWydania = dyrektorDodanieGry.getRok();
                        dodajGre(nazwa, rokWydania, wydawca, gatunek, klasa);

                        layout.show(getContentPane(), "dyrektorPrzegladEgzemplarzy");
                        break;
                }
                dyrektorPrzegladGier.czyscTabele();
                dyrektorLadujGry(ladujGry());
            }
        });

    }


    void ladujPracownikow() {

        try (
                Statement zapytanie = bazaDanych.createStatement();
                ResultSet resultSet = zapytanie.executeQuery("SELECT idPracownika, imię, nazwisko, idPlacówki FROM 00018732_kw.Pracownicy;");
        ) {
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

    void ladujLogi() {

        try (
                Statement zapytanie = bazaDanych.createStatement();
                ResultSet resultSet = zapytanie.executeQuery("SELECT idLogu, idEgzemplarza, idPracownika, akcja, data FROM 00018732_kw.Log;");
        ) {
            while (resultSet.next()) {
                int idLogu = resultSet.getInt("idLogu");
                int idEgzemplarza = resultSet.getInt("idEgzemplarza");
                int idPracownika = resultSet.getInt("idPracownika");
                String akcja = resultSet.getString("akcja");
                String data = resultSet.getDate("data").toString();

                System.out.println(idLogu);
                System.out.println(idEgzemplarza);

                dyrektorPrzegladLogow.dodajDaneZBazy(new Object[]{idLogu, idEgzemplarza, idPracownika, akcja, data});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    List<Object[]> filtrujGry(String nazwa, String gatunek, String rokWydania, List<Object[]>aktualneGry){

        for(int i = 0; i < aktualneGry.size();i++){
            if(nazwa!=null && !aktualneGry.get(i)[1].toString().toLowerCase().contains(nazwa.toLowerCase())){
                System.out.println("Usuwam po tytule "+aktualneGry.get(i)[1].toString().toLowerCase());
                aktualneGry.remove(i);
                i--;
                continue;
            }
            if(gatunek!=null && !aktualneGry.get(i)[4].toString().toLowerCase().contains(gatunek.toLowerCase())){
                System.out.println("Usuwam po gatunku "+aktualneGry.get(i)[4].toString().toLowerCase());
                aktualneGry.remove(i);
                i--;
                continue;
            }
            if(rokWydania!=null && !aktualneGry.get(i)[2].toString().contains(rokWydania)){
                System.out.println("Usuwam po rokWydania "+aktualneGry.get(i)[2].toString().toLowerCase());
                aktualneGry.remove(i);
                i--;
                continue;
            }
        }
        return aktualneGry;
    }

    List<Object[]> ladujGry() {

        List<Object[]> object = new ArrayList<>();
        try (
                Statement zapytanie = bazaDanych.createStatement();
                ResultSet resultSet = zapytanie.executeQuery("SELECT idGry, nazwa, rokWydania, wydawca, gatunek FROM 00018732_kw.Gry;");
        ) {
            while (resultSet.next()) {
                int idGry = resultSet.getInt("idGry");
                String nazwa = resultSet.getString("nazwa");
                int rokWydania = resultSet.getInt("rokWydania");
                String wydawca = resultSet.getString("wydawca");
                String gatunek = resultSet.getString("gatunek");

                object.add(new Object[]{idGry, nazwa, rokWydania, wydawca, gatunek});

//                dyrektorPrzegladEgzemplarzy.dodajDaneZBazy(new Object[]{idGry, nazwa, rokWydania, wydawca, gatunek});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return object;
    }

    void dyrektorLadujGry( List<Object[]> object) {
        for (Object[] ob : object) {
            dyrektorPrzegladGier.dodajDaneZBazy(ob);
        }
    }

    void sprzedawcaLadujGry(List<Object[]> object) {
        for (Object[] ob : object) {
            ekranSprzedawcy.dodajGreZBazy(ob);
        }
    }

    void sprzedawcaLadujGryKupno(List<Object[]> object) {
        for (Object[] ob : object) {
            kupnoEgzemplarza.dodajDaneZBazy(ob);
        }
    }

    List<Object[]> ladujEgzemplarze() {

        List<Object[]> object = new ArrayList<>();
        try (
                Statement zapytanie = bazaDanych.createStatement();
                ResultSet resultSet = zapytanie.executeQuery("SELECT idEgzemplarza, stan, cena, idPlacówki FROM 00018732_kw.Egzemplarze WHERE idGry=" + ekranSprzedawcy.getIDGry());
        ) {
            while (resultSet.next()) {
                int idEgzemplarza = resultSet.getInt("idEgzemplarza");
                String stan = resultSet.getString("stan");
                int cena = resultSet.getInt("cena");
                int placowka = resultSet.getInt("idPlacówki");

                object.add(new Object[]{idEgzemplarza, stan, cena, placowka});

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return object;
    }


    void sprzedawcaLadujEgzemplarze() {

        List<Object[]> object = ladujEgzemplarze();
        for (Object[] ob : object) {
            ekranSprzedawcy.dodajEgzemplarzZBazy(ob);
        }
    }

    List<Object[]> rzeczoznawcaISerwisantZaladujTabele() {
        List<Object[]> object = new ArrayList<>();
        try (
                Statement zapytanie = bazaDanych.createStatement();
                ResultSet resultSet = zapytanie.executeQuery("SELECT e.idEgzemplarza, e.stan, e.cena, g.nazwa, g.rokWydania, g.wydawca FROM 00018732_kw.Gry g JOIN 00018732_kw.Egzemplarze e ON e.idGry=g.idGry;");
        ) {
            while (resultSet.next()) {
                int idEgzemplarza = resultSet.getInt("idEgzemplarza");
                int stan = resultSet.getInt("stan");
                int cena = resultSet.getInt("cena");
                int rokWydania = resultSet.getInt("rokWydania");
                String wydawca = resultSet.getString("wydawca");
                String nazwa = resultSet.getString("nazwa");
                object.add(new Object[]{idEgzemplarza, nazwa, stan, cena, rokWydania, wydawca});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return object;

    }


    void ladujPlacowki() {

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
        List<Object[]> object = rzeczoznawcaISerwisantZaladujTabele();
        for (Object[] ob : object) {
            Object[] nowyob = new Object[]{ob[0],ob[1]};
            ekranRzeczoznawcy.dodajDaneZBazy(nowyob);
        }
    }

    void serwisantZaladujTabele() {
        List<Object[]> object = rzeczoznawcaISerwisantZaladujTabele();
        for (Object[] ob : object) {
            Object[] nowyob = new Object[]{ob[0],ob[1]};
            ekranSerwisanta.dodajDaneZBazy(nowyob);
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
                sprzedawcaLadujGry(ladujGry());
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

    private int dodajEgzemplarz(int idGry, int idPlacowki, int stan, int cena, String status) {
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

        int idEgzemplarza=-1;
        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda.toString());
            zapytanie = bazaDanych.createStatement();
            ResultSet ezgemplarz = zapytanie.executeQuery("SELECT max(idEgzemplarza) AS idEgzemplarza FROM 00018732_kw.Egzemplarze");
            ezgemplarz.next();
            idEgzemplarza = ezgemplarz.getInt("idEgzemplarza");

            dodajLog(idEgzemplarza, zalogowanyPracownik, "dodano egzemplarz");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idEgzemplarza;
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
        StringBuilder komenda = new StringBuilder("INSERT INTO 00018732_kw.Zamówienia (idEgzemplarza, placówkaWysyłająca, placówkaOdbierająca, status) VALUES (");
        komenda.append(idEgzemplarza);
        komenda.append(",");
        komenda.append(idPlacowkiWysylajacej);
        komenda.append(",");
        komenda.append(idPlacowkiOdbierajacej);
        komenda.append(", \"do wysłania\");");

        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda.toString());
            dodajLog(idEgzemplarza, zalogowanyPracownik, "zamowiono");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void zmienSatusZamowienia(String status,int idZamowienia){
        String komenda = "UPDATE 00018732_kw.Zamówienia SET status=" + status + " WHERE idZamówienia=" + idZamowienia + ";";
        Statement zapytanie = null;
        try {
            zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dodajLog(int idEgzemplarza, int idPracownika, String akcja) {
        StringBuilder komenda = new StringBuilder("INSERT INTO 00018732_kw.Log (idEgzemplarza, idPracownika, akcja, data) VALUES (");
        komenda.append(idEgzemplarza);
        komenda.append(",");
        komenda.append(idPracownika);
        komenda.append(",\"");
        komenda.append(akcja);
        komenda.append("\",");
        komenda.append("CURDATE()");
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
            dodajLog(idEgzemplarza, zalogowanyPracownik, "zmiana ceny");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void zmienStatus(String status, int idEgzemplarza) {
        String komenda = "UPDATE 00018732_kw.Egzemplarze SET status=\"" + status + "\" WHERE idEgzemplarza=" + idEgzemplarza + ";";
        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda);
            dodajLog(idEgzemplarza, zalogowanyPracownik, "zmiana statusu");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void zmienStan(int stan, int idEgzemplarza) {
        String komenda = "UPDATE 00018732_kw.Egzemplarze SET stan=" + stan + " WHERE idEgzemplarza=" + idEgzemplarza + ";";
        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate(komenda);
            dodajLog(idEgzemplarza, zalogowanyPracownik, "zmiana stanu");
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

    private int placowka(int idPracownika) {
        int idPlacowki = -1;
        try {
            Statement zapytanie = bazaDanych.createStatement();
            ResultSet egzemplarz = zapytanie.executeQuery("SELECT idPlacówki FROM 00018732_kw.Pracownicy WHERE idPracownika=" + idPracownika + ";");
            egzemplarz.next();
            idPlacowki = egzemplarz.getInt("idPlacówki");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idPlacowki;
    }

    private void zamowienieDoKoszyka(int idZamowienia) {
        int idEgzemplarza = -1;
        try {
            Statement zapytanie = bazaDanych.createStatement();
            ResultSet egzemplarz = zapytanie.executeQuery("SELECT idEgzemplarza, status FROM 00018732_kw.Zamówienia WHERE idZamówienia=" + idZamowienia + ";");
            egzemplarz.next();
            if(!egzemplarz.getString("status").contains("do sprzedaży")){
                JOptionPane.showMessageDialog(null, "Brak zamowienia.");
                return;
            }
            idEgzemplarza = egzemplarz.getInt("idEgzemplarza");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        koszyk.add(idEgzemplarza);

        zmienSatusZamowienia("w koszyku", idZamowienia);
        zamowienia.add(new Integer[]{idEgzemplarza, idZamowienia});
    }


    void wyswietlenieKoszyka(){
        for(int i =0; i< koszyk.size();i++){

            try {
                int idEgzemplarza, cena, rokWydania;
                String nazwa, stan, wydawca;
                Statement zapytanie = bazaDanych.createStatement();
                ResultSet egzemplarz = zapytanie.executeQuery("SELECT e.idEgzemplarza, g.nazwa, e.stan, e.cena, g.rokWydania, g.wydawca  FROM 00018732_kw.Gry g JOIN 00018732_kw.Egzemplarze e ON e.idGry=g.idGry WHERE e.idEgzemplarza=" + koszyk.get(i) + ";");
                egzemplarz.next();

                idEgzemplarza = egzemplarz.getInt("idEgzemplarza");
                cena = egzemplarz.getInt("cena");
                rokWydania = egzemplarz.getInt("rokWydania");
                nazwa = egzemplarz.getString("nazwa");
                stan = egzemplarz.getString("stan");
                wydawca = egzemplarz.getString("wydawca");

                ekranKoszyka.dodajDaneZBazy(new Object[]{idEgzemplarza, nazwa, stan, cena, rokWydania, wydawca});

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


    private void usunZamowienie(int idZamowienia){
        try {
            Statement zapytanie = bazaDanych.createStatement();
            zapytanie.executeUpdate("UPDATE 00018732_kw.Zamówienia SET status=\"do sprzedaży\" WHERE idZamówienia=" + idZamowienia + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private int klasaGry(int idGry) {
        String klasa = null;
        try {
            Statement zapytanie = bazaDanych.createStatement();
            ResultSet egzemplarz = zapytanie.executeQuery("SELECT klasa FROM 00018732_kw.Gry WHERE idGry=" + idGry + ";");
            egzemplarz.next();
            klasa = egzemplarz.getString("klasa");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int klasaINT = 3;

        switch (klasa) {
            case "AAA":
                klasaINT = 0;
                break;
            case "Srednia":
                klasaINT = 1;
                break;
            case "Niska":
                klasaINT = 2;
                break;
            case "Edukacyjne":
                klasaINT = 3;
                break;
            case "Edycje specjalne":
                klasaINT = 4;
                break;
        }


        return klasaINT;
    }

}