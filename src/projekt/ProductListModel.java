package projekt;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class ProductListModel extends AbstractListModel implements ComboBoxModel {
	
	Product[] objects = null;
	Product selection = null;

	@Override
	public Product getElementAt(int index) {
		return objects[index];
	}

	@Override
	public int getSize() {
		return objects.length;
	}

	@Override
	public Product getSelectedItem() {
		return selection;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selection = (Product)anItem;
		
	}
	
	public void setProducts(Product[] objects) {
		this.objects = objects;
	}

}
