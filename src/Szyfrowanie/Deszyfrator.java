package Szyfrowanie;

public class Deszyfrator {
    private final int ID;
    private final long kluczPrywatny = 27547297L;
    private final long modul = 2640601497976797L;
    private int szyfr;
    private int PIN;


    public Deszyfrator(int ID) {
        this.ID = ID;
        pobierz();
        deszyfruj();
    }

    private int[] konwersja(Integer liczba) {
        String temp = Integer.toString(liczba);
        int[] tablica = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++) {
            tablica[i] = temp.charAt(i) - '0';
        }
        return tablica;
    }

    private int konwersja(int[] tablica) {
        StringBuilder liczba = new StringBuilder();
        for (int i : tablica) {
            liczba.append(i);
        }

        return Integer.parseInt(liczba.toString());
    }

    private void pobierz() {
        //pobranie z bazy
    }

    private void deszyfruj() {
        int[] doOdszyfrowania = konwersja(szyfr);

        for (int i : doOdszyfrowania) {
            i = (int) (Math.pow(i, kluczPrywatny) % modul);
        }

        PIN = konwersja(doOdszyfrowania);
    }

    public int PIN() {
        return PIN;
    }
}
