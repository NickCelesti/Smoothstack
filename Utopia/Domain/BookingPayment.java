package Utopia.Domain;

public class BookingPayment extends BaseDomain<BookingPayment> {

    private Integer bookingId;
    private Integer stripeId;
    private Boolean isRefunded;

    public Integer getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getStripeId() {
        return this.stripeId;
    }

    public void setStripeId(Integer stripeId) {
        this.stripeId = stripeId;
    }

    public Boolean isIsRefunded() {
        return this.isRefunded;
    }

    public Boolean getIsRefunded() {
        return this.isRefunded;
    }

    public void setIsRefunded(Boolean isRefunded) {
        this.isRefunded = isRefunded;
    }

}