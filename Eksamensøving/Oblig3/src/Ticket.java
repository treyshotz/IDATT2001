/**
 * Klassen Ticket med subklasser
 * Denne blir delt ut sammen med øvingen
 */
abstract class Ticket {
	private final String tribuneName;
	private final int price;
	
	/**
	 * Konstruktør:
	 * Tribunenavn må oppgis. Ingen krav til pris.
	 */
	public Ticket(String tribuneName, int price) {
		if (tribuneName == null || tribuneName.trim().equals("")) {
			throw new IllegalArgumentException("Tribunenavn må oppgis.");
		}
		this.tribuneName = tribuneName.trim();
		this.price = price;
	}
	
	public String getTribune() {
		return tribuneName;
	}
	
	public int getPris() {
		return price;
	}
	
	public String toString() {
		return "Tribune: " + tribuneName + " Pris: " + price;
	}
}

/**
 * STandTicket.
 */
class StandingTicket extends Ticket {
	public StandingTicket(String tribuneName, int price) {
		super(tribuneName, price);
	}
}

/**
 * Sitteplassbilletter. Rad og plass på raden må oppgis.
 */
class SittingTicket extends Ticket {
	private final int row;
	private final int place;
	
	public SittingTicket(String tribuneName, int price, int row, int place) {
		super(tribuneName, price);
		if (row < 0 || place < 0) {
			throw new IllegalArgumentException("Verken rad eller plass kan være negativ.\n"
					+ "Oppgitte verdier: " + row +
					", " + place);
		}
		this.row = row;
		this.place = place;
	}
	
	public int getRad() {
		return row;
	}
	
	public int getPlass() {
		return place;
	}
	
	public String toString() {
		String res = super.toString();
		res += " Rad: " + row + " Plass: " + place;
		return res;
	}
}