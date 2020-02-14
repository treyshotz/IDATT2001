import java.util.ArrayList;

public class Stand extends Tribune {

    private int noSoldTickets = 0;

    public Stand(String tribuneName, int capacity, int price) {
        super(tribuneName, capacity, price);
    }

    @Override
    int findNumberOfSoldTickets() {
        return noSoldTickets;
    }

    @Override
    int findIncome() {
        return noSoldTickets*getPrice();
    }

    @Override
    ArrayList<Ticket> buyTicket(int noOfTickets) {
        ArrayList<Ticket> boughtTickets = new ArrayList<>();
        int available = getCapacity();
        if(noOfTickets < available) {
            for(int i = 0; i < noOfTickets; i++) {
                Ticket t = new StandingTicket(getTribuneName(), getPrice());
                boughtTickets.add(t);
                noSoldTickets++;
            }
            return boughtTickets;
        }
        else return null;
    }

    @Override
    ArrayList<Ticket> buyTicket(ArrayList<String> names) {
        return buyTicket(names.size());
    }


}
