package LMS_Final_Assignment.Domain;

import java.util.List;

public class Genre extends BaseDomain<Genre> {

    private static final long serialVersionUID = 4541731732126491242L;

    private Integer id;
    private String name;
    private List<Book> bookList;

    public Genre() {
        this.id = null;
        this.name = null;
        this.bookList = null;
    }

    public Genre(Integer id, String name, List<Book> bookList) {
        this.id = id;
        this.name = name;
        this.bookList = bookList;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Book> getBookList() {
        return this.bookList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

}
