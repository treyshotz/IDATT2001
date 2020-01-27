import java.time.LocalDate;

public class SilverMember extends BonusMember {
    public SilverMember(int memberNo, Personals personals, LocalDate enrolledDate, int point) {
        super(memberNo, personals, enrolledDate, point);
    }

    @Override
    public void registerPoints(int points) {
        super.registerPoints((int) (points*1.2));
    }
}
