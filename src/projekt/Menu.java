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


	public void displayMenu() {
		// todo:
	}
	
	

}
