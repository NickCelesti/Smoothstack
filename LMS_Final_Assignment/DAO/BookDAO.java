package LMS_Final_Assignment.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import LMS_Final_Assignment.Domain.Author;
import LMS_Final_Assignment.Domain.Book;
import LMS_Final_Assignment.Domain.Genre;

public class BookDAO extends BaseDAO<Book> {

    public BookDAO(Connection conn) {
        super(conn);
    }

    public void add(Book book) throws SQLException, ClassNotFoundException {
        save("insert into tbl_book (bookId, title, authId, pubId) values (?, ?, ?, ?)",
                new Object[] { book.getId(), book.getTitle(), book.getAuthorId(), book.getPublisherId() });
    }

    public void addToEnd(Book book) throws SQLException, ClassNotFoundException {
        save("SET @max_id = (SELECT MAX(bookId) FROM `tbl_book`)", null);
        save("insert into tbl_book values (@max_id + 1, ?, ?, ?)",
                new Object[] { book.getTitle(), book.getAuthorId(), book.getPublisherId() });
    }

    public void update(Book book) throws SQLException, ClassNotFoundException {
        save("update tbl_book set title = ?, authId = ?, pubId = ? where bookId = ?",
                new Object[] { book.getTitle(), book.getAuthorId(), book.getPublisherId(), book.getId() });
    }

    public void delete(Book book) throws SQLException, ClassNotFoundException {
        save("delete from tbl_book where bookId = ?", new Object[] { book.getId() });
    }

    public void insertBookAuthor(Book book, Author author) throws SQLException, ClassNotFoundException {
        save("insert into tbl_book_authors (bookId, authorId) values(?, ?)",
                new Object[] { book.getId(), author.getId() });
    }

    public void insertBookGenre(Genre genre, Book book) throws SQLException, ClassNotFoundException {
        save("insert into tbl_book_genres (genreId, bookId) values(?, ?)",
                new Object[] { genre.getId(), book.getId() });
    }

    public List<Book> readAllBooks() throws SQLException, ClassNotFoundException {
        return this.read("select * from tbl_book", null);
    }

    public List<Book> readBooksByTitle(String title) throws SQLException, ClassNotFoundException {
        return this.read("select * from tbl_book where title = ?", new Object[] { title });
    }

    public Book readBookById(Integer id) throws SQLException, ClassNotFoundException {
        return readSingle("select * from tbl_book where bookId = ?", new Object[] { id });
    }

    public List<Book> readBooksWithCopy(Integer branchId) throws SQLException, ClassNotFoundException {
        String sql = "select tbl_book.* from ((tbl_book inner join tbl_author on authorId = authId) inner join tbl_book_copies on tbl_book.bookId = tbl_book_copies.bookId) where branchId = ? and noOfCopies > 0";
        return read(sql, new Object[] { branchId });
    }

    @Override
    public List<Book> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

        try {
            List<Book> bookList = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("bookId"));
                book.setTitle(rs.getString("title"));
                book.setAuthorId(rs.getInt("authId"));
                bookList.add(book);
            }
            return bookList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException {
        try {
            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("bookId"));
                book.setTitle(rs.getString("title"));
                book.setAuthorId(rs.getInt("authId"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // @Override
    // public List<Book> extract(ResultSet resultSet) {
    // try {
    // List<Book> bookList = new ArrayList<>();
    // PublisherDAO publisherDAO = new PublisherDAO();
    // AuthorDAO authorDAO = new AuthorDAO();
    // BookCopyDAO bookCopyDAO = new BookCopyDAO();
    // BookLoanDAO bookLoanDAO = new BookLoanDAO();
    // GenreDAO genreDAO = new GenreDAO();
    // while (resultSet.next()) {
    // Book book = new Book();
    // book.setId(resultSet.getInt("bookId"));
    // book.setTitle(resultSet.getString("title"));
    // List<Publisher> publisherList = publisherDAO.readFirstLevel(
    // "select * from tbl_publisher where publisherId = ?",
    // new Object[] { resultSet.getInt("pubId") });
    // if (!publisherList.isEmpty())
    // book.setPublisher((Publisher) publisherList.get(0));
    // book.setAuthorList(authorDAO.readFirstLevel(
    // "select * from tbl_author where authorId in (select authorId from
    // tbl_book_authors where bookId = ?)",
    // new Object[] { book.getId() }));
    // book.setBookCopyList(bookCopyDAO.readFirstLevel("select * from
    // tbl_book_copies where bookId = ?",
    // new Object[] { book.getId() }));
    // book.setBookLoanList(bookLoanDAO.readFirstLevel("select * from tbl_book_loans
    // where bookId = ?",
    // new Object[] { book.getId() }));
    // book.setGenreList(genreDAO.readFirstLevel(
    // "select * from tbl_genre where genreId in (select genreId from
    // tbl_book_genres where bookId = ?)",
    // new Object[] { book.getId() }));
    // bookList.add(book);
    // }
    // return bookList;
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return null;
    // }

}