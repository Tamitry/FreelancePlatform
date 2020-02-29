package by.tarlikovski.freelance.bean;

import java.util.Objects;

public class Work extends Entity {
    private byte grade;
    private Order order;
    private User user;
    private Status status;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Work work = (Work) o;
        return getGrade() == work.getGrade() &&
                Objects.equals(getOrder(), work.getOrder()) &&
                Objects.equals(getUser(), work.getUser()) &&
                getStatus() == work.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGrade(), getOrder(), getUser(), getStatus());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Work{");
        sb.append("grade=").append(grade);
        sb.append(", order=").append(order);
        sb.append(", user=").append(user);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
