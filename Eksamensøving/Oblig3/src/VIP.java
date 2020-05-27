import java.util.Arrays;

public class VIP extends Tribune {
	
	private String[][] spectator;
	
	public VIP(String stadiumName, int capacity, int price) {
		super(stadiumName, capacity, price);
		this.spectator = new String[getRows()][getColumns()];
	}
	
	@Override
	int findIncome() {
		return getPrice() * findNumberOfSoldTickets();
	}
	
	@Override
	int findNumberOfSoldTickets() {
		int sum = 0;
		
		for (int i = 0; i < spectator.length; i++) {
			for (int j = 0; j < spectator[i].length; j++) {
				if (spectator[i][j] == "T")
					sum++;
			}
		}
		return sum;
	}
	
	/**
	 * Finds available places for tickets
	 *
	 * @param noTickets
	 * @return
	 */
	private int[] findAvailable(int noTickets) {
		//First number is row and second is column
		int[] availableFirstSeat = new int[2];
		
		for (int i = 0; i < spectator.length; i++) {
			for (int j = 0; j < spectator[i].length; j++) {
				if (noTickets > (spectator[i].length - j)) {
					continue;
				}
				if (spectator[i][j] == null) {
					if (spectator[i][j+noTickets] == null) {
						availableFirstSeat[0] = i;
						availableFirstSeat[1] = j;
						return availableFirstSeat;
					}
				}
			}
		}
		return null;
	}
	
	@Override
	Ticket[] buyTicket(int noTickets) {
		return null;
	}
	
	@Override
	Ticket[] buyTicket(String[] names) {
		int[] available = findAvailable(names.length);
		if(available == null)
			return null;
		
		Ticket[] boughtTickets = new Ticket[names.length];
		
		for (int i = 0; i < names.length ; i++) {
			int row = available[0];
			int column = (available[1]+i);
			Ticket newTicket = new SittingTicket(getStadiumName(), getPrice(), row, column);
			spectator[row][column] = "T";
			boughtTickets[i] = newTicket;
		}
		
		return boughtTickets;
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
