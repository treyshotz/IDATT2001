import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
public class ZooClient {
    public static void main(String[] args) throws ZooException {

        Zoo zoo = new Zoo("Kristiansand Dyrepark");

        Collection<Animal> animals = new ArrayList<Animal>();

        animals.add(new Crocodile("Crocodylus niloticus", 1001));
        animals.add(new Crocodile("Crocodylus niloticus", 1002));
        animals.add(new Crocodile("Crocodylus porosus", 1101));
        animals.add(new Crocodile("Crocodylus porosus", 1102));
        animals.add(new Pelican("Brown Pelican  ", 4001));
        animals.add(new Pelican("Dalmatian Pelican  ", 4101));


        animals.add(new Whale("Blue whale", 2001));
        animals.add(new Whale("Blue whale", 2002));
        animals.add(new Whale("Minke whale", 2101));
        animals.add(new Whale("Minke whale", 2102));
        animals.add(new Bat("Acerodon ", 3001));
        animals.add(new Bat("Cistugo  ", 3002));

        zoo.setAnimals(animals);

        animals.stream()
                .filter(animal -> animal instanceof Flyable)
                .map(animal -> (Flyable) animal)
                .forEach(animal -> ((Flyable) animal).fly());

        animals.stream()
                .filter(animal -> animal instanceof Mammal)
                .map(animal -> (Mammal) animal)
                .filter(animal -> animal instanceof Jumpable)
                .map(animal -> (Jumpable) animal)
                .forEach(animal -> ((Jumpable) animal)
                        .jump());


                List<Object> walker = zoo.getAnimals().stream()
                .filter(p -> p instanceof  Walkable)
                .collect(Collectors.toList());

                try {
                    walker.stream()
                            .forEach(p -> ((Flyable) p)
                                    .fly());
                } catch (ClassCastException e) {
                    throw new ZooException("Not all walkable can jump");
                }

    }
}