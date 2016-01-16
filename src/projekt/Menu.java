package projekt;

public class Menu {
	Recipe[] breakfastMenu;
	Recipe[] lunchMenu;
	Recipe[] dinnerMenu;
	

	public Menu(int breakfast, int lunch, int dinner) {
        breakfastMenu = new Recipe[breakfast];
        lunchMenu = new Recipe[lunch];
        dinnerMenu = new Recipe[dinner];
    }
	
		
	public Menu(Recipe[] bf, Recipe[] lu, Recipe[] di) {
		this.breakfastMenu = bf;
		this.lunchMenu = lu;
		this.dinnerMenu = di;
	}


	public String getBreakfast() {
		return this.breakfastMenu[0].getName();
	}
	
	public String getLunch() {
		return this.lunchMenu[0].getName();
	}
	
	public String getDinner() {
		return this.dinnerMenu[0].getName();
	}
	
	
	public void setBreakfast(Recipe r) {
		this.breakfastMenu[0] = r;
	}
	
	public void setLunch(Recipe r) {
		this.lunchMenu[0] = r;
	}
	
	public void setDinner(Recipe r) {
		this.dinnerMenu[0] = r;
	}
}
