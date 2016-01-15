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
    	if(s.contains("dla diabetyków")) return 1;
    	if(s.contains("bez glutenu")) return 2;
    	if(s.contains("dla dzieci")) return 3;
    	if(s.contains("dla sercowców")) return 4;
    	if(s.contains("dla ciężarnych")) return 5;
    	return 0;
    }
}
