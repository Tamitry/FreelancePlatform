package by.tarlikovski.freelance.bean;

import java.sql.Timestamp;
import java.util.Objects;

public class Order extends Entity {
    private String orderName;
    private Timestamp orderRegDate;
    private Timestamp orderDeadLine;
    private String description;
    private User client;
//TODO Показываюься заказы забанненых пользователей
    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(final String name) {
        this.orderName = name;
    }

    public Timestamp getOrderRegDate() {
        return orderRegDate;
    }

    public void setOrderRegDate(final Timestamp regDate) {
        this.orderRegDate = regDate;
    }

    public Timestamp getOrderDeadLine() {
        return orderDeadLine;
    }

    public void setOrderDeadLine(final Timestamp deadline) {
        this.orderDeadLine = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String desc) {
        this.description = desc;
    }

    public User getClient() {
        return client;
    }

    public void setClient(final User client) {
        this.client = client;
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
        Order order = (Order) o;
        return getOrderName().equals(order.getOrderName()) &&
                Objects.equals(getOrderRegDate(), order.getOrderRegDate()) &&
                getOrderDeadLine().equals(order.getOrderDeadLine()) &&
                Objects.equals(getDescription(), order.getDescription()) &&
                getClient().equals(order.getClient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderName(), getOrderRegDate(), getOrderDeadLine(), getDescription(), getClient());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderName='").append(orderName).append('\'');
        sb.append(", orderRegDate=").append(orderRegDate);
        sb.append(", orderDeadLine=").append(orderDeadLine);
        sb.append(", description='").append(description).append('\'');
        sb.append(", client=").append(client);
        sb.append('}');
        return sb.toString();
    }
}
