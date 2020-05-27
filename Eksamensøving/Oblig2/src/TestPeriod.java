import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
/**
 * @author rouhani
 * I denne klassen tester vi 3 forskjellige måter å finne antall dager mellom to datoer
 * Vi ser at doTestPeriod er feil og kan ikke brukes for å finne denne differansen.
 */
public class TestPeriod {
	private void doTestPeriod(LocalDate date1, LocalDate date2) {
		int daysBetween = Period.between(date1, date2).getDays();
		System.out.println("Test med doTestPeriod: "+daysBetween);
	}
	private void doTestChronoUnit(LocalDate date1, LocalDate date2) {
		long daysBetween = ChronoUnit.DAYS.between(date1, date2);
		
		System.out.println	("Test med doTestChronoUnit: "+daysBetween);
	}
	
	private void doTestDays(LocalDate date1, LocalDate date2) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		
		Date d1 = Date.from(date1.atStartOfDay(defaultZoneId).toInstant());
		Date d2 = Date.from(date2.atStartOfDay(defaultZoneId).toInstant());
		
		long daysBetween=(d2.getTime()-d1.getTime())/86400000;
		
		System.out.println("Test med doTestDays: "+daysBetween);
	}
	
	
	public static void main(String[] args) {
		TestPeriod t = new TestPeriod();
		LocalDate date1;
		LocalDate date2;
		
		System.out.println("\nTest 1: OK");
		date1 = LocalDate.of(2020, 2, 5);
		date2 = LocalDate.of(2020, 2, 10);
		
		t.doTestPeriod(date1, date2);
		t.doTestChronoUnit(date1, date2);
		t.doTestDays(date1, date2);
		
		System.out.println("\nTest 2: Feil på doTestPeriod");
		date1 = LocalDate.of(2020, 2, 5);
		date2 = LocalDate.of(2020, 3, 10);
		

		t.doTestPeriod(date1, date2);
		t.doTestChronoUnit(date1, date2);
		t.doTestDays(date1, date2);
		
		
		System.out.println("\nTest 3: Feil på doTestPeriod");
		date1 = LocalDate.of(2019, 2, 5);
		date2 = LocalDate.of(2020, 2, 10);
		
		t.doTestPeriod(date1, date2);
		t.doTestChronoUnit(date1, date2);
		t.doTestDays(date1, date2);
	}
}