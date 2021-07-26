package LMS_Final_Assignment.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import LMS_Final_Assignment.InputHandler;
import LMS_Final_Assignment.DAO.LibraryBranchDAO;
import LMS_Final_Assignment.Domain.LibraryBranch;

public class AdminServiceLibraryBranch {

    Util util = new Util();

    public String addBranch() throws SQLException {
        Connection conn = null;
        String s;
        LibraryBranch branch = new LibraryBranch();
        try {
            conn = util.getConnection();
            LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);

            System.out.print("Enter Name ('quit' to cancel): ");
            String branchName = InputHandler.getStringInput();
            if (branchName.equals("quit")) {
                return "Transaction cancelled";
            }

            System.out.print("Enter Address ('quit' to cancel): ");
            String branchAddress = InputHandler.getStringInput();
            if (branchAddress.equals("quit")) {
                return "Transaction cancelled";
            }

            branch.setName(branchName);
            branch.setAddress(branchAddress);
            lbdao.addToEnd(branch);
            conn.commit();
            s = "Successfully added " + branch.getName();
        } catch (Exception e) {
            conn.rollback();
            s = "Branch not added";
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return s;
    }

    public String updateBranch() throws SQLException {
        Connection conn = null;
        String s;
        LibraryBranch branch = new LibraryBranch();
        try {
            conn = util.getConnection();
            LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);

            System.out.println("Select Branch you wish to update: ");
            List<LibraryBranch> branches = lbdao.readAllBranches();
            int branchIndex = 1;
            for (LibraryBranch b : branches) {
                System.out.println(branchIndex + ") " + b.getName() + ", " + b.getAddress());
                branchIndex++;
            }
            System.out.println(branchIndex + ") Cancel Transaction");
            int branchChoice = InputHandler.getIntInput(1, branchIndex);
            if (branchChoice == branchIndex) {
                return "Transaction Cancelled";
            }

            System.out.print("Enter Name ('quit' to cancel): ");
            String branchName = InputHandler.getStringInput();
            if (branchName.equals("quit")) {
                return "Transaction cancelled";
            }

            System.out.print("Enter Address ('quit' to cancel): ");
            String branchAddress = InputHandler.getStringInput();
            if (branchAddress.equals("quit")) {
                return "Transaction cancelled";
            }

            branch.setId(branches.get(--branchChoice).getId());
            branch.setName(branchName);
            branch.setAddress(branchAddress);
            lbdao.update(branch);
            conn.commit();
            s = "Successfully updated " + branch.getName();
        } catch (Exception e) {
            conn.rollback();
            s = "Problem has occured, Branch not updated";
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return s;
    }

    public String deleteBranch() throws SQLException {
        Connection conn = null;
        String s;
        LibraryBranch branch = new LibraryBranch();
        try {
            conn = util.getConnection();
            LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);

            System.out.println("Select Branch you wish to update: ");
            List<LibraryBranch> branches = lbdao.readAllBranches();
            int branchIndex = 1;
            for (LibraryBranch b : branches) {
                System.out.println(branchIndex + ") " + b.getName() + ", " + b.getAddress());
                branchIndex++;
            }
            System.out.println(branchIndex + ") Cancel Transaction");
            int branchChoice = InputHandler.getIntInput(1, branchIndex);
            if (branchChoice == branchIndex) {
                return "Transaction Cancelled";
            }

            branch.setId(branches.get(--branchChoice).getId());
            lbdao.delete(branch);

            conn.commit();
            s = "Successfully deleted Branch";
        } catch (Exception e) {
            conn.rollback();
            s = "Problem has occured, Branch not deleted";
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return s;
    }

    public String readAllBranches() throws SQLException {
        Connection conn = null;
        String s;
        try {
            conn = util.getConnection();
            LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);

            System.out.println("List of branches: ");
            List<LibraryBranch> branches = lbdao.readAllBranches();
            int branchIndex = 1;
            for (LibraryBranch b : branches) {
                System.out.println(branchIndex + ") " + b.getName() + ", " + b.getAddress());
                branchIndex++;
            }

            conn.commit();
            s = "Successfully displayed branches";
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
