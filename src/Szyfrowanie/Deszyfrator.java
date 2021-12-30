package Szyfrowanie;

import java.util.IllegalFormatCodePointException;

public class Deszyfrator {
    private final long kluczPrywatny =  7;
    private final long modul = 10;
    private int szyfr;
    private int PIN;


    public Deszyfrator(int szyfr) {
        this.szyfr = szyfr;
        deszyfruj();
    }

    private int[] konwersja(Integer liczba) {
        StringBuilder temp= new StringBuilder();
        temp.append(liczba);

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

    private void deszyfruj() {
        int[] doOdszyfrowania = konwersja(szyfr);

        for (int i =0; i<doOdszyfrowania.length; i++) {
            int temp =(int) (Math.pow(doOdszyfrowania[i], kluczPrywatny) % modul);
            doOdszyfrowania[i] = temp;
        }

        PIN = konwersja(doOdszyfrowania);
    }

    public int PIN() {
        return PIN;
    }
}
