package com.example.livrebiblio.domain.book;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookRequest {
    @NotBlank
    private String isbn;
    @NotBlank
    private String titre;
    private Long auteur;
    private String datePublication;
    @NotBlank
    private String synopsis;
}
