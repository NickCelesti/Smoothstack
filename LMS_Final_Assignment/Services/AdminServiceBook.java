package LMS_Final_Assignment.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import LMS_Final_Assignment.DAO.AuthorDAO;
import LMS_Final_Assignment.DAO.BookDAO;
import LMS_Final_Assignment.DAO.PublisherDAO;
import LMS_Final_Assignment.Domain.Author;
import LMS_Final_Assignment.Domain.Book;
import LMS_Final_Assignment.Domain.Publisher;

public class AdminServiceBook {

    Util util = new Util();

    public String addBook() throws SQLException {
        Connection conn = null;
        String s;
        Book book = new Book();
        try {
            conn = util.getConnection();
            BookDAO bdao = new BookDAO(conn);
            AuthorDAO adao = new AuthorDAO(conn);
            PublisherDAO pdao = new PublisherDAO(conn);

            System.out.print("Enter title ('quit' to cancel): ");
            String bookName = InputHandler.getStringInput();
            if (bookName.equals("quit")) {
                return "Transaction cancelled";
            }

            System.out.println("List of Authors: ");
            List<Author> authors = adao.readAllAuthors();
            int authorIndex = 1;
            for (Author a : authors) {
                System.out.println(authorIndex + ") " + a.getName());
                authorIndex++;
            }
            System.out.println(authorIndex + ") Cancel Transaction");
            int authorChoice = InputHandler.getIntInput(1, authorIndex);
            if (authorChoice == authorIndex) {
                return "Transaction cancelled";
            }

            System.out.println("List of Genres: ");
            List<Publisher> publishers = pdao.readAllPublishers();
            int publisherIndex = 1;
            for (Publisher p : publishers) {
                System.out.println(publisherIndex + ") " + p.getName());
                publisherIndex++;
            }
            System.out.println(publisherIndex + ") Cancel Transaction");
            int publisherChoice = InputHandler.getIntInput(1, publisherIndex);
            if (publisherChoice == publisherIndex) {
                return "Transaction cancelled";
            }

            book.setTitle(bookName);
            book.setAuthorId(authors.get(--authorChoice).getId());
            book.setPublisherId(publishers.get(--publisherChoice).getId());
            bdao.addToEnd(book);
            conn.commit();
            s = "Successfully added " + bookName;
        } catch (Exception e) {
            conn.rollback();
            s = "Book not added";
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return s;
    }

    public String updateBook() throws SQLException {
        Connection conn = null;
        String s;
        Book book = new Book();
        try {
            conn = util.getConnection();
            BookDAO bdao = new BookDAO(conn);
            AuthorDAO adao = new AuthorDAO(conn);
            PublisherDAO pdao = new PublisherDAO(conn);

            System.out.println("Select Book you wish to update: ");
            List<Book> books = bdao.readAllBooks();
            int bookIndex = 1;
            for (Book b : books) {
                Author author = adao.readAuthorById(b.getAuthorId());
                System.out.println(bookIndex + ") " + b.getTitle() + " by " + author.getName());
                bookIndex++;
            }
            System.out.println(bookIndex + ") Cancel Transaction");
            int bookChoice = InputHandler.getIntInput(1, bookIndex);
            if (bookChoice == bookIndex) {
                return "Transaction Cancelled";
            }

            System.out.print("Enter title ('quit' to cancel): ");
            String bookName = InputHandler.getStringInput();
            if (bookName.equals("quit")) {
                return "Transaction cancelled";
            }

            System.out.println("List of Authors: ");
            List<Author> authors = adao.readAllAuthors();
            int authorIndex = 1;
            for (Author a : authors) {
                System.out.println(authorIndex + ") " + a.getName());
                authorIndex++;
            }
            System.out.println(authorIndex + ") Cancel Transaction");
            int authorChoice = InputHandler.getIntInput(1, authorIndex);
            if (authorChoice == authorIndex) {
                return "Transaction cancelled";
            }

            System.out.println("List of Genres: ");
            List<Publisher> publishers = pdao.readAllPublishers();
            int publisherIndex = 1;
            for (Publisher p : publishers) {
                System.out.println(publisherIndex + ") " + p.getName());
                publisherIndex++;
            }
            System.out.println(publisherIndex + ") Cancel Transaction");
            int publisherChoice = InputHandler.getIntInput(1, publisherIndex);
            if (publisherChoice == publisherIndex) {
                return "Transaction cancelled";
            }

            book.setId(books.get(--bookChoice).getId());
            book.setTitle(bookName);
            book.setAuthorId(authors.get(--authorChoice).getId());
            book.setPublisherId(publishers.get(--publisherChoice).getId());
            bdao.update(book);
            conn.commit();
            s = "Successfully updated " + book.getTitle();
        } catch (Exception e) {
            conn.rollback();
            s = "Problem has occured, book not updated";
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return s;
    }

    public String deleteBook() throws SQLException {
        Connection conn = null;
        String s;
        Book book = new Book();
        try {
            conn = util.getConnection();
            BookDAO bdao = new BookDAO(conn);
            AuthorDAO adao = new AuthorDAO(conn);

            System.out.println("Select Book you wish to delete: ");
            List<Book> books = bdao.readAllBooks();
            int bookIndex = 1;
            for (Book b : books) {
                Author author = adao.readAuthorById(b.getAuthorId());
                System.out.println(bookIndex + ") " + b.getTitle() + " by " + author.getName());
                bookIndex++;
            }
            System.out.println(bookIndex + ") Cancel Transaction");
            int bookChoice = InputHandler.getIntInput(1, bookIndex);
            if (bookChoice == bookIndex) {
                return "Transaction Cancelled";
            }

            book.setId(books.get(--bookChoice).getId());
            bdao.delete(book);

            conn.commit();
            s = "Successfully deleted book";
        } catch (Exception e) {
            conn.rollback();
            s = "Problem has occured, book not deleted";
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return s;
    }

    public String readAllBooks() throws SQLException {
        Connection conn = null;
        String s;
        try {
            conn = util.getConnection();
            BookDAO bdao = new BookDAO(conn);
            AuthorDAO adao = new AuthorDAO(conn);

            System.out.println("List of books: ");
            List<Book> books = bdao.readAllBooks();
            int bookIndex = 1;
            for (Book b : books) {
                Author author = adao.readAuthorById(b.getAuthorId());
                System.out.println(bookIndex + ") " + b.getTitle() + " by " + author.getName());
                bookIndex++;
            }

            conn.commit();
            s = "Successfully displayed books";
        } catch (Exception e) {
            conn.rollback();
            s = "Problem has occured";
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return s;
    }
}
