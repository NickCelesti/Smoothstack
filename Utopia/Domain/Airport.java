package Utopia.Domain;

public class Airport extends BaseDomain<Airport> {

    private String iata_id;
    private String city;

    public String getIata_id() {
        return this.iata_id;
    }

    public void setIata_id(String iata_id) {
        this.iata_id = iata_id;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}