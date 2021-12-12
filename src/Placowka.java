public class Placowka {
    int idPlacowki;
    String ulica;
    int numer;
    String miasto;
    int nrLokalu;
    boolean status;

    public Placowka(int idPlacowki, String ulica, int numer, String miasto, int nrLokalu, boolean status) {
        this.idPlacowki = idPlacowki;
        this.ulica = ulica;
        this.numer = numer;
        this.miasto = miasto;
        this.nrLokalu = nrLokalu;
        this.status = status;
    }

    public int getIdPlacowki() {
        return idPlacowki;
    }

    public void setIdPlacowki(int idPlacowki) {
        this.idPlacowki = idPlacowki;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public int getNrLokalu() {
        return nrLokalu;
    }

    public void setNrLokalu(int nrLokalu) {
        this.nrLokalu = nrLokalu;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}


