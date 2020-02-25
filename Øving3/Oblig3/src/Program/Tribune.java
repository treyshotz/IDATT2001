package Program;

import java.util.ArrayList;

abstract class Tribune {

    private final String tribuneName;
    private final int capacity;
    private final int price;

    public Tribune(String tribuneName, int capacity, int price) {
        this.tribuneName = tribuneName;
        this.capacity = capacity;
        this.price = price;
    }

    abstract int findNumberOfSoldTickets();

    abstract int findIncome();

    abstract ArrayList<Ticket> buyTicket(int noOfTickets);

    abstract ArrayList<Ticket> buyTicket(ArrayList<String> names);

    public String getTribuneName() {
        return tribuneName;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return ("\nNavn: " + getTribuneName() + "\nKapasitet: "+ getCapacity() + "\nSolgte biletter: " + findNumberOfSoldTickets() + "\nInntekt: " + findIncome());
    }
}

