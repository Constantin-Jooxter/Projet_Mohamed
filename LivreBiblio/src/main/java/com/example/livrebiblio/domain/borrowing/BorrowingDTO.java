package com.example.livrebiblio.domain.borrowing;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BorrowingDTO {
    private Long id;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowingDTO(Borrowing borrowing) {
        this.id = borrowing.getId();
        this.borrowDate = borrowing.getBorrowingdate();
        this.returnDate = borrowing.getReturndate();
    }
}
