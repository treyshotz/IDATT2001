import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.function.Function;
import java.util.spi.AbstractResourceBundleProvider;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class Card {

    private final char suit;
    private final int face;

    // initialises with suit ('S'=spade, 'H'=heart, 'D'=diamonds, 'C'=clubs) and face (1=ace, 2, ... 10, 11=knight, 12=queen and 13=king).
    public Card(char suit, int face) {
        this.suit = suit;
        this.face = face;
    }

    @Override
    public String toString() {
        return String.format("%s%s", suit, face);
    }

    public char getSuit() {
        return suit;
    }

    public int getFace() {
        return face;
    }

}

class Deck {

    private static ArrayList<Card> deck = new ArrayList<>();
    private static ArrayList<Card> random = new ArrayList<>();
    private static Random r = new Random();


    public ArrayList<Card> assign() {
        int n = r.nextInt(52 + 1 - 1) + 1;
        for(int i = 0; i < n; i++) {
            random.add(deck.get(n));
        }
        return random;
    }

    public static void main(String[] args) {

        Deck deck1 = new Deck();

        //Hearts
        for(int i = 1; i < 14; i++) {
            deck.add(new Card('H', i));
        }

        //Spades
        for(int i = 1; i < 14; i++) {
            deck.add(new Card('S', i));
        }

        //Diamonds
        for(int i = 1; i < 14; i++) {
            deck.add(new Card('D', i));
        }

        //Clubs
        for(int i = 1; i < 14; i++) {
            deck.add(new Card('C', i));
        }



        //Skriver ut alle spades
        System.out.println("C");
        System.out.println(deck.stream().filter(c -> c.getSuit() =='S').collect(Collectors.toList()));

        //Skriver ut alle hjerter
        System.out.println("D");
        ArrayList<Card> hearts = (ArrayList<Card>) deck.stream().filter(c -> c.getSuit() == 'H').collect(Collectors.toList());
        System.out.println(hearts.toString());

        //Lager en ny liste
        System.out.println("E");
        List<Character> color = deck.stream().map(Card::getSuit).collect(Collectors.toList());
        System.out.println(color.toString());

        //For å sortere i to lister
        System.out.println("E gjort på en annen måte");
        ArrayList<Card> black = (ArrayList<Card>) deck.stream().filter(c -> c.getSuit() == 'C' || c.getSuit() == 'S' ).collect(Collectors.toList());
        ArrayList<Card> reds = (ArrayList<Card>) deck.stream().filter(c -> c.getSuit() == 'H' || c.getSuit() == 'D').collect(Collectors.toList());

        System.out.println(black.toString());
        System.out.println(reds.toString());

        //Legger til en sum av alle kortene
        int sum = deck.stream().map(Card::getFace).reduce((a,b) -> a+b).get();
        System.out.println(sum);

        //Skriver ut om spar dame finnes i listen
        System.out.println(deck.stream().anyMatch(c -> c.getSuit() == 'S' && c.getFace() == 12));

        //Skriver ut om det finnes en flush
        //System.out.println(deck.stream().collect(Collectors.groupingBy(c -> c.getSuit(), Collectors.counting())));
        Map<Character, Long> colors = deck.stream().collect(Collectors.groupingBy(c -> c.getSuit(), Collectors.counting()));
        if(colors.get('C') + colors.get('S') >=5 || colors.get('H') + colors.get('D') >= 5) System.out.println("FLUSH");

        //BRUK DENNE ISTEDET
        if(colors.values().stream().anyMatch(c -> c >= 5)) System.out.println("HEII");


       /* int b = (int) black.stream().count();
        int r = (int) reds.stream().count();
*/


/*
        //Funksjon uten lambda
        int bcount = 0;
        int rcount = 0;
        for(Card c: deck) {
            if(c.getSuit() == 'S' || c.getSuit() == 'C') bcount++;
            else rcount++;
            if (rcount >= 5 || bcount >= 5) {System.out.println("FLUSH"); break; }
        }*/
    }



}