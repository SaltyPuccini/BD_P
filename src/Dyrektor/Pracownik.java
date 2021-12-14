package Dyrektor;

public class Pracownik {

    int idPracownika;
    int idPlacowki;
    String imie;
    String nazwisko;
    String stanowisko;
    boolean statusl;

    public Pracownik(int idPracownika, int idPlacowki, String imie, String nazwisko, String stanowisko, boolean statusl) {
        this.idPracownika = idPracownika;
        this.idPlacowki = idPlacowki;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.stanowisko = stanowisko;
        this.statusl = statusl;
    }

    public int getIdPracownika() {
        return idPracownika;
    }

    public void setIdPracownika(int idPracownika) {
        this.idPracownika = idPracownika;
    }

    public int getIdPlacowki() {
        return idPlacowki;
    }

    public void setIdPlacowki(int idPlacowki) {
        this.idPlacowki = idPlacowki;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public boolean isStatusl() {
        return statusl;
    }

    public void setStatusl(boolean statusl) {
        this.statusl = statusl;
    }
}
