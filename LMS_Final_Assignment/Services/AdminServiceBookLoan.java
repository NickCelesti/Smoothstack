package LMS_Final_Assignment.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import LMS_Final_Assignment.InputHandler;
import LMS_Final_Assignment.DAO.BookLoanDAO;
import LMS_Final_Assignment.Domain.BookLoan;

public class AdminServiceBookLoan {

    Util util = new Util();

    public String overrideBookLoan() throws SQLException {
        Connection conn = null;
        String s;
        BookLoan loan = new BookLoan();
        try {
            conn = util.getConnection();
            BookLoanDAO bdao = new BookLoanDAO(conn);

            System.out.println("Select Book Loan: ");
            List<BookLoan> loans = bdao.readAllLoans();
            int loanIndex = 1;
            for (BookLoan bl : loans) {
                System.out.println(loanIndex + ") [CardNo: " + bl.getCardNo() + "] [BookId: " + bl.getBookId()
                        + "] [BranchId:" + bl.getBranchId() + "] [Checkout Date: " + bl.getDateOut() + "] [Due Date: "
                        + bl.getDueDate() + "]");
                loanIndex++;
            }
            System.out.println(loanIndex + ") Cancel Transaction");
            int loanChoice = InputHandler.getIntInput(1, loanIndex);
            if (loanChoice == loanIndex) {
                return "Transaction cancelled";
            }

            LocalDateTime dt;
            System.out.println("Enter new Due Date ('quit' to cancel, 'next' for 7 days from now): ");
            // TODO: add dateInput
            String dueDate = InputHandler.getStringInput();
            if (dueDate.equals("quit")) {
                return "Transaction cancelled";
            } else if (dueDate.equals("next")) {
                dt = LocalDateTime.now().plusWeeks(1);
            } else {
                dt = LocalDateTime.parse(dueDate);
            }

            loan = loans.get(--loanChoice);
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