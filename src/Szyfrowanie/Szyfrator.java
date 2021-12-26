package Szyfrowanie;

public class Szyfrator {
    private final int ID;
    private final Integer PIN;
    private final long kluczPubliczny = 191714009L;
    private final long modul = 2640601497976797L;
    private int szyfr;


    public Szyfrator(int ID, int PIN) {
        this.ID = ID;
        this.PIN = PIN;
        szyfruj();
        wyslij();
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
            liczba.append(i); //add all the ints to a string
        }

        return Integer.parseInt(liczba.toString());
    }

    public void szyfruj() {
        int[] doSzyfrowania = konwersja(PIN);

        for (int i : doSzyfrowania) {
            i = (int) (Math.pow(i, kluczPubliczny) % modul);
        }

        szyfr = konwersja(doSzyfrowania);
    }

    public void wyslij() {
        //wysłanie szyfru do bazy dla danego ID
    }

}
