SELECT noOfCopies
FROM tbl_book, tbl_book_copies, tbl_library_branch 
WHERE  tbl_book.BookId = tbl_book_copies.BookId AND     
tbl_book_copies.BranchId = tbl_library_branch.BranchId AND
title='The Lost Tribe' AND
branchName='Sharpstown'