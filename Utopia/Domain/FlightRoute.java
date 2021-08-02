package Utopia.Domain;

public class FlightRoute extends BaseDomain<FlightRoute> {
    private Integer flightId;
    private String originCode;
    private String originCity;
    private String destCode;
    private String destCity;

    public Integer getFlightId() {
        return this.flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getOriginCode() {
        return this.originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getOriginCity() {
        return this.originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getDestCode() {
        return this.destCode;
    }

    public void setDestCode(String destCode) {
        this.destCode = destCode;
    }

    public String getDestCity() {
        return this.destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }
}
