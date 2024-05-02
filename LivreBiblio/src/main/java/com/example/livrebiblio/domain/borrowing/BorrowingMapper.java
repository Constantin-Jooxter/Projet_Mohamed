package com.example.livrebiblio.domain.borrowing;

import com.example.livrebiblio.domain.book.BookMapper;

public class BorrowingMapper {
    public static BorrowingDTO convertToDTO(Borrowing borrowing) {
        String userName = borrowing.getUser() != null ? borrowing.getUser().getName() : null;
        return new BorrowingDTO(
                borrowing.getId(),
                borrowing.getStart_date(),
                borrowing.getEnd_date(),
                BookMapper.convertToBookDTO(borrowing.getBook()),
                userName
                //UserMapper.convertToUserDTO(borrowing.getUser())
        );
    }
}
