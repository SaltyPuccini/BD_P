import Szyfrowanie.Deszyfrator;
import Szyfrowanie.Szyfrator;

import javax.swing.*;

public class testy {
    private JButton dodajGręButton;
    private JButton filtrujButton;
    private JButton wróćButton;
    private JButton dodajEgzemplarzeButton;
    private JTextField tytułTextField;
    private JTextField cenaTextField;
    private JTable table1;
    private JTextField liczbaTextField;

    public static void main(String[] args) {

    Deszyfrator deszyfrator = new Deszyfrator(872688);
        System.out.println(deszyfrator.PIN());


    }

}



