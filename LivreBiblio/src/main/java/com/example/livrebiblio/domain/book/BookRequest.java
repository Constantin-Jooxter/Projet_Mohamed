package com.example.livrebiblio.domain.book;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookRequest {
    @NotBlank
    private String isbn;
    @NotBlank
    private String title;
    private Long auteur;
    private LocalDate datePublication;
    @NotBlank
    private String synopsis;
}
