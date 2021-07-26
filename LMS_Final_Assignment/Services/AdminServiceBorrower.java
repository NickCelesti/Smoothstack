package LMS_Final_Assignment.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import LMS_Final_Assignment.DAO.BorrowerDAO;
import LMS_Final_Assignment.Domain.Borrower;

public class AdminServiceBorrower {

    Util util = new Util();

    public String addBorrower() throws SQLException {
        Connection conn = null;
        String s;
        Borrower borr = new Borrower();
        try {
            conn = util.getConnection();
            BorrowerDAO bdao = new BorrowerDAO(conn);

            System.out.print("Enter Name ('quit' to cancel): ");
            String borrowerName = InputHandler.getStringInput();
            if (borrowerName.equals("quit")) {
                return "Transaction cancelled";
            }

            System.out.print("Enter Address ('quit' to cancel): ");
            String borrowerAddress = InputHandler.getStringInput();
            if (borrowerAddress.equals("quit")) {
                return "Transaction cancelled";
            }

            System.out.print("Enter Phone xxx-xxx-xxxx ('quit' to cancel): ");
            String borrowerPhone = InputHandler.getStringInput();
            if (borrowerName.equals("quit")) {
                return "Transaction cancelled";
            }

            borr.setName(borrowerName);
            borr.setAddress(borrowerAddress);
            borr.setPhone(borrowerPhone);
            bdao.addToEnd(borr);
            conn.commit();
            s = "Successfully added " + borr.getName();
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

    public String updateBorrower() throws SQLException {
        Connection conn = null;
        String s;
        Borrower borr = new Borrower();
        try {
            conn = util.getConnection();
            BorrowerDAO bdao = new BorrowerDAO(conn);

            System.out.println("Select Borrower you wish to update: ");
            List<Borrower> borrowers = bdao.readAllBorrowers();
            int borrIndex = 1;
            for (Borrower b : borrowers) {
                System.out.println(borrIndex + ") " + b.getName() + ", " + b.getAddress());
                borrIndex++;
            }
            System.out.println(borrIndex + ") Cancel Transaction");
            int borrChoice = InputHandler.getIntInput(1, borrIndex);
            if (borrChoice == borrIndex) {
                return "Transaction Cancelled";
            }

            System.out.print("Enter Name ('quit' to cancel): ");
            String borrName = InputHandler.getStringInput();
            if (borrName.equals("quit")) {
                return "Transaction cancelled";
            }

            System.out.print("Enter Address ('quit' to cancel): ");
            String borrAddress = InputHandler.getStringInput();
            if (borrAddress.equals("quit")) {
                return "Transaction cancelled";
            }

            System.out.print("Enter Phone xxx-xxx-xxxx ('quit' to cancel): ");
            String borrPhone = InputHandler.getStringInput();
            if (borrName.equals("quit")) {
                return "Transaction cancelled";
            }

            borr.setCardNo(borrowers.get(--borrChoice).getCardNo());
            borr.setName(borrName);
            borr.setAddress(borrAddress);
            borr.setPhone(borrPhone);
            bdao.update(borr);
            conn.commit();
            s = "Successfully updated " + borr.getName();
        } catch (Exception e) {
            conn.rollback();
            s = "Problem has occured, borrower not updated";
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return s;
    }

    public String deleteBorrower() throws SQLException {
        Connection conn = null;
        String s;
        Borrower borr = new Borrower();
        try {
            conn = util.getConnection();
            BorrowerDAO bdao = new BorrowerDAO(conn);

            System.out.println("Select Borrower you wish to update: ");
            List<Borrower> borrowers = bdao.readAllBorrowers();
            int borrIndex = 1;
            for (Borrower b : borrowers) {
                System.out.println(borrIndex + ") " + b.getName() + ", " + b.getAddress());
                borrIndex++;
            }
            System.out.println(borrIndex + ") Cancel Transaction");
            int borrChoice = InputHandler.getIntInput(1, borrIndex);
            if (borrChoice == borrIndex) {
                return "Transaction Cancelled";
            }

            borr.setCardNo(borrowers.get(--borrChoice).getCardNo());
            bdao.delete(borr);

            conn.commit();
            s = "Successfully deleted borrower";
        } catch (Exception e) {
            conn.rollback();
            s = "Problem has occured, borrower not deleted";
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return s;
    }

    public String readAllBorrowers() throws SQLException {
        Connection conn = null;
        String s;
        try {
            conn = util.getConnection();
            BorrowerDAO bdao = new BorrowerDAO(conn);

            System.out.println("List of borrowers: ");
            List<Borrower> borrowers = bdao.readAllBorrowers();
            int borrIndex = 1;
            for (Borrower b : borrowers) {
                System.out.println(borrIndex + ") " + b.getName() + ", " + b.getAddress());
                borrIndex++;
            }

            conn.commit();
            s = "Successfully displayed borrowers";
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
