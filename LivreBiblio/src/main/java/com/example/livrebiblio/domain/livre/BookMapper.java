package com.example.livrebiblio.domain.livre;

public class BookMapper {

    public static BookDTO convertToBookDTO(Book book) {
        return new BookDTO(
                book.getIsbn(),
                book.getTitre(),
                book.getAuteur(),
                book.getDatePublication(),
                book.getSynopsis()
        );
    }
}
