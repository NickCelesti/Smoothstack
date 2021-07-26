package LMS_Final_Assignment.Domain;

public class Book extends BaseDomain<Book> {

    private static final long serialVersionUID = -6341760223870258400L;

    private Integer id;
    private String title;
    private Integer authorId;
    private Integer publisherId;

    public Book() {
        this.id = null;
        this.title = null;
        this.publisherId = null;
        this.authorId = null;
    }

    public Book(Integer id, String title, Integer publisherId, Integer authorId) {
        this.id = id;
        this.title = title;
        this.publisherId = publisherId;
        this.authorId = authorId;
    }

    public Integer getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getPublisherId() {
        return this.publisherId;
    }

    public Integer getAuthorId() {
        return this.authorId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}