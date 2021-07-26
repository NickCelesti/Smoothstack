package LMS_Final_Assignment.Domain;

public class Author extends BaseDomain<Author> {

    private Integer id;
    private String name;

    public Author() {
        this.id = null;
        this.name = null;
    }

    public Author(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}