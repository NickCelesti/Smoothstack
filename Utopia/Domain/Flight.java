package Utopia.Domain;

import java.sql.Timestamp;

public class Flight extends BaseDomain<Flight> {

    private Integer id;
    private Integer routeId;
    private Integer airplaneId;
    private Timestamp departureTime;
    private Integer reservedSeats;
    private Float seatPrice;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRouteId() {
        return this.routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getAirplaneId() {
        return this.airplaneId;
    }

    public void setAirplaneId(Integer airplaneId) {
        this.airplaneId = airplaneId;
    }

    public Timestamp getDepartureTime() {
        return this.departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getReservedSeats() {
        return this.reservedSeats;
    }

    public void setReservedSeats(Integer reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public Float getSeatPrice() {
        return this.seatPrice;
    }

    public void setSeatPrice(Float seatPrice) {
        this.seatPrice = seatPrice;
    }

}