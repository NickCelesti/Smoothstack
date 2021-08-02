package Utopia.Domain;

public class Route extends BaseDomain<Route> {

    private Integer id;
    private String originId;
    private String destId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginId() {
        return this.originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getDestId() {
        return this.destId;
    }

    public void setDestId(String destId) {
        this.destId = destId;
    }

}
