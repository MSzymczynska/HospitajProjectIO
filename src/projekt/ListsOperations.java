package projekt;

import java.util.List;

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
	
	

}
