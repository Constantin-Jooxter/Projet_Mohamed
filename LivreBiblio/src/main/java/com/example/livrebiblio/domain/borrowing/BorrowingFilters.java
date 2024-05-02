package com.example.livrebiblio.domain.borrowing;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BorrowingFilters {
    @Parameter(name = "start_date", description = "Search by Borrow Date")
    private LocalDate start_date;
    @Parameter(name = "end_date", description = "Search by Return Date")
    private LocalDate end_date;
    @Parameter(name = "beforeDate", description = "Search for borrowings before this date")
    private LocalDate beforeDate;
    @Parameter(name = "afterDate", description = "Search for borrowings after this date")
    private LocalDate afterDate;
    @Parameter(name = "bookTitle", description = "Search by Book Title")
    private String bookTitle;
    @Parameter(name = "userName", description = "Search by User Name")
    private String userName;
}
