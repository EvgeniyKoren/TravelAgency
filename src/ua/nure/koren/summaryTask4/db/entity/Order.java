package ua.nure.koren.summaryTask4.db.entity;

/**
 * Order entity.
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public class Order extends AbstractEntity {

    private static final long serialVersionUID = 122325786080834988L;

    private int id;
    private int userId;
    private int tourId;
    private boolean handled;

    /**
     * Default constructor
     */
    public Order() {}

    /**
     * Constructor with parameters
     *
     * @param userId User id that make an order
     * @param tourId Tour id that is ordered
     */
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
