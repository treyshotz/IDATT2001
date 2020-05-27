import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Deck {
	
	private static ArrayList<Character> suites = new ArrayList<>(Arrays.asList('S', 'H', 'D', 'C'));
	private static ArrayList<Integer> faces = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
	private static Random random = new Random();
	
	private static ArrayList<Card> deck = new ArrayList<>();
	
	public static void createDeck() {
		for (char suite : suites) {
			for (int face : faces) {
				deck.add(new Card(suite, face));
			}
		}
	}
	
	public static ArrayList<Card> assign(int num) {
		ArrayList<Card> assigned = new ArrayList<>();
		if (deck.size() != 52)
			createDeck();
		
		for (int i = 0; i < num; i++) {
			int randomNum = random.nextInt(52) + 1;
			assigned.add(deck.get(randomNum));
		}
		return assigned;
	}
	
	public static void getSpades() {
		deck.stream()
				.filter(card -> card.getSuit() == 'S')
				.forEach(System.out::println);
	}
	
	public static List<Card> getHearts() {
		return deck.stream()
				.filter(card -> card.getSuit() == 'H')
				.collect(Collectors.toList());
	}
	
	public static List<String> getColors() {
		return deck.stream()
				.map(Card -> {
					if (Card.getSuit() == 'H' || Card.getSuit() == 'C') return "Red";
					else return "Black";})
				.collect(Collectors.toList());
	}
	
	public static int getSum() {
		return deck.stream()
				.map(Card::getFace)
				.reduce(Integer::sum).get();
	}
	
	public static boolean findAceQueen() {
		return deck.stream().anyMatch(card -> card.getFace() == 12 && card.getSuit() == 'S');
	}
	
	public static boolean isFlush() {
		return ((deck.stream().filter(card -> card.getSuit() == 'S').count() > 4 ||
				deck.stream().filter(card -> card.getSuit() == 'H').count() > 4 ||
				deck.stream().filter(card -> card.getSuit() == 'D').count() > 4 ||
				deck.stream().filter(card -> card.getSuit() == 'C').count() > 4));
	}
	
	
	public static void main(String[] args) {
		createDeck();
		System.out.println(assign(10));
		getSpades();
		System.out.println(getHearts());
		System.out.println(getColors());
		System.out.println(getSum());
		System.out.println(findAceQueen());
		System.out.println(isFlush());
	}
}
