import java.util.HashMap;

public class Task1A {
	
	public static void main(String[] args) {
		HashMap<String, String> hmCountry = new HashMap<String, String>();
		hmCountry.put("ES", "Spain");
		hmCountry.put("DE", "Germany");
		hmCountry.put("FR", "France");
		hmCountry.put("CN", "China");
		hmCountry.put("US", "United States");
		hmCountry.put("RU", "Russia");
		hmCountry.put("GB", "United Kingdom");
		hmCountry.put("IN", "India");
		hmCountry.put("BR", "Brazil");
		hmCountry.put("SA", "South Africa");
		
		System.out.println(hmCountry.size());
		
		for (String i : hmCountry.keySet()) {
			System.out.println("Key: " + i + ", Value: " + hmCountry.get(i));
		}
		
		System.out.println(hmCountry.get("CN"));
		
		System.out.println(hmCountry.remove("GB"));
		
		HashMap<String, String> hmCountry2 = new HashMap<String, String>(hmCountry);
		hmCountry.clear();
		
		System.out.println(hmCountry);
		System.out.println(hmCountry2);
	}
}
