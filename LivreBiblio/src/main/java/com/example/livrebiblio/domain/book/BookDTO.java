package com.example.livrebiblio.domain.book;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookDTO {
    private String isbn;
    private String titre;
    private String auteur;
    private LocalDate datePublication;
    private String synopsis;

    public BookDTO(Book book) {
        this.isbn = book.getIsbn();
        this.titre = book.getTitre();
        if (book.getAuthor() != null) {
            this.auteur = book.getAuthor().toString();
        } else {
            this.auteur = null;
        }
        this.datePublication = book.getDatePublication();
        this.synopsis = book.getSynopsis();
    }
}


