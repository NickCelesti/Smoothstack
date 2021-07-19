SELECT title, noOfCopies
FROM   (((tbl_author NATURAL JOIN tbl_book) NATURAL JOIN tbl_book_copies) NATURAL JOIN tbl_library_branch)
WHERE authorName='Stephen King' AND 
BranchName='Central'; 