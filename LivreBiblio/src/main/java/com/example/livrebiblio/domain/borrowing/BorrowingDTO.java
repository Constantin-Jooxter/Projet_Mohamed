package com.example.livrebiblio.domain.borrowing;

import com.example.livrebiblio.domain.book.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BorrowingDTO {
    private Long id;
    private LocalDate start_date;
    private LocalDate end_date;
    private BookDTO book;
    private String user;
    // private UserDTO user;
}
