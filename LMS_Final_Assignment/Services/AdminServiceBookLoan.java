package LMS_Final_Assignment.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import LMS_Final_Assignment.DAO.AuthorDAO;
import LMS_Final_Assignment.DAO.BookDAO;
import LMS_Final_Assignment.DAO.BookLoanDAO;
import LMS_Final_Assignment.DAO.BorrowerDAO;
import LMS_Final_Assignment.DAO.LibraryBranchDAO;
import LMS_Final_Assignment.Domain.Author;
import LMS_Final_Assignment.Domain.Book;
import LMS_Final_Assignment.Domain.BookLoan;
import LMS_Final_Assignment.Domain.Borrower;
import LMS_Final_Assignment.Domain.LibraryBranch;

public class AdminServiceBookLoan {

    Util util = new Util();

    public String overrideBookLoan() throws SQLException {
        Connection conn = null;
        String s;
        BookLoan loan = new BookLoan();
        try {
            conn = util.getConnection();
            BookLoanDAO bdao = new BookLoanDAO(conn);
            BookDAO bookDao = new BookDAO(conn);
            AuthorDAO authorDAO = new AuthorDAO(conn);
            LibraryBranchDAO branchDao = new LibraryBranchDAO(conn);
            BorrowerDAO borrowerDAO = new BorrowerDAO(conn);

            List<Borrower> borrList = borrowerDAO.readAllBorrowers();

            List<Integer> cardNos = borrList.stream().map(n -> (n.getCardNo())).collect(Collectors.toList());

            System.out.println("Enter card number: ");
            int cardNo = 0;
            while (true) {
                cardNo = InputHandler.getCardInput();
                if (cardNos.contains(cardNo)) {
                    System.out.println("Accepted");
                    break;
                } else {
                    System.out.println("Card number not in system, try again");
                }
            }

            System.out.println("List of Book Loans for [CardNo: " + cardNo + "]: ");
            List<BookLoan> loansCardNo = bdao.readLoansByCardNo(cardNo);
            int loanIndex = 1;
            for (BookLoan bl : loansCardNo) {
                Book book = bookDao.readBookById(bl.getBookId());
                Author author = authorDAO.readAuthorById(book.getAuthorId());
                LibraryBranch branch = branchDao.readBranchFromId(bl.getBranchId());
                System.out.println(loanIndex + ") [Book: " + book.getTitle() + " by " + author.getName()
                        + "]\t[Branch: " + branch.getName() + ", " + branch.getAddress() + "]\t[Checkout Date: "
                        + bl.getDateOut() + "]\t[Due Date: " + bl.getDueDate() + "]");
                loanIndex++;
            }
            System.out.println(loanIndex + ") Cancel Transaction");
            int loanChoice = InputHandler.getIntInput(1, loanIndex);
            if (loanChoice == loanIndex) {
                return "Transaction cancelled";
            }

            LocalDateTime dt;
            System.out.println("Enter new Due Date ('quit' to cancel, 'next' for 7 days from now): ");
            String dueDate = InputHandler.getStringInput();
            if (dueDate.equals("quit")) {
                return "Transaction cancelled";
            } else if (dueDate.equals("next")) {
                dt = LocalDateTime.now().plusWeeks(1);
            } else {
                dt = LocalDateTime.parse(dueDate);
            }

            loan = loansCardNo.get(--loanChoice);
            loan.setDueDate(Timestamp.valueOf(dt));
            bdao.update(loan);
            conn.commit();
            s = "Successfully changed date to " + loan.getDueDate();
        } catch (Exception e) {
            conn.rollback();
            s = "Loan not changed";
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return s;
    }
}