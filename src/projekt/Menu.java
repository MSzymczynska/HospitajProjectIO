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
	
		
	public void displayMenu() {
		// todo:
	}

}
