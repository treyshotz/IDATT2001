import java.time.LocalDate;

public class BasicMember extends BonusMember {
    public BasicMember(int memberNo, Personals personals, LocalDate enrolledDate) {
        super(memberNo, personals, enrolledDate, 0);
    }

    @Override
    public void registerPoints(int points) {
        super.registerPoints(points);
    }
}
