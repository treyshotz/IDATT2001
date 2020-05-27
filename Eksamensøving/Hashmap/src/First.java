import java.util.HashMap;

public class First {
	
	public static void main(String[] args) {
		HashMap<String, String> capitalCities = new HashMap<String, String>();
		
		capitalCities.put("England", "London");
		capitalCities.put("Denmark", "Copenhagen");
		capitalCities.put("Norway", "Oslo");
		capitalCities.put("USA", "Washington");
		
		System.out.println(capitalCities);
		
		capitalCities.putIfAbsent("Sweden", "Stockholm");
		
		System.out.println(capitalCities);
		
		capitalCities.remove("England");
		
		System.out.println(capitalCities);
		
		capitalCities.put("Norway", "Trondheim");
		
		System.out.println(capitalCities);
		
		capitalCities.remove("Norway");
		
		System.out.println(capitalCities);
		
		System.out.println(capitalCities.get("USA"));
		

		
		System.out.println(capitalCities);
	}
}
