import java.lang.Math.*;

public class Sit extends Tribune {
	
	private int[] noBusy;
	
	public Sit(String stadiumName, int capacity, int price) {
		super(stadiumName, capacity, price);
		this.noBusy = new int[getRows()];
	}
	
	@Override
	int findIncome() {
		return getPrice() * findNumberOfSoldTickets();
	}
	
	@Override
	int findNumberOfSoldTickets() {
		int sum = 0;
		for (int i = 0; i < noBusy.length; i++) {
			sum += noBusy[i];
		}
		return sum;
	}
	
	private int findAvailabe(int noTickets) {
		int row = noBusy.length;
		int column = getColumns();
		for (int i = 0; i < row ; i++) {
			int available = column-noBusy[i];
			if(available > noTickets)
				return row;
		}
		return 0;
	}
	
	@Override
	Ticket[] buyTicket(int noTickets) {
		Ticket[] boughtTickets = new Ticket[noTickets];
		
		for (int i = 0; i < noTickets; i++) {
			//Sets 0 as place because it is impossible to know which places has been taken
			Ticket newTicket = new SittingTicket(getStadiumName(), getPrice(), findAvailabe(noTickets), 0);
			noBusy[i] += 1;
			boughtTickets[i] = newTicket;
		}
		return boughtTickets;
	}
	
	@Override
	Ticket[] buyTicket(String[] names) {
		return buyTicket(names.length);
	}
	
	private int getRows() {
		int capacity = getCapacity();
		
		int a = -1;
		int b = -1;
		for (int i = 2; i * i <= capacity; i++) {
			if (capacity % i == 0) {
				a = i;
				b = capacity / i;
			}
		}
		return a;
	}
	
	private int getColumns() {
		int capacity = getCapacity();
		
		int a = -1;
		int b = -1;
		for (int i = 2; i * i <= capacity; i++) {
			if (capacity % i == 0) {
				a = i;
				b = capacity / i;
			}
		}
		return b;
	}
}
