package Szyfrowanie;

public class Szyfrator {
    private final Integer PIN;
    private final long kluczPubliczny =  3;
    private final long modul = 10;
    private int szyfr;


    public Szyfrator(int PIN) {
        this.PIN = PIN;
        szyfruj();
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

        for(int i =0; i<doSzyfrowania.length; i++){
            int temp =(int) (Math.pow(doSzyfrowania[i], kluczPubliczny) % modul);
            doSzyfrowania[i] = temp;
        }

        szyfr = konwersja(doSzyfrowania);
    }

    public int szyfr() {
        return szyfr;
    }

}
