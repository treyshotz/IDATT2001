import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        Tribune t1 = new Stand("Lyngdal", 500, 75);
        Tribune t2 = new VIP("Farsund", 500, 1000);
        ArrayList<Tribune> T = new ArrayList<>(Arrays.asList(t1, t2));

        ArrayList<String> names = new ArrayList<>(Arrays.asList("Mads", "Cedrick", "Filip"));

        //Kjøper 10 biletter på en standing tribune
        System.out.println("Test 1.1: " + t1.buyTicket(10));
        System.out.println("Test 1.2: " + t1.toString());


        //Kjøper 3 standing biletter med navn
        System.out.println("Test 2.1: " + t1.buyTicket(names));
        System.out.println("Test 2.2: " + t1.toString());


        //Kjøper 10 VIP-biletter uten å oppgi navn. Skal returnere null

        //Skal returnere null
        System.out.println("Test 3.1: " + t2.buyTicket(10));
        //Skal ha 0 solgte biletter og inntekt
        System.out.println("Test 3.2: " + t2.toString());

        //Kjøper 3 VIP-biletter med navn
        System.out.println("Test 4.1: " + t2.buyTicket(names));
        System.out.println("Test 4.2: " + t2.toString());
        for(int i = 0; i < 10; i++) {
            System.out.println("Test 4.3:\n" + t2.buyTicket(names));
        }

        System.out.println("\n\nFør sort: " + T.toString());
        Collections.sort(T,  new SortByIncome());
        System.out.println("\n\nEtter sort:" + T.toString());

    }

    public static class SortByIncome implements Comparator<Tribune> {
        public int compare(Tribune a, Tribune b) {
            return b.findIncome() - a.findIncome();
        }
    }
}