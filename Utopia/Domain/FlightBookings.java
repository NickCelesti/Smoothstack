package Utopia.Domain;

public class FlightBookings extends BaseDomain<FlightBookings> {

    private Integer flightId;
    private Integer bookingId;

    public Integer getFlightId() {
        return this.flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Integer getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

}