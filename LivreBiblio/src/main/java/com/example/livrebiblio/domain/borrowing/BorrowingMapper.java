package com.example.livrebiblio.domain.borrowing;

public class BorrowingMapper {
    public static BorrowingDTO convertToDTO(Borrowing borrowing) {
        return new BorrowingDTO(
                borrowing.getId(),
                borrowing.getBorrowingdate(),
                borrowing.getReturndate()
        );
    }
}
