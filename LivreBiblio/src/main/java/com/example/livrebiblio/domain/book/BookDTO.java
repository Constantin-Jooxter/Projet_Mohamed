package com.example.livrebiblio.domain.book;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookDTO {
    private String isbn;
    private String title;
    private String author;
    private LocalDate datePublication;
    private String synopsis;
    private String type;

    public BookDTO(Book book) {
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        if (book.getAuthor() != null) {
            this.author = book.getAuthor().toString();
        } else {
            this.author = null;
        }
        this.datePublication = book.getDatePublication();
        this.synopsis = book.getSynopsis();
        this.type = book.getType();
    }
}


