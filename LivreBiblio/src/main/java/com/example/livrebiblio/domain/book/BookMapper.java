package com.example.livrebiblio.domain.book;

public class BookMapper {

    public static BookDTO convertToBookDTO(Book book) {
        String authorName = book.getAuthor() != null ? book.getAuthor().toString() : null;
        return new BookDTO(
                book.getIsbn(),
                book.getTitre(),
                authorName,
                book.getDatePublication(),
                book.getSynopsis()
        );
    }
}
