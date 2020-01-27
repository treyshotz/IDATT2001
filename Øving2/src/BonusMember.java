import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class BonusMember {

    private final int memberNo;
    private final Personals personals;
    private final LocalDate enrolledDate;
    private int point = 0;


    public BonusMember(int memberNo, Personals personals, LocalDate enrolledDate, int point) {
        this.memberNo = memberNo;
        this.personals = personals;
        this.enrolledDate = enrolledDate;
        this.point = point;
    }


    /**
     * Takes input and checks if the user has been member for more than a year
     * @param inputDate, int
     * @return 0 if the user has been registered for more than a year or the number of points if he has been a member for less than a year
     */
    public int findQualificationPoints(LocalDate inputDate) {

        int antall_dager = (int) (Period.between(inputDate, enrolledDate).getMonths() * 30.416668 + Period.between(inputDate, enrolledDate).getDays());
        int antall_år = Period.between(inputDate, enrolledDate).getYears();

        if(antall_år < 0 && antall_dager+365 > 0) {
            return 0;
        }
return getPoints();
    }

 public boolean okPassword(String password) {
        return okPassword(password);
 }

 public void registerPoints(int points) {
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
}
