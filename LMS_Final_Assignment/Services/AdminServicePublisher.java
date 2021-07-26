package LMS_Final_Assignment.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import LMS_Final_Assignment.DAO.PublisherDAO;
import LMS_Final_Assignment.Domain.Publisher;

public class AdminServicePublisher {

    Util util = new Util();

    public String addPublisher() throws SQLException {
        Connection conn = null;
        String s;
        Publisher pub = new Publisher();
        try {
            conn = util.getConnection();
            PublisherDAO pdao = new PublisherDAO(conn);

            System.out.print("Enter Name ('quit' to cancel): ");
            String publisherName = InputHandler.getStringInput();
            if (publisherName.equals("quit")) {
                return "Transaction cancelled";
            }

            System.out.print("Enter Address ('quit' to cancel): ");
            String publisherAddress = InputHandler.getStringInput();
            if (publisherAddress.equals("quit")) {
                return "Transaction cancelled";
            }

            System.out.print("Enter Phone xxx-xxx-xxxx ('quit' to cancel): ");
            String publisherPhone = InputHandler.getStringInput();
            if (publisherName.equals("quit")) {
                return "Transaction cancelled";
            }

            pub.setName(publisherName);
            pub.setAddress(publisherAddress);
            pub.setPhone(publisherPhone);
            pdao.addToEnd(pub);
            conn.commit();
            s = "Successfully added " + pub.getName();
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

    public String updatePublisher() throws SQLException {
        Connection conn = null;
        String s;
        Publisher pub = new Publisher();
        try {
            conn = util.getConnection();
            PublisherDAO pdao = new PublisherDAO(conn);

            System.out.println("Select Publisher you wish to update: ");
            List<Publisher> pubs = pdao.readAllPublishers();
            int pubIndex = 1;
            for (Publisher p : pubs) {
                System.out.println(pubIndex + ") " + p.getName() + ", " + p.getAddress());
                pubIndex++;
            }
            System.out.println(pubIndex + ") Cancel Transaction");
            int pubChoice = InputHandler.getIntInput(1, pubIndex);
            if (pubChoice == pubIndex) {
                return "Transaction Cancelled";
            }

            System.out.print("Enter Name ('quit' to cancel): ");
            String publisherName = InputHandler.getStringInput();
            if (publisherName.equals("quit")) {
                return "Transaction cancelled";
            }

            System.out.print("Enter Address ('quit' to cancel): ");
            String publisherAddress = InputHandler.getStringInput();
            if (publisherAddress.equals("quit")) {
                return "Transaction cancelled";
            }

            System.out.print("Enter Phone xxx-xxx-xxxx ('quit' to cancel): ");
            String publisherPhone = InputHandler.getStringInput();
            if (publisherName.equals("quit")) {
                return "Transaction cancelled";
            }

            pub.setId(pubs.get(--pubChoice).getId());
            pub.setName(publisherName);
            pub.setAddress(publisherAddress);
            pub.setPhone(publisherPhone);
            pdao.update(pub);
            conn.commit();
            s = "Successfully updated " + pub.getName();
        } catch (Exception e) {
            conn.rollback();
            s = "Problem has occured, publisher not updated";
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return s;
    }

    public String deletePublisher() throws SQLException {
        Connection conn = null;
        String s;
        Publisher pub = new Publisher();
        try {
            conn = util.getConnection();
            PublisherDAO pdao = new PublisherDAO(conn);

            System.out.println("Select Publisher you wish to update: ");
            List<Publisher> pubs = pdao.readAllPublishers();
            int pubIndex = 1;
            for (Publisher p : pubs) {
                System.out.println(pubIndex + ") " + p.getName() + ", " + p.getAddress());
                pubIndex++;
            }
            System.out.println(pubIndex + ") Cancel Transaction");
            int pubChoice = InputHandler.getIntInput(1, pubIndex);
            if (pubChoice == pubIndex) {
                return "Transaction Cancelled";
            }

            pub.setId(pubs.get(--pubChoice).getId());
            pdao.delete(pub);

            conn.commit();
            s = "Successfully deleted publisher";
        } catch (Exception e) {
            conn.rollback();
            s = "Problem has occured, publisher not deleted";
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return s;
    }

    public String readAllPublishers() throws SQLException {
        Connection conn = null;
        String s;
        try {
            conn = util.getConnection();
            PublisherDAO pdao = new PublisherDAO(conn);

            System.out.println("List of publishers: ");
            List<Publisher> pubs = pdao.readAllPublishers();
            int pubIndex = 1;
            for (Publisher p : pubs) {
                System.out.println(pubIndex + ") " + p.getName() + ", " + p.getAddress());
                pubIndex++;
            }

            conn.commit();
            s = "Successfully displayed publishers";
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
