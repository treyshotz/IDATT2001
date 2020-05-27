public class Stand extends Tribune {
	
	private int noSoldTickets;
	
	public Stand(String stadiumName, int capacity, int price) {
		super(stadiumName, capacity, price);
		this.noSoldTickets = 0;
	}
	
	public int getNoSoldTickets() {
		return noSoldTickets;
	}
	
	@Override
	int findIncome() {
		return getNoSoldTickets()*getPrice();
	}
	
	@Override
	int findNumberOfSoldTickets() {
		return getNoSoldTickets();
	}
	
	@Override
	Ticket[] buyTicket(int noTickets) {
		if(noTickets > (getCapacity()-getNoSoldTickets()))
			return null;
		
		Ticket[] boughtTickets = new Ticket[noTickets];
		
		for (int i = 0; i < noTickets; i++) {
			Ticket newTicket = new StandingTicket(getStadiumName(), getPrice());
			boughtTickets[i] = newTicket;
			noSoldTickets += 1;
		}
		
		return boughtTickets;
	}
	
	@Override
	Ticket[] buyTicket(String[] names) {
		return buyTicket(names.length);
	}
}
