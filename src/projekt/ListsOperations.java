package projekt;

import java.util.Date;
import java.util.List;
import java.util.Calendar;

public class ListsOperations {
	
	public static List<Product> eraseMedicine(List<Product> products) {
		
		for(int i=0; i<products.size(); i++) {
			Product p = products.get(i);
			if(p.getName().equals("Ibuprom") ||
					p.getName().equals("Apap") ||
					p.getName().equals("Herbapect") ||
					p.getName().equals("Fenistil") ||
					p.getName().equals("Altacet") ||
					p.getProducer().contains("Ingridient")) {
				products.remove(p);
				i = 0;
			}
		}
		
		return products;
	}
	
	public List<Product> getByDate(List<Product> products) {
		List<Product> temp = products;
		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 14); 
		date = cal.getTime();
		
		for(int i=0; i<temp.size(); i++) {
			Product p = temp.get(i);
			if(p.getExpirationDate().compareTo(date) > 0) {
				temp.remove(p);
				i = 0;
			}
		}
		return temp;
	}

}
