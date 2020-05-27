import java.time.LocalDate;

public class GoldMember extends BonusMember {
	public GoldMember(int memberNo, Personals personals, LocalDate enrolledDate, int points) {
		super(memberNo, personals, enrolledDate, points);
	}
	
	@Override
	public void registerPoints(int points) {
		points = (int) (points*FACTOR_GOLD);
		super.registerPoints(points);
	}
}
