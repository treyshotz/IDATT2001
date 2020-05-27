public class Pelican extends Oviparous implements Flyable, Walkable {
	public Pelican(String name, int code) {
		super(name, code);
	}
	
	@Override
	public boolean fly() {
		return true;
	}
	
	@Override
	public boolean walk() {
		return true;
	}
}
