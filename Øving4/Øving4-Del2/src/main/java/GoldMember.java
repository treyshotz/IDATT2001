import java.time.LocalDate;

public class GoldMember extends BonusMember {
    public GoldMember(int memberNo, Personals personals, LocalDate enrolledDate, int point) {
        super(memberNo, personals, enrolledDate, point);
    }

    @Override
    public void registerPoints(int points) {
        super.registerPoints((int) (points*1.5));
    }
}
