package com.example.livrebiblio.domain.borrowing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingRequest {
    private Long userId;
    private Long bookId;
    private LocalDate start_date;
    private LocalDate end_date;
}
