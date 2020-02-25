package Program;

import java.util.ArrayList;

public class VIP extends Tribune {

    private ArrayList<ArrayList<String>> spectator = new ArrayList<ArrayList<String>>();
    private boolean isArrayset = false;

    public VIP(String tribuneName, int capacity, int price) {
        super(tribuneName, capacity, price);
        if(!isArrayset) initiateArrayList();
    }


    private void initiateArrayList() {
        for(int i = 0; i < getRows(); i++) {
            spectator.add(i, new ArrayList<String>());
            for(int j = 0; j < getColumns(); j++) {
                //System.out.println(spectator.toString());
                //System.out.println("i:" + i + " | j:" + j);
                spectator.get(i).add(j, " ");
            }
        }
        isArrayset = true;
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
        int sold = 0;
        for(int i = 0; i < getRows(); i++) {
            for(int j = 0; j < getColumns(); j++) {
                if(!spectator.get(i).get(j).equals(" ")) {
                    sold++;
                }
            }
        }
        return sold;
    }

    @Override
    int findIncome() {
        return findNumberOfSoldTickets()*getPrice();
    }

    @Override
    ArrayList<Ticket> buyTicket(int noOfTickets) {
        return null;
    }
/*


    private ArrayList<Integer> getStartIndex(int size) {
        ArrayList<Integer> index = new ArrayList<>();
        int a = getRows();
        int b = getColumns();

        //System.out.println(spectator.toString());
        //System.out.println((spectator.get(0).get(0)));
        for(int i = 0; i <a; i++) {
            for(int j = 0; j < b; j++) {
                //System.out.println("i:" + i + "| j: " + j);
                if(spectator.get(i).get(j).isBlank() && size < rowAvailable(i)) {
                index.add(0, i);
                index.add(1, j);
                //System.out.println("Index" + index);
                return index;
                }
            }
        }
        return null;
    }

    private int rowAvailable(int i) {
    return (getColumns() - i);
    }
*/


    /*
    Buy ticket:
    Buy tickets skal ta imot en liste med navn.
    Den skal så sjekke at spectator arrayet har blitt iniert
    Den skal så hente start posisjon x og y for hvor den skal begynne å registrere kjøpere

    Metoden for å finne x og y:
    Den må ta imot antall biletter det er ønsket å kjøpe.
    Den må skal da begynne å se om det er nok ledige plasser på 0. linje
    Når den finner at det er nok ledig plass, skal den returnere et array med x og y posisjon til buyticket

    */

    private ArrayList<Integer> getStartIndex(int noOfTickets) {
        ArrayList<Integer> posistion = new ArrayList<>();
        int rowCapapcity = getRows();
        int colCapacity = getColumns();

        //Looper gjennom en og en rad hvor den sjekker hvert sete om det er ledig
        //og om det er nok plass til alle bilettene.
        for(int row = 0; row < rowCapapcity; row++) {
            for (int col = 0; col < colCapacity; col++) {
                if(spectator.get(row).get(col).isBlank() && (colCapacity - col) > noOfTickets) {
                    posistion.add(row);
                    posistion.add(col);
                    return posistion;
                }
            }
        }
        return posistion;
    }

    @Override
    ArrayList<Ticket> buyTicket(ArrayList<String> names) {
        if(!isArrayset) initiateArrayList();

        ArrayList<Ticket> boughTickets = new ArrayList<>();
        int noOfTickets = names.size();
        int rowPos = getStartIndex(noOfTickets).get(0);
        int colPos = getStartIndex(noOfTickets).get(1);

        for(String s : names) {
            spectator.get(rowPos).set(colPos, s);
            Ticket t = new SittingTicket(getTribuneName(), getPrice(), rowPos, colPos);
            boughTickets.add(t);
            colPos++;
        }
        return boughTickets;
    }

/*

    @Override
    ArrayList<Program.Ticket> buyTicket(ArrayList<String> names) {
        if(!isArrayset) initiateArrayList();

        ArrayList<Program.Ticket> boughtTickets = new ArrayList<>();
        int rowAvailable =0;
        int startIndexI = getStartIndex(names.size()).get(0);
        int startIndexJ = getStartIndex(names.size()).get(1);


    */
/*
        for(int i = 0; i < a; i++) {
            int rowAvailable = 0;
            int startindex = 0;
            for(int j = 0; j < b; j++) {
                if(spectator.get(i).get(j).isEmpty()) {
                    if(startindex != 0) {
                        startindex = j;
                    }
                 rowAvailable++;
                }
            }
            if (rowAvailable > names.size()) {
                for(int k = startindex; k < rowAvailable; k++) {
                    int n = 0;
                    spectator.get(i).set(k, names.get(n));
                    n++;
                    }
            }
        }
        return boughtTickets;
    }*//*


    for(int a = startIndexI; a < getRows(); a++) {
        rowAvailable = rowAvailable(a);
        System.out.println(rowAvailable);
        if ((rowAvailable > names.size())) {
            for (int i = 0; i < names.size(); i++) {
                System.out.println(spectator.toString());
                spectator.get(a).set(startIndexJ, names.get(i));
                Program.Ticket t = new Program.SittingTicket(getTribuneName(), getPrice(), a, i + 1);
                boughtTickets.add(t);
                startIndexJ++;
            }
        }
    }
        System.out.println(spectator.toString());
    return boughtTickets;
    }
*/
}
