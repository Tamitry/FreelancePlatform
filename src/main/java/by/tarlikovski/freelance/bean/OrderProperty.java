package by.tarlikovski.freelance.bean;

public class OrderProperty extends Entity {
    private int orderId;
    private int CategoryId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(final int ordId) {
        this.orderId = ordId;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(final int catId) {
        CategoryId = catId;
    }
}
