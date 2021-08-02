package Utopia.Domain;

public class BookingAgent extends BaseDomain<BookingAgent> {

    private Integer bookingId;
    private Integer agentId;

    public Integer getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getAgentId() {
        return this.agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

}