package projekt;

import java.util.ArrayList;

/**
 * Created by dustpuppy on 2015-12-06.
 */
public class KitchenPanel {
    private static KitchenPanel instance;
    ArrayList<ProductQuantity> resources = new ArrayList<ProductQuantity>();
    ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
    Menu menu;
    
    
    public static KitchenPanel getInstance() {
        if(instance == null) {
            instance = new KitchenPanel();
        }
        return instance;
    }
    
    public void generateMenu(ArrayList<Recipe> recipes) {
		// todo:
	}

    public void addRecipe(Recipe r) {
        recipeList.add(r);
    }

    public void removeRecipe(Recipe r) {
        recipeList.remove(r);
    }

    public void addProductQuantity(ProductQuantity p) {
        resources.add(p);
    }

    public void removeProductQuantity(ProductQuantity p) {
        resources.remove(p);
    }

    public void updateResources() {
        // todo: to bedzie dzialalo z baza?
    }

    public ArrayList<ProductQuantity> getResources() {
        return resources;
    }
    
    public ArrayList<Recipe> getRecipeList() {
    	return recipeList;
    }
}
