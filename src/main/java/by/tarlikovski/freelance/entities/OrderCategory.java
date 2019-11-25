package by.tarlikovski.freelance.entities;

public class OrderCategory extends Entity {
    private int category;
    private int numOfPeople;
    private int price;
    private int orderId;

    public OrderCategory() {
    }

    public OrderCategory(final int cat,
                         final int workNumb) {
        this.category = cat;
        this.numOfPeople = workNumb;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(final int cat) {
        this.category = cat;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(final int numOfPpl) {
        this.numOfPeople = numOfPpl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int pr) {
        this.price = pr;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(final int ordId) {
        this.orderId = ordId;
    }
}
