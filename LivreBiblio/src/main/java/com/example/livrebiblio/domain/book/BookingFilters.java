package com.example.livrebiblio.domain.book;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookingFilters {

    @Parameter(name = "isbn", description = "Search isbn")
    private String isbn;
    @Parameter(name = "title", description = "Search title")
    private String title;
    @Parameter(name = "author", description = "Search author")
    private String author;
    @Parameter(name = "datePublication", description = "Search Date", example = "yyyy-MM-dd")
    private LocalDate datePublication;
    @Parameter(name = "Synopsis", description = "Resume of Story")
    private String synopsis;
    @Parameter(name = "Type", description = "Give a Type for this book")
    private String type;
}


