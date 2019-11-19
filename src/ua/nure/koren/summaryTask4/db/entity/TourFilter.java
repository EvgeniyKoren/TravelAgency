package ua.nure.koren.summaryTask4.db.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Container for search criteria to find {@link Tour} in DB.
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public class TourFilter implements Serializable {

    private static final long serialVersionUID = 122025786080834988L;

    @NotNull
    private String type;
    private int price;
    private int peopleQuantity;
    private int hotelType;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPeopleQuantity() {
        return peopleQuantity;
    }

    public void setPeopleQuantity(int peopleQuantity) {
        this.peopleQuantity = peopleQuantity;
    }

    public int getHotelType() {
        return hotelType;
    }

    public void setHotelType(int hotelType) {
        this.hotelType = hotelType;
    }
}
