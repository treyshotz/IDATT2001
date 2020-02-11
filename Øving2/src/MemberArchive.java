import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class MemberArchive   {
    private final static int SILVER_LIMIT = 25000;
    private final static int GOLD_LIMIT = 75000;
    private final static int MAx_TRY = 3;
    private static Random r = new Random();
    private final static int RANDOM = r.nextInt();
    private ArrayList<BonusMember> register = new ArrayList<>();

    public MemberArchive() {
        this.register = register;
    }

    public int newMember(Personals p, LocalDate t) {
        int n = findAvailableNO();
        BonusMember m = new BasicMember(n, p, t);
        register.add(m);
        return n;
    }

    /**
     * Generates random number and checks if the number if taken
     * @return available member no
     */
    private int findAvailableNO() {
        boolean found = false;
        while(!found) {
            int n = r.nextInt();
            if(register.size() == 0) return n;
            for (BonusMember m : register) {
                if (n != m.getMemberNo()) {
                    found = true;
                    return n;
                }
                }
            }
        return 0;
        }

    /**
     * Finds a member with matching input
     * @param memberNo int
     * @param password string
     * @return points if found or -1 if not found
     */
    public int findPoints(int memberNo, String password) {
     for(BonusMember m : register) {
         if(m.okPassword(password) && m.getMemberNo() == memberNo) return m.getPoints();
     }
     return -1;
    }

    /**
     * Finds member with matching input and registers points if found
     * @param memberNO int
     * @param points int
     * @return true if member was found
     */
    public boolean registerPoints(int memberNO, int points) {
        for(BonusMember m : register) {
            if(m.getMemberNo() == memberNO) {
                m.registerPoints(points);
                return true;
            }
        }
        return false;
    }

    public void checkMembers(LocalDate t) {
        int index = 0;
        LocalDate d = LocalDate.now();
        for(BonusMember m : register) {
            if(m instanceof SilverMember) {
                if(checkGoldLimit(m.getMemberNo(), t) != null) {
                    BonusMember n = new GoldMember(m.getMemberNo(), m.getPersonals(), d, m.getPoints());
                    register.set(index, n);
                    System.out.println(n.getPersonals().getFirstname() + " ble oppgradert til Gullmedlem\n");
                }
            }

            else if(m instanceof BasicMember) {
                if (checkSilverLimit(m.getMemberNo(), t) != null) {
                    if(checkGoldLimit(m.getMemberNo(), t) != null) {
                        BonusMember M = new GoldMember(m.getMemberNo(), m.getPersonals(), d, m.getPoints());
                        register.set(index, M);
                        System.out.println(M.getPersonals().getFirstname() + " ble oppgradert fra basic til GULL\n");
                        break;
                    }
                    BonusMember N = new SilverMember(m.getMemberNo(), m.getPersonals(), d, m.getPoints());
                    register.set(index, N);
                    System.out.println(N.getPersonals().getFirstname() + " ble oppgradert til Sølvmedlem\n");
                }
            }
            index++;
        }
    }

    private BonusMember checkSilverLimit(int memberNO, LocalDate t) {
        BonusMember m = findMember(memberNO);
        if(m != null) {
            if (m.findQualificationPoints(t) > SILVER_LIMIT) {
                return m;
            }
        }
        return null;
    }

    private BonusMember checkGoldLimit(int memberNo, LocalDate t) {
        BonusMember m = findMember(memberNo);
        if(m != null) {
            if (m.findQualificationPoints(t) > GOLD_LIMIT) {
                return m;
            }
        }
        return null;
    }

    private BonusMember findMember(int memberNO) {
        for(BonusMember m : register) {
            if(m.getMemberNo() == memberNO) return m;
        }
        return null;
    }

    public ArrayList<BasicMember> basic() {
        ArrayList<BasicMember> b = new ArrayList<>();
        for(BonusMember m : register) {
            if(m instanceof BasicMember) b.add((BasicMember) m);
        }
        return b;
    }

    public ArrayList<SilverMember> silver() {
        ArrayList<SilverMember> s = new ArrayList<>();
        for(BonusMember m : register) {
            if(m instanceof SilverMember) s.add((SilverMember) m);
        }
        return s;
    }

    public ArrayList<GoldMember> gold() {
        ArrayList<GoldMember> g = new ArrayList<>();
        for (BonusMember m : register) {
            if(m instanceof GoldMember) g.add((GoldMember) m);
        }
        return g;
    }

    @Override
    public String toString() {
        return register.toString();
    }

    public static void main(String[] args) {
        //TESTING AV PROGRAM
        MemberArchive m = new MemberArchive();

        //Testdata
        Personals ole = new Personals("Olsen", "Ole", "ole.olsen@dot.com", "ole");
        Personals tove = new Personals("Hansen", "Tove", "tove.hansen@dot.com", "tove");
        LocalDate testdato = LocalDate.of(2008, 2, 10);


        System.out.println("Totalt antall tester: 2");
        //BasicMember b1 = new BasicMember(100, ole, LocalDate.of(2006, 2, 15));

        //Registrerer poeng og og bruker med new user metoden
        m.registerPoints(m.newMember(ole, testdato), 30000);


        //BonusMember b2 = new SilverMember(110, tove, LocalDate.of(2007, 3, 5), 760000);

        //Registrer poeng og bruker med new user metoden
        m.registerPoints(m.newMember(tove, testdato), 76000);

        //Skriver ut de to brukerene som er opprettet
        System.out.println(m.toString());

        //Foretar en sjekk om programmet kan oppgradere noen av brukerene
        //Her skal begge oppgraderes til sølvmedlem fordi begge er innenfor kravet
        //Metoden vil skrive ut hvilke brukere som oppgraderes
        m.checkMembers(testdato);

        //Skriver ut brukerene på nytt for å se at de har blitt erstattet
        /*System.out.println("Basicmedlemmer: \n" +m.basic());
        System.out.println("Silvermedlemmer: \n" + m.silver());
        System.out.println("Goldmedlemmer: \n" + m.gold());*/
        if(m.silver().size() == 2 && m.basic().size() == 0 && m.gold().size() == 0) System.out.println("TEST 1 VELLYKKET");

        //Her skal Tove oppgraderes til Goldmedlem mens ole blir i sølv
        m.checkMembers(testdato);
        /*System.out.println("Basicmedlemmer: \n" +m.basic());
        System.out.println("Silvermedlemmer: \n" + m.silver());
        System.out.println("Goldmedlemmer: \n" + m.gold());*/
        if(m.gold().size() == 1 && m.silver().size() == 1 && m.basic().size() ==0) System.out.println("TEST 2 VELLYKKET");





    }
}
