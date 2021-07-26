package LMS_Final_Assignment.Domain;

public class BookCopy extends BaseDomain<BookCopy> {

    private static final long serialVersionUID = -7007087596339055539L;

    private Integer noOfCopies;
    private Integer bookId;
    private Integer branchId;

    public BookCopy() {
        this.noOfCopies = null;
        this.bookId = null;
        this.branchId = null;
    }

    public BookCopy(int noOfCopies, Integer book, Integer branchId) {
        this.noOfCopies = noOfCopies;
        this.bookId = book;
        this.branchId = branchId;
    }

    public int getNoOfCopies() {
        return noOfCopies;
    }

    public Integer getBookId() {
        return bookId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

}