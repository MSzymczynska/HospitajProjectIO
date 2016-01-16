package projekt;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

public class ListsOperations {
	
	public static List<ProductQuantity> eraseMedicine(List<ProductQuantity> products) {
		
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
	
	public static Product[] productsToProductArray() {
		List<ProductQuantity> products = DatabaseConnectionKuchnia.getProductQuantites();
		products = eraseMedicine(products);
		List<Product> l = new ArrayList();
		for(int i=0; i<products.size(); i++) {
			l.add(products.get(i).getProduct());
		}
		return l.toArray(new Product[l.size()]);
	}
	
	
	public static int getProductIdByName(String s) {
    	List<Product> p = DatabaseConnectionKuchnia.getProducts();
    	for(int i=0; i<p.size(); i++) {
    		if(p.get(i).getName().contains(s)) {
    			return p.get(i).getId();
    		}
    	}
    	return 0;
    }
	
	public static Product getProductById(int id) {
		List<Product> p = DatabaseConnectionKuchnia.getProducts();
    	for(int i=0; i<p.size(); i++) {
    		if(p.get(i).getId() == id) {
    			return p.get(i);
    		}
    	}
    	return null;
	}
	
		
	public static String generateMealId() {
		List<Recipe> recipes = DatabaseConnectionKuchnia.getRecipes();
		String s = recipes.get(recipes.size()-1).getDescription();
		System.out.println("ost id: " + s);
		String sub = s.substring(s.length()-2, s.length());
		System.out.println("sub: " + sub);
		Integer i = Integer.parseInt(sub) +1;
		StringBuilder sb = new StringBuilder();
		sb.append("MEA");
		int len = i.toString().length() + 3;
		for(int j=len; j<8; j++) {
			sb.append("0");
		}
		sb.append(i.toString());
		
		return sb.toString();
	}
	


}
