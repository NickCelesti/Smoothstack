package LMS_Final_Assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibrarianSQL {
    Connection conn;

    public LibrarianSQL() {
        conn = Connector.getInstance().getConnection();
    }

    /**
     * This is for menuLib2 Prints all library branches in the format: [branchId])
     * [branchName], [branchAddress]
     * 
     * @return the total number of rows printed (equal to highest branchId)
     */
    public int printBranches() {
        String sql = "SELECT branchId, branchName, branchAddress FROM library.tbl_library_branch";
        int rowCount = 0;
        try (PreparedStatement prepareStatement = conn.prepareStatement(sql); // try with resources
                ResultSet resultSet = prepareStatement.executeQuery();) {

            while (resultSet.next()) {
                rowCount = resultSet.getRow();
                System.out.println(resultSet.getInt("branchId") + ") " + resultSet.getString("branchName") + ", "
                        + resultSet.getString("branchAddress"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(++rowCount + ") Quit to previous");
        System.out.println();
        return rowCount;
    }

    /**
     * used in updateBranch
     * 
     * @param branchId id of the desired name of branch
     * @return branchName where branchId = param.branchId
     */
    public String getBranchName(int branchId) {

        String sql = "SELECT branchName FROM library.tbl_library_branch WHERE branchId = ?";
        String branchName = "";

        try (PreparedStatement prepareStatement = conn.prepareStatement(sql);) {
            prepareStatement.setInt(1, branchId);
            try (ResultSet resultSet = prepareStatement.executeQuery();) { // apparently you dont need another catch for
                                                                           // nested try's
                if (resultSet.next())
                    branchName = resultSet.getString(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return branchName;
    }

    /**
     * Used in updateBranch
     * 
     * @param newName  desired new name of the current branch
     * @param branchId id of current branch
     */
    public void updateBranchName(String newName, int branchId) {
        String sql = "UPDATE library.tbl_library_branch SET branchName = ? WHERE branchId = ?";

        try (PreparedStatement prepareStatement = conn.prepareStatement(sql)) { // try with resources
            prepareStatement.setString(1, newName);
            prepareStatement.setInt(2, branchId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Used in updateBranch
     * 
     * @param newAddress desired new address of the current branch
     * @param branchId   id of current branch
     */
    public void updateBranchAddress(String newAddress, int branchId) {
        String sql = "UPDATE library.tbl_library_branch SET branchAddress = ? WHERE branchId = ?";

        try (PreparedStatement prepareStatement = conn.prepareStatement(sql);) {
            prepareStatement.setString(1, newAddress);
            prepareStatement.setInt(2, branchId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * This is for updateBooks. Prints all library books contained in the database
     * in the format: [bookId]) [bookName] by [bookAuthor]
     * 
     * @return the total number of rows printed (equal to highest bookId)
     */
    public int printBooks() {
        String sql = "SELECT bookId, title, authorName FROM tbl_book, tbl_author WHERE tbl_book.authId = tbl_author.authorId ORDER BY bookId";
        int rowCount = 0;
        try (PreparedStatement prepareStatement = conn.prepareStatement(sql); // try with resources
                ResultSet resultSet = prepareStatement.executeQuery();) {

            while (resultSet.next()) {
                rowCount = resultSet.getRow();
                System.out.println(resultSet.getInt("bookId") + ") " + resultSet.getString("title") + ", "
                        + resultSet.getString("authorName"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(++rowCount + ") Quit to previous");
        System.out.println();
        return rowCount;
    }

    /**
     * provides the number of book copies that a certain branch has of a certain
     * book
     * 
     * @param bookId
     * @param branchId
     * @return number of copies if it exists, -1 otherwise
     */
    public int getBookCopies(int bookId, int branchId) {
        String sql = "SELECT noOfCopies FROM tbl_book_copies WHERE bookId = ? and branchId = ?";
        int bookCopies = 0;

        try (PreparedStatement prepareStatement = conn.prepareStatement(sql);) {
            prepareStatement.setInt(1, bookId);
            prepareStatement.setInt(2, branchId);
            try (ResultSet resultSet = prepareStatement.executeQuery();) {
                if (resultSet.next()) {
                    bookCopies = resultSet.getInt(1);
                } else {
                    bookCopies = -1; // if the row does not exist
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bookCopies;
    }

    /**
     * Used in updateBranch
     * 
     * @param newAddress desired new address of the current branch
     * @param branchId   id of current branch
     */
    public void updateBookCopies(int bookId, int branchId, int oldNumCopies, int newNumCopies) {

        String sql;
        String outputString;
        if (oldNumCopies == -1) { // can't check for 0 because that doesn't ensure that the element does not exist
            sql = "INSERT INTO tbl_book_copies (noOfCopies, bookId, branchId) VALUES (?, ?, ?)";
            outputString = "Successfully inserted new value into table";
        } else {
            sql = "UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId = ?";
            outputString = "Successfully updated existing value";
        }

        try (PreparedStatement prepareStatement = conn.prepareStatement(sql);) {
            prepareStatement.setInt(1, newNumCopies);
            prepareStatement.setInt(2, bookId);
            prepareStatement.setInt(3, branchId);
            prepareStatement.executeUpdate(); // both update and insert into use the executeUpdate method
            System.out.println(outputString);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
