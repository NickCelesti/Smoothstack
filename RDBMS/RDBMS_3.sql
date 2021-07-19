SELECT name
FROM tbl_borrower
WHERE NOT EXISTS 
	(SELECT *
	FROM tbl_book_loans
	WHERE tbl_borrower.CardNo = tbl_book_loans.CardNo );