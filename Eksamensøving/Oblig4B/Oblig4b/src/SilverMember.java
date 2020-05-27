import java.time.LocalDate;

public class SilverMember extends BonusMember {
	public SilverMember(int memberNo, Personals personals, LocalDate enrolledDate, int points) {
		super(memberNo, personals, enrolledDate, points);
	}
	
	@Override
	public void registerPoints(int points) {
		points = (int) (points*FACTOR_SILVER);
		super.registerPoints(points);
	}
}
