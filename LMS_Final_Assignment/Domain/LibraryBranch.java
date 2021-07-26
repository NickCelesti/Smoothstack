package LMS_Final_Assignment.Domain;

public class LibraryBranch extends BaseDomain<LibraryBranch> {

    private Integer id;
    private String name;
    private String address;

    public LibraryBranch() {
        this.id = null;
        this.name = null;
        this.address = null;
    }

    public LibraryBranch(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
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

}
