package by.tarlikovski.freelance.bean;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        OrderProperty that = (OrderProperty) o;
        return getOrderId() == that.getOrderId() &&
                getCategoryId() == that.getCategoryId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getOrderId(), getCategoryId());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderProperty{");
        sb.append("orderId=").append(orderId);
        sb.append(", categoryId=").append(categoryId);
        sb.append('}');
        return sb.toString();
    }
}
