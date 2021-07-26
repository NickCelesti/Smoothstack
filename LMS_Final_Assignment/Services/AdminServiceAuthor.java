package LMS_Final_Assignment.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import LMS_Final_Assignment.InputHandler;
import LMS_Final_Assignment.DAO.AuthorDAO;
import LMS_Final_Assignment.Domain.Author;

public class AdminServiceAuthor {

    Util util = new Util();

    public String addAuthor() throws SQLException {
        Connection conn = null;
        String s;
        Author author = new Author();
        try {
            conn = util.getConnection();
            AuthorDAO adao = new AuthorDAO(conn);

            System.out.print("Enter name ('quit' to cancel): ");
            String authorName = InputHandler.getStringInput();
            if (authorName.equals("quit")) {
                return "Transaction cancelled";
            }

            author.setName(authorName);

            adao.addToEnd(author);
            conn.commit();
            s = "Successfully added " + author.getName();
        } catch (Exception e) {
            conn.rollback();
            s = "Author not added";
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return s;
    }

    public String updateAuthor() throws SQLException {
        Connection conn = null;
        String s;
        Author author = new Author();
        try {
            conn = util.getConnection();
            AuthorDAO adao = new AuthorDAO(conn);

            System.out.println("Select Author you wish to update: ");
            List<Author> authors = adao.readAllAuthors();
            int authorIndex = 1;
            for (Author a : authors) {
                System.out.println(authorIndex + ") " + a.getName());
                authorIndex++;
            }
            System.out.println(authorIndex + ") Cancel Transaction");
            int authorChoice = InputHandler.getIntInput(1, authorIndex);
            if (authorChoice == authorIndex) {
                return "Transaction Cancelled";
            }

            System.out.print("Enter new name ('quit' to cancel): ");
            String authorName = InputHandler.getStringInput();
            if (authorName.equals("quit")) {
                return "Transaction cancelled";
            }

            author.setId(authors.get(--authorChoice).getId());
            author.setName(authorName);
            adao.update(author);
            conn.commit();
            s = "Successfully updated " + author.getName();
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

    public String deleteAuthor() throws SQLException {
        Connection conn = null;
        String s;
        Author author = new Author();
        try {
            conn = util.getConnection();
            AuthorDAO adao = new AuthorDAO(conn);

            System.out.println("Select Author you wish to delete: ");
            List<Author> authors = adao.readAllAuthors();
            int authorIndex = 1;
            for (Author a : authors) {
                System.out.println(authorIndex + ") " + a.getName());
                authorIndex++;
            }
            System.out.println(authorIndex + ") Cancel Transaction");
            int authorChoice = InputHandler.getIntInput(1, authorIndex);
            if (authorChoice == authorIndex) {
                return "Transaction Cancelled";
            }

            author.setId(authors.get(--authorChoice).getId());
            adao.delete(author);

            conn.commit();
            s = "Successfully deleted author";
        } catch (Exception e) {
            conn.rollback();
            s = "Problem has occured, author not deleted";
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return s;
    }

    public String readAllAuthors() throws SQLException {
        Connection conn = null;
        String s;
        try {
            conn = util.getConnection();
            AuthorDAO adao = new AuthorDAO(conn);

            System.out.println("All authors: ");
            System.out.println("Select Author you wish to update: ");
            List<Author> authors = adao.readAllAuthors();
            int authorIndex = 1;
            for (Author a : authors) {
                System.out.println(authorIndex + ") " + a.getName());
                authorIndex++;
            }

            conn.commit();
            s = "Successfully displayed authors";
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
