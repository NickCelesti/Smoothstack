package LMS_Final_Assignment.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import LMS_Final_Assignment.Domain.Author;

public class AuthorDAO extends BaseDAO<Author> {

    public AuthorDAO(Connection conn) {
        super(conn);
    }

    public void add(Author author) throws SQLException, ClassNotFoundException {
        this.save("INSERT INTO tbl_author VALUES (?, ?)", new Object[] { author.getId(), author.getName() });
    }

    public void addToEnd(Author author) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(authorId) FROM `tbl_author`)", null);
        save("insert into tbl_author values (@max_id + 1, ?)", new Object[] { author.getName() });
    }

    public void update(Author author) throws SQLException, ClassNotFoundException {
        this.save("UPDATE tbl_author SET authorName = ? where authorId = ?",
                new Object[] { author.getName(), author.getId() });
    }

    public void delete(Author author) throws SQLException, ClassNotFoundException {
        this.save("DELETE FROM tbl_author WHERE authorId = ?", new Object[] { author.getId() });
    }

    public List<Author> readAllAuthors() throws SQLException, ClassNotFoundException {
        return read("select * from tbl_author", null);
    }

    public Author readAuthorsByName(String authorName) throws SQLException, ClassNotFoundException {
        return this.readSingle("select * from tbl_author where authorName = ?", new Object[] { authorName });
    }

    public Author readAuthorById(Integer authorId) throws SQLException, ClassNotFoundException {
        return this.readSingle("select * from tbl_author where authorId = ?", new Object[] { authorId });
    }

    @Override
    public List<Author> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

        List<Author> authorList = new ArrayList<>();
        while (rs.next()) {
            Author author = new Author();
            author.setId(rs.getInt("authorId"));
            author.setName(rs.getString("authorName"));
            authorList.add(author);
        }
        return authorList;
    }

    @Override
    public Author extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {

        if (rs.next()) {
            Author author = new Author();
            author.setId(rs.getInt("authorId"));
            author.setName(rs.getString("authorName"));
            return author;
        }
        return null;
    }

    // @Override
    // public List<Author> extract(ResultSet resultSet) {
    // try {
    // List<Author> authorList = new ArrayList<>();
    // BookDAO bookDAO = new BookDAO();
    // while (resultSet.next()) {
    // Author author = new Author();
    // author.setId(resultSet.getInt("authorId"));
    // author.setName(resultSet.getString("authorName"));
    // author.setBookList(bookDAO.readFirstLevel(
    // "select * from tbl_book where bookId in (select bookId from tbl_book_authors
    // where authorId = ?)",
    // new Object[] { author.getId() }));
    // authorList.add(author);
    // }
    // return authorList;
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return null;
    // }

}