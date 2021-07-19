SELECT tbl_library_branch.branchName, COUNT(tbl_book_loans.bookId)
FROM tbl_library_branch, tbl_book_loans
WHERE tbl_book_loans.BranchId = tbl_library_branch.BranchId
GROUP BY tbl_library_branch.branchId;