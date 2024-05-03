package com.example.livrebiblio.domain.review;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewFilters {
    @Parameter(name = "Rating", description = "Search for Rating")
    private Integer rating;
    @Parameter(name = "bookTitle", description = "Search by Book Title")
    private String bookTitle;
    @Parameter(name = "userName", description = "Search by User Name")
    private String userName;
}
