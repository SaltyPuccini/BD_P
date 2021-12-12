import java.util.Date;

public class Log {
    int idLogu;
    int idEgzemplarza;
    int idPracownika;
    String akcja;
    Date data;

    public Log(int idLogu, int idEgzemplarza, int idPracownika, String akcja, Date data) {
        this.idLogu = idLogu;
        this.idEgzemplarza = idEgzemplarza;
        this.idPracownika = idPracownika;
        this.akcja = akcja;
        this.data = data;
    }

    public int getIdLogu() {
        return idLogu;
    }

    public void setIdLogu(int idLogu) {
        this.idLogu = idLogu;
    }

    public int getIdEgzemplarza() {
        return idEgzemplarza;
    }

    public void setIdEgzemplarza(int idEgzemplarza) {
        this.idEgzemplarza = idEgzemplarza;
    }

    public int getIdPracownika() {
        return idPracownika;
    }

    public void setIdPracownika(int idPracownika) {
        this.idPracownika = idPracownika;
    }

    public String getAkcja() {
        return akcja;
    }

    public void setAkcja(String akcja) {
        this.akcja = akcja;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}

