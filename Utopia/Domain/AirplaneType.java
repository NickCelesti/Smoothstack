package Utopia.Domain;

public class AirplaneType extends BaseDomain<AirplaneType> {

    private Integer id;
    private Integer maxCapacity;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaxCapacity() {
        return this.maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

}