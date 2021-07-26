package LMS_Final_Assignment.Domain;

public class Publisher extends BaseDomain<Publisher> {

    private Integer id;
    private String name;
    private String address;
    private String phone;

    public Publisher() {
        this.id = null;
        this.name = null;
        this.address = null;
        this.phone = null;
    }

    public Publisher(Integer id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
