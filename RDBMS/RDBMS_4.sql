SELECT tbl_book.title, tbl_borrower.name, tbl_borrower.address
FROM tbl_book, tbl_borrower, tbl_book_loans, tbl_library_branch
WHERE tbl_library_branch.BranchName='Sharpstown' AND 
tbl_library_branch.BranchId = tbl_book_loans.BranchId AND
tbl_book_loans.DueDate='2021-7-18' AND 
tbl_book_loans.CardNo = tbl_borrower.CardNo AND 
tbl_book_loans.BookId = tbl_book.BookId