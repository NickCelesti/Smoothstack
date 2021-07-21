SELECT title, noOfCopies
FROM (((tbl_book inner join tbl_author on authId = authorId) inner join 
tbl_book_copies on tbl_book.bookId = tbl_book_copies.bookId) inner join 
tbl_library_branch on tbl_book_copies.branchId = tbl_library_branch.branchId)
WHERE authorName='Stephen King' AND 
BranchName='Central'; 