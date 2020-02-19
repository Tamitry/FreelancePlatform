package by.tarlikovski.freelance.bean;

public class OrderProperty extends Entity {
    private int orderId;
    private int categoryId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(final int ordId) {
        this.orderId = ordId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(final int catId) {
        categoryId = catId;
    }
}
