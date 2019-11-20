package ua.nure.koren.summaryTask4.db.entity;

/**
 * Tour entity.
 *
 * @author E.Koren
 * @version 1.0
 * @since 2019-11-19
 */
public class Tour extends AbstractEntity {

    private static final long serialVersionUID = 122225786080834988L;

    private String type;
    private String country;
    private String city;
    private String hotelName;
    private int price;
    private int peopleQuantity;
    private int duration;
    private int hotelType;
    private boolean lastMinute;
    private String status;
    private int sale;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

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

    public boolean isLastMinute() {
        return lastMinute;
    }

    public void setLastMinute(boolean lastMinute) {
        this.lastMinute = lastMinute;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id='" + getId() + '\'' +
                "type='" + type + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", price=" + price +
                ", peopleQuantity=" + peopleQuantity +
                ", duration=" + duration +
                ", typeOfHotel=" + hotelType +
                ", lastMinute=" + lastMinute +
                ", status=" + status +
                ", sale=" + sale +
                '}';
    }

}
