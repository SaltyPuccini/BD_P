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

        layout.show(getContentPane(), "przegladPlacowek");

        pack();
        setLocationRelativeTo(null);
    }

}
