public class Crocodile extends Oviparous implements Walkable, Swimable {

    public Crocodile(String name, int code) {
        super(name, code);
    }

    @Override
    public boolean swim() {
        return false;
    }

    @Override
    public boolean walk() {
        return false;
    }
}
