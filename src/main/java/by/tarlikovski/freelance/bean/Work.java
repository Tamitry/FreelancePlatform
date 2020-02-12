package by.tarlikovski.freelance.bean;

public class Work extends Entity {
    private byte grade;
    private Order order;
    private User user;

    public byte getGrade() {
        return grade;
    }

    public void setGrade(final byte gr) {
        this.grade = gr;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(final Order ord) {
        this.order = ord;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User us) {
        this.user = us;
    }
}
