SELECT B.Name, B.Address, COUNT(*)
FROM tbl_borrower B, tbl_book_loans L
WHERE B.CardNo = L.CardNo
GROUP BY B.Name
HAVING COUNT(*) > 5;