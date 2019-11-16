package ua.nure.koren.summaryTask4.db.entity;

public class Order {

    private int id;
    private int userId;
    private int tourId;
    private boolean handled;

    public Order() {}

    public Order(int userId, int tourId) {
        this.userId = userId;
        this.tourId = tourId;
        this.handled = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", tourId=" + tourId +
                ", handled=" + handled +
                '}';
    }
}
