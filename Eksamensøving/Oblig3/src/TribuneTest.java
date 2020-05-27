import java.util.Arrays;
import java.util.Comparator;

public class TribuneTest {
	public static void main(String[] args) {
		VIP vip = new VIP("Farsund", 200, 150);
		Sit sit = new Sit("Lyngdal", 150, 75);
		Stand stand = new Stand("Borhaug", 75, 25);
		
		Tribune[] tribunes = new Tribune[3];
		tribunes[0] = vip;
		tribunes[1] = sit;
		tribunes[2] = stand;
		
		String[] names = new String[3];
		names[0] = "Mads";
		names[1] = "Eirik";
		names[2] = "Lars";
		
		System.out.println(Arrays.toString(vip.buyTicket(names)));
		System.out.println(vip.findNumberOfSoldTickets());
		System.out.println(vip.findIncome());
		vip.toString();
		
		System.out.println(Arrays.toString(vip.buyTicket(3)));
		
		System.out.println(Arrays.toString(sit.buyTicket(3)));
		System.out.println(Arrays.toString(sit.buyTicket(names)));
		System.out.println(sit.findNumberOfSoldTickets());
		System.out.println(sit.findIncome());
		
		System.out.println(Arrays.toString(stand.buyTicket(3*6)));
		System.out.println(Arrays.toString(stand.buyTicket(names)));
		System.out.println(stand.findNumberOfSoldTickets());
		System.out.println(stand.findIncome());
		
		System.out.println(Arrays.toString(tribunes));
		Arrays.sort(tribunes, new SortByIncome());
		System.out.println(Arrays.toString(tribunes));
	}
	
	public static class SortByIncome implements Comparator<Tribune> {
		public int compare(Tribune a, Tribune b) {
			return b.findIncome() - a.findIncome();
		}
	}
}
