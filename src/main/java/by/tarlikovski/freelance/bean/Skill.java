package by.tarlikovski.freelance.bean;

import java.util.Objects;

public class Skill extends Entity {
    private int categoryId;
    private int userId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(final int catId) {
        this.categoryId = catId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int usId) {
        this.userId = usId;
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
        Skill skill = (Skill) o;
        return getCategoryId() == skill.getCategoryId() &&
                getUserId() == skill.getUserId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCategoryId(), getUserId());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Skill{");
        sb.append("categoryId=").append(categoryId);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}
