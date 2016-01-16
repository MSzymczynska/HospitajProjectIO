package projekt;

/**
 * Created by dustpuppy on 2015-12-06.
 */
public class MealFeature {
    String name;
    
    public MealFeature(String name) {
    	this.name = name;
    }

    public MealFeature() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
        return name;
    }
    
    public static int getIdByName(String s) {
    	if(s.contains("dla diabetyk�w")) return 1;
    	if(s.contains("bez glutenu")) return 2;
    	if(s.contains("dla dzieci")) return 3;
    	if(s.contains("dla sercowc�w")) return 4;
    	if(s.contains("dla ci�arnych")) return 5;
    	return 0;
    }
    
    public static MealFeature getById(int id) {
    	if(id == 1) return new MealFeature("dla diabetyk�w");
    	if(id == 2) return new MealFeature("bez glutenu");
    	if(id == 3) return new MealFeature("dla dzieci");
    	if(id == 4) return new MealFeature("dla sercowc�w");
    	if(id == 5) return new MealFeature("dla ci�arnych");
    	return new MealFeature("dla wszystkich");
    }
    
    @Override 
    public String toString() {
    	return this.name;
    }
}
