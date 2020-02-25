public class Whale extends Mammal implements Jumpable, Swimable{

    public Whale(String name, int code) {
        super(name, code);
    }

    @Override
    public boolean jump() {
        return false;
    }

    @Override
    public boolean swim() {
        return false;
    }
}
