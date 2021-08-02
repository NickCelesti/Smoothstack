package Utopia.Domain;

public class BookingUser extends BaseDomain<BookingUser> {

    private Integer bookingId;
    private Integer userId;

    public Integer getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}