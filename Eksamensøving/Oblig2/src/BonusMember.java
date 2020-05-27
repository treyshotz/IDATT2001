import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BonusMember {
	public final double FACTOR_GOLD = 1.5;
	public final double FACTOR_SILVER = 1.2;
	
	private int memberNo;
	private Personals personals;
	private LocalDate enrolledDate;
	private int points = 0;
	
	public BonusMember(int memberNo, Personals personals, LocalDate enrolledDate) {
		this.memberNo = memberNo;
		this.personals = personals;
		this.enrolledDate = enrolledDate;
	}
	
	public BonusMember(int memberNo, Personals personals, LocalDate enrolledDate, int points) {
		this.memberNo = memberNo;
		this.personals = personals;
		this.enrolledDate = enrolledDate;
		this.points = points;
	}
	
	public Personals getPersonals() {
		return personals;
	}
	
	public int getMemberNo() {
		return memberNo;
	}
	
	public LocalDate getEnrolledDate() {
		return enrolledDate;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int findQualificationPoints(LocalDate date) {
		int daysBetween = (int) ChronoUnit.DAYS.between(enrolledDate, date);
		
		if (daysBetween <= 365) {
			return this.points;
		}
		return 0;
	}
	
	public boolean okPassword(String password) {
		return personals.okPassword(password);
	}
	
	public void registerPoints(int points) {
		this.points += points;
	}
	
	public static void main(String[] args) {
	
	}
}
