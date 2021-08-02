package Utopia.Domain;

public class Airplane extends BaseDomain<Airplane> {

    private Integer id;
    private Integer typeId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return this.typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

}