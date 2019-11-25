package by.tarlikovski.freelance.entities;

public class Work extends Entity {
    private int UserId;
    private int OrderCategoryId;
    private int Rating;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getOrderCategoryId() {
        return OrderCategoryId;
    }

    public void setOrderCategoryId(int orderCategoryId) {
        OrderCategoryId = orderCategoryId;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }
}
