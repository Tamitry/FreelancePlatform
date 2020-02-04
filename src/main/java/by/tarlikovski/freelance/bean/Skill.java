package by.tarlikovski.freelance.bean;

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
}
