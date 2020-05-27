abstract class Tribune {
	
	private final String stadiumName;
	private final int capacity;
	private final int price;
	
	public Tribune(String stadiumName, int capacity, int price) {
		this.stadiumName = stadiumName;
		this.capacity = capacity;
		this.price = price;
	}
	
	abstract int findIncome();
	
	abstract int findNumberOfSoldTickets();
	
	abstract Ticket[] buyTicket(int noTickets);
	
	abstract Ticket[] buyTicket(String[] names);
	
	public String getStadiumName() {
		return stadiumName;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public int getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return "Tribune{" +
				"stadiumName='" + stadiumName + '\'' +
				", capacity=" + capacity +
				", price=" + price +
				'}';
	}
}
