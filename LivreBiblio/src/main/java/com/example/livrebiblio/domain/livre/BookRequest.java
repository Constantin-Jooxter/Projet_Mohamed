package com.example.livrebiblio.domain.livre;


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
    @NotBlank
    private String auteur;
    @NotBlank
    private String datePublication;
    @NotBlank
    private String synopsis;
}
