import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class Patient implements Iterable<String> {
	
	private Collection<String> conditions;
	private int priority;
	
	public Patient() {
		this.conditions = new ArrayList<>();
	}
	
	public Patient(Collection<String> conditions) {
		this.conditions = conditions;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public boolean RequiresTreatment() {
		return !conditions.isEmpty();
	}
	
	public void addConditions(String... condition) {
		conditions.addAll(Arrays.asList(condition));
	}
	
	public Collection<String> getConditions() {
		return conditions;
	}
	
	public void removeConditions(Collection<String> conditions) {
		this.conditions.removeAll(conditions);
	}
	
	@Override
	public String toString() {
		return "Patient{" +
				"conditions=" + conditions +
				", priority=" + priority +
				'}';
	}
	
	@Override
	public Iterator<String> iterator() {
		return conditions.iterator();
	}
}
