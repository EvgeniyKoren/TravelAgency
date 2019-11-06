package ua.nure.koren.SummaryTask4.db.entity;

public class Order {

    private int userId;
    private int tourId;
    private boolean isPaid;

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

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", tourId=" + tourId +
                ", isPaid=" + isPaid +
                '}';
    }
}
