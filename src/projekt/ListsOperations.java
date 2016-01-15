package projekt;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

public class ListsOperations {
	
	public List<ProductQuantity> eraseMedicine(List<ProductQuantity> products) {
		
		for(int i=0; i<products.size(); i++) {
			Product p = products.get(i).getProduct();
			if(p.getName().equals("Ibuprom") ||
					p.getName().equals("Apap") ||
					p.getName().equals("Herbapect") ||
					p.getName().equals("Fenistil") ||
					p.getName().equals("Altacet") ||
					p.getProducer().contains("Ingridient") ||
					products.get(i).getQuantity() == 0) {
				products.remove(products.get(i));
				i = 0;
			}
		}
		
		return products;
	}
	
	public List<ProductQuantity> getByDate(List<ProductQuantity> products) {
		List<ProductQuantity> returned = products;
		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 14); 
		date = cal.getTime();
		
		for(int i=0; i<returned.size(); i++) {
			Product p = returned.get(i).getProduct();
			if(p.getExpirationDate().compareTo(date) > 0) {
				returned.remove(returned.get(i));
				i = 0;
			}
		}
		return returned;
	}
	
	public List<ProductQuantity> getSearched(String searched) {
		List<ProductQuantity> products = DatabaseConnectionKuchnia.getProductQuantites();
		products = eraseMedicine(products);
		List<ProductQuantity> returned = new ArrayList<ProductQuantity>();
		
		for(int i=0; i<products.size(); i++) {
			ProductQuantity pq = products.get(i);
			if(pq.getProduct().getName().toLowerCase().contains(searched) || 
					pq.getProduct().getProducer().contains(searched)) {
				returned.add(pq);
			}
		}
		
		return returned;
	}

}
