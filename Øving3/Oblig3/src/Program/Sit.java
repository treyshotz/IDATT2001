package Program;

import java.util.*;

public class Sit extends Tribune{

    private ArrayList<Integer> noBusy;

    public Sit(String tribuneName, int capacity, int price) {
        super(tribuneName, capacity, price);
    }

    private int getRows() {
        int capacity = getCapacity();

        int a = -1;
        int b = -1;
        for(int i = 2; i*i <= capacity; i++) {
            if(capacity % i == 0) {
                a = i;
                b = capacity/i;
            }
        }
        return a;
    }

    private int getColumns() {
        int capacity = getCapacity();

        int a = -1;
        int b = -1;
        for(int i = 2; i*i <= capacity; i++) {
            if(capacity % i == 0) {
                a = i;
                b = capacity/i;
            }
        }
        return b;
    }

    @Override
    int findNumberOfSoldTickets() {
        return noBusy.stream().mapToInt(a -> a).sum();
    }

    @Override
    int findIncome() {
        return noBusy.stream().mapToInt(a -> a).sum()*getPrice();
    }

    @Override
    ArrayList<Ticket> buyTicket(int noOfTickets) {
        ArrayList<Ticket> boughtTickets = new ArrayList<>();
        int a = getRows();
        int b = getColumns();

        for(int i = 0; i < a; i++) {
            if(noOfTickets <= (b-noBusy.get(i))) {
                for(int j = 0; j < noOfTickets; j++) {
                    Ticket t = new SittingTicket(getTribuneName(), getPrice(), a, b);
                    boughtTickets.add(t);
                }
                noBusy.set(i, noOfTickets);
                return boughtTickets;
            }
        }
        return null;
    }

    @Override
    ArrayList<Ticket> buyTicket(ArrayList<String> names) {
        return buyTicket(names.size());
    }
}
