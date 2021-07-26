package LMS_Final_Assignment.Domain;

import java.sql.Timestamp;

public class BookLoan extends BaseDomain<BookLoan> {

    private Integer bookId;
    private Integer branchId;
    private Integer cardNo;
    private Timestamp dateOut;
    private Timestamp dueDate;

    public BookLoan() {
        bookId = null;
        branchId = null;
        cardNo = null;
        dateOut = null;
        dueDate = null;
    }

    public Timestamp getDateOut() {
        return this.dateOut;
    }

    public void setDateOut(Timestamp dateOut) {
        this.dateOut = dateOut;
    }

    public Timestamp getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getBookId() {
        return this.bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getCardNo() {
        return this.cardNo;
    }

    public void setCardNo(Integer cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getBranchId() {
        return this.branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

}