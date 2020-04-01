import java.time.LocalDate;
import java.time.Period;

public abstract class BonusMember {

    private final int memberNo;
    private final Personals personals;
    private final LocalDate enrolledDate;
    private int point = 0;

    /**
     * Creates a Bonusmember with given input
     * Throws a Illegargument if some of the input is wrong
     *
     * @param memberNo
     * @param personals
     * @param enrolledDate
     * @param point
     */
    public BonusMember(int memberNo, Personals personals, LocalDate enrolledDate, int point) {
        if(memberNo<=0) throw new IllegalArgumentException("Cannot have negative membernumber");

        if(personals == null || enrolledDate == null) throw new IllegalArgumentException("Personals or enrolledDate was null");

        this.memberNo = memberNo;
        this.personals = personals;
        this.enrolledDate = enrolledDate;
        this.point = point;
    }


    /**
     * Takes input and checks if the user has been member for more than a year
     * Throws IllegalArgurment becayse the inputDate is an argument
     *
     * @param inputDate, int
     * @return 0 if the user has been registered for more than a year or the number of points if he has been a member for less than a year
     */
    public int findQualificationPoints(LocalDate inputDate) {
        if(inputDate == null) {
            throw new IllegalArgumentException("Inputdate cannot be null");
        }

        int antall_dager = (int) (Period.between(inputDate, enrolledDate).getMonths() * 30.416668 + Period.between(inputDate, enrolledDate).getDays());
        int antall_år = Period.between(inputDate, enrolledDate).getYears();

        //Endre antall år og dager til positivt
        if(antall_år < 0 && antall_dager+365 > 0) {
            return 0;
        }
        return getPoints();
    }

    /**
     * Checks if the input password is correct
     * Throws IllegalArgument if the password is blank.
     * The exception was chosen since the passsword in this case
     *
     * @param password that will be checked
     * @return true or false
     */
 public boolean okPassword(String password) {
        if(password.isEmpty() || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be blank");
        }
        return okPassword(password);
 }


    /**
     * Registers points on a bonusmember
     * Throws IllegalArgument as the points cannot be negative
     *
     * @param points
     */
 public void registerPoints(int points) {
     if(points < 0 ) {
         throw new IllegalArgumentException("Points cannot be negative");
     }

        this.point += points;
 }

    public int getMemberNo() {
        return memberNo;
    }

    public Personals getPersonals() {
        return personals;
    }

    public LocalDate getEnrolledDate() {
        return enrolledDate;
    }

    public int getPoints() {
        return point;
    }

    @Override
    public String toString() {
        return ("NO: " + this.getMemberNo() +
                "\nPoints: " + this.getPoints() +
                "\nPersonals: "+ this.getPersonals());
    }
}
