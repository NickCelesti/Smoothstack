package Utopia.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Utopia.DAO.UserDao;
import Utopia.DAO.UserRoleDao;
import Utopia.Domain.User;
import Utopia.Domain.UserRole;

public class AdminServiceUser {

    ConnectionUtil util = new ConnectionUtil();

    public void addUser() throws SQLException {
        Connection conn = null;
        User user = new User();
        try {
            conn = util.getConnection();
            UserDao udao = new UserDao(conn);
            UserRoleDao urdao = new UserRoleDao(conn);

            List<UserRole> userRoles = urdao.readAllUserRoles();
            int index = 1;
            for (UserRole ur : userRoles) {
                System.out.println(index + ") Role name: " + ur.getName());
                index++;
            }
            System.out.println(index + ") Cancel");
            int urChoice = InputHandler.getIntInput(1, index);
            if (urChoice == index) {
                System.out.println("Transaction cancelled");
                return;
            }
            UserRole userRole = userRoles.get(--urChoice);

            System.out.println("Enter given name ('quit' to cancel): ");
            String givenName = InputHandler.getStringInput();
            if (givenName.compareToIgnoreCase("quit") == 0) {
                System.out.println("Transaction cancelled");
                return;
            }

            System.out.println("Enter family name ('quit' to cancel): ");
            String familyName = InputHandler.getStringInput();
            if (familyName.compareToIgnoreCase("quit") == 0) {
                System.out.println("Transaction cancelled");
                return;
            }
            System.out.println("Enter username ('quit' to cancel): ");
            String username = InputHandler.getStringInput();
            if (username.compareToIgnoreCase("quit") == 0) {
                System.out.println("Transaction cancelled");
                return;
            }

            System.out.println("Enter email ('quit' to cancel): ");
            String email = InputHandler.getStringInput();
            if (email.compareToIgnoreCase("quit") == 0) {
                System.out.println("Transaction cancelled");
                return;
            }

            System.out.println("Enter password ('quit' to cancel): ");
            String password = InputHandler.getStringInput();
            if (password.compareToIgnoreCase("quit") == 0) {
                System.out.println("Transaction cancelled");
                return;
            }

            System.out.println("Enter phone ('quit' to cancel): ");
            String phone = InputHandler.getStringInput();
            if (phone.compareToIgnoreCase("quit") == 0) {
                System.out.println("Transaction cancelled");
                return;
            }

            user.setRoleId(userRole.getId());
            user.setGivenName(givenName);
            user.setFamilyName(familyName);
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);
            udao.addToEnd(user);
            conn.commit();
            System.out.println("Successfully added user");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("User not added");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void updateUser() throws SQLException {
        Connection conn = null;
        User user = new User();
        try {
            conn = util.getConnection();
            UserDao udao = new UserDao(conn);
            UserRoleDao urdao = new UserRoleDao(conn);

            List<User> users = udao.readAllUsers();
            int index = 1;
            for (User u : users) {
                System.out.println(
                        index + ") Name: " + u.getGivenName() + " " + u.getFamilyName() + ", Phone: " + u.getPhone());
                index++;
            }
            System.out.println(index + ") Cancel");
            int userChoice = InputHandler.getIntInput(1, index);
            if (userChoice == index) {
                System.out.println("Transaction cancelled");
                return;
            }
            user = users.get(--userChoice);

            List<UserRole> userRoles = urdao.readAllUserRoles();
            index = 1;
            for (UserRole ur : userRoles) {
                System.out.println(index + ") Role name: " + ur.getName());
                index++;
            }
            System.out.println(index + ") No change");
            int urChoice = InputHandler.getIntInput(1, index);
            if (urChoice != index) {
                UserRole userRole = userRoles.get(--urChoice);
                user.setRoleId(userRole.getId());
            }

            System.out.println("Enter given name ('n/a' for no change): ");
            String givenName = InputHandler.getStringInput();
            if (givenName.compareToIgnoreCase("n/a") != 0) {
                user.setGivenName(givenName);
            }

            System.out.println("Enter family name ('n/a' for no change): ");
            String familyName = InputHandler.getStringInput();
            if (familyName.compareToIgnoreCase("n/a") != 0) {
                user.setFamilyName(familyName);
            }
            System.out.println("Enter username ('n/a' for no change): ");
            String username = InputHandler.getStringInput();
            if (username.compareToIgnoreCase("n/a") != 0) {
                user.setUsername(username);
            }

            System.out.println("Enter email ('n/a' for no change): ");
            String email = InputHandler.getStringInput();
            if (email.compareToIgnoreCase("n/a") != 0) {
                user.setEmail(email);
            }

            System.out.println("Enter password ('n/a' for no change): ");
            String password = InputHandler.getStringInput();
            if (password.compareToIgnoreCase("n/a") != 0) {
                user.setPassword(password);
            }

            System.out.println("Enter phone ('n/a' for no change): ");
            String phone = InputHandler.getStringInput();
            if (phone.compareToIgnoreCase("n/a") != 0) {
                user.setPhone(phone);
            }

            udao.update(user);
            conn.commit();
            System.out.println("Successfully updated user");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("User not updated");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void deleteUser() throws SQLException {
        Connection conn = null;
        User user = new User();
        try {
            conn = util.getConnection();
            UserDao udao = new UserDao(conn);

            List<User> users = udao.readAllUsers();
            int index = 1;
            for (User u : users) {
                System.out.println(
                        index + ") Name: " + u.getGivenName() + " " + u.getFamilyName() + ", Phone: " + u.getPhone());
                index++;
            }
            System.out.println(index + ") Cancel");
            int userChoice = InputHandler.getIntInput(1, index);
            if (userChoice == index) {
                System.out.println("Transaction cancelled");
                return;
            }
            user = users.get(--userChoice);

            udao.delete(user);
            conn.commit();
            System.out.println("Successfully deleted user");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("User not deleted");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void readAllUsers() throws SQLException {
        Connection conn = null;
        try {
            conn = util.getConnection();
            UserDao udao = new UserDao(conn);

            List<User> users = udao.readAllUsers();
            int index = 1;
            for (User u : users) {
                System.out.println(
                        index + ") Name: " + u.getGivenName() + " " + u.getFamilyName() + ", Phone: " + u.getPhone());
                index++;
            }

            conn.commit();
            System.out.println("Successfully read users");
        } catch (Exception e) {
            conn.rollback();
            System.out.println("Users not read");
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
