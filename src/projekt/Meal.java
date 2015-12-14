package projekt;

/**
 * Created by dustpuppy on 2015-12-06.
 */
public class Meal extends Recipe{
    Integer numberOfOrders;
    Integer numberOfReceived;
    Integer numberOfMade;


    public Meal() {
        this.numberOfOrders = 0;
        this.numberOfReceived = 0;
        this.numberOfMade = 0;
    }

    public Meal(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public Integer getNumberOfOrders() {
        return numberOfOrders;
    }

    public Integer getNumberOfReceived() {
        return numberOfReceived;
    }

    public Integer getNumberOfMade() {
        return numberOfMade;
    }

    public void setNumberOfMade(Integer numberOfMade) {
        this.numberOfMade = numberOfMade;
    }

    public void setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public void setNumberOfReceived(Integer numberOfReceived) {
        this.numberOfReceived = numberOfReceived;
    }

    void addOrder() {
        this.setNumberOfOrders(getNumberOfOrders()+1);
    }

    void addReceived() {
        this.setNumberOfReceived(getNumberOfReceived()+1);
    }

    void addMade() {
        this.setNumberOfMade(getNumberOfMade()+1);
    }

    void makeMeal() {
        // todo: usuwanie wykorzystanych zasobow z panelu

        addMade();
    }
}
