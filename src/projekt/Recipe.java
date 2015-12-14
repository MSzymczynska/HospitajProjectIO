package projekt;
import java.util.ArrayList;

/**
 * Created by dustpuppy on 2015-12-06.
 */
public class Recipe {
    String name;
    Integer calorificValue;
    ArrayList<MealFeature> mealFeatures = new ArrayList<>();
    ArrayList<ProductQuantity> products = new ArrayList<>();
    String description;


    public String getName() {
        return name;
    }

    public Integer getCalorificValue() {
        return calorificValue;
    }

    public ArrayList<MealFeature> getMealFeatures() {
        return mealFeatures;
    }

    public String getDescription() {
        return description;
    }

    public Recipe getRecipe() {
        return this;
    }

    public ArrayList<ProductQuantity> getProducts() {
        return products;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalorificValue(Integer calorificValue) {
        this.calorificValue = calorificValue;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addProduct(ProductQuantity newProduct) {
        products.add(newProduct);
    }

    public void removeProduct(ProductQuantity oldProduct) {
        products.remove(oldProduct);
    }

    public void addMealFeature(MealFeature newMealFeature) {
        mealFeatures.add(newMealFeature);
    }

    public void removeMealFeature(MealFeature oldMealFeature) {
        mealFeatures.remove(oldMealFeature);
    }
   public String[] getFeatures() {
	   String[] list = new String[5];
	   for(int i=0; i<this.getMealFeatures().size(); i++) {
		   list[i] = this.getMealFeatures().get(i).getName();
	   }
	   return list;
   }
   
   public String[] getProductArray() {
	   String[] list = new String[100];
	   for(int i=0; i<this.getProducts().size(); i++) {
		   String s = this.getProducts().get(i).getProduct().getName() + ": " + this.getProducts().get(i).getQuantity().toString();
		   list[i] = s;
	   }
	   return list;
   }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ProductQuantity product : products) {
            sb.append(product.toString());
        }
        return sb.toString();
    }
}
