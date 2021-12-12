package Dyrektor;

import java.util.Date;

public class Gra {
    private int idGry;
    private String nazwa;
    private String wydawca;
    private String gatunek;
    private String klasa;
    private int rokWydania;


    public Gra(int idGry, String nazwa, String wydawca, String gatunek, String klasa, int rokWydania) {
        this.idGry = idGry;
        this.nazwa = nazwa;
        this.wydawca = wydawca;
        this.gatunek = gatunek;
        this.klasa = klasa;
        this.rokWydania = rokWydania;
    }

    public int getIdGry() {
        return idGry;
    }

    public void setIdGry(int idGry) {
        this.idGry = idGry;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getWydawca() {
        return wydawca;
    }

    public void setWydawca(String wydawca) {
        this.wydawca = wydawca;
    }

    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    public String getKlasa() {
        return klasa;
    }

    public void setKlasa(String klasa) {
        this.klasa = klasa;
    }

    public int getRokWydania() {
        return rokWydania;
    }

    public void setRokWydania(int rokWydania) {
        this.rokWydania = rokWydania;
    }
}
