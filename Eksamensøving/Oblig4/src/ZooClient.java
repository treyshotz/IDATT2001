
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
		
		
		animals.stream().filter(animal -> animal instanceof Flyable).forEach(animal -> ((Flyable) animal).fly());
		animals.stream().filter(animal -> animal instanceof Jumpable).forEach(animal -> ((Jumpable) animal).jump());
		
		try {
			List<Object> walker = zoo.getAnimals().stream().filter(animal -> animal instanceof Walkable).collect(Collectors.toList());
			walker.stream().forEach(animal -> {
				((Flyable) animal).fly();
			});
		} catch (Exception e) {
			throw new ZooException("Fuck uuu");
		}
	}
	
	public static class ZooException extends Exception {
		public ZooException(String errorMessage) {
			super(errorMessage);
		}
	}
}