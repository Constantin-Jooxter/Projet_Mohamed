package com.example.livrebiblio.domain.livre;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingFilters {
   //private Long id;
    private String isbn;
    private String titre;
    private String auteur;
    private String datePublication;
    private String synopsis;

   /* public BookingFilters(Book book) {
        this.isbn = book.getIsbn();
        this.titre = book.getTitre();
        this.auteur = book.getAuteur();
        this.datePublication = book.getDatePublication();
        this.synopsis = book.getSynopsis();
    }*/
}


