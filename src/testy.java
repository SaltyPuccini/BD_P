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

        Szyfrator szyfrator = new Szyfrator(402);
        System.out.println(szyfrator.szyfr());


    }

}



