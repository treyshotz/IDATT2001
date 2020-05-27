import java.awt.image.AreaAveragingScaleFilter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.IllformedLocaleException;
import java.util.Random;

public class MemberArchive {
	private final int GOLD_LIMIT = 75000;
	private final int SILVER_LIMIT = 25000;
	private final int MAX_TRY = 3;
	private final Random RANDOM_NUMBER = new Random();
	
	private ArrayList<BonusMember> members = new ArrayList<>();
	
	public MemberArchive() {
	}
	
	/**
	 * Creates a new member with given information
	 * @param personals
	 * @param date
	 * @return memberNo
	 */
	public int newMember(Personals personals, LocalDate date) {
		int memberNo = findAvailableNo();
		BonusMember member = new BasicMember(memberNo, personals, date);
		members.add(member);
		return memberNo;
	}
	
	/**
	 * Find points by using qualification method in bonusmember class
	 * @param memberNo
	 * @param password
	 * @return points found
	 */
	public int findPoints(int memberNo, String password) {
		LocalDate date = LocalDate.now();
		
		BonusMember member = findMember(memberNo);
		
		if (member == null)
			throw new IllegalArgumentException("Member not found");
		
		if (!(member.okPassword(password)))
			throw new IllegalArgumentException("Wrong password!");
		
		return member.findQualificationPoints(date);
	}
	
	/**
	 * Finds a member with the given member no
	 * @param memberNo
	 * @return member if found, returns null else
	 */
	private BonusMember findMember(int memberNo) {
		for (BonusMember member : members) {
			if (member.getMemberNo() == memberNo)
				return member;
		}
		return null;
	}
	
	/**
	 * Registers points on a member with given memberNo if found
	 * @param memberNo
	 * @param points
	 * @return returns true if it was a success
	 */
	public boolean registerPoints(int memberNo, int points) {
		BonusMember member = findMember(memberNo);
		
		if (member == null)
			throw new IllegalArgumentException("Member not found");
		
		if (points < 0)
			throw new IllegalArgumentException("Points cannot be under 0");
		
		member.registerPoints(points);
		return true;
	}
	
	/**
	 * Finds an available memberNo
	 * @return
	 */
	private int findAvailableNo() {
		//TODO: Maybe add a check that a number doesn't already exist even though it is almost impossible
		boolean notfound = true;
		int number = 0;
		number = RANDOM_NUMBER.nextInt(Integer.MAX_VALUE);
		
		return number;
	}
	
	//TODO:
	// The flow of the limit methods are pretty bad because the task required them to return bonusmember
	// To reduce extra work and keeping tasks split apart the limit methods therefore upgrade the member
	// It might be an idea to refactor the checkMembers and limit methods to have a better flow
	// The limit methods also use qualification method instead of find points....
	
	/**
	 * Checks the silver limit on a bonusmember with matching memberNO
	 * @param memberNo
	 * @param date
	 * @return upgraded member
	 */
	private BonusMember checkSilverLimit(int memberNo, LocalDate date) {
		BonusMember member = findMember(memberNo);
		
		if (member == null)
			throw new IllegalArgumentException("Could not find member");
		
		int points = member.findQualificationPoints(date);
		
		if (points > SILVER_LIMIT) {
			BonusMember sMember = new SilverMember(memberNo, member.getPersonals(), date, points);
			return sMember;
		}
		
		return null;
	}
	
	/**
	 * Checks the silver limit on the given bonusmember and upgrades if "allowed"
	 * @param member
	 * @param date
	 * @return upgaded member if "allowed", null elsewise
	 */
	private BonusMember checkSilverLimit(BonusMember member, LocalDate date) {
		if (member == null)
			throw new IllegalArgumentException("Could not find member");
		
		int points = member.findQualificationPoints(date);
		
		if (points > SILVER_LIMIT) {
			SilverMember silverMember = new SilverMember(member.getMemberNo(), member.getPersonals(), date, points);
			return silverMember;
		}
		
		return null;
	}
	
	/**
	 * Checks the gold limit on a bonusmember with matching memberNo and upgrades if "allowed"
	 * @param memberNo
	 * @param date
	 * @return upgraded member if "allowed", null elsewise
	 */
	private BonusMember checkGoldLimit(int memberNo, LocalDate date) {
		BonusMember member = findMember(memberNo);
		
		if (member == null)
			throw new IllegalArgumentException("Could not find member");
		
		int points = member.findQualificationPoints(date);
		
		if (points > GOLD_LIMIT) {
			GoldMember goldMember = new GoldMember(memberNo, member.getPersonals(), date, points);
			return goldMember;
		}
		
		return null;
	}
	
	/**
	 * Checks the gold limit on given bonusmember and upgrades if "allowed"
	 * @param member
	 * @param date
	 * @return upgraded member if "allowed", null elsewise
	 */
	private BonusMember checkGoldLimit(BonusMember member, LocalDate date) {
		if (member == null)
			throw new IllegalArgumentException("Could not find member");
		
		int points = member.findQualificationPoints(date);

		if (points > GOLD_LIMIT) {
			GoldMember goldMember = new GoldMember(member.getMemberNo(), member.getPersonals(), date, points);
			return goldMember;
		}
		
		return null;
	}
	
	/**
	 * Checks through all members and uses limit methods to upgrade
	 * @param date
	 */
	public void checkMembers(LocalDate date) {
		//TODO: This flow is waaay to shitty.
		for (int i = 0; i < members.size(); i++) {
			BonusMember member = members.get(i);
			BonusMember newMember = checkGoldLimit(member, date);
			
			if (newMember instanceof GoldMember) {
				members.set(i, newMember);
				System.out.println(newMember.getPersonals().getFirstname() + " er nå " + newMember.getClass());
				break;
			}

			newMember = checkSilverLimit(member, date);
			if (newMember instanceof SilverMember) {
				members.set(i, newMember);
				System.out.println(newMember.getPersonals().getFirstname() + " er nå " + newMember.getClass());
			}
		}
	}
	
	public static void main(String[] args) {
		//Creates a new test personals
		Personals ole = new Personals("Olsen", "Ole", "ole.olsen@dot.com", "ole");
		Personals tove = new Personals("Hansen", "Tove", "tove.hansen@dot.com", "tove");
		
		//Creates a new instance of a memberArchive
		MemberArchive memberArchive = new MemberArchive();
		
		//Creates a test date, which is now
		LocalDate date = LocalDate.now();
		
		//Registers new bonusmembers
		int oleNr = memberArchive.newMember(ole, date);
		int toveNr = memberArchive.newMember(tove, date);
		
		//Registers points
		memberArchive.registerPoints(oleNr, 26000);
		memberArchive.registerPoints(toveNr, 76000);
		
		//Upgrades members. Ole should be upgraded to silver and Tove should be upgraded to Gold
		memberArchive.checkMembers(date);
	}
}

