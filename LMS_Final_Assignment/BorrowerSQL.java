package LMS_Final_Assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowerSQL {
    Connection conn;

    public BorrowerSQL() {
        conn = Connector.getInstance().getConnection();
    }

    /**
     * This is for menuBorr2_1 Prints all library branches in the format:
     * [branchId]) [branchName], [branchAddress]
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
     * This is for updateBooks. Prints all library books contained in the database
     * in the format: [bookId]) [bookName] by [bookAuthor]
     * 
     * @return the Id of the chosen book
     */
    public int printBooks(int branchId) {
        String sql = "select title, authorName, tbl_book.bookId from ((tbl_book inner join tbl_author on authorId = authId) inner join tbl_book_copies on tbl_book.bookId = tbl_book_copies.bookId) where branchId = ? and noOfCopies > 0";
        ResultSet resultSet = null;
        int rowCount = 0;
        int id = 0;
        try (PreparedStatement prepareStatement = conn.prepareStatement(sql);) {
            prepareStatement.setInt(1, branchId);
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                rowCount = resultSet.getRow();
                System.out.println(
                        rowCount + ") " + resultSet.getString("title") + ", " + resultSet.getString("authorName"));
            }
            System.out.println(++rowCount + ") Quit to previous");
            System.out.println();
            int ans = InputHandler.getIntInput(1, rowCount);
            if (ans == rowCount) {
                System.out.println("Transation Cancelled");
                return 0;
            }
            resultSet.absolute(ans);
            id = resultSet.getInt("bookId");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return id;
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

    public void checkOut(int bookId, int branchId) {

    }
}
