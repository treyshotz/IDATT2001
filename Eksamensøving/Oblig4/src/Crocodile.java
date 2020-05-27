public class Crocodile extends Oviparous implements Swimmable, Walkable {
	public Crocodile(String name, int code) {
		super(name, code);
	}
	
	@Override
	public boolean swim() {
		return true;
	}
	
	@Override
	public boolean walk() {
		return true;
	}
}
